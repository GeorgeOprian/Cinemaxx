package com.example.cinemaxx.API;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TMDBMAPIMovieInfoBuilder {

    private static TMDBMAPIMovieInfoService apiBuilder;
    private final static String BASE_URL = "https://api.themoviedb.org/";
    public final static String API_KEY = "d93bc871dae06ca875f3350886d89042";
    public final static String LANGUAGE = "en-US";
    public final static String DOWNLOAD_IMAGE_URL = "https://image.tmdb.org//t/p/w185/";

    public static TMDBMAPIMovieInfoService getInstance(){
        if(apiBuilder == null){
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            apiBuilder = retrofit.create(TMDBMAPIMovieInfoService.class);
        }
        return apiBuilder;
    }

}
