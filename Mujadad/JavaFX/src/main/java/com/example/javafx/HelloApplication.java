package com.example.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.tree.TreeNode;
import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Transition.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/css/hello-view.css").toExternalForm());
        Transition t = loader.getController();
        t.setStage(stage);
        stage.setScene(scene);
        stage.setWidth(1000);
        stage.setHeight(700);
        stage.setTitle("FitSync");
        stage.show();

    }
}
