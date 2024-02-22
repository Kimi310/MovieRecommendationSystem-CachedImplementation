package dk.easv.presentation.controller;

import dk.easv.entities.*;
import dk.easv.presentation.model.AppModel;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.*;

public class AppController implements Initializable {
    @FXML
    private VBox topRatedMovie1;
    @FXML
    private VBox topRatedMovie2;
    @FXML
    private VBox topRatedMovie3;
    @FXML
    private VBox topRatedMovie4;
    @FXML
    private ObservableList<Movie> lvTopForUser;
    @FXML
    private ObservableList<Movie> lvTopAvgNotSeen;
    @FXML
    private ObservableList<UserSimilarity> lvTopSimilarUsers;
    @FXML
    private ObservableList<TopMovie> lvTopFromSimilar;


    private AppModel model;
    private long timerStartMillis = 0;
    private String timerMsg = "";
    private BackgroundSize bgSize = new BackgroundSize(224,126,false,false,false,false);
    private Background deafultBg = new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY));
    private Background placeholderBg;

    private void startTimer(String message){
        timerStartMillis = System.currentTimeMillis();
        timerMsg = message;
    }

    private void stopTimer(){
        System.out.println(timerMsg + " took : " + (System.currentTimeMillis() - timerStartMillis) + "ms");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setModel(AppModel model) {
        this.model = model;
        lvTopForUser = model.getObsTopMovieSeen();
        lvTopAvgNotSeen=model.getObsTopMovieNotSeen();
        lvTopSimilarUsers=model.getObsSimilarUsers();
        lvTopFromSimilar=model.getObsTopMoviesSimilarUsers();
        setTopForUser();
        setTopNotSeen();
        startTimer("Load users");
        model.loadUsers();
        stopTimer();
    }

    private void setTopForUser(){
        for (Movie m: lvTopForUser) {
            m.setBg(new Background(new BackgroundImage(new Image("Example_FXML/"+m.getId()+".jpg"),null,null,null,bgSize)));
        }
        topRatedMovie1.setBackground(lvTopForUser.get(0).getBg());
        topRatedMovie2.setBackground(lvTopForUser.get(1).getBg());
        topRatedMovie3.setBackground(lvTopForUser.get(2).getBg());
        topRatedMovie4.setBackground(lvTopForUser.get(3).getBg());
    }

    private void setTopNotSeen(){
        for (Movie m:lvTopAvgNotSeen) {
            System.out.println(m.getId());
        }
    }

    public void changeBGDeafult(MouseEvent mouseEvent) {
        VBox box = (VBox) mouseEvent.getSource();
        placeholderBg = box.getBackground();
        box.setBackground(deafultBg);
        ObservableList<Node> labels = box.getChildren();
        for (Node n:labels) {
            n.setVisible(true);
        }
    }

    public void changeBGMovie(MouseEvent mouseEvent) {
        VBox box = (VBox) mouseEvent.getSource();
        box.setBackground(placeholderBg);
        ObservableList<Node> labels = box.getChildren();
        for (Node n:labels) {
            n.setVisible(false);
        }
    }
}
