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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class Calisthenics {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private boolean running = false;
    @FXML
    public Button backButton;
    @FXML
    public Button calisthenics5minStartButton;
    @FXML
    public Button calisthenics5minPauseButton;
    @FXML
    public Button calisthenics5minStopButton;

    @FXML
    public Button calisthenics10minStartButton;
    @FXML
    public Button calisthenics10minPauseButton;
    @FXML
    public Button calisthenics10minStopButton;

    @FXML
    public Button calisthenics20minStartButton;
    @FXML
    public Button calisthenics20minPauseButton;
    @FXML
    public Button calisthenics20minStopButton;

    @FXML
    public Label timerLabel5min;
    @FXML
    public Label timerLabel10min;
    @FXML
    public Label timerLabel20min;

    @FXML
    private MediaView cali5minMediaView;
    private MediaPlayer cali5minMediaPlayer;

    @FXML
    private MediaView cali10minMediaView;
    private MediaPlayer cali10minMediaPlayer;

    @FXML
    private MediaView cali20minMediaView;
    private MediaPlayer cali20minMediaPlayer;

    private Timeline timeline5min;
    private Timeline timeline10min;
    private Timeline timeline20min;

    int seconds;

    private int seconds5min  = 5  * 60;
    private int seconds10min = 10 * 60;
    private int seconds20min = 20 * 60;


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
//                if (onFinish != null) onFinish.run();
            }
        }));
        tl.setCycleCount(secondsHolder[0]);
        return tl;
    }

    @FXML
    Label quoteLabel;
    @FXML
    public void initialize() {
        quoteLabel.setStyle("-fx-text-fill: #00c853; -fx-font-family: Georgia; -fx-font-size: 24px; -fx-font-style: italic;");

        if (timerLabel5min  != null) timerLabel5min .setText(format(seconds5min));
        if (timerLabel10min != null) timerLabel10min.setText(format(seconds10min));
        if (timerLabel20min != null) timerLabel20min.setText(format(seconds20min));

        try {
            var resource = getClass().getResource("/animation/cali-5min.mp4");
            if(resource == null)
            {
                System.err.println("Video file not found: " + resource.toString());
                return;
            }
            Media media = new Media(resource.toExternalForm());

            cali5minMediaPlayer = new MediaPlayer(media);

            javafx.scene.shape.Rectangle clip = new javafx.scene.shape.Rectangle(320, 220);
            clip.setArcWidth(40);   // matches -fx-background-radius: 20 (doubled)
            clip.setArcHeight(40);
            cali5minMediaView.setClip(clip);

            cali5minMediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            cali5minMediaPlayer.setMute(false);
            cali5minMediaPlayer.setOnError(() ->
                    System.err.println("MediaPlayer error: " + cali5minMediaPlayer.getError())
            );

            cali5minMediaView.mediaPlayerProperty().set(cali5minMediaPlayer);
            cali5minMediaPlayer.play();
        }

        catch (Exception e) {
            System.err.println("Could not load workout video: " + e.getMessage());
        }

        if (cali10minMediaView != null) {
            try {
                var resource = getClass().getResource("/animation/cali-10min.mp4");

                if (resource == null) {
                    System.err.println("Video file not found: /animation/cali-10min.mp4");
                    return;
                }

                Media media = new Media(resource.toExternalForm());
                cali10minMediaPlayer = new MediaPlayer(media);

                cali10minMediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
                cali10minMediaPlayer.setMute(false);
                cali10minMediaPlayer.setOnError(() ->
                        System.err.println("MediaPlayer error: " + cali10minMediaPlayer.getError())
                );

                cali10minMediaView.mediaPlayerProperty().set(cali10minMediaPlayer);
                cali10minMediaPlayer.play();

                // Clip the StackPane for rounded corners
                javafx.scene.shape.Rectangle clip = new javafx.scene.shape.Rectangle(320, 220);
                clip.setArcWidth(40);
                clip.setArcHeight(40);
                cali10minMediaView.setClip(clip);

            } catch (Exception e) {
                System.err.println("Could not load 10min video: " + e.getMessage());
            }
        }


        if (cali20minMediaView != null) {
            try {
                var resource = getClass().getResource("/animation/cali-20min.mp4");

                if (resource == null) {
                    System.err.println("Video file not found: /animation/cali-20min.mp4");
                    return;
                }

                Media media = new Media(resource.toExternalForm());
                cali20minMediaPlayer = new MediaPlayer(media);

                cali20minMediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
                cali20minMediaPlayer.setMute(false);
                cali20minMediaPlayer.setOnError(() ->
                        System.err.println("MediaPlayer error: " + cali20minMediaPlayer.getError())
                );

                cali20minMediaView.mediaPlayerProperty().set(cali20minMediaPlayer);
                cali20minMediaPlayer.play();

                // Clip the StackPane for rounded corners
                javafx.scene.shape.Rectangle clip = new javafx.scene.shape.Rectangle(320, 220);
                clip.setArcWidth(40);
                clip.setArcHeight(40);
                cali20minMediaView.setClip(clip);

            } catch (Exception e) {
                System.err.println("Could not load 20min video: " + e.getMessage());
            }
        }

    }
    public void cleanup() {
        if (cali5minMediaPlayer != null) {
            cali5minMediaPlayer.stop();
            cali5minMediaPlayer.dispose();
            cali5minMediaPlayer = null;
        }

        if (cali10minMediaPlayer != null) {
            cali10minMediaPlayer.stop();
            cali10minMediaPlayer.dispose();
            cali10minMediaPlayer = null;
        }

        if (cali20minMediaPlayer != null) {
            cali20minMediaPlayer.stop();
            cali20minMediaPlayer.dispose();
            cali20minMediaPlayer = null;
        }

    }
    private MediaPlayer mp;
    public void startAudio(String path) {
        if(mp==null) {
            try {
                Media media = new Media(getClass().getResource(path).toExternalForm());
                mp = new MediaPlayer(media);
                mp.play();
            } catch (Exception e) {
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
    //  5-minute timer:
    @FXML
    public void start5min(ActionEvent event) {
        while(!running)
        {
            startAudio("/music/cali-5min.mpeg");
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
                    calisthenics5minPauseButton.setText("Resume");
                    double calories = calculateCalories("5_min", seconds);
                    caloriesLabel5min.setText(String.format("Calories Burned: %.1f cal", calories));
                }
                case PAUSED  -> {
                    timeline5min.play();
                    mp.play();
                    calisthenics5minPauseButton.setText("Pause");
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
            if (timeline5min != null) {
                timeline5min.stop();
                seconds5min = 5 * 60;
                mp = null;
                double calories = calculateCalories("5_min", seconds);
                caloriesLabel5min.setText(String.format("Calories Burned: %.1f cal", calories));
                JsonLoader.updateCalories(Session.username,calories);
                timerLabel5min.setText(format(seconds5min));
                running = false;
            }
        }
    }
    //  10-minute timer:
    @FXML
    public void start10min(ActionEvent event) {
        while(!running)
        {
            startAudio("/music/cali-10min.mpeg");
            seconds = 0;
            if (timeline10min != null) timeline10min.stop();
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
                    calisthenics10minPauseButton.setText("Resume");
                    double calories = calculateCalories("10_min", seconds);
                    caloriesLabel10min.setText(String.format("Calories Burned: %.1f cal", calories));
                }
                case PAUSED  -> {
                    timeline10min.play();
                    mp.play();
                    calisthenics10minPauseButton.setText("Pause");
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
            if (timeline10min != null) {
                timeline10min.stop();
                seconds10min = 10 * 60;
                mp.stop();

                double calories = calculateCalories("10_min", seconds);
                caloriesLabel10min.setText(String.format("Calories Burned: %.1f cal", calories));
                JsonLoader.updateCalories(Session.username,calories);
                mp = null;
                timerLabel10min.setText(format(seconds10min));
                running = false;
            }
        }

    }

    //  20-minute timer:

    @FXML
    public void start20min(ActionEvent event) {
        while(!running)
        {
            startAudio("/music/Cali20min.mp3");
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
                    mp.pause();
                    timeline20min.pause();
                    calisthenics20minPauseButton.setText("Resume");
                    double calories = calculateCalories("20_min", seconds);
                    caloriesLabel20min.setText(String.format("Calories Burned: %.1f cal", calories));
                }
                case PAUSED  -> {
                    mp.play();
                    timeline20min.play();
                    calisthenics20minPauseButton.setText("Pause");
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
                timeline20min.stop();
                seconds20min = 20 * 60;
                mp.stop();
                double calories = calculateCalories("20_min", seconds);
                caloriesLabel20min.setText(String.format("Calories Burned: %.1f cal", calories));
                JsonLoader.updateCalories(Session.username,calories);
                timerLabel20min.setText(format(seconds20min));
                running = false;
                mp = null;
            }
        }

    }

    /* ==============================Calorie Calculation====================================
   Calories = MET × weight(kg) × time(hours)
   MET values: 5min = 3.5 | 10min = 4.0 | 20min = 4.5 */
    private double calculateCalories(String activity, int seconds) {
        double weightKg = 70.0; // FOR TESTING
        double hours = seconds / 3600.0; // convert seconds → hours

        double met;
        switch (activity) {
            case "5_min":  met = 3.5; break;
            case "10_min":  met = 4.0; break;
            case "20_min": met = 4.5; break;
            default:         met = 1.0;
        }

        return Math.round(met * weightKg * hours * 10.0) / 10.0;
    }

    //  Navigation:
    @FXML
    public void backToCalisthenics(ActionEvent actionEvent) throws IOException {
        cleanup();
        stopAudio();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Calisthenics.fxml"));
        root = loader.load();
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        ThemeManager.apply(scene);
        stage.setScene(scene);
        stage.show();
    }
}
