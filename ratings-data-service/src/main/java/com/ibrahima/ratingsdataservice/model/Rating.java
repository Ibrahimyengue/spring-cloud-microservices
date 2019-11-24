package com.ibrahima.ratingsdataservice.model;

import lombok.*;

@Data
public class Rating {

    private String movieId;
    private int rating;

    public Rating(String movieId, int rating) {
        this.movieId = movieId;
        this.rating = rating;
    }

}
