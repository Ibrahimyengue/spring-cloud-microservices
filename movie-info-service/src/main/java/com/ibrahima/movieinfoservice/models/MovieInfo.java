package com.ibrahima.movieinfoservice.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@Entity
public class MovieInfo {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int movieId;
    @NotEmpty(message = "Please provide a name")
    private String name;
    @NotEmpty(message = "Please provide a description")
    private String description;

    public MovieInfo() {
    }
    public MovieInfo(int movieId, String name, String description) {
        this.movieId = movieId;
        this.name = name;
        this.description = description;
    }
}
