package noPlayer.client.editor;

import noPlayer.api.Album;
import noPlayer.api.Artist;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class DataPicker extends JFrame {
    EditorController editorController;
    JPanel content;
    JComboBox<Artist> artistJComboBox;
    JComboBox<Album> albumJComboBox;
    public DataPicker(EditorController editorController) throws Exception {
        super("Select Data to Edit");
        content = new JPanel();
        this.editorController = editorController;
        setContentPane(content);
        JLabel artistlbl = new JLabel("Select Artist");
        List<Artist> artists = new ArrayList<>();
        artists.clear();
        artists.addAll(editorController.getArtists());
        artistJComboBox = new JComboBox<>();
        albumJComboBox = new JComboBox<>();
        for(Artist artist:artists){
            artistJComboBox.addItem(artist);
        }

        artistJComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    initArtistAlbums((Artist) artistJComboBox.getSelectedItem());
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        JLabel albumlbl = new JLabel("Select Album");

        content.add(artistlbl);
        content.add(artistJComboBox);
        content.add(albumlbl);
        content.add(albumJComboBox);

        JButton gotoedit = new JButton("Edit Those Data");
        content.add(gotoedit);


        gotoedit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    new AlbumEditor(editorController,(Album)albumJComboBox.getSelectedItem());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        setVisible(true);
        pack();
    }


    public void initArtistAlbums(Artist artist) throws Exception {
        List<Album> albums = new ArrayList<>();
        albums.clear();
        albums.addAll(editorController.getAlbums(artist));
        for(Album album:albums){
            albumJComboBox.addItem(album);
        }


    }
}
