package com.example.javafx;

import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Signup {

    @FXML
    private StackPane rootPane;
    @FXML
    private Pane overlay;
    @FXML
    private ImageView bgImage;
    @FXML
    private Button backToLogin;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField ageField;
    @FXML
    private TextField weightField;
    @FXML
    private TextField heightField;
    @FXML
    private ChoiceBox<String> genderField;
    @FXML
    private Label statusLabel;

    @FXML
    public void initialize() {

        overlay.setStyle("-fx-background-color: rgba(0,0,0,0.4);");

        bgImage.setImage(
                new Image(getClass()
                        .getResource("/images/fitness.jpg")
                        .toExternalForm())
        );

        bgImage.fitWidthProperty().bind(rootPane.widthProperty());
        bgImage.fitHeightProperty().bind(rootPane.heightProperty());

        bgImage.setPreserveRatio(false);

        overlay.prefWidthProperty().bind(rootPane.widthProperty());
        overlay.prefHeightProperty().bind(rootPane.heightProperty());

        genderField.getItems().addAll("Male", "Female");
    }

    public void onAccount(ActionEvent e) {

        String username = usernameField.getText();
        String password = passwordField.getText();
        String gender = genderField.getValue();

        int age;
        int height;
        double weight;

        try {
            age = Integer.parseInt(ageField.getText());
            height = Integer.parseInt(heightField.getText());
            weight = Double.parseDouble(weightField.getText());
        } catch (NumberFormatException ex) {
            statusLabel.setText("Enter valid numeric values!");
            statusLabel.setStyle("-fx-text-fill: red");
            return;
        }

        if (username.isEmpty() || JsonLoader.usernameTaken(username)) {
            statusLabel.setText("Invalid or already taken username!");
            statusLabel.setStyle("-fx-text-fill: red");
            return;
        }

        if (password.length() < 5) {
            statusLabel.setText("Password must be at least 5 characters!");
            statusLabel.setStyle("-fx-text-fill: red");
            return;
        }

        if (age <= 10) {
            statusLabel.setText("Not allowed under age 10!");
            statusLabel.setStyle("-fx-text-fill: red");
            return;
        }

        if (gender == null || !(gender.equals("Male") || gender.equals("Female"))) {
            statusLabel.setText("Select valid gender!");
            statusLabel.setStyle("-fx-text-fill: red");
            return;
        }

        if (height <= 0) {
            statusLabel.setText("Invalid height!");
            statusLabel.setStyle("-fx-text-fill: red");
            return;
        }

        if (weight <= 20) {
            statusLabel.setText("Weight too low!");
            statusLabel.setStyle("-fx-text-fill: red");
            return;
        }
        User user = new User(username, password, age, gender, weight, height);

        ArrayList<User> users = JsonLoader.loadUsers();
        users.add(user);

        try (FileWriter writer = new FileWriter("user.json")) {

            Gson gson = new Gson();
            gson.toJson(users, writer);
            JsonLoader.saveUsers(users);

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public void setUsername(String username)
    {
        usernameField.setText(username);
    }
    public void setPassword(String password)
    {
        passwordField.setText(password);
    }
    public void onBackToLogin(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
