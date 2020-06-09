package noPlayer.api;

import java.io.Serializable;

public class Album implements Serializable {
    int id;
    Artist artist;
    Genre genre;
    int Date;
    String name;
    String pictureUrl;

    public Album(int id, Artist artist, Genre genre, int date, String name, String pictureUrl) {
        this.id = id;
        this.artist = artist;
        this.genre = genre;
        Date = date;
        this.name = name;
        this.pictureUrl = pictureUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public int getDate() {
        return Date;
    }

    public void setDate(int date) {
        Date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }
}