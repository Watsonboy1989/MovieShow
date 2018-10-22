package com.puppy.movieshow.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.puppy.movieshow.bean.MovieData;
import com.puppy.movieshow.bean.MovieResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Util {
    public static final String TAG = "MovieShow";
    public static final String BASE_URL = "https://api.themoviedb.org/3/search/movie?";
    public static final String POSTER_BASE_URL = "https://image.tmdb.org/t/p/w500";
    public static final String API_KEY= "876db37c9eb2be92a162285d2d985373";

    public static MovieResponse getMovie(String searhQuery,int pageNum){
        try {
            MovieResponse movieResponse = new MovieResponse();
            List<MovieData> movieList = new ArrayList<>();
            OkHttpClient client = new OkHttpClient();
            HttpUrl.Builder urlBuilder = HttpUrl.parse(BASE_URL).newBuilder();
            urlBuilder.addQueryParameter("query", searhQuery);
            urlBuilder.addQueryParameter("page", String.valueOf(pageNum));
            urlBuilder.addQueryParameter("api_key", API_KEY);
            String url = urlBuilder.build().toString();
            Log.d(TAG,url);
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            Response response = client.newCall(request).execute();
            String data = response.body().string();
            JSONObject jobj = new JSONObject(data);
            int page = jobj.getInt("page");
            int totalResult = jobj.getInt("total_results");
            int totalPage = jobj.getInt("total_pages");
            JSONArray jsonArray = jobj.getJSONArray("results");
            for(int i=0; i<jsonArray.length(); i++){
                JSONObject item = jsonArray.getJSONObject(i);
                int id = item.getInt("id");
                int voteCount = item.getInt("vote_count");
                double voteAvg = item.getDouble("vote_average");
                String title = item.getString("title");
                double popularity = item.getDouble("popularity");
                String posterPath = POSTER_BASE_URL + item.getString("poster_path");
                String originalLang = item.getString("original_language");
                String originalTitle = item.getString("original_title");
                JSONArray genreArray = item.getJSONArray("genre_ids");
                int[] genreIds = new int[genreArray.length()];
                for(int j=0; j<genreArray.length();j++){
                    genreIds[j] = genreArray.optInt(j);
                }
                boolean isAdult = item.getBoolean("adult");
                String overview = item.getString("overview");
                String releaseDate = item.getString("release_date");
                MovieData movie = new MovieData(id,voteCount,voteAvg,title,popularity,posterPath,
                        originalLang,originalTitle,genreIds,isAdult,overview,releaseDate);
                movieList.add(movie);
//                Log.d(TAG,movie.toString());
            }
            movieResponse.setPage(page);
            movieResponse.setTotalPage(totalPage);
            movieResponse.setTotalResult(totalResult);
            movieResponse.setDataList(movieList);
            movieResponse.setItemCount(movieList.size());
//            Log.d(TAG,movieResponse.toString());
            return movieResponse;
        }
        catch (IOException |JSONException e){
            return null;
        }
    }

    public static boolean isNetworkOn(Context context){
        ConnectivityManager cm =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }
}
