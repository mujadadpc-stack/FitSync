package com.example.javafx;

import javafx.animation.Animation;
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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class Cardio {

 public Stage stage;
 public Scene scene;
 public Parent root;

    @FXML
    public Button walkingStartButton;
    @FXML
    public Button walkingStopButton;

    @FXML
    public Button runningStartButton;
    @FXML
    public Button runningStopButton;

    @FXML
    public Button swimmingStartButton;
    @FXML
    public Button swimmingStopButton;

    @FXML
    public Label walkingTimerLabel;
    @FXML
    public Label runningTimerLabel;
    @FXML
    public Label swimmingTimerLabel;
    public ImageView cardioImage;

    @FXML
    private MediaView runningMediaView;
    private MediaPlayer runningMediaPlayer;

    @FXML
    private MediaView walkingMediaView;
    private MediaPlayer walkingMediaPlayer;

    @FXML
    private MediaView swimmingMediaView;
    private MediaPlayer swimmingMediaPlayer;

    private MediaPlayer mp;
    // Timer state:

    private Timeline walkingTimeline;
    private Timeline runningTimeline;
    private Timeline swimmingTimeline;


    private int walkingSeconds  = 0;
    private int runningSeconds  = 0;
    private int swimmingSeconds = 0;
    private int walkingSteps = 0;
    private static final double STEPS_PER_SECOND = 1.0; // 1 step per second as you said

    @FXML
    public Label stepsLabel;  // wire to your FXML fx:id="stepsLabel"

    //  Initialization:

    @FXML
    Label quoteLabel;
    @FXML
    public void initialize() {
        quoteLabel.setStyle(
                "-fx-text-fill: #ff6d00;" +
                        "-fx-font-family: Georgia;" +
                        "-fx-font-size: 24px;" +
                        "-fx-font-style: italic;"
        );

        // Build the timelines HERE
        walkingTimeline = buildTimeline(() -> {
            walkingSeconds++;
            walkingSteps = (int)(walkingSeconds * STEPS_PER_SECOND);
            updateLabel(walkingTimerLabel, walkingSeconds);
            stepsLabel.setText("Steps: " + walkingSteps); // uncomment if needed
        });

        runningTimeline = buildTimeline(() -> {
            runningSeconds++;
            updateLabel(runningTimerLabel, runningSeconds);
        });

        swimmingTimeline = buildTimeline(() -> {
            swimmingSeconds++;
            updateLabel(swimmingTimerLabel, swimmingSeconds);
        });

        //  always play audio
            startAudio("/music/2in1audio.mpeg");

        // Your existing video setup
        if (runningMediaView != null) {
            setupVideo("/animation/running.mp4", runningMediaView);
        } else if (walkingMediaView != null) {
            setupVideo("/animation/walking.mp4", walkingMediaView);
        } else if (swimmingMediaView != null) {
            setupVideo("/animation/swimming.mp4", swimmingMediaView);
        }

        // audio setup
        if(mp != null) {
            startAudio("/music/2in1audio.mpeg");
        }
    }

    // Helper method to keep your code clean
    private void setupVideo(String path, MediaView view) {
        try {
            Media media = new Media(getClass().getResource(path).toExternalForm());
            MediaPlayer player = new MediaPlayer(media); //  local variable first

            javafx.scene.shape.Rectangle clip = new javafx.scene.shape.Rectangle(320, 220);
            clip.setArcWidth(40);
            clip.setArcHeight(40);
            view.setClip(clip);
            view.setMediaPlayer(player);
            player.setMute(true);
            player.setCycleCount(MediaPlayer.INDEFINITE);
            player.play();

            //  Assign to the right field based on which view it is
            if (view == runningMediaView)       runningMediaPlayer  = player;
            else if (view == walkingMediaView)  walkingMediaPlayer  = player;
            else if (view == swimmingMediaView) swimmingMediaPlayer = player;

        } catch (Exception e) {
            System.err.println("Could not load video: " + e.getMessage());
        }
    }

    public void startAudio(String path) {
        if(mp == null)
        {
            try {
                Media media = new Media(getClass().getResource(path).toExternalForm());
                mp = new MediaPlayer(media);
                mp.setCycleCount(MediaPlayer.INDEFINITE);
            }catch (Exception e) {
                System.err.println("Could not load video: " + e.getMessage());
            }
        }

    }

    public void cleanup() {
        if (walkingMediaPlayer != null) {
            walkingMediaPlayer.stop();
            walkingMediaPlayer.dispose();
            walkingMediaPlayer = null;
        }

        if (runningMediaPlayer != null) {
            runningMediaPlayer.stop();
            runningMediaPlayer.dispose();
            runningMediaPlayer = null;
        }

        if (swimmingMediaPlayer != null) {
            swimmingMediaPlayer.stop();
            swimmingMediaPlayer.dispose();
            swimmingMediaPlayer = null;
        }
    }


    // Creates a Timeline that fires every second and runs the given tick action.
    private Timeline buildTimeline(Runnable onTick) {
        Timeline tl = new Timeline(new KeyFrame(Duration.seconds(1), e -> onTick.run()));
        tl.setCycleCount(Animation.INDEFINITE);
        return tl;
    }

    // Converts total seconds → "MM:SS" and pushes it to the label.
    private void updateLabel(Label label, int totalSeconds) {
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;
        label.setText(String.format("%02d:%02d", minutes, seconds));
    }

    //  Walking controls:

    @FXML
    public void startWalking(ActionEvent event) {
        if(mp !=null){
            mp.play();
        }
        walkingTimeline.play();

        walkingStartButton.setDisable(true);
        walkingStopButton.setDisable(false);
    }

    @FXML
    public Label walkingCaloriesLabel;

    @FXML
    public void stopWalking(ActionEvent event) {
        if(mp !=null) {
            mp.pause();
        }
        walkingTimeline.stop();
        int sessionSeconds = walkingSeconds;

        double calories = calculateCalories("walking", sessionSeconds);
        JsonLoader.updateSteps(Session.username,walkingSteps);
        walkingCaloriesLabel.setText(String.format("Calories: %.1f cal", calories));

        // Reset everything
        walkingSeconds = 0;
        walkingSteps = 0;                    // ← reset steps
        walkingTimerLabel.setText("00:00");
       // stepsLabel.setText("Steps: 0");      // ← reset display
        walkingStartButton.setDisable(false);
        walkingStopButton.setDisable(true);
    }

    //  Running controls:

    @FXML
    public void startRunning(ActionEvent event) {
        if(mp != null){
            mp.play();
        }
        runningTimeline.play();
        runningStartButton.setDisable(true);
        runningStopButton.setDisable(false);
    }

    @FXML
    public Label runningCaloriesLabel;

    @FXML
    public void stopRunning(ActionEvent event) {
        if(mp != null){
            mp.pause();
        }
        runningTimeline.stop();
        int sessionSeconds = runningSeconds; // SAVE IT FIRST
        double calories = calculateCalories("running", sessionSeconds);
        JsonLoader.updateCalories(Session.username,calories);
        runningCaloriesLabel.setText(String.format("Calories Burned: %.1f cal", calories));
        runningSeconds = 0;           // WIPING FOR NEXT WORKOUT
        runningTimerLabel.setText("00:00");
        runningStartButton.setDisable(false);
        runningStopButton.setDisable(true);
    }


    // Swimming controls:

    @FXML
    public void startSwimming(ActionEvent event) {
        if(mp != null) {
            mp.play();
        }
        swimmingTimeline.play();
        swimmingStartButton.setDisable(true);
        swimmingStopButton.setDisable(false);
    }

    @FXML
    public Label swimmingCaloriesLabel;

    @FXML
    public void stopSwimming(ActionEvent event) {
        if(mp !=null) {
            mp.pause();
        }
        swimmingTimeline.stop();
        int sessionSeconds = swimmingSeconds; // SAVE IT FIRST

        double calories = calculateCalories("swimming", sessionSeconds);
        swimmingCaloriesLabel.setText(String.format("Calories Burned: %.1f cal", calories));
        JsonLoader.updateCalories(Session.username,calories);
        swimmingSeconds = 0;      // WIPING FOR NEXT WORKOUT
        swimmingTimerLabel.setText("00:00");
        swimmingStartButton.setDisable(false);
        swimmingStopButton.setDisable(true);
    }

    /* ==============================Calorie Calculation====================================
       Calories = MET × weight(kg) × time(hours)
       MET values: Walking = 3.5 | Running = 9.8 | Swimming = 8.0 */
    private double calculateCalories(String activity, int seconds) {
        double weightKg = 70.0; // FOR TESTING
        double hours = seconds / 3600.0; // convert seconds → hours

        double met;
        switch (activity) {
            case "walking":  met = 3.5; break;
            case "running":  met = 9.8; break;
            case "swimming": met = 8.0; break;
            default:         met = 1.0;
        }

        return Math.round(met * weightKg * hours * 10.0) / 10.0;
    }

    //  Navigation :

    public void backToCardio(ActionEvent actionEvent) throws IOException {
        cleanup();
        mp.stop();
        mp.dispose();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Cardio.fxml"));
        Parent root = loader.load();
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        ThemeManager.apply(scene);

        stage.setScene(scene);
        stage.show();
    }

}
