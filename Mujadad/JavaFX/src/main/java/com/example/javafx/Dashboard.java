package com.example.javafx;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.ScaleTransition;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.input.MouseEvent;
import java.io.IOException;

public class Dashboard {
    @FXML
    private BorderPane rootPane;
    @FXML
    private TilePane tilePane;
    @FXML
    private VBox vbox;
    @FXML
    private ImageView snkPic;
    @FXML
    private Button dashboardButton;
    @FXML
    private Button progressButton;
    @FXML
    private Button caloriesButton;
    @FXML
    private Button logoutButton;
    @FXML
    private VBox cardioTile;
    @FXML
    private VBox calisthenicsTile;
    @FXML
    private VBox weightsTile;
    @FXML
    private VBox yogaTile;
    @FXML
    private ImageView cardio;
    @FXML
    private ImageView calisthenics;
    @FXML
    private ImageView weights;
    @FXML
    private ImageView yoga;
    @FXML
    private Button cardioButton;
    @FXML
    private Button calisthenicsButton;
    @FXML
    private Button weightsButton;
    @FXML
    private Button yogaButton;
    @FXML
    private Label cardioLabel;
    @FXML
    private Label calisthenicsLabel;
    @FXML
    private Label weightsLabel;
    @FXML
    private Label yogaLabel;
    @FXML
    private Label welcomeLabel;
    @FXML
    private String username;
    @FXML
    private Parent root;
    @FXML
    private PieChart caloriesBurned;

    @FXML
    public void onCardio(MouseEvent e){
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Cardio.fxml"));
            root = loader.load();
            rootPane.setCenter(root);
        } catch (Exception ev) {
            ev.printStackTrace();
        }
    }
    @FXML
    public void onWeights(MouseEvent e) {
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Weights.fxml"));
            root = loader.load();
            rootPane.setCenter(root);

        } catch (Exception ev) {
            ev.printStackTrace();
        }    }
    @FXML
    public void onYoga(MouseEvent e) {
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Yoga.fxml"));
            root = loader.load();
            rootPane.setCenter(root);

        } catch (Exception ev) {
            ev.printStackTrace();
        }    }
    @FXML
    public void onCalisthenics(MouseEvent e) {
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Calisthenics.fxml"));
            root = loader.load();
            rootPane.setCenter(root);

        } catch (Exception ev) {
            ev.printStackTrace();
        }    }

    @FXML
    private Button themeButton;
    @FXML
    private void onToggleTheme() {
        ThemeManager.Theme next = ThemeManager.toggle();
        themeButton.setText(next == ThemeManager.Theme.LIGHT ? "☀ Light" : "🌙 Dark");
        ThemeManager.apply(themeButton.getScene());
    }

    @FXML
    public void initialize() {
        user.setText(Session.username);
        welcomeLabel.setText("Welcome, " + Session.username);
        MusicManager.playMusic("/music/gym.mp3");

        snkPic.setImage(
                new Image(
                        getClass().getResource("/images/jutt.jpeg").toExternalForm()
                )
        );

        snkPic.setFitHeight(100);
        snkPic.setFitWidth(100);
        cardio.setImage(
                new Image(
                        getClass().getResource("/images/cardio.png").toExternalForm()
                )
        );
        weights.setImage(
                new Image(
                        getClass().getResource("/images/weights.png").toExternalForm()
                )
        );
        calisthenics.setImage(
                new Image(
                        getClass().getResource("/images/calisthenics.png").toExternalForm()
                )
        );
        yoga.setImage(
                new Image(
                        getClass().getResource("/images/yoga.png").toExternalForm()
                )
        );
        addCardEffects(cardioTile);
        addCardEffects(weightsTile);
        addCardEffects(calisthenicsTile);
        addCardEffects(yogaTile);
        Circle clip = new Circle(50, 50, 50);
        snkPic.setClip(clip);

        ThemeManager.Theme t = ThemeManager.getCurrent();
        themeButton.setText(t == ThemeManager.Theme.LIGHT ? "☀ Light" : "🌙 Dark");
    }

    @FXML
    public void onDashboard() throws IOException {
        rootPane.setCenter(tilePane);
    }

    @FXML
    public void addCardEffects(VBox card) {
        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.web("#00BFFF"));
        shadow.setRadius(15);
        card.setOnMouseEntered(e -> {
            card.setEffect(shadow);

            ScaleTransition st = new ScaleTransition(Duration.millis(150), card);
            st.setToX(1.05);
            st.setToY(1.05);
            st.play();
        });
        card.setOnMouseExited(e -> {
            card.setEffect(null);

            ScaleTransition st = new ScaleTransition(Duration.millis(150), card);
            st.setToX(1.0);
            st.setToY(1.0);
            st.play();
        });
        card.setOnMousePressed(e -> {
            ScaleTransition st = new ScaleTransition(Duration.millis(80), card);
            st.setToX(0.97);
            st.setToY(0.97);
            st.play();
        });
        card.setOnMouseReleased(e -> {
            ScaleTransition st = new ScaleTransition(Duration.millis(80), card);
            st.setToX(1.05);
            st.setToY(1.05);
            st.play();
        });
    }
    @FXML
    public void onLogout(ActionEvent e)
    {
        try {
            MusicManager.stopMusic();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/css/hello-view.css").toExternalForm());
            stage.setScene(scene);
            stage.show();

        } catch (IOException ev) {
            ev.printStackTrace();
        }
    }
    @FXML
    Label user;

    @FXML
    public void onProgress(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Progress.fxml"));
        Parent root = loader.load();
        rootPane.setCenter(root);
    }
    @FXML
    public void onBMI(ActionEvent e) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/BMICalculator.fxml"));
        Parent root = loader.load();
        rootPane.setCenter(root);
    }
    @FXML
    public void onResetCalories(ActionEvent e) throws IOException
    {
        JsonLoader.resetProgress(Session.username);

    }
}
