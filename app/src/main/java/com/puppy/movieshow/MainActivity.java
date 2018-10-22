package com.puppy.movieshow;


import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.puppy.movieshow.bean.MovieData;
import com.puppy.movieshow.bean.MovieResponse;
import com.puppy.movieshow.util.Util;

public class MainActivity extends AppCompatActivity implements MovieListFragment.OnMovieListSelectedListener{
    Fragment fragment;
    int curPage = 1;
    String searchWord = "hot";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(Util.isNetworkOn(this)) {
            fragment = new MovieListFragment();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.replace(R.id.fragment_container, fragment);
            transaction.commit();
        }
        else{
            Toast.makeText(this,"No Internet Connection",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void showMovieDetail(MovieData s) {
        fragment = new MovieDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("moviedata",s);
        fragment.setArguments(bundle);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }

    @Override
    public void loadMore() {
        curPage ++;
        new LoadMoreMovieTask().execute(searchWord);
    }

    @Override
    public void onSearch(String s) {
        searchWord = s;
        new SearchMoviesTask().execute(searchWord);
    }

    @Override
    public void onMovieListFragmentStart() {
        new SearchMoviesTask().execute(searchWord);
    }


    private class SearchMoviesTask extends AsyncTask<String, Integer, MovieResponse> {
        @Override
        protected void onPreExecute() {
        }

        protected MovieResponse doInBackground(String... words) {
            String word = words[0];
            Log.d(Constants.TAG, "SearchMoviesTask doInBackground: " + words[0]);
            curPage = 1;
            MovieResponse movieResponse = Util.getMovie(word, curPage);
            return movieResponse;
        }

        protected void onProgressUpdate(Integer... progress) {
        }

        protected void onPostExecute(MovieResponse result) {
            if((fragment instanceof MovieListFragment)) {
                ((MovieListFragment)fragment).setMovieList(result);
            }
        }
    }

    private class LoadMoreMovieTask extends AsyncTask<String, Integer, MovieResponse> {

        @Override
        protected void onPreExecute() {
        }

        protected MovieResponse doInBackground(String... words) {
            String word = words[0];
            MovieResponse mr = Util.getMovie(word, curPage);
            Log.d(Constants.TAG, "LoadMoreMovieTask doInBackground: " + mr.toString());
            return mr;
        }

        protected void onProgressUpdate(Integer... progress) {
        }

        protected void onPostExecute(MovieResponse result) {
            if(fragment instanceof MovieListFragment) {
                ((MovieListFragment) fragment).updateMovieList(result);
            }
        }
    }

    @Override
    public void onBackPressed() {
        if(fragment instanceof MovieDetailFragment) {
            fragment = new MovieListFragment();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.replace(R.id.fragment_container, fragment);
            transaction.commit();
        }
        else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                if(fragment instanceof MovieDetailFragment) {
                    onBackPressed();
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
