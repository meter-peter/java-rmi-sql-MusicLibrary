package noPlayer.client.editor;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DataEditorGUI extends JFrame {
    JSplitPane splitPane;
    JButton addArtistButton;
    JButton addAlbumButton;
    JPanel actions;
    EditorController functions;
    public DataEditorGUI(EditorController editorController) {
        super("BlurredEditor");
        functions = editorController;
        splitPane = new JSplitPane();
        actions = new JPanel(new GridLayout(8,2));
        addArtistButton = new JButton("Add a new Artist");
        addAlbumButton = new JButton("Add a new Album");
        JButton addgenre = new JButton("Add a New Genre");
        setSize(800,600);
        setContentPane(splitPane);
        splitPane.setLeftComponent(actions);
        actions.add(addArtistButton);
        actions.add(addgenre);
        actions.add(addAlbumButton);
        Button gotoedit = new Button("Go to Editor");
        actions.add(gotoedit);

        gotoedit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    new DataPicker(editorController);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        setVisible(true);
        pack();

        addAlbumButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    new AlbumEditor(functions);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        addArtistButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new ArtistWindow(functions);
            }
        });

        addgenre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new GenreWindow(editorController);
            }
        });
    }

    }



class ArtistWindow extends JFrame{
    JLabel artistnameLabel;
    JPanel content;
    JTextField nameField;
    JLabel profilePhotoUrllabel;
    JTextField urlfield;
    JButton applyButton;
    EditorController functions;
    JFrame clone;

    public ArtistWindow(EditorController editorController) {
        super();
        clone = this;
        functions = editorController;
        content = new JPanel(new GridLayout(8, 2));
        setContentPane(content);
        artistnameLabel = new JLabel("Name :");
        nameField = new JTextField();
        profilePhotoUrllabel = new JLabel("Photo Url");
        urlfield = new JTextField();
        applyButton = new JButton("Apply");
        content.add(artistnameLabel);
        content.add(nameField);
        content.add(profilePhotoUrllabel);
        content.add(urlfield);
        content.add(applyButton);
        setVisible(true);
        setSize(200, 200);


        applyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    functions.addArtist(nameField.getText(), urlfield.getText());
                    clone.dispose();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

}
class GenreWindow extends JFrame{
    JLabel artistnameLabel;
    JPanel content;
    JTextField nameField;
    JLabel profilePhotoUrllabel;
    JTextField urlfield;
    JButton applyButton;
    EditorController functions;
    GenreWindow clone;

    public GenreWindow(EditorController editorController){
        super("GenreAdder");
        clone = this;
        functions = editorController;
        content = new JPanel(new GridLayout(2, 2));
        setContentPane(content);
        artistnameLabel = new JLabel("Name :");
        nameField = new JTextField();
        applyButton = new JButton("Apply");
        content.add(artistnameLabel);
        content.add(nameField);
        content.add(applyButton);
        setVisible(true);
        setSize(200, 200);


        applyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    functions.addGenre(nameField.getText());
                    clone.dispose();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }



    }

