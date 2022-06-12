package com.example.cinemaxx.ui.topRated;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.cinemaxx.API.TMDBMAPIMovieInfoBuilder;
import com.example.cinemaxx.Domain.API.genre.GenreAPI;
import com.example.cinemaxx.Domain.API.genre.GetGenreResult;
import com.example.cinemaxx.Domain.API.movie.GetMoviesResponse;
import com.example.cinemaxx.Domain.API.movie.MovieResult;
import com.example.cinemaxx.Domain.DB.Genre;
import com.example.cinemaxx.Domain.ui.MovieToDisplay;
import com.example.cinemaxx.adapters.DisplayMoviesAdapter;
import com.example.cinemaxx.databinding.FragmentTopRatedBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;

public class TopRatedFragment extends Fragment {

    private FragmentTopRatedBinding binding;

    private DisplayMoviesAdapter adapter;
    private Realm realm;
    private Map<Integer, String> genres;
    private EditText searchInput;
    private Button searchButton;

    List<MovieToDisplay> moviesDisplayed;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initRealm();

        genres = loadAllGenresFromRealm();

        if (genres.isEmpty()) {
            getAllGenreFromApi();
        }

    }

    @Override
    public void onResume() {
        super.onResume();

        getPopularMovies();

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentTopRatedBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        initAdapter();

        handleSearchComponents();

        return root;
    }

    private void handleSearchComponents() {
        searchInput = binding.searchBar;
        searchButton = binding.searchButton;

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String genre = searchInput.getText().toString().trim();
                if (genre.equals("")) {
                    adapter.submitList(moviesDisplayed);
                } else {
                    adapter.submitList(filterMoviesByGenre(genre));
                }
            }
        });
    }

    private List<MovieToDisplay> filterMoviesByGenre(String genre) {
        List<MovieToDisplay> filteredMovies = new ArrayList<>();

        for(MovieToDisplay movieToDisplay: moviesDisplayed){
            for (String movieGenre: movieToDisplay.getGenresName()) {
                if (movieGenre.toLowerCase().startsWith(genre.toLowerCase())) {
                    filteredMovies.add(movieToDisplay);
                    break;
                }
            }
        }
        return filteredMovies;
    }


    private Map<Integer, String> loadAllGenresFromRealm() { // mapez id-urile de genuri la denumiri
        List<Genre> genresFromDb = realm.where(Genre.class).findAll().stream().collect(Collectors.toList());
        Map<Integer, String> genres = new HashMap<>();

        for (Genre genre: genresFromDb) {
            genres.put(genre.getId(), genre.getName());
        }

        return genres;
    }

    private void initRealm(){
        Realm.init(getActivity());
        realm = Realm.getDefaultInstance();
    }

    private void initAdapter() {
        adapter = new DisplayMoviesAdapter();
        binding.container.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.container.setAdapter(adapter);
    }


    private void getAllGenreFromApi() {
        Call<GetGenreResult> call = TMDBMAPIMovieInfoBuilder.getInstance().getAllGenre(TMDBMAPIMovieInfoBuilder.API_KEY, TMDBMAPIMovieInfoBuilder.LANGUAGE);

        call.enqueue(new Callback<GetGenreResult>() {
            @Override
            public void onResponse(Call<GetGenreResult> call, retrofit2.Response<GetGenreResult> response) {

                if (response.code() == 200) {
                    GetGenreResult body = response.body();
                    List<Genre> genresToSave = createGenresToSave(body.getGenres());
                    saveGenres(genresToSave);
                }

            }

            @Override
            public void onFailure(Call<GetGenreResult> call, Throwable t) {

            }
        });
    }

    private void saveGenres(List<Genre> genresToSave) {
        realm.beginTransaction();

        realm.copyToRealm(genresToSave);

        realm.commitTransaction();

    }

    private List<Genre> createGenresToSave(List<GenreAPI> genresFromApi) {

        return genresFromApi.stream().map(fromAPI -> createFromAPI(fromAPI)).collect(Collectors.toList());
    }

    private Genre createFromAPI(GenreAPI genreAPI) {
        Genre genre = new Genre();

        genre.setId(genreAPI.getId());
        genre.setName(genreAPI.getName());

        return genre;
    }

    private void getPopularMovies() {
        Call<GetMoviesResponse> call = TMDBMAPIMovieInfoBuilder.getInstance().getTopRatedMovies(TMDBMAPIMovieInfoBuilder.API_KEY, TMDBMAPIMovieInfoBuilder.LANGUAGE);

        call.enqueue(new Callback<GetMoviesResponse>() {
            @Override
            public void onResponse(Call<GetMoviesResponse> call, retrofit2.Response<GetMoviesResponse> response) {

                if (response.code() == 200) {

                    List<MovieResult> results = response.body().getResults();
                    moviesDisplayed = createMoviesToDisplay(results);
                    adapter.submitList(moviesDisplayed);
                }
            }

            @Override
            public void onFailure(Call<GetMoviesResponse> call, Throwable t) {

            }
        });
    }

    private List<MovieToDisplay> createMoviesToDisplay(List<MovieResult> movieResults) {
        List<MovieToDisplay> moviesToDisplay = new ArrayList<>();

        for (MovieResult movieFromApi: movieResults) {
            MovieToDisplay movieToDisplay = new MovieToDisplay();

            movieToDisplay.setTitle(movieFromApi.getTitle());
            movieToDisplay.setPosterPath(movieFromApi.getPosterPath());
            movieToDisplay.setVoteAverage(movieFromApi.getVoteAverage());

            addGenresToMovieToDisplay(movieToDisplay, movieFromApi.getGenreIds());

            moviesToDisplay.add(movieToDisplay);
        }
        return moviesToDisplay;
    }

    private void addGenresToMovieToDisplay(MovieToDisplay moviesToDisplay, List<Integer> genreIds) {
        for (Integer genreId: genreIds) {
            moviesToDisplay.addMovieGenre(genres.get(genreId));
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}