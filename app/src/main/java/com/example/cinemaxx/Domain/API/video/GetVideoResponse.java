package com.example.cinemaxx.Domain.API.video;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class GetVideoResponse implements Serializable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("results")
    @Expose
    private List<VideoResult> results = null;
    private final static long serialVersionUID = 4456501136194220140L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<VideoResult> getResults() {
        return results;
    }

    public void setResults(List<VideoResult> results) {
        this.results = results;
    }

    public List<VideoResult> getYoutubeTrailers() {
        List<VideoResult> youtubeTrailers = results.stream()
                .filter(videoResult -> videoResult.getSite().toLowerCase().contains("youtube") && videoResult.getName().toLowerCase().contains("trailer"))
                .collect(Collectors.toList());
        List<VideoResult> officialTrailers = youtubeTrailers.stream().filter(videoResult -> videoResult.getName().toLowerCase().contains("official"))
                .collect(Collectors.toList());
        return !officialTrailers.isEmpty()? officialTrailers: youtubeTrailers;
    }
}
