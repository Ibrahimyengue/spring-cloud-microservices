package com.ibrahima.moviecatalogservice.models;

import lombok.*;

import java.util.List;

@Data
public class UserRating {

    private String userId;
    private List<Rating> ratings;

}
