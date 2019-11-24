package com.ibrahima.ratingsdataservice.model;

import lombok.*;

import java.util.Arrays;
import java.util.List;

@Data
public class UserRating {

    private String userId;
    private List<Rating> ratings;

    public void initData(String userId) {
        this.setUserId(userId);
        this.setRatings(Arrays.asList(
                new Rating("2", 3),
                new Rating("3", 4)
        ));
    }
}
