package noPlayer.client.editor;

import noPlayer.api.Album;
import noPlayer.api.Artist;
import noPlayer.api.Genre;
import noPlayer.api.Track;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AlbumEditor extends JFrame {
    EditorController editorController;
    JList<Track> trackJList;
    JSplitPane actions;
    JSplitPane splitPane;
    JPanel jpanelofactions;
    JTextField albumname;
    JTextField imageurl;
    JTextField duration;
    JComboBox<Genre> genreJComboBox;
    DefaultListModel<Track> defaultListModel;
    List<Track> tracks;
    JPanel trackUtils;
    JButton applyAlbum;
    JComboBox<Artist> artists;
    JButton addTrackButton;
    JTextField trackname;
    JTextField trackduration;
    JComboBox<Integer> position;
    Album album;

    JTextField date;


    public AlbumEditor(EditorController functions) throws Exception {
        super("AlbumEditor");
        defaultListModel = new DefaultListModel();
        trackJList = new JList<>(defaultListModel);
        editorController = functions;
        splitPane = new JSplitPane();
        actions = new JSplitPane();
        applyAlbum = new JButton("Apply Album");
        artists = new JComboBox<>();
        splitPane.setLeftComponent(trackJList);
        actions.setOrientation(JSplitPane.VERTICAL_SPLIT);
        splitPane.setLeftComponent(actions);
        splitPane.setRightComponent(new JScrollPane(trackJList));
        jpanelofactions = new JPanel(new GridLayout(8,2));
        actions.setTopComponent(jpanelofactions);
        JLabel jLabel = new JLabel("Album Name");JLabel jLabel1 = new JLabel("Artwork URL");
        albumname = new JTextField();
        jpanelofactions.add(jLabel);jpanelofactions.add(albumname);
        imageurl = new JTextField();
        jpanelofactions.add(jLabel1);jpanelofactions.add(imageurl);
        genreJComboBox = new JComboBox<>();
        initgenrebox();
        JLabel datelbl = new JLabel("Date");
        date = new JTextField();
        jpanelofactions.add(datelbl);
        jpanelofactions.add(date);
        jpanelofactions.add(genreJComboBox);
        trackUtils = new JPanel(new GridLayout(6,2));
        actions.setBottomComponent(trackUtils);
        trackUtils.setVisible(true);
        jpanelofactions.add(applyAlbum);
        jpanelofactions.add(artists);
        applyAlbum.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    functions.addAlbum(albumname.getText(),imageurl.getText(),(Artist) artists.getSelectedItem(),Integer.parseInt(date.getText()),(Genre)genreJComboBox.getSelectedItem());
                    album = functions.getAlbumbyname(albumname.getText());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        initartist();

        JLabel namelbl = new JLabel("Track Name");
        trackname = new JTextField();
        JLabel poslbl = new JLabel("Track Position");
        position = new JComboBox<>();
        JLabel durlbl = new JLabel("Duration(seconds)");
        duration = new JTextField();

        JButton submitracks = new JButton("Submit Album Tracks");

        for(int i=1;i<21;i++){
            position.addItem(i);
        }
        trackUtils.add(namelbl);
        trackUtils.add(trackname);
        trackUtils.add(poslbl);
        trackUtils.add(position);
        trackUtils.add(durlbl);
        trackUtils.add(duration);

        addTrackButton = new JButton("Add Track");
        trackUtils.add(addTrackButton);
        trackUtils.add(submitracks);
        addTrackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    defaultListModel.add(defaultListModel.size(),new Track(album.getId(),
                            (Integer)position.getSelectedItem(),
                            trackname.getText(),
                            Integer.parseInt(duration.getText()),
                            album.getArtist().getId()));


                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        setContentPane(splitPane);
        setVisible(true);
        pack();

        submitracks.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ArrayList list = new ArrayList(trackJList.getModel().getSize());
                for (int i = 0; i < trackJList.getModel().getSize(); i++) {
                    list.add(trackJList.getModel().getElementAt(i));
                }
                try {
                    editorController.addTracks(album,list);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }
    public void initartist() throws Exception {
        List<Artist> artists = editorController.getArtists();
        for(Artist artist :artists){
            this.artists.addItem(artist);
        }

    }
    public void initgenrebox() throws Exception {
        List<Genre> genreList = editorController.getGenres();
        for(Genre genre :genreList){
            genreJComboBox.addItem(genre);
        }
    }
}
