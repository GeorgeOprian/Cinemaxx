package com.example.cinemaxx.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.cinemaxx.API.TMDBMAPIMovieInfoBuilder;
import com.example.cinemaxx.Domain.Response;
import com.example.cinemaxx.adapters.DisplayMoviesAdapter;
import com.example.cinemaxx.databinding.FragmentHomeBinding;

import retrofit2.Call;
import retrofit2.Callback;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    private DisplayMoviesAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPopularMovies();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        initAdapter();

        return root;
    }

    private void initAdapter() {
        adapter = new DisplayMoviesAdapter();
        binding.container.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.container.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void getPopularMovies() {
        Call<Response> call = TMDBMAPIMovieInfoBuilder.getInstance().getPopularMovies(TMDBMAPIMovieInfoBuilder.API_KEY, TMDBMAPIMovieInfoBuilder.LANGUAGE);

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {

                if (response.code() == 200) {
                    adapter.submitList(response.body().getResults());
                }

            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

            }
        });
    }
}