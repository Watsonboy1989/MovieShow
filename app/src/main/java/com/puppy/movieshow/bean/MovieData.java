package com.puppy.movieshow.bean;

import java.io.Serializable;
import java.util.Arrays;

/**
 * an entry of movie in json response looks like below, abstract this into {@link MovieData} class
 * {
 * 		"vote_count": 108,
 * 		"id": 10296,
 * 		"video": false,
 * 		"vote_average": 6.5,
 * 		"title": "The Football Factory",
 * 		"popularity": 5.391,
 * 		"poster_path": "\/ja76eLRw7XSc1VdlNXEb8ufTIoW.jpg",
 * 		"original_language": "en",
 * 		"original_title": "The Football Factory",
 * 		"genre_ids": [18],
 * 		"backdrop_path": "\/9S6gAxuvrXn1Zp8m4NDOInbz0MU.jpg",
 * 		"adult": false,
 * 		"overview": "The Football Factory is more than just a study of the English....",
 * 		"release_date": "2004-05-13"
 * }
 *
 */

public class MovieData implements Serializable {
    private int id;
    private int voteCount;
    private double voteAvg;
    private String title;
    private double popularity;
    private String posterPath;
    private String originalLang;
    private String originalTitle;
    private int[] genreIds;
    private boolean isAdult;
    private String overview;
    private String releaseDate;

    public MovieData(int id, int voteCount, double voteAvg, String title, double popularity,
                     String posterPath, String originalLang, String originalTitle,
                     int[] genreIds, boolean isAdult, String overView, String releaseDate) {
        this.id = id;
        this.voteCount = voteCount;
        this.voteAvg = voteAvg;
        this.title = title;
        this.popularity = popularity;
        this.posterPath = posterPath;
        this.originalLang = originalLang;
        this.originalTitle = originalTitle;
        this.genreIds = genreIds;
        this.isAdult = isAdult;
        this.overview = overView;
        this.releaseDate = releaseDate;
    }

    public MovieData(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public double getVoteAvg() {
        return voteAvg;
    }

    public void setVoteAvg(double voteAvg) {
        this.voteAvg = voteAvg;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getOriginalLang() {
        return originalLang;
    }

    public void setOriginalLang(String originalLang) {
        this.originalLang = originalLang;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public int[] getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(int[] genreIds) {
        this.genreIds = genreIds;
    }

    public boolean isAdult() {
        return isAdult;
    }

    public void setAdult(boolean adult) {
        isAdult = adult;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overView) {
        this.overview = overView;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return "MovieData{" +
                "id=" + id +
                ", voteCount=" + voteCount +
                ", voteAvg=" + voteAvg +
                ", title='" + title + '\'' +
                ", popularity=" + popularity +
                ", posterPath='" + posterPath + '\'' +
                ", originalLang='" + originalLang + '\'' +
                ", originalTitle='" + originalTitle + '\'' +
                ", genreIds=" + Arrays.toString(genreIds) +
                ", isAdult=" + isAdult +
                ", overview='" + overview + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                '}';
    }
}
