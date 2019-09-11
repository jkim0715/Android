package com.example.p535;

public class Movie {
    String title;
    int rating;
    String actor;
    String img;

    public Movie() {
    }

    public Movie(String title, int rating, String actor, String img) {
        this.title = title;
        this.rating = rating;
        this.actor = actor;
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public int getRating() {
        return rating;
    }

    public String getActor() {
        return actor;
    }

    public String getImg() {
        return img;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", rating=" + rating +
                ", actor='" + actor + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}
