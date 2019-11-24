package com.ibrahima.moviecatalogservice.service;

import com.ibrahima.moviecatalogservice.models.Movie;
import com.ibrahima.moviecatalogservice.models.CatalogItem;
import com.ibrahima.moviecatalogservice.models.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieInfo {

    @Autowired
    private RestTemplate restTemplate;

   // @HystrixCommand(fallbackMethod = "getFalebackCatalogItem")
    public CatalogItem getCatalogItem(Rating rating) {
        Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
        return new CatalogItem(movie.getName(), movie.getDescription(), rating.getRating());

    }

    public CatalogItem getFalebackCatalogItem(Rating rating) {
        return new CatalogItem("Movie not found!", "", rating.getRating());
    }
}
