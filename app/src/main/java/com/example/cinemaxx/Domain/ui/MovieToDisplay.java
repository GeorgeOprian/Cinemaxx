package com.example.cinemaxx.Domain.ui;

import java.util.ArrayList;
import java.util.List;

public class MovieToDisplay {

    private List<String> genresName = null;

    private String posterPath;

    private String title;

    private Double voteAverage;


    public List<String> getGenresName() {
        return genresName;
    }

    public void setGenresName(List<String> genresName) {
        this.genresName = genresName;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public void addMovieGenre(String genre) {
        if (genresName == null) {
            genresName = new ArrayList<>();
        }
        genresName.add(genre);
    }
}
