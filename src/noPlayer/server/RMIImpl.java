package noPlayer.server;

import noPlayer.api.*;

import java.sql.SQLException;
import java.util.List;

public class RMIImpl implements ClientInterface {
    ServerController serverController;

    public RMIImpl(ServerController serverController){
        this.serverController=serverController;


    }

    public List<Genre> getGenres() throws SQLException {
        return this.serverController.getSqlDriver().getGenres();
    }

    public void addArtist(String name , String url) {
        this.serverController.getSqlDriver().addArtistQuery(name ,url);

    }

    public List<Artist> getArtists() throws SQLException {
        return serverController.getSqlDriver().getArtistsQuery();
    }

    public void addAlbum(String name, String url, Artist artist, int date, Genre genre) {

    }

    public void addTrack(Album album, int length, String name, int pos) {

    }

    public void addGenre(String name) throws SQLException {
        serverController.getSqlDriver().addGenre(name);

    }
    public List<Album> getArtistAlbums(Artist artist) throws SQLException {
        return serverController.getSqlDriver().getArtistAlbums(artist);

    }

    public Album getAlbumbyname(String name) throws SQLException {
        return serverController.getSqlDriver().getAlbumbyName(name);
    }


    public List<Album> getAllAlbums() {
        return null;
    }

    public List<Track> getAlbumTracks(Album album) throws SQLException {
        return serverController.getSqlDriver().getAlbumTracks(album);
    }

}
