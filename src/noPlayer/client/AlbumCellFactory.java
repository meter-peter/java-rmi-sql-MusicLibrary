package noPlayer.client;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import noPlayer.api.Album;
import noPlayer.api.Track;

import java.io.IOException;
import java.util.List;

public class AlbumCellFactory implements Callback<ListView<Album>, ListCell<Album>> {

    RMI rmi ;

    public AlbumCellFactory(RMI rmi) {
        this.rmi = rmi;
    }

    @Override
    public ListCell<Album> call(ListView<Album> albumListView) {
        try {
            return new AlbumView(rmi);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }
}