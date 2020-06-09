package noPlayer.server;

import noPlayer.api.Album;
import noPlayer.api.Artist;
import noPlayer.api.Genre;
import noPlayer.api.Track;
import org.apache.commons.dbcp.BasicDataSource;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class SQLDriver {
    BasicDataSource connectionpool;
    File db;
    Connection connection;
    private static final String SQLNULLSTRING="?";
    public SQLDriver() throws ClassNotFoundException, SQLException, IOException {
        connectionpool = new BasicDataSource();
        connectionpool.setDriverClassName("org.sqlite.JDBC");
        connectionpool.setUrl("jdbc:sqlite:music.db");
        connectionpool.setMaxActive(50);
        connectionpool.setMaxOpenPreparedStatements(50);
       // db=new File("music.db");
        System.out.println ("Database connection established");
        //sample();

      }


        public void sample() throws SQLException {
          //  createatbles();
          //  addGenre("Electronic");
          //  addArtistQuery("Ohnokia","https://i.ytimg.com/vi/MMchWtgeyFc/maxresdefault.jpg");
          //  System.out.println(getArtistsQuery());
          //  addAlbumQuery("I Missed You","https://m.media-amazon.com/images/I/61u1ly9QRyL._SS500_.jpg" ,new Artist(2,"Sotgore" ,"https://scontent.fath3-3.fna.fbcdn.net/v/t1.0-9/93173483_124508695856385_4920833563915452416_n.jpg?_nc_cat=109&_nc_sid=7aed08&_nc_ohc=KtYSz1ICYaMAX9_-ts0&_nc_ht=scontent.fath3-3.fna&oh=d5d53117eaf146bdcdfbf72e18c70a23&oe=5EFD8D55"),2008,new Genre(1,"electronica"));
           // System.out.println(getArtistObjectById(1).getName());
           // addArtistQuery("Sotgore","https://scontent.fath3-3.fna.fbcdn.net/v/t1.0-9/93173483_124508695856385_4920833563915452416_n.jpg?_nc_cat=109&_nc_sid=7aed08&_nc_ohc=KtYSz1ICYaMAX9_-ts0&_nc_ht=scontent.fath3-3.fna&oh=d5d53117eaf146bdcdfbf72e18c70a23&oe=5EFD8D55");
            addTrack(getAlbumQuery(1),4,"DEMO SONG" ,500);
            //addTrack(getAlbumQuery(1),1,"DEMO SONG2" ,500);

        }


    public List<Album> getArtistAlbums(Artist artist) throws SQLException {
        Connection conn = connectionpool.getConnection();
        Statement stat = conn.createStatement();
        List<Album> artistAlbums = new ArrayList<>();
        ResultSet records = stat.executeQuery("SELECT * from Album WHERE ArtistID="+artist.getId());
        while(records.next()){
            artistAlbums.add(new Album(records.getInt(1),getArtistObjectById(records.getInt(2)),getGenreObjectfromId(records.getInt(3)),records.getInt(4),records.getString(5),records.getString(6)));
        }
        conn.close();
        return artistAlbums;


    }



    public void createatbles() throws SQLException {
        Connection conn = connectionpool.getConnection();
        Statement stat = conn.createStatement();
            stat.executeUpdate("CREATE TABLE Artists(ArtistID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, ArtistName TEXT ,ProfileUrl TEXT)");
            stat.executeUpdate("CREATE TABLE Genre(GenreID INTEGER PRIMARY KEY AUTOINCREMENT, GenreDescription TEXT)");
            stat.executeUpdate("CREATE TABLE Album(AlbumID INTEGER PRIMARY KEY AUTOINCREMENT, ArtistID INTEGER, GenreID INTEGER , ReleaseDate INTEGER, Name TEXT , PictureUrl TEXT)");
            stat.executeUpdate("CREATE TABLE Track(AlbumID INTEGER, TrackNumber INTEGER, TrackName TEXT, PlayTime INTEGER,ArtistID INTEGER,PRIMARY KEY(AlbumID, TrackNumber))");

            conn.close();
    }



    public void addArtistQuery(String Name,String ImageUrl){
        try {
            Connection conn = connectionpool.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO Artists(ArtistName,ProfileUrl) VALUES (?,?);");
            preparedStatement.setString(1, Name);
            preparedStatement.setString(2,ImageUrl);
            preparedStatement.executeUpdate();
            conn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }



    public Artist getArtistObjectById(int id) throws SQLException {
        Connection conn = connectionpool.getConnection();
        Statement stat = conn.createStatement();
        ResultSet records = stat.executeQuery("SELECT * from Artists WHERE ArtistID="+id);
        Artist temp =new Artist(records.getInt(1),records.getString(2),records.getString(3));
        conn.close();
        return temp;


    }

    //public Album getAlbumObjectById(int id) throws SQLException {
     //   ResultSet records = stat.executeQuery("SELECT * from Album WHERE ID="+id);
     //   return new Album();
//
  //  }


    public List<Artist> getArtistsQuery() throws SQLException {
        Connection conn = connectionpool.getConnection();
        Statement stat = conn.createStatement();
        ResultSet records = stat.executeQuery("SELECT * from Artists");
        List<Artist> temp = new ArrayList<>();
        while (records.next()){
            temp.add(new Artist(records.getInt(1),records.getString(2),records.getString(3)));
        }
        conn.close();
        return temp;
    }



    public void addAlbumQuery(String name, String url , Artist artist , int date , Genre genre) throws SQLException {
        Connection conn = connectionpool.getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO Album(ArtistID,GenreID,ReleaseDate,Name,PictureUrl) VALUES (?,?,?,?,?)");
        preparedStatement.setInt(1,artist.getId());
        preparedStatement.setInt(2,genre.getGenreid());
        preparedStatement.setInt(3,date);
        preparedStatement.setString(4,name);
        preparedStatement.setString(5,url);
        preparedStatement.executeUpdate();
        conn.close();

    }

    public void addTrack(Album album , int pos,String name ,int length) throws SQLException {
        Connection conn = connectionpool.getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO Track(AlbumID,TrackNumber,TrackName,PlayTime,ArtistID) VALUES (?,?,?,?,?)");
        preparedStatement.setInt(1,album.getId());
        preparedStatement.setInt(2,pos);
        preparedStatement.setString(3,name);
        preparedStatement.setInt(4,length);
        preparedStatement.setInt(5,album.getArtist().getId());
        preparedStatement.executeUpdate();
        conn.close();


    }

    public void addGenre(String Tag) throws SQLException {
        Connection conn = connectionpool.getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO Genre(GenreDescription) VALUES(?)");
        preparedStatement.setString(1,Tag);
        preparedStatement.executeUpdate();
        conn.close();
    }

    public Album getAlbumQuery(int AlbumID) throws SQLException {
        Connection conn = connectionpool.getConnection();
        Statement stat = conn.createStatement();
        ResultSet records = stat.executeQuery("SELECT * from Album WHERE AlbumID="+AlbumID);
        Album album = new Album(records.getInt(1),getArtistObjectById(records.getInt(2)),getGenreObjectfromId(records.getInt(3)),records.getInt(4),records.getString(5),records.getString(6));
        records.close();
        return album;



    }

    public List<Track> getAlbumTracks(Album Album) throws SQLException {
        List<Track> temp = new ArrayList<>();
        Connection conn = connectionpool.getConnection();
        Statement stat = conn.createStatement();
        ResultSet records = stat.executeQuery("SELECT * from Track WHERE AlbumID="+Album.getId());
        while (records.next()){
           temp.add(new Track(records.getInt(1),records.getInt(2),records.getString(3),records.getInt(4),records.getInt(5)));
        }
        records.close();
        conn.close();
        return temp;
    }

    public Genre getGenreObjectfromId(int id) throws SQLException {
        Connection conn = connectionpool.getConnection();
        Statement stat = conn.createStatement();
        ResultSet records = stat.executeQuery("SELECT * from Genre WHERE GenreID="+id);
        Genre temp = new Genre(records.getInt(1),records.getString(2));
        records.close();
        return temp;





    }
    public List<Genre> getGenres() throws SQLException {
        List<Genre> temp = new ArrayList<>();
        Connection conn = connectionpool.getConnection();
        Statement stat = conn.createStatement();
        ResultSet records = stat.executeQuery("SELECT * from GENRE");
        while(records.next()){
            temp.add(new Genre(records.getInt(1),records.getString(2)));
        }
        return temp;
    }


    public List<Track> getAllTracks() throws SQLException {
        Connection conn = connectionpool.getConnection();
        Statement stat = conn.createStatement();
        ResultSet records = stat.executeQuery("SELECT * from Track");
        List<Track> temp = new ArrayList<>();
        while (records.next()){
           // temp.add(new Track(records.getInt(1)new Album(),records.getInt(3),));
        }
        return temp;

    }




    public List<Album> getAlbums() throws SQLException {
        List temp = new ArrayList();
        Connection conn = connectionpool.getConnection();
        Statement stat = conn.createStatement();
        ResultSet records = stat.executeQuery("SELECT * from Album");
        int id;
        int artist;
        int genre;
        int releasedate;
        while(records.next()){
          id = records.getInt("AlbumID");
          artist = records.getInt("ArtistID");
          genre =records.getInt("GenreID");

            //temp.add(new Album(id,artist,)


        }
         return null;



    }

    public Album getAlbumbyName(String name) throws SQLException {
        Connection connection = connectionpool.getConnection();
        Statement statement = connection.createStatement();
        ResultSet records = statement.executeQuery("SELECT * from Album WHERE Name="+name);
        Album album = new Album(records.getInt(1),getArtistObjectById(records.getInt(2)),getGenreObjectfromId(records.getInt(3)),records.getInt(4),records.getString(5),records.getString(5));
        records.close();
        connection.close();
        return album;
    }





}
