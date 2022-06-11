package com.example.cinemaxx.Domain.API.genre;
import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class GetGenreResult implements Serializable
{

    @SerializedName("genres")
    @Expose
    private List<GenreAPI> genres = null;
    private final static long serialVersionUID = 778409132572967726L;

    public List<GenreAPI> getGenres() {
        return genres;
    }

    public void setGenres(List<GenreAPI> genres) {
        this.genres = genres;
    }

}
