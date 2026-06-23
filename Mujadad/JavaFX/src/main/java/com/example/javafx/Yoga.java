package com.example.javafx;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class Yoga {
    public Stage stage;
    public Scene scene;
    public Parent root;
    private boolean running;

    @FXML
    public Button yoga5minStartButton;
    @FXML
    public Button yoga5minPauseButton;
    @FXML
    public Button yoga5minStopButton;

    @FXML
    public Button yoga10minStartButton;
    @FXML
    public Button yoga10minPauseButton;
    @FXML
    public Button yoga10minStopButton;

    @FXML
    public Button yoga20minStartButton;
    @FXML
    public Button yoga20minPauseButton;
    @FXML
    public Button yoga20minStopButton;

    @FXML
    public Label timerLabel5min;
    @FXML
    public Label timerLabel10min;
    @FXML
    public Label timerLabel20min;

    @FXML
    private MediaView yoga5minMediaView;
    private MediaPlayer yoga5minMediaPlayer;

    @FXML
    private MediaView yoga10minMediaView;
    private MediaPlayer yoga10minMediaPlayer;

    private MediaPlayer mp;

    @FXML
    private MediaView yoga20minMediaView;
    private MediaPlayer yoga20minMediaPlayer;


    // --- Timer state ---
    private Timeline timeline5min;
    private Timeline timeline10min;
    private Timeline timeline20min;

    private int seconds5min  = 5  * 60;
    private int seconds10min = 10 * 60;
    private int seconds20min = 20 * 60;

    // ── Helpers ──────────────────────────────────────────────────────────────

    private String format(int totalSeconds) {
        int m = totalSeconds / 60;
        int s = totalSeconds % 60;
        return String.format("%02d:%02d", m, s);
    }

    private Timeline buildTimer(int[] secondsHolder, Label label, Runnable onFinish) {
        Timeline tl = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            secondsHolder[0]--;
            label.setText(format(secondsHolder[0]));
            if (secondsHolder[0] <= 0) {
                label.setText("Done!");
                if (onFinish != null) onFinish.run();
            }
        }));
        tl.setCycleCount(secondsHolder[0]); // exactly as many ticks as seconds remain
        return tl;
    }

    @FXML
    Label quoteLabel;
    @FXML
    public void initialize() {
        quoteLabel.setStyle("-fx-text-fill: #ff00ff; -fx-font-family: Georgia; -fx-font-size: 24px; -fx-font-style: italic;");


        if (timerLabel5min  != null) timerLabel5min .setText(format(seconds5min));
        if (timerLabel10min != null) timerLabel10min.setText(format(seconds10min));
        if (timerLabel20min != null) timerLabel20min.setText(format(seconds20min));


        try {

            Media media = new Media(getClass().getResource("/animation/yoga-5min.mp4").toExternalForm());

            yoga5minMediaPlayer = new MediaPlayer(media);

            javafx.scene.shape.Rectangle clip = new javafx.scene.shape.Rectangle(320, 220);
            clip.setArcWidth(40);   // matches -fx-background-radius: 20 (doubled)
            clip.setArcHeight(40);
            yoga5minMediaView.setClip(clip);

            yoga5minMediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            yoga5minMediaPlayer.setMute(false);
            yoga5minMediaPlayer.setOnError(() ->
                    System.err.println("MediaPlayer error: " + yoga5minMediaPlayer.getError())
            );

            yoga5minMediaView.mediaPlayerProperty().set(yoga5minMediaPlayer);
            yoga5minMediaPlayer.play();
        }

        catch (Exception e) {
            System.err.println("Could not load workout video: " + e.getMessage());
        }

        if (yoga10minMediaView != null) {
            try {
                var resource = getClass().getResource("/animation/yoga-10min.mp4");

                if (resource == null) {
                    System.err.println("Video file not found: /animation/yoga-10min.mp4");
                    return;
                }

                Media media = new Media(resource.toExternalForm());
                yoga10minMediaPlayer = new MediaPlayer(media);

                yoga10minMediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
                yoga10minMediaPlayer.setMute(false);
                yoga10minMediaPlayer.setOnError(() ->
                        System.err.println("MediaPlayer error: " + yoga10minMediaPlayer.getError())
                );

                yoga10minMediaView.mediaPlayerProperty().set(yoga10minMediaPlayer);
                yoga10minMediaPlayer.play();

                // Clip the StackPane for rounded corners
                javafx.scene.shape.Rectangle clip = new javafx.scene.shape.Rectangle(320, 220);
                clip.setArcWidth(40);
                clip.setArcHeight(40);
                yoga10minMediaView.setClip(clip);

            } catch (Exception e) {
                System.err.println("Could not load 10min video: " + e.getMessage());
            }
        }
    }

     public void startAudio() {
        if(mp==null)
        {
            try {
                Media media = new Media(getClass().getResource("/music/2in1audio.mpeg").toExternalForm());
                mp = new MediaPlayer(media);
                mp.setCycleCount(MediaPlayer.INDEFINITE);
                mp.play();
            }  catch (Exception e){
                System.out.println("Could not load audio: " + e.getMessage());
            }
        }
     }

     public void stopAudio() {
          if(mp != null){
              mp.stop();
              mp.dispose();
          }
    }

    public void cleanup() {

        if (yoga5minMediaPlayer != null) {
            yoga5minMediaPlayer.stop();
            yoga5minMediaPlayer.dispose();
            yoga5minMediaPlayer = null;
        }

        if (yoga10minMediaPlayer != null) {
            yoga10minMediaPlayer.stop();
            yoga10minMediaPlayer.dispose();
            yoga10minMediaPlayer = null;
        }

        if (yoga20minMediaPlayer != null) {
            yoga20minMediaPlayer.stop();
            yoga20minMediaPlayer.dispose();
            yoga20minMediaPlayer = null;
        }
    }


    @FXML
    public void start5min(ActionEvent event) {
        while(!running)
        {
            startAudio();
            if (timeline5min != null)
                timeline5min.stop();
            int[] holder = {seconds5min};
            timeline5min = buildTimer(holder, timerLabel5min, null);
            timeline5min.setOnFinished(e -> seconds5min = 5 * 60); // auto-reset internal state
            timeline5min.play();
            running = true;
        }

    }

    @FXML
    public void pause5min(ActionEvent event) {
        if (timeline5min != null) {
            switch (timeline5min.getStatus()) {
                case RUNNING ->{
                    timeline5min.pause();
                    mp.pause();
                    yoga5minPauseButton.setText("Resume");
                }
                case PAUSED  -> {
                    timeline5min.play();
                    mp.play();
                    yoga5minPauseButton.setText("Pause");
                }
                default      -> {}
            }
        }
    }

    @FXML
    public void stop5min(ActionEvent event) {
        while(running)
        {
            stopAudio();
            if (timeline5min != null) {
                timeline5min.stop();
                seconds5min = 5 * 60;
                timerLabel5min.setText(format(seconds5min));
                running = false;
                mp = null;
            }

        }

    }

    @FXML
    public void start10min(ActionEvent event) {
        while(!running)
        {
            startAudio();
            if (timeline10min != null)
                timeline10min.stop();
            int[] holder = {seconds10min};
            timeline10min = buildTimer(holder, timerLabel10min, null);
            timeline10min.setOnFinished(e -> seconds10min = 10 * 60);
            timeline10min.play();
            running = true;
        }

    }

    @FXML
    public void pause10min(ActionEvent event) {
        if (timeline10min != null) {
            switch (timeline10min.getStatus()) {
                case RUNNING -> {
                    timeline10min.pause();
                    mp.pause();
                    yoga10minPauseButton.setText("Resume");
                }
                case PAUSED  -> {
                    timeline10min.play();
                    mp.play();
                    yoga10minPauseButton.setText("Pause");
                }
                default      -> {}
            }
        }
    }

    @FXML
    public void stop10min(ActionEvent event) {
        while(running)
        {
            stopAudio();
            if (timeline10min != null) timeline10min.stop();
            seconds10min = 10 * 60;
            timerLabel10min.setText(format(seconds10min));
            running = false;
            mp = null;
        }

    }


    @FXML
    public void start20min(ActionEvent event) {
        while(!running)
        {
            startAudio();
            if (timeline20min != null) timeline20min.stop();
            int[] holder = {seconds20min};
            timeline20min = buildTimer(holder, timerLabel20min, null);
            timeline20min.setOnFinished(e -> seconds20min = 20 * 60);
            timeline20min.play();
            running = true;
        }

    }

    @FXML
    public void pause20min(ActionEvent event) {
        if (timeline20min != null) {
            switch (timeline20min.getStatus()) {
                case RUNNING -> {
                    timeline20min.pause();
                    mp.pause();
                    yoga20minPauseButton.setText("Resume");
                }
                case PAUSED  -> {
                    timeline20min.play();
                    mp.play();
                    yoga20minPauseButton.setText("Pause");
                }
                default      -> {}
            }
        }
    }

    @FXML
    public void stop20min(ActionEvent event) {
        while(running)
        {
            stopAudio();
            if (timeline20min != null)
                timeline20min.stop();
            seconds20min = 20 * 60;
            timerLabel20min.setText(format(seconds20min));
            running = false;
            mp = null;
        }

    }


    public void backToYoga(ActionEvent actionEvent) throws IOException {
        stopAudio();
        cleanup();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Yoga.fxml"));
        root = loader.load();
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        ThemeManager.apply(scene);
        stage.setScene(scene);
        stage.show();
    }

}
