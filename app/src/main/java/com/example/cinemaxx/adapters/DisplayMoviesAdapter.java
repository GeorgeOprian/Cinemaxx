package com.example.cinemaxx.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinemaxx.API.TMDBMAPIMovieInfoBuilder;
import com.example.cinemaxx.Domain.ui.MovieToDisplay;
import com.example.cinemaxx.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DisplayMoviesAdapter extends RecyclerView.Adapter<DisplayMoviesAdapter.MoviesViewHolder> {

    private List<MovieToDisplay> localDataSet;

    public void submitList(List<MovieToDisplay> movies){
        this.localDataSet = movies;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MoviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_card, parent, false);

        return new MoviesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesViewHolder holder, int position) {
        holder.bind(localDataSet.get(position));
    }

    @Override
    public int getItemCount() {
        if(localDataSet == null){
            return 0;
        }
        return localDataSet.size();
    }

    public class MoviesViewHolder extends RecyclerView.ViewHolder{

        private final ImageView poster;
        private final TextView title;
        private final TextView genres;
        private final TextView rating;

        public MoviesViewHolder(@NonNull View view) {
            super(view);
            poster = (ImageView) view.findViewById(R.id.poster);
            title = (TextView) view.findViewById(R.id.title);
            genres = (TextView) view.findViewById(R.id.genres_values);
            rating = (TextView) view.findViewById(R.id.rating_value);

        }

        public void bind(MovieToDisplay movieResult) {
            Picasso.get().load(TMDBMAPIMovieInfoBuilder.DOWNLOAD_IMAGE_URL + movieResult.getPosterPath()).into(poster);
            title.setText(movieResult.getTitle());
            genres.setText(movieResult.getGenresName().toString());
            rating.setText(movieResult.getVoteAverage().toString());

        }
    }
}
