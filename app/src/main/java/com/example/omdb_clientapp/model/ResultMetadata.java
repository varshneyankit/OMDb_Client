package com.example.omdb_clientapp.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class ResultMetadata {
    @SerializedName("Title")
    private String title;
    @SerializedName("Year")
    private String year;
    @SerializedName("imdbRating")
    private String imdbRating;
    @SerializedName("Rated")
    private String rated;
    @SerializedName("Poster")
    private String poster;
    @SerializedName("Runtime")
    private String runtime;
    @SerializedName("Genre")
    private String genre;
    @SerializedName("Director")
    private String director;
    @SerializedName("Actors")
    private String cast;
    @SerializedName("Plot")
    private String plot;

    public ResultMetadata(String title, String year, String imdbRating, String rated, String poster, String runtime, String genre, String director, String cast, String plot) {
        this.title = title;
        this.year = year;
        this.imdbRating = imdbRating;
        this.rated = rated;
        this.poster = poster;
        this.runtime = runtime;
        this.genre = genre;
        this.director = director;
        this.cast = cast;
        this.plot = plot;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public String getRated() {
        return rated;
    }

    public String getPoster() {
        return poster;
    }

    public String getRuntime() {
        return runtime;
    }

    public String getGenre() {
        return genre;
    }

    public String getDirector() {
        return director;
    }

    public String getCast() {
        return cast;
    }

    public String getPlot() {
        return plot;
    }

    @NonNull
    @Override
    public String toString() {


        return "Title:\t" + this.title + "\n" +
                "Year:\t" + this.year + "\n" +
                "IMdbRating:\t" + this.imdbRating + "\n" +
                "Rated:\t" + this.rated + "\n" +
                "Poster:\t" + this.poster + "\n" +
                "Runtime:\t" + this.runtime + "\n" +
                "Genre:\t" + this.genre + "\n" +
                "Director:\t" + this.director + "\n" +
                "Cast:\t" + this.cast + "\n" +
                "Plot:\t" + this.plot;
    }
}
