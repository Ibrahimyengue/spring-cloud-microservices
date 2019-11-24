package com.ibrahima.movieinfoservice.service;

import com.ibrahima.movieinfoservice.exception.MovieInfoNotFoundException;
import com.ibrahima.movieinfoservice.models.MovieInfo;
import com.ibrahima.movieinfoservice.repository.MovieInfoRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
@WebMvcTest(MovieInfoService.class)
public class MovieInfoServiceImplTest {

    @InjectMocks
    private MovieInfoServiceImpl movieInfoService;

    @MockBean
    private MovieInfoRepository movieInfoRepository;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllMoviesInfo()  {

        when(movieInfoRepository.findAll()).thenReturn(Arrays.asList(new MovieInfo(1, "Movie One","Movie One Desc"),
                                                                     new MovieInfo(2, "Movie two","Movie two Desc")));

        List<MovieInfo> movieInfos = movieInfoService.getAllMoviesInfo();

        assertEquals(1, movieInfos.get(0).getMovieId());
        assertEquals(2, movieInfos.get(1).getMovieId());
        assertEquals(2, movieInfos.size());
        verify(movieInfoRepository, times(1)).findAll();
    }

    @Test
    public void testGetMoviesInfoById(){
        when(movieInfoRepository.findById(1)).thenReturn(java.util.Optional.of(new MovieInfo(1, "Movie One", "Movie One Desc")));

        Optional<MovieInfo> movieInfo = movieInfoService.getMovieInfo(1);

        assertEquals(1, movieInfo.get().getMovieId());
        verify(movieInfoRepository, times(1)).findById(1);
    }

    @Test
    public void testUpdateMoviesInfo() throws MovieInfoNotFoundException {

        MovieInfo movieInfo = new MovieInfo(1, "Movie One","Movie One Desc");

        when(movieInfoRepository.findById(1)).thenReturn(Optional.of(movieInfo));
        movieInfo.setName("Movie One Updated");
        when(movieInfoRepository.save(movieInfo)).thenReturn(movieInfo);

        assertEquals("Movie One Updated", movieInfoService.updateMovieInfo(1, movieInfo).getName());
        verify(movieInfoRepository, times(1)).findById(1);
    }
//
    @Test
    public void testCreateMovieInfo() {
        MovieInfo movieInfo = new MovieInfo(1, "Movie One","Movie One Desc");

        when(movieInfoRepository.save(movieInfo)).thenReturn(movieInfo);

        assertEquals("Movie One", movieInfoService.createMovieInfo(movieInfo).getName());
        verify(movieInfoRepository, times(1)).save(movieInfo);
    }
    @Test
    public void testDeleteMovieInfo() throws MovieInfoNotFoundException {
        MovieInfo movieInfo = new MovieInfo(1, "Movie One","Movie One Desc");
        when(movieInfoRepository.findById(1)).thenReturn(Optional.of(movieInfo));
        movieInfoRepository.delete(movieInfo);

        verify(movieInfoRepository, times(1)).delete(movieInfo);
    }
}
