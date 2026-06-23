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
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class Weight {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private boolean running;

    @FXML
    public Button weight5minStartButton;
    @FXML
    public Button weight5minPauseButton;
    @FXML
    public Button weight5minStopButton;

    @FXML
    public Button weight10minStartButton;
    @FXML
    public Button weight10minPauseButton;
    @FXML
    public Button weight10minStopButton;

    @FXML
    public Button weight20minStartButton;
    @FXML
    public Button weight20minPauseButton;
    @FXML
    public Button weight20minStopButton;

    @FXML
    public Label timerLabel5min;
    @FXML
    public Label timerLabel10min;
    @FXML
    public Label timerLabel20min;
    
    @FXML
    private MediaView weightlifting5minMediaView;
    private MediaPlayer weightlifting5minMediaPlayer;
    
    @FXML
    private MediaView weightlifting10minMediaView;
    private MediaPlayer weightlifting10minMediaPlayer;
    
    @FXML
    private MediaView weightlifting20minMediaView;
    private MediaPlayer weightlifting20minMediaPlayer;

    // --- Timer state ---
    private Timeline timeline5min;
    private Timeline timeline10min;
    private Timeline timeline20min;

    private int seconds;

    private int seconds5min  = 5  * 60;
    private int seconds10min = 10 * 60;
    private int seconds20min = 20 * 60;

    //  Helpers:

    /** Formats a raw second count as MM:SS */
    private String format(int totalSeconds) {
        int m = totalSeconds / 60;
        int s = totalSeconds % 60;
        return String.format("%02d:%02d", m, s);
    }

    /** Builds a one-second-tick Timeline that counts down the supplied int[]{seconds} array. */
    private Timeline buildTimer(int[] secondsHolder, Label label) {
        Timeline tl = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            seconds++;
            secondsHolder[0]--;
            label.setText(format(secondsHolder[0]));
            if (secondsHolder[0] <= 0) {
                label.setText("Done!");
                stopAudio();
            }
        }));
        tl.setCycleCount(secondsHolder[0]); // exactly as many ticks as seconds remain
        return tl;
    }

    //  FXML initialiser:

    @FXML
    Label quoteLabel;
    @FXML
    public void initialize() {
        quoteLabel.setStyle("-fx-text-fill: #dc143c; -fx-font-family: Georgia; -fx-font-size: 24px; -fx-font-style: italic;");


        if (timerLabel5min  != null)
            timerLabel5min .setText(format(seconds5min));
        if (timerLabel10min != null)
            timerLabel10min.setText(format(seconds10min));
        if (timerLabel20min != null)
            timerLabel20min.setText(format(seconds20min));


        try {

            Media media = new Media(getClass().getResource("/animation/weights-5min.mp4").toExternalForm());

            weightlifting5minMediaPlayer = new MediaPlayer(media);

            Rectangle clip = new Rectangle(320, 220);
            clip.setArcWidth(40);   // matches -fx-background-radius: 20 (doubled)
            clip.setArcHeight(40);
            weightlifting5minMediaView.setClip(clip);

            weightlifting5minMediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            weightlifting5minMediaPlayer.setMute(false);
            weightlifting5minMediaPlayer.setOnError(() ->
                    System.err.println("MediaPlayer error: " + weightlifting5minMediaPlayer.getError())
            );

            weightlifting5minMediaView.mediaPlayerProperty().set(weightlifting5minMediaPlayer);
            weightlifting5minMediaPlayer.play();
        }

        catch (Exception e) {
            System.err.println("Could not load workout video: " + e.getMessage());
        }

        if (weightlifting10minMediaView != null) {
            try {
                var resource = getClass().getResource("/animation/weights-5min.mp4");

                if (resource == null) {
                    System.err.println("Video file not found: /animation/weights-5min.mp4");
                    return;
                }

                Media media = new Media(resource.toExternalForm());
                weightlifting10minMediaPlayer = new MediaPlayer(media);

                weightlifting10minMediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
                weightlifting10minMediaPlayer.setMute(false);
                weightlifting10minMediaPlayer.setOnError(() ->
                        System.err.println("MediaPlayer error: " + weightlifting10minMediaPlayer.getError())
                );

                weightlifting10minMediaView.mediaPlayerProperty().set(weightlifting10minMediaPlayer);
                weightlifting10minMediaPlayer.play();

                // Clip the StackPane for rounded corners
                Rectangle clip = new Rectangle(320, 220);
                clip.setArcWidth(40);
                clip.setArcHeight(40);
                weightlifting10minMediaView.setClip(clip);

            } catch (Exception e) {
                System.err.println("Could not load 10min video: " + e.getMessage());
            }
        }


        if (weightlifting20minMediaView != null) {
            try {
                var resource = getClass().getResource("/animation/weights-5min.mp4");

                if (resource == null) {
                    System.err.println("Video file not found: /animation/weights-5min.mp4");
                    return;
                }

                Media media = new Media(resource.toExternalForm());
                weightlifting20minMediaPlayer = new MediaPlayer(media);

                weightlifting20minMediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
                weightlifting20minMediaPlayer.setMute(false);
                weightlifting20minMediaPlayer.setOnError(() ->
                        System.err.println("MediaPlayer error: " + weightlifting20minMediaPlayer.getError())
                );

                weightlifting20minMediaView.mediaPlayerProperty().set(weightlifting20minMediaPlayer);
                weightlifting20minMediaPlayer.play();

                // Clip the StackPane for rounded corners
                Rectangle clip = new Rectangle(320, 220);
                clip.setArcWidth(40);
                clip.setArcHeight(40);
                weightlifting20minMediaView.setClip(clip);

            } catch (Exception e) {
                System.err.println("Could not load 20min video: " + e.getMessage());
            }
        }
    }


    public void cleanup() {
        if (weightlifting5minMediaPlayer != null) {
            weightlifting5minMediaPlayer.stop();
            weightlifting5minMediaPlayer.dispose();
            weightlifting5minMediaPlayer = null;
        }

        if (weightlifting10minMediaPlayer != null) {
            weightlifting10minMediaPlayer.stop();
            weightlifting10minMediaPlayer.dispose();
            weightlifting10minMediaPlayer = null;
        }

        if (weightlifting20minMediaPlayer != null) {
            weightlifting20minMediaPlayer.stop();
            weightlifting20minMediaPlayer.dispose();
            weightlifting20minMediaPlayer = null;
        }

    }
    private MediaPlayer mp;
    private void startAudio(String path) {
        if(mp==null)
        {
            try {
                Media media = new Media(getClass().getResource(path).toExternalForm());
                mp = new MediaPlayer(media);
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



    @FXML
    public void start5min(ActionEvent event) {
        while(!running)
        {
            startAudio("/music/weight-5 min.mp3");
            seconds = 0;
            if (timeline5min != null) timeline5min.stop();
            int[] holder = {seconds5min};
            timeline5min = buildTimer(holder, timerLabel5min);
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
                    weight5minPauseButton.setText("Resume");
                    double calories = calculateCalories("5_min", seconds);
                    caloriesLabel5min.setText(String.format("Calories Burned: %.1f cal", calories));
                }
                case PAUSED  -> {
                    timeline5min.play();
                    mp.play();
                    weight5minPauseButton.setText("Pause");
                }
                default      -> {}
            }
        }
    }

    @FXML
    private Label caloriesLabel5min;
    @FXML
    public void stop5min(ActionEvent event) {
        while(running)
        {
            stopAudio();
            if (timeline5min != null) {
                timeline5min.stop();
                seconds5min = 5 * 60;

                double calories = calculateCalories("5_min", seconds);
                caloriesLabel5min.setText(String.format("Calories Burned: %.1f cal", calories));
                JsonLoader.updateCalories(Session.username,calories);
                mp = null;

                timerLabel5min.setText(format(seconds5min));}
                running = false;
        }
    }

    //  10-minute timer:

    @FXML
    public void start10min(ActionEvent event) {
        while(!running)
        {
            startAudio("/music/weight-10min.mp3");
            seconds = 0;
            if (timeline10min != null)
                timeline10min.stop();
            int[] holder = {seconds10min};
            timeline10min = buildTimer(holder, timerLabel10min);
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
                    weight10minPauseButton.setText("Resume");
                    double calories = calculateCalories("5_min", seconds);
                    caloriesLabel10min.setText(String.format("Calories Burned: %.1f cal", calories));
                }
                case PAUSED  -> {
                    timeline10min.play();
                    mp.play();
                    weight10minPauseButton.setText("Pause");
                }
                default      -> {}
            }
        }
    }

    @FXML
    private Label caloriesLabel10min;
    @FXML
    public void stop10min(ActionEvent event) {
        while(running)
        {
            stopAudio();
            if (timeline10min != null) {
            timeline10min.stop();
            seconds10min = 10 * 60;
            mp.stop();

            double calories = calculateCalories("10_min", seconds);
            caloriesLabel10min.setText(String.format("Calories Burned: %.1f cal", calories));
            JsonLoader.updateCalories(Session.username,calories);

            timerLabel10min.setText(format(seconds10min));
            running = false;
            mp = null;
        }

        }

    }
    //  20 minute timer

    @FXML
    public void start20min(ActionEvent event) {
        while(!running)
        {
            startAudio("/music/weight-20min.mp3");
            seconds = 0;
            if (timeline20min != null) timeline20min.stop();
            int[] holder = {seconds20min};
            timeline20min = buildTimer(holder, timerLabel20min);
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
                    weight20minPauseButton.setText("Resume");
                    double calories = calculateCalories("20_min", seconds);
                    caloriesLabel20min.setText(String.format("Calories Burned: %.1f cal", calories));
                }
                case PAUSED  -> {
                    timeline20min.play();
                    mp.play();
                    weight20minPauseButton.setText("Pause");
                }
                default      -> {}
            }
        }
    }

    @FXML
    private Label caloriesLabel20min;
    @FXML
    public void stop20min(ActionEvent event) {
        while(running)
        {
            if (timeline20min != null) {
                stopAudio();
                timeline20min.stop();
                seconds20min = 20 * 60;
                mp.stop();
                mp = null;
                double calories = calculateCalories("20_min", seconds);
                caloriesLabel20min.setText(String.format("Calories Burned: %.1f cal", calories));
                JsonLoader.updateCalories(Session.username,calories);

                timerLabel20min.setText(format(seconds20min));
                running = false;
            }
        }
    }

    /* ==============================Calorie Calculation====================================
     Calories = MET × weight(kg) × time(hours)
     MET values: 5min = 3.5 | 10min = 4.5 | 20min = 6.5 */
    private double calculateCalories(String activity, int seconds) {
        double weightKg = 70.0; // FOR TESTING
        double hours = seconds / 3600.0; // convert seconds → hours

        double met;
        switch (activity) {
            case "5_min":  met = 3.5; break;
            case "10_min":  met = 4.5; break;
            case "20_min": met = 6.5; break;
            default:         met = 1.0;
        }

        return Math.round(met * weightKg * hours * 10.0) / 10.0;
    }
    //  Navigation:
    public void backToWeight(ActionEvent actionEvent) throws IOException {
        cleanup();
        stopAudio();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Weights.fxml"));
        root = loader.load();
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        ThemeManager.apply(scene);
        stage.setScene(scene);
        stage.show();

    }
}
