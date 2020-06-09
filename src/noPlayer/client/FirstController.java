package noPlayer.client;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import noPlayer.api.Album;
import noPlayer.api.Artist;

import javax.imageio.ImageIO;

import javafx.scene.image.ImageView;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class FirstController implements Initializable {
    @FXML
    ListView<Artist> artists;
    @FXML
    ListView<Album> artistAlbums;
    final ObservableList<Artist> artistData = FXCollections.observableArrayList();
    final ObservableList<Album> albumData = FXCollections.observableArrayList();
    Artist selectedArtist;
    @FXML
    ImageView artistImage;
    @FXML
    Label artistLabel;


    RMI rmi;
    Client client;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            initArtistList();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public FirstController(RMI rmi, Client client) throws Exception {
        this.rmi=rmi;
        this.client=client;

    }

    public void setArtistWindow(Artist artist){
        URL urlim = null;
        try {
            urlim = new URL(artist.getProfilePhotoUrl());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        BufferedImage c = null;
        try {
            c = ImageIO.read(urlim);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image imagefx = SwingFXUtils.toFXImage(c, null);
        artistImage.setImage(imagefx);
        artistLabel.setText(artist.getName());

    }

    private void initArtistAlbums(Artist artist) throws Exception {
      albumData.clear();
      albumData.addAll(rmi.getAristsAlbums(artist));
      System.out.println(albumData);
      artistAlbums.setOrientation(Orientation.VERTICAL);
      artistAlbums.setCellFactory(new AlbumCellFactory(rmi));
      artistAlbums.setItems(albumData);

    }

    private void initArtistList() throws Exception {
            artistData.clear();
            artistData.addAll(rmi.getArtists());
           artists.setOrientation(Orientation.HORIZONTAL);
           artists.setCellFactory(new Callback<ListView<Artist>, ListCell<Artist>>() {
            @Override
            public ListCell<Artist> call(ListView<Artist> artistListView) {
                ListCell<Artist> cell = new ListCell<Artist>(){
                    @Override
                    protected void updateItem(Artist artist , boolean boll){
                        super.updateItem(artist,boll);
                        setPrefHeight(48);

                        if(artist!=null) {
                            URL urlim = null;
                            try {
                                urlim = new URL(artist.getProfilePhotoUrl());
                            } catch (MalformedURLException e) {
                                e.printStackTrace();
                            }
                            BufferedImage c = null;
                            try {
                                c = ImageIO.read(urlim);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            Image imagefx = SwingFXUtils.toFXImage(c, null);
                            ImageView imageView = new ImageView(imagefx);
                            imageView.setFitHeight(50);
                            imageView.setFitWidth(50);
                            setGraphic(imageView);
                            setText(artist.getName());


                        }

                    }

                };

                return cell;
            }
        });

        artists.setItems(artistData);
        artists.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                try {
                    setArtistWindow(artists.getSelectionModel().getSelectedItem());
                    initArtistAlbums(artists.getSelectionModel().getSelectedItem());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


 public void setSelectedArtist(Artist artist){
        this.selectedArtist=artist;

 }


 public void openEditorGUI(){

 }





}

