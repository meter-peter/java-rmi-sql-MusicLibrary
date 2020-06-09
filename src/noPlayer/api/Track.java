package noPlayer.api;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

public class Track implements Serializable {
   int albumid;
   int number;
   String name;
   int time;
   int artistid;


    public Track(int albumid, int number, String name, int time, int artistid) {
        this.albumid = albumid;
        this.number = number;
        this.name = name;
        this.time = time;
        this.artistid = artistid;
    }

    public int getAlbumid() {
        return albumid;
    }

    public void setAlbumid(int albumid) {
        this.albumid = albumid;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getArtistid() {
        return artistid;
    }

    public void setArtistid(int artistid) {
        this.artistid = artistid;
    }

    @Override
    public String toString() {
        return getNumber()+"-"+getName()+"-"+TimeUnit.SECONDS.toMinutes(getTime())+" mins";
    }
}
