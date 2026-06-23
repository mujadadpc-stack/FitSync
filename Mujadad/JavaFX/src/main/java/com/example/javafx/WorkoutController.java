package com.example.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.util.Stack;

public class WorkoutController {
    private Stage stage;
    private Scene scene;
    @FXML
    private StackPane cardioPane;
    @FXML
    private StackPane weightsPane;
    @FXML
    private StackPane yogaPane;
    @FXML
    private StackPane calisthenicsPane;
    @FXML
    public ImageView cardioImage;
    @FXML
    public ImageView calisthenicsImage;
    @FXML
    public ImageView yogaImage;
    @FXML
    public ImageView weightsImage;
    @FXML
    public Button cardio5min;
    @FXML
    public Button cardio10min;
    @FXML
    public Button cardio20min;
    @FXML
    public Button yoga5min;
    @FXML
    public Button yoga10min;
    @FXML
    public Button yoga20min;
    @FXML
    public Button calisthenics5min;
    @FXML
    public Button calisthenics10min;
    @FXML
    public Button calisthenics20min;
    @FXML
    public Button weights5min;
    @FXML
    public Button weights10min;
    @FXML
    public Button weights20min;
    public void backToDashboard(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/hello-view.fxml"));
        Parent root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add("/css/hello-view.css");
        ThemeManager.apply(scene);
        stage.setScene(scene);
        stage.show();
    }
    public void initialize() {

        if (cardioImage != null) {
            cardioImage.setImage(
                    new Image(getClass().getResource("/images/cardio.jpg").toExternalForm())
            );
            cardioImage.fitWidthProperty().bind(cardioPane.widthProperty());
            cardioImage.fitHeightProperty().bind(cardioPane.heightProperty());
        }

        if (calisthenicsImage != null) {
            calisthenicsImage.setImage(
                    new Image(getClass().getResource("/images/calisthenicsImage.png").toExternalForm())
            );
            calisthenicsImage.fitWidthProperty().bind(calisthenicsPane.widthProperty());
            calisthenicsImage.fitHeightProperty().bind(calisthenicsPane.heightProperty());
        }

        if (yogaImage != null) {
            yogaImage.setImage(
                    new Image(getClass().getResource("/images/Yoga.jpg").toExternalForm())
            );
            yogaImage.fitWidthProperty().bind(yogaPane.widthProperty());
            yogaImage.fitHeightProperty().bind(yogaPane.heightProperty());
        }

        if (weightsImage != null) {
            weightsImage.setImage(
                    new Image(getClass().getResource("/images/benchPress.jpg").toExternalForm())
            );
            weightsImage.fitWidthProperty().bind(weightsPane.widthProperty());
            weightsImage.fitHeightProperty().bind(weightsPane.heightProperty());
        }
    }
    @FXML
    public void onY5(ActionEvent e) throws IOException {
        MusicManager.stopMusic();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/yoga5.fxml"));
        Parent root = loader.load();
        stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        ThemeManager.apply(scene);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void onY10(ActionEvent e) throws IOException {
        MusicManager.stopMusic();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/yoga10.fxml"));
        Parent root = loader.load();
        stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        ThemeManager.apply(scene);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void onY20(ActionEvent e) throws IOException {
        MusicManager.stopMusic();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/yoga20.fxml"));
        Parent root = loader.load();
        stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        ThemeManager.apply(scene);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void onCal5(ActionEvent e) throws IOException {
        MusicManager.stopMusic();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/calisthenics5.fxml"));
        Parent root = loader.load();
        stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        ThemeManager.apply(scene);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void onCal10(ActionEvent e) throws IOException {
        MusicManager.stopMusic();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/calisthenics10.fxml"));
        Parent root = loader.load();
        stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        ThemeManager.apply(scene);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void onCal20(ActionEvent e) throws IOException {
        MusicManager.stopMusic();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/calisthenics20.fxml"));
        Parent root = loader.load();
        stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        ThemeManager.apply(scene);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void onWalking(ActionEvent e) throws IOException {
        MusicManager.stopMusic();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Walking.fxml"));
        Parent root = loader.load();
        stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        ThemeManager.apply(scene);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void onSwimming(ActionEvent e) throws IOException {
        MusicManager.stopMusic();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Swimming.fxml"));
        Parent root = loader.load();
        stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        ThemeManager.apply(scene);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void onRunning(ActionEvent e) throws IOException {
        MusicManager.stopMusic();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Running.fxml"));
        Parent root = loader.load();
        stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        ThemeManager.apply(scene);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void onW5(ActionEvent e) throws IOException {
        MusicManager.stopMusic();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/weight5.fxml"));
        Parent root = loader.load();
        stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        ThemeManager.apply(scene);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void onW10(ActionEvent e) throws IOException {
        MusicManager.stopMusic();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/weight10.fxml"));
        Parent root = loader.load();
        stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        ThemeManager.apply(scene);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void onW20(ActionEvent e) throws IOException {
        MusicManager.stopMusic();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/weight20.fxml"));
        Parent root = loader.load();
        stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        ThemeManager.apply(scene);
        stage.setScene(scene);
        stage.show();
    }

}
