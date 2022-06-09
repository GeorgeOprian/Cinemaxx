package com.example.cinemaxx.API;

import com.example.cinemaxx.Domain.Response;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TMDBMAPIMovieInfoService {

    @GET("/3/movie/popular")
    Call<Response> getPopularMovies(
            @Query("api_key") String apiKey,
            @Query("language") String language
    );


}