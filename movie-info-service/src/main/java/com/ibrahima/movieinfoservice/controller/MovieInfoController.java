package com.ibrahima.movieinfoservice.controller;

import com.ibrahima.movieinfoservice.exception.MovieInfoNotFoundException;
import com.ibrahima.movieinfoservice.models.MovieInfo;
import com.ibrahima.movieinfoservice.service.MovieInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class MovieInfoController {

    @Autowired
    private MovieInfoService movieInfoService;

    @GetMapping("/movies/{movieId}")
    public ResponseEntity<MovieInfo> getMovieInfoById(@PathVariable("movieId") int movieId) throws MovieInfoNotFoundException {
        return ResponseEntity.ok(movieInfoService.getMovieInfo(movieId)
                .orElseThrow(() -> new MovieInfoNotFoundException("Movie with id " + movieId + " not found!")));
    }

    @GetMapping("/movies")
    public ResponseEntity<List<MovieInfo>> getAllMoviesInfo() {
        return new ResponseEntity<>(movieInfoService.getAllMoviesInfo(), HttpStatus.OK);
    }

    @PutMapping("/movies/{movieId}")
    public ResponseEntity<MovieInfo> updateMovie(@PathVariable(value = "movieId") int movieId,
                                                    @Valid @RequestBody MovieInfo movieInfo) throws MovieInfoNotFoundException {
        return ResponseEntity.ok(movieInfoService.updateMovieInfo(movieId, movieInfo));
    }

    @PostMapping("/movies")
    public ResponseEntity<MovieInfo> createMovieInfo(@RequestBody MovieInfo movieInfo) {
        return new ResponseEntity<>(movieInfoService.createMovieInfo(movieInfo), HttpStatus.CREATED);
    }

    @DeleteMapping("/movies/{movieId}")
    public ResponseEntity<MovieInfo> deleteMovieInfo(@PathVariable(value = "movieId") int movieId) throws MovieInfoNotFoundException {
        movieInfoService.deleteMovieInfo(movieId);
        return new ResponseEntity<>( HttpStatus.OK);
    }
}
