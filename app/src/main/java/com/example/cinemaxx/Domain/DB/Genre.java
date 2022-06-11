package com.example.cinemaxx.Domain.DB;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Genre extends RealmObject {

    @PrimaryKey
    private Integer id;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
