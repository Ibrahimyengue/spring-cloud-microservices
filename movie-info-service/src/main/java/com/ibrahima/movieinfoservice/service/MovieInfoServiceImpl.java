package com.ibrahima.movieinfoservice.service;

import com.ibrahima.movieinfoservice.exception.MovieInfoNotFoundException;
import com.ibrahima.movieinfoservice.models.MovieInfo;
import com.ibrahima.movieinfoservice.repository.MovieInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class MovieInfoServiceImpl implements MovieInfoService {

    @Value("${api.key}")
    private String apiKey;

    @Autowired
    private MovieInfoRepository movieInfoRepository;

    @Override
    public Optional<MovieInfo> getMovieInfo(int movieId) {
        return movieInfoRepository.findById(movieId);
    }

    @Override
    public List<MovieInfo> getAllMoviesInfo() {
        return movieInfoRepository.findAll();
    }
    @Override
    public MovieInfo updateMovieInfo(int movieId, MovieInfo movieInfo) throws MovieInfoNotFoundException {
        MovieInfo newMovieInfo = movieInfoRepository.findById(movieId)
                .orElseThrow(() -> new MovieInfoNotFoundException("Movie info not found for this id :: " + movieId));
        newMovieInfo.setName(movieInfo.getName());
        newMovieInfo.setDescription(movieInfo.getDescription());
        final MovieInfo updatedMovieInfo = movieInfoRepository.save(newMovieInfo);
        return updatedMovieInfo;
    }
    @Override
    public MovieInfo createMovieInfo(MovieInfo movieInfo) {

        return movieInfoRepository.save(movieInfo);
    }
    @Override
    public Map<String, Boolean> deleteMovieInfo(int movieId) throws MovieInfoNotFoundException {
        MovieInfo movieInfo = movieInfoRepository.findById(movieId)
                .orElseThrow(() -> new MovieInfoNotFoundException("Movie Info not found for this id :: " + movieId));

        movieInfoRepository.delete(movieInfo);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
