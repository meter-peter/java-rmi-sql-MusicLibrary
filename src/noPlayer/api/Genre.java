package noPlayer.api;

import java.io.Serializable;

public class Genre implements Serializable {
    int genreid;
    String Genre;

    public Genre(int genreid, String genre) {
        this.genreid = genreid;
        Genre = genre;
    }

    public int getGenreid() {
        return genreid;
    }

    public void setGenreid(int genreid) {
        this.genreid = genreid;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }
}
