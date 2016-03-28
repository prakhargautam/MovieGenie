package com.example.prakhargautam.moviegenie;

import java.util.ArrayList;

/**
 * Created by prakhargautam on 26/03/16.
 */
public class MovieList {
    int page;
    ArrayList<Movie> results;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public ArrayList<Movie> getResults() {
        return results;
    }

    public void setResults(ArrayList<Movie> results) {
        this.results = results;
    }
}
