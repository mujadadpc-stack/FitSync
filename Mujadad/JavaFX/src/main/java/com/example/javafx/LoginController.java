package com.example.javafx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;

import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.ArrayList;


public class LoginController {
    @FXML private StackPane rootPane;
    @FXML private Label title;
    @FXML private Label usernameLabel;
    @FXML private Label passwordLabel;
    @FXML private Label status;
    @FXML private Pane overlay;
    @FXML
    private HBox usernameRow;
    @FXML private HBox passwordRow;
    @FXML private HBox loginorSignup;

    @FXML private TextField usernameInput;
    @FXML private TextField passwordInput;
    @FXML
    private ImageView bgImage;
    @FXML private CheckBox rememberMe;

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
    }


    @FXML private Button login;
    @FXML private Button newAccount;
    private ArrayList<User> users;
    public void onLogin(ActionEvent event) throws IOException
    {
        String username = usernameInput.getText();
        String password = passwordInput.getText();
        users = JsonLoader.loadUsers();
        for(User u : users) {
            if (u.getUsername().equals(username)) {
                if (u.getPassword().equals(password)) {
                    Session.username = username;
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/hello-view.fxml"));
                        Parent root = loader.load();
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        Scene scene = new Scene(root);
                        scene.getStylesheets().add(getClass().getResource("/css/hello-view.css").toExternalForm());
                        stage.setScene(scene);
                        stage.show();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
                status.setText("Invalid Username or Password!");
                status.setStyle("-fx-text-fill: red;");
        }


    }
    public void onSignup(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/signup.fxml"));
        Parent root = loader.load();
        Signup controller = loader.getController();
        controller.setUsername(usernameInput.getText());
        controller.setPassword(passwordInput.getText());
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
