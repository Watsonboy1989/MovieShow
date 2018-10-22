package com.puppy.movieshow;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.puppy.movieshow.bean.MovieData;
import com.puppy.movieshow.util.GlideApp;

public class MovieDetailFragment extends Fragment{

    private ProgressBar progressBar;
    private TextView title, releaseDate, voteAvg, overview, isAdult, popularity, voteCount, originalLang, originalTitle;
    private ImageView poster;
    private Context context;
    private MovieData movieData;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        movieData = (MovieData) bundle.getSerializable("moviedata");
        context = getActivity();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movie_detail, container, false);
        title = rootView.findViewById(R.id.title);
        isAdult = rootView.findViewById(R.id.isAdult);
        releaseDate = rootView.findViewById(R.id.releaseDate);
        popularity = rootView.findViewById(R.id.popularity);
        voteAvg = rootView.findViewById(R.id.voteAvg);
        voteCount = rootView.findViewById(R.id.voteCount);
        originalLang = rootView.findViewById(R.id.originalLang);
        originalTitle = rootView.findViewById(R.id.originalTitle);
        overview = rootView.findViewById(R.id.overview);
        progressBar = rootView.findViewById(R.id.progress_bar);
        poster = rootView.findViewById(R.id.poster);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        updateMovieDetail(movieData);
    }

    public void updateMovieDetail(MovieData md) {
        progressBar.setVisibility(View.GONE);
        title.setText(md.getTitle());
        isAdult.setText(md.isAdult()? "Rated: R": "Rated: PG");
        popularity.setText("Popularity: " + md.getPopularity());
        releaseDate.setText("Release Date: " + md.getReleaseDate());
        voteAvg.setText("Vote Avg: " + md.getVoteAvg());
        voteCount.setText("Vote Count: " + md.getVoteCount());
        originalLang.setText("Original Language: " + md.getOriginalLang());
        originalTitle.setText("Original Title: " + md.getOriginalTitle());
        overview.setText(md.getOverview());
        GlideApp.with(context)
                .load(md.getPosterPath())
                .placeholder(R.mipmap.placeholder)
                .error(R.mipmap.placeholder)
                .into(poster);
    }

}
