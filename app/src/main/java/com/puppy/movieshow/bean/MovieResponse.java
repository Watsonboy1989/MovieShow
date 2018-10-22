package com.puppy.movieshow.bean;

import java.io.Serializable;
import java.util.List;


/**
 *  response looks like below, abstract into {@link MovieResponse}
 * {
 * 	"page": 1,
 * 	"total_results": 155,
 * 	"total_pages": 8,
 * 	"results": [{
 * 		"vote_count": 0,
 * 		"id": 520928,
 * 		"video": false,
 * 		"vote_average": 0,
 * 		"title": "Elephants Can Play Football",
 * 		"popularity": 12.672,
 * 		"poster_path": "\/pSnfuhfE6hXO45tPLePbO6BYSkB.jpg",
 * 		"original_language": "ru",
 * 		"original_title": "Слоны могут играть в футбол",
 * 		"genre_ids": [],
 * 		"backdrop_path": null,
 * 		"adult": false,
 * 		"overview": "Dmitry is a strong willed, solid guy. He could easily quit the game...",
 * 		"release_date": "2018-10-18"
 *        }, {
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
 * 		"overview": "The Football Factory is more than just a study of the English obsession ...",
 * 		"release_date": "2004-05-13"
 *    }]
 * }
 */
public class MovieResponse implements Serializable {
    private int page;
    private int totalPage;
    private int totalResult;
    private int itemCount;
    private List<MovieData> dataList;

    public MovieResponse() {
    }

    public MovieResponse(int page, int totalPage, int totalResult, int itemCount, List<MovieData> dataList) {

        this.page = page;
        this.totalPage = totalPage;
        this.totalResult = totalResult;
        this.itemCount = itemCount;
        this.dataList = dataList;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalResult() {
        return totalResult;
    }

    public void setTotalResult(int totalResult) {
        this.totalResult = totalResult;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public List<MovieData> getDataList() {
        return dataList;
    }

    public void setDataList(List<MovieData> dataList) {
        this.dataList = dataList;
    }

    @Override
    public String toString() {
        return "MovieResponse{" +
                "page=" + page +
                ", totalPage=" + totalPage +
                ", totalResult=" + totalResult +
                ", itemCount=" + itemCount +
                ", dataList=" + dataList +
                '}';
    }
}
