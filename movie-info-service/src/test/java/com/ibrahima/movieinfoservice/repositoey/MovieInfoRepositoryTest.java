package com.ibrahima.movieinfoservice.repositoey;

import com.ibrahima.movieinfoservice.models.MovieInfo;
import com.ibrahima.movieinfoservice.repository.MovieInfoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MovieInfoRepositoryTest {

    @Autowired
    private MovieInfoRepository movieInfoRepository;

    @Test
    public void tesGetAllMovies() {
        List<MovieInfo> movieInfos = movieInfoRepository.findAll();
//        verify(movieInfoRepository, atLeastOnce()).findAll();
//        verify(movieInfoRepository, times(1)).findAll();

        assertEquals(0,movieInfos.size());
    }
}
