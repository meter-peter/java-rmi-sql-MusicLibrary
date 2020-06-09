package noPlayer.client;

import noPlayer.api.*;

import java.net.Inet4Address;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;
import java.util.List;

public class RMI {

    ClientInterface stub;

    public RMI() {
        initRMI();
    }
    public void initRMI(){
        try {
            Registry registry = LocateRegistry.getRegistry(5555);
            System.setProperty("java.rmi.server.hostname", Inet4Address.getLocalHost().getHostAddress());
            stub = (ClientInterface) Naming.lookup("rmi://localhost:5555/laMusica");

        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }

    }

    public List<Artist> getArtists() throws Exception {
        return stub.getArtists();

    }

    public List<Album> getAristsAlbums(Artist artist) throws Exception {
        return stub.getArtistAlbums(artist);
    }

    public List<Track> getAlbumTracks(Album album) throws Exception {
        return stub.getAlbumTracks(album);
    }

    public void addArtist(String name , String url) throws Exception {
        stub.addArtist(name, url);
    }

    public List<Genre> getGenres() throws Exception {
        return stub.getGenres();
    }

    public void addAlbum(String name , String url , Artist artist ,int date , Genre genre) throws Exception {
        stub.addAlbum(name, url, artist, date, genre);
    }

    public void addAlbumTracks(Album album , List<Track> tracks) throws Exception {
        for(Track track :tracks){
            stub.addTrack(album,track.getTime(),track.getName(),track.getNumber());
        }

    }

    public void modifyAlbum(int albumid,int artistid ,int genreid ,int releasedate, String Pictureurl,String name) throws Exception {
        stub.modifyAlbum(albumid, artistid, genreid, releasedate, Pictureurl,name);
    }

    public void addGenre(String name) throws Exception {
        stub.addGenre(name);

    }
    public Album getAlbumbyName(String name) throws Exception {
        return stub.getAlbumbyname(name);
    }
}
