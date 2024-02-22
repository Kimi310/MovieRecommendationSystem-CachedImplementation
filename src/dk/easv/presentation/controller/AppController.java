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
    private Label titleAlsoLiked1;
    @FXML
    private Label yearAlsoLiked1;
    @FXML
    private Label ratingAlsoLiked1;
    @FXML
    private Label titleAlsoLiked2;
    @FXML
    private Label yearAlsoLiked2;
    @FXML
    private Label ratingAlsoLiked2;
    @FXML
    private Label titleAlsoLiked3;
    @FXML
    private Label yearAlsoLiked3;
    @FXML
    private Label ratingAlsoLiked3;
    @FXML
    private Label titleAlsoLiked4;
    @FXML
    private Label yearAlsoLiked4;
    @FXML
    private Label ratingAlsoLiked4;
    @FXML
    private Label titleTopMoviesfy1;
    @FXML
    private Label yearTopMoviesfy1;
    @FXML
    private Label ratingTopMoviesfy1;
    @FXML
    private Label titleTopMoviesfy2;
    @FXML
    private Label yearTopMoviesfy2;
    @FXML
    private Label ratingTopMoviesfy2;
    @FXML
    private Label titleTopMoviesfy3;
    @FXML
    private Label yearTopMoviesfy3;
    @FXML
    private Label ratingTopMoviesfy3;
    @FXML
    private Label titleTopMoviesfy4;
    @FXML
    private Label yearTopMoviesfy4;
    @FXML
    private Label ratingTopMoviesfy4;
    @FXML
    private Label TitleTopRated1;
    @FXML
    private Label yearTopRated1;
    @FXML
    private Label ratingTopRated1;
    @FXML
    private Label TitleTopRated2;
    @FXML
    private Label yearTopRated2;
    @FXML
    private Label ratingTopRated2;
    @FXML
    private Label TitleTopRated3;
    @FXML
    private Label yearTopRated3;
    @FXML
    private Label ratingTopRated3;
    @FXML
    private Label TitleTopRated4;
    @FXML
    private Label yearTopRated4;
    @FXML
    private Label ratingTopRated4;
    @FXML
    private Label similarUser1;
    @FXML
    private Label similarUser2;
    @FXML
    private Label similarUser3;
    @FXML
    private Label similarUser4;
    @FXML
    private Label similarUser5;
    @FXML
    private Label similarUser6;
    @FXML
    private Label userLabel;
    @FXML
    private VBox similarUsers1;
    @FXML
    private VBox similarUsers2;
    @FXML
    private VBox similarUsers3;
    @FXML
    private VBox similarUsers4;
    @FXML
    private  VBox moviesForYou1;
    @FXML
    private  VBox moviesForYou2;
    @FXML
    private  VBox moviesForYou3;
    @FXML
    private  VBox moviesForYou4;
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
        setVboxes(lvTopForUser,topRatedMovie1,topRatedMovie2,topRatedMovie3,topRatedMovie4);
        setVboxes(lvTopAvgNotSeen,moviesForYou1,moviesForYou2,moviesForYou3,moviesForYou4);
        setVboxesTopMovie(lvTopFromSimilar,similarUsers1,similarUsers2,similarUsers3,similarUsers4);
        setSimilarUsers();
        startTimer("Load users");
        model.loadUsers();
        setLabels();
        stopTimer();
        userLabel.setText(model.getObsLoggedInUser().getName());
    }

    public void setSimilarUsers(){
        similarUser1.setText(lvTopSimilarUsers.get(0).getName());
        similarUser2.setText(lvTopSimilarUsers.get(1).getName());
        similarUser3.setText(lvTopSimilarUsers.get(2).getName());
        similarUser4.setText(lvTopSimilarUsers.get(3).getName());
        similarUser5.setText(lvTopSimilarUsers.get(4).getName());
        similarUser6.setText(lvTopSimilarUsers.get(5).getName());
    }

    private void setVboxes(ObservableList<Movie> movies,VBox box1,VBox box2,VBox box3, VBox box4){
        for (Movie m: movies) {
            m.setBg(new Background(new BackgroundImage(new Image("Example_FXML/"+m.getId()+".jpg"),null,null,null,bgSize)));
        }
        box1.setBackground(movies.get(0).getBg());
        box2.setBackground(movies.get(1).getBg());
        box3.setBackground(movies.get(2).getBg());
        box4.setBackground(movies.get(3).getBg());
    }

    private void setLabels(){
        //Top rated section
        TitleTopRated1.setText(lvTopForUser.get(0).getTitle());
        TitleTopRated2.setText(lvTopForUser.get(1).getTitle());
        TitleTopRated3.setText(lvTopForUser.get(2).getTitle());
        TitleTopRated4.setText(lvTopForUser.get(3).getTitle());
        yearTopRated1.setText(String.valueOf(lvTopForUser.get(0).getYear()));
        yearTopRated2.setText(String.valueOf(lvTopForUser.get(1).getYear()));
        yearTopRated3.setText(String.valueOf(lvTopForUser.get(2).getYear()));
        yearTopRated4.setText(String.valueOf(lvTopForUser.get(3).getYear()));
        ratingTopRated1.setText(String.valueOf(String.format("%.2f", lvTopForUser.get(0).getAverageRating())));
        ratingTopRated2.setText(String.valueOf(String.format("%.2f", lvTopForUser.get(1).getAverageRating())));
        ratingTopRated3.setText(String.valueOf(String.format("%.2f", lvTopForUser.get(2).getAverageRating())));
        ratingTopRated4.setText(String.valueOf(String.format("%.2f", lvTopForUser.get(3).getAverageRating())));

        //Top rated that you didnt see section
        titleTopMoviesfy1.setText(lvTopAvgNotSeen.get(0).getTitle());
        titleTopMoviesfy2.setText(lvTopAvgNotSeen.get(1).getTitle());
        titleTopMoviesfy3.setText(lvTopAvgNotSeen.get(2).getTitle());
        titleTopMoviesfy4.setText(lvTopAvgNotSeen.get(3).getTitle());
        yearTopMoviesfy1.setText(String.valueOf(lvTopAvgNotSeen.get(0).getYear()));
        yearTopMoviesfy2.setText(String.valueOf(lvTopAvgNotSeen.get(1).getYear()));
        yearTopMoviesfy3.setText(String.valueOf(lvTopAvgNotSeen.get(2).getYear()));
        yearTopMoviesfy4.setText(String.valueOf(lvTopAvgNotSeen.get(3).getYear()));
        ratingTopMoviesfy1.setText(String.valueOf(String.format("%.2f",lvTopAvgNotSeen.get(0).getAverageRating())));
        ratingTopMoviesfy2.setText(String.valueOf(String.format("%.2f",lvTopAvgNotSeen.get(1).getAverageRating())));
        ratingTopMoviesfy3.setText(String.valueOf(String.format("%.2f",lvTopAvgNotSeen.get(2).getAverageRating())));
        ratingTopMoviesfy4.setText(String.valueOf(String.format("%.2f",lvTopAvgNotSeen.get(3).getAverageRating())));

        //Movies that similar users liked
        titleAlsoLiked1.setText(lvTopFromSimilar.get(0).getTitle());
        titleAlsoLiked2.setText(lvTopFromSimilar.get(1).getTitle());
        titleAlsoLiked3.setText(lvTopFromSimilar.get(2).getTitle());
        titleAlsoLiked4.setText(lvTopFromSimilar.get(3).getTitle());
        yearAlsoLiked1.setText(String.valueOf(lvTopFromSimilar.get(0).getYear()));
        yearAlsoLiked2.setText(String.valueOf(lvTopFromSimilar.get(1).getYear()));
        yearAlsoLiked3.setText(String.valueOf(lvTopFromSimilar.get(2).getYear()));
        yearAlsoLiked4.setText(String.valueOf(lvTopFromSimilar.get(3).getYear()));
        ratingAlsoLiked1.setText(String.valueOf(String.format("%.2f",lvTopFromSimilar.get(0).getAverageRating())));
        ratingAlsoLiked2.setText(String.valueOf(String.format("%.2f",lvTopFromSimilar.get(1).getAverageRating())));
        ratingAlsoLiked3.setText(String.valueOf(String.format("%.2f",lvTopFromSimilar.get(2).getAverageRating())));
        ratingAlsoLiked4.setText(String.valueOf(String.format("%.2f",lvTopFromSimilar.get(3).getAverageRating())));


    }
    private void setVboxesTopMovie(ObservableList<TopMovie> movies,VBox box1,VBox box2,VBox box3, VBox box4){

        for (TopMovie m: movies) {
            m.setBg(new Background(new BackgroundImage(new Image("Example_FXML/"+m.getId()+".jpg"),null,null,null,bgSize)));
        }
        box1.setBackground(movies.get(0).getBg());
        box2.setBackground(movies.get(1).getBg());
        box3.setBackground(movies.get(2).getBg());
        box4.setBackground(movies.get(3).getBg());
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

    public void setUserPics(){

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
