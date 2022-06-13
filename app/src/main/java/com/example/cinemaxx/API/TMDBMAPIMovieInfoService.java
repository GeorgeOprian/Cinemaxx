package com.example.cinemaxx.API;

import com.example.cinemaxx.Domain.API.genre.GetGenreResult;
import com.example.cinemaxx.Domain.API.movie.GetMoviesResponse;
import com.example.cinemaxx.Domain.API.video.GetVideoResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TMDBMAPIMovieInfoService {

    @GET("/3/movie/popular")
    Call<GetMoviesResponse> getPopularMovies(
            @Query("api_key") String apiKey,
            @Query("language") String language
    );


    @GET("/3/genre/movie/list")
    Call<GetGenreResult> getAllGenre(
            @Query("api_key") String apiKey,
            @Query("language") String language
    );

    @GET("/3/movie/top_rated")
    Call<GetMoviesResponse> getTopRatedMovies(
            @Query("api_key") String apiKey,
            @Query("language") String language
    );

    @GET("/3/movie/{movieId}/videos")
    Call<GetVideoResponse> getMovieVideos(
            @Path("movieId") long movieId,
            @Query("api_key") String apiKey,
            @Query("language") String language
    );
}