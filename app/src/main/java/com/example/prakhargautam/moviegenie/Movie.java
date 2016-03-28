package com.example.prakhargautam.moviegenie;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by prakhargautam on 26/03/16.
 */
public class Movie {
    int id;
    @SerializedName("original_title")
    String name;
    String original_name;
    String poster_path;
    String overview;
    String vote_average;

    ArrayList<Genre> genres;

    public String getVote_average() {
        return vote_average;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }

    public String getOriginal_name() {
        return original_name;
    }

    public void setOriginal_name(String original_name) {
        this.original_name = original_name;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String description) {
        this.overview = description;
    }

    public ArrayList<Genre> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<Genre> genres) {
        this.genres = genres;
    }
}
