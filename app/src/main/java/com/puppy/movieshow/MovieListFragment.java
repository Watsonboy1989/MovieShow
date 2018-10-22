package com.puppy.movieshow;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.puppy.movieshow.adapter.MovieAdapter;
import com.puppy.movieshow.bean.MovieData;
import com.puppy.movieshow.bean.MovieResponse;

import java.util.ArrayList;
import java.util.List;

public class MovieListFragment extends Fragment{

    Context context;
    OnMovieListSelectedListener onMovieListSelectedListener;
    ProgressBar progressBar;
    private static MovieAdapter adapter;
    RecyclerView recyclerView;
    TextView noResult;
    LinearLayoutManager linearLayoutManager;
    boolean isLoading = false;
    SearchView searchView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);

    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movie_list, container, false);
        context = getActivity();
        noResult = rootView.findViewById(R.id.no_result);
        noResult.setVisibility(View.INVISIBLE);
        progressBar = rootView.findViewById(R.id.progress_bar);
        recyclerView = rootView.findViewById(R.id.recycler_view);
        linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!recyclerView.canScrollVertically(1)) {
                    if(!isLoading) {
                        isLoading = true;
                        onMovieListSelectedListener.loadMore();
                    }
                }
            }
        });
        searchView = rootView.findViewById(R.id.search_view);
        searchView.setQueryHint("Find Movies");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Log.d(Constants.TAG, "onQueryTextSubmit: " + s);
                searchView.clearFocus();
                onMovieListSelectedListener.onSearch(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                Log.d(Constants.TAG, "onQueryTextChange: " + s);
                return false;
            }
        });
        return rootView;
    }

    public interface OnMovieListSelectedListener{
        void showMovieDetail(MovieData movieData);
        void loadMore();
        void onSearch(String s);
        void onMovieListFragmentStart();
    }

    @Override
    public void onAttach(Context context) {
        if (context instanceof OnMovieListSelectedListener) {
            onMovieListSelectedListener = (OnMovieListSelectedListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnMovieListListener");
        }
        super.onAttach(context);
    }

    @Override
    public void onStart() {
        super.onStart();
        onMovieListSelectedListener.onMovieListFragmentStart();
    }

    public void setMovieList(MovieResponse movieResponse) {
        progressBar.setVisibility(View.INVISIBLE);
        if(null == movieResponse){
            noResult.setVisibility(View.VISIBLE);
            return;
        }
        List<MovieData> movieDataList = movieResponse.getDataList();
        Log.d(Constants.TAG, "updating movie list " + movieDataList.size());
        if (null == movieDataList || movieDataList.size() == 0) {
            noResult.setVisibility(View.VISIBLE);
            return;
        } else {
            noResult.setVisibility(View.GONE);
        }
        adapter = new MovieAdapter(context, movieDataList, new MovieAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(MovieData item) {
                onMovieListSelectedListener.showMovieDetail(item);
            }
        });
        recyclerView.setAdapter(adapter);
    }

    public void updateMovieList(MovieResponse movieResponse){
        adapter.addToList(movieResponse.getDataList());
        isLoading = false;
    }

}
