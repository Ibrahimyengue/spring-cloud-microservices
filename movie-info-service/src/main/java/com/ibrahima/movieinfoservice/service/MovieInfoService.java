package com.ibrahima.movieinfoservice.service;

import com.ibrahima.movieinfoservice.exception.MovieInfoNotFoundException;
import com.ibrahima.movieinfoservice.models.MovieInfo;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface MovieInfoService {
    public Optional<MovieInfo> getMovieInfo(int movieId);
    public List<MovieInfo> getAllMoviesInfo();
    public MovieInfo updateMovieInfo(int movieId, MovieInfo movieInfo) throws MovieInfoNotFoundException;
    public MovieInfo createMovieInfo(MovieInfo movieInfo);
    public Map<String, Boolean> deleteMovieInfo(int movieId) throws MovieInfoNotFoundException;
}
