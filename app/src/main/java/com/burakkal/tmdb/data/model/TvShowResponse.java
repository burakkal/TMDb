package com.burakkal.tmdb.data.model;

import java.util.List;

/**
 * Created by Burak on 4.10.2017.
 * burakkal54@gmail.com
 */

public class TvShowResponse {

    private int page;
    private int total_results;
    private int totalPages;
    private List<TvShow> results;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<TvShow> getResults() {
        return results;
    }

    public void setResults(List<TvShow> results) {
        this.results = results;
    }
}
