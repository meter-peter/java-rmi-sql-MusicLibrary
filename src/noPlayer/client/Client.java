package noPlayer.client;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import noPlayer.client.editor.DataEditorGUI;
import noPlayer.client.editor.EditorController;


public class Client  extends Application {
    Scene scene;
    RMI rmi;



    public void start(Stage stage) throws Exception {
        rmi = new RMI();
        FXMLLoader fxmlLoader = new FXMLLoader(Client.class.getResource("primary" + ".fxml"));
        FirstController firstController = new FirstController(rmi,this);
        fxmlLoader.setController(firstController);
        AnchorPane anchorPane = fxmlLoader.load();
        Button button = new Button("Editor");
        Button button1 = new Button("RefreshData");
        button1.setLayoutX(200);
        anchorPane.getChildren().add(button);
        anchorPane.getChildren().add(button1);
        scene = new Scene(anchorPane,1024,768);
        stage.setScene(scene);
        stage.show();

        button.onActionProperty().setValue(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                new EditorController(rmi);
            }
        });
        button1.onActionProperty().setValue(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    start(stage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }






    public static void main(String[] args) {
        launch();
    }
}

