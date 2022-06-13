package com.example.cinemaxx.Domain.API.video;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class VideoResult implements Serializable {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("key")
    @Expose
    private String key;

    @SerializedName("site")
    @Expose
    private String site;

    @SerializedName("id")
    @Expose
    private String id;

    private final static long serialVersionUID = -3368943408596296940L;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}