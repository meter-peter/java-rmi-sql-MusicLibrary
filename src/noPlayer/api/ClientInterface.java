package noPlayer.api;

import java.rmi.Remote;
import java.sql.SQLException;
import java.util.List;

public interface ClientInterface extends Remote {
    public void addArtist(String name , String url) throws Exception;
    public List<Artist> getArtists() throws Exception;
    public void addAlbum(String name , String url , Artist artist , int date , Genre genre) throws Exception;
    public void addTrack(Album album , int length ,String name , int pos) throws Exception;
    public List<Genre> getGenres() throws Exception;
    public List<Album> getAllAlbums() throws Exception;
    public List<Album> getArtistAlbums(Artist artist) throws Exception;
    public List<Track> getAlbumTracks(Album album) throws Exception;
    public Album getAlbumbyname(String name) throws Exception;
    public void addGenre(String name) throws Exception;
    public void modifyAlbum(int albumid,int artistid ,int genreid ,int releasedate,String Pictureurl ,String name) throws  Exception;
    }





