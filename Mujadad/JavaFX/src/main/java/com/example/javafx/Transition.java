package com.example.javafx;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Arc;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Transition implements Initializable {
    @FXML
    private ImageView bgImage;
    @FXML
    private Arc loadingArc;
    @FXML
    private Stage primaryStage;
    public void setStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        bgImage.setImage(new Image(
                getClass().getResource("/images/fitsync.png").toExternalForm()
        ));

        loadingArc.setVisible(false);

        ScaleTransition scaleIn = new ScaleTransition(Duration.seconds(2), bgImage);
        scaleIn.setToX(1.7);
        scaleIn.setToY(1.7);
        scaleIn.setInterpolator(Interpolator.EASE_IN);

        RotateTransition spinner = new RotateTransition(Duration.seconds(2), loadingArc);
        spinner.setByAngle(360);
        spinner.setCycleCount(RotateTransition.INDEFINITE);
        spinner.setInterpolator(Interpolator.LINEAR);

        scaleIn.play();

        scaleIn.statusProperty().addListener((obs, oldStatus, newStatus) -> {

            if (newStatus == Animation.Status.STOPPED) {

                loadingArc.setVisible(true);
                spinner.play();

                PauseTransition delay = new PauseTransition(Duration.seconds(1));
                delay.setOnFinished(e -> {

                    try {
                        FXMLLoader loader = new FXMLLoader(
                                getClass().getResource("/fxml/login.fxml")
                        );

                        Parent loginRoot = loader.load();
                        MusicManager.stopMusic();
                        primaryStage.setScene(new Scene(loginRoot));


                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                });

                delay.play();
            }
        });
        MusicManager.playMusic("/music/transition.mp3");
    }
}