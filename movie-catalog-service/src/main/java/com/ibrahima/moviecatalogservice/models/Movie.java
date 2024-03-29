package com.ibrahima.moviecatalogservice.models;

import lombok.*;

@Data
public class Movie {
    private String movieId;
    private String name;
    private String description;

    public Movie() {

    }

    public Movie(String movieId, String name, String description) {
        this.movieId = movieId;
        this.name = name;
        this.description = description;
    }

}
