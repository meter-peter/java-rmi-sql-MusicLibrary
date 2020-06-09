package noPlayer.client;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import noPlayer.api.Album;
import noPlayer.api.Track;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class AlbumView extends ListCell<Album> {

    RMI rmi;
    @FXML
    private Label AlbumName;

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private ImageView AlbumArt;

    @FXML
    private Label genreLabel;

    @FXML
   private ListView<Track> trackListView;

    ObservableList<Track> trackList= FXCollections.observableArrayList();

    public AlbumView(RMI rmi) throws IOException {
        loadFXML();
        this.rmi=rmi;
    }



    private void loadFXML() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("albumview.fxml"));
        loader.setController(this);
        loader.load();
    }


    @Override
    protected void updateItem(Album item, boolean empty) {
        super.updateItem(item, empty);
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(item!=null){
            URL urlim = null;
            try {
                urlim = new URL(item.getPictureUrl());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            BufferedImage c = null;
            try {
                c = ImageIO.read(urlim);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                trackList.clear();
                trackList.addAll(rmi.getAlbumTracks(item));
            } catch (Exception e) {
                e.printStackTrace();
            }


            Image imagefx = SwingFXUtils.toFXImage(c, null);
            AlbumArt= new ImageView(imagefx);
            AlbumArt.setFitHeight(75);
            AlbumArt.setFitWidth(75);
            anchorPane.getChildren().add(AlbumArt);
            setGraphic(anchorPane);
            AlbumName.setText(item.getName());

            trackListView.setItems(trackList);
        }
    }
}






