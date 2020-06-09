package noPlayer.client.editor;

import noPlayer.api.Album;
import noPlayer.api.Artist;
import noPlayer.api.Genre;
import noPlayer.api.Track;
import noPlayer.client.RMI;

import java.util.List;

public class EditorController {
    DataEditorGUI dataEditorGUI;
    RMI rmi;


    public EditorController(RMI rmi){
        dataEditorGUI = new DataEditorGUI(this);
        this.rmi = rmi;
    }

    public List<Artist> getArtists() throws Exception {
       return rmi.getArtists();
    }

    public void addArtist(String name , String url) throws Exception {
        rmi.addArtist(name, url);

    }

    public List<Genre> getGenres() throws Exception {
        return rmi.getGenres();

    }

    public void addAlbum(String name , String url , Artist artist ,int date , Genre genre) throws Exception {
        rmi.addAlbum(name, url, artist, date, genre);

    }

    public void addTracks(Album album , List<Track> tracks) throws Exception {
        rmi.addAlbumTracks(album,tracks);

    }

    public void modifyAlbum(int albumid,int artistid ,int genreid ,int releasedate, String Pictureurl, String name) throws Exception {
        rmi.modifyAlbum(albumid, artistid, genreid, releasedate, Pictureurl,name);
    }

    public List<Album> getAlbums(Artist artist) throws Exception {
        return rmi.getAristsAlbums(artist);
    }

    public List<Track> getAlbumTrakcs(Album album) throws Exception {
        return rmi.getAlbumTracks(album);
    }

    public Album getAlbumbyname(String name) throws Exception {
        return rmi.getAlbumbyName(name);
    }

    public void addGenre(String name) throws Exception {
        rmi.addGenre(name);

    }
}
