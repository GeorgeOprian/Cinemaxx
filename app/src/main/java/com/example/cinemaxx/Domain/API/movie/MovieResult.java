package com.example.cinemaxx.Domain.API.movie;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class MovieResult implements Serializable
{


    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("genre_ids")
    @Expose
    private List<Integer> genreIds = null;

    @SerializedName("poster_path")
    @Expose
    private String posterPath;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("vote_average")
    @Expose
    private Double voteAverage;

    private final static long serialVersionUID = -1217225042415917271L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<Integer> genreIds) {
        this.genreIds = genreIds;
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
}
