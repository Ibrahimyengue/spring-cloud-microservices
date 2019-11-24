package com.ibrahima.movieinfoservice.repository;

import com.ibrahima.movieinfoservice.models.MovieInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieInfoRepository extends JpaRepository<MovieInfo, Integer> {
}
