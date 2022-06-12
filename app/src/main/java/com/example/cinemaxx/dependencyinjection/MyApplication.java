package com.example.cinemaxx.dependencyinjection;

import android.app.Application;

import com.example.cinemaxx.API.TMDBMAPIMovieInfoBuilder;

public class MyApplication extends Application {

    private ApiComponent mApiComponent;

//    @Override
//    public void onCreate() {
//        super.onCreate();
//        // https://restcountries.eu/rest/v2/all -> It will list all the country details
//        mApiComponent = DaggerAppComponent.builder()
//                .appModule(new AppModule(this))
//                .apiModule(new ApiModule(TMDBMAPIMovieInfoBuilder.BASE_URL))
//                .build();
//    }
//
//    public ApiComponent getNetComponent() {
//        return mApiComponent;
//    }
}
