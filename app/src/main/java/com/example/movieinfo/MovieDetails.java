package com.example.movieinfo;

import java.util.Objects;

public class MovieDetails {

    public MovieDetails(String title, String year, String imdbID, String type, String poster) {
        this.Title = title;
        this.Year = year;
        this.imdbID = imdbID;
        this.Type = type;
        this.Poster = poster;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MovieDetails)) return false;
        MovieDetails movieDetails = (MovieDetails) o;
        return getPoster() == movieDetails.getPoster() && Objects.equals(getTitle(), movieDetails.getTitle()) && Objects.equals(getYear(), movieDetails.getYear()) && Objects.equals(getImdbID(), movieDetails.getImdbID()) && Objects.equals(getType(), movieDetails.getType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getYear(), getImdbID(), getType(), getPoster());
    }

    String Title;
    String Year;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getPoster() {
        return Poster;
    }

    public void setPoster(String poster) {
        Poster = poster;
    }

    String imdbID;
    String Type;
    String Poster;
}
