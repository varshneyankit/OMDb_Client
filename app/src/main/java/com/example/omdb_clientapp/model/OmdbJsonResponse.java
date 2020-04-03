package com.example.omdb_clientapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.LinkedList;
import java.util.List;

public class OmdbJsonResponse {

    @SerializedName("Search")
    private List<SearchResult> results;
    @SerializedName("totalResults")
    private int totalResults;
    @SerializedName("Response")
    private boolean response;

    public OmdbJsonResponse(List<SearchResult> results, int totalResults, boolean response) {
        this.results = results;
        this.totalResults = totalResults;
        this.response = response;
    }

    public List<SearchResult> getResults() {
        return results;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public boolean isResponse() {
        return response;
    }
}
