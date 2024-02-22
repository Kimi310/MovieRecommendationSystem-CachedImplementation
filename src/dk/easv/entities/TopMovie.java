package dk.easv.entities;

import javafx.scene.layout.Background;

import java.util.ArrayList;
import java.util.List;

public class TopMovie {
    private Movie movie;
    private List<Double> rawRatings;
    private Background bg;

    public TopMovie(Movie movie, Background bg) {
        this.movie = movie;
        this.rawRatings = new ArrayList<>();
        this.bg = bg;
    }

    public void setBg(Background bg) {
        this.bg = bg;
    }

    public Background getBg() {
        return bg;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public List<Double> getRawRatings() {
        return rawRatings;
    }

    public void setRawRatings(List<Double> rawRatings) {
        this.rawRatings = rawRatings;
    }

    public String getTitle(){
        return movie.getTitle();
    }

    public int getYear(){
        return movie.getYear();
    }

    public int getId(){return movie.getId();}

    public double getAverageRating(){
        double sum=0;
        for (double rating : rawRatings){
            sum+=rating;
        }
        return sum/rawRatings.size();
    }

    @Override
    public String toString() {
        return movie +
                ", rating=" + rawRatings;
    }
}
