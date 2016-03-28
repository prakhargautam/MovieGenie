package com.example.prakhargautam.moviegenie;

import java.util.ArrayList;

/**
 * Created by prakhargautam on 27/03/16.
 */
public class TVList {
    ArrayList<TV> results;
    int page;

    public ArrayList<TV> getResults() {
        return results;
    }

    public void setResults(ArrayList<TV> results) {
        this.results = results;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
