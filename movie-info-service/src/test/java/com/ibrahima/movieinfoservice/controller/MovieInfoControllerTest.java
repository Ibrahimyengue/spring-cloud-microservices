package com.ibrahima.movieinfoservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibrahima.movieinfoservice.models.MovieInfo;
import com.ibrahima.movieinfoservice.service.MovieInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MovieInfoController.class)
public class MovieInfoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieInfoService movieInfoService;

    @Test
    public void testGetAllMoviesInfo() throws Exception {
        String url = "/movies";
        List<MovieInfo> movieInfos = Arrays.asList(new MovieInfo(1, "Movie One","Movie One Desc"),
                new MovieInfo(2, "Movie two","Movie two Desc"));

        String inputInJson = this.mapToJson(movieInfos);

        when(movieInfoService.getAllMoviesInfo()).thenReturn(movieInfos);

        RequestBuilder request = MockMvcRequestBuilders.get(url)
                .accept(MediaType.APPLICATION_JSON).content(inputInJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();
        assertThat(result.getResponse().getContentAsString()).isEqualTo(inputInJson);
    }

    @Test
    public void testGetMoviesInfoById() throws Exception {
        String url = "/movies/1";
        MovieInfo movieInfo = new MovieInfo(1, "Movie One","Movie One Desc");
        String inputInJson = this.mapToJson(movieInfo);

        when(movieInfoService.getMovieInfo(1)).thenReturn(java.util.Optional.of(movieInfo));
        RequestBuilder request = MockMvcRequestBuilders.get(url)
                .accept(MediaType.APPLICATION_JSON).content(inputInJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();
        assertThat(result.getResponse().getContentAsString()).isEqualTo(inputInJson);
    }

    @Test
    public void testUpdateMoviesInfo() throws Exception {

        String url = "/movies/1";
        MovieInfo movieInfo = new MovieInfo(1, "Movie One","Movie One Desc");
        String inputInJson = this.mapToJson(movieInfo);

        when(movieInfoService.updateMovieInfo(1,movieInfo)).thenReturn(movieInfo);
        RequestBuilder request = MockMvcRequestBuilders.put(url)
                .accept(MediaType.APPLICATION_JSON).content(inputInJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();
        assertThat(result.getResponse().getContentAsString()).isEqualTo(inputInJson);
    }

    @Test
    public void testCreateMovieInfo() throws Exception {

        String url = "/movies";
        MovieInfo movieInfo = new MovieInfo(1, "Movie One","Movie One Desc");
        String inputInJson = this.mapToJson(movieInfo);

        when(movieInfoService.createMovieInfo(movieInfo)).thenReturn(movieInfo);
        RequestBuilder request = MockMvcRequestBuilders.post(url)
                .accept(MediaType.APPLICATION_JSON).content(inputInJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andReturn();
        assertThat(result.getResponse().getContentAsString()).isEqualTo(inputInJson);
    }
    @Test
    public void testDeleteMovieInfo() throws Exception {

        String url = "/movies/1";

        RequestBuilder request = MockMvcRequestBuilders.delete(url)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

         mockMvc.perform(request).andExpect(status().isOk()).andReturn();
    }

    /**
     * Maps an Object into a JSON String. Uses a Jackson ObjectMapper.
     */
    private String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }
}
