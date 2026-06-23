package com.example.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class BMICalculator {
    @FXML
    private TextField weightField;
    @FXML
    private TextField heightField;
    @FXML
    private Button calculateButton;
    @FXML
    private Label currentBMI;
    @FXML
    private Label calculateBMI;
    @FXML
    private Label rangeBMI;
    private double weight;
    private double height;
    @FXML
    public void setLabels()
    {
        double bmi = JsonLoader.findUser(Session.username).calculateBMI();
        currentBMI.setText("BMI for " + Session.username + " is " + String.format("%.2f",bmi));
        rangeBMI.setText("Falling under the " + getRange(bmi) + " Range.");
    }
    @FXML
    public void onCalculate(ActionEvent e)
    {
        weight = Double.parseDouble(weightField.getText());
        height = Double.parseDouble(heightField.getText());
        double h_m = height/100.0;
        while((weight >=0 && weight <= 200) && (height>=62.8 && height<=251))
        {
            calculateBMI.setText("Your BMI is " + String.format("%.2f",weight/(h_m*h_m)));
            currentBMI.setText(null);
            rangeBMI.setText("You are falling under the " + getRange(weight/(h_m*h_m)) + " range.");
            return;
        }
        rangeBMI.setText("Please enter the correct details");
        rangeBMI.setStyle("-fx-text-fill: red");
    }
    @FXML
    public void initialize()
    {
        setLabels();
    }
    @FXML
    public String getRange(double bmi)
    {
        if(bmi<18.5)
        {
            rangeBMI.setStyle("-fx-text-fill: #85b7eb;");
            return "Underweight";
        }
        else if(bmi<24.9 && bmi>=18.5)
        {
            rangeBMI.setStyle("-fx-text-fill: #5dcaa5;");
            return "Healthy";
        }
        else if(bmi<29.9 && bmi>=24.9)
        {
            rangeBMI.setStyle("-fx-text-fill: #ef9f27;");
            return "Overweight";
        }
        else
        {
            rangeBMI.setStyle("-fx-text-fill: #e24b4a;");
            return "Obese";
        }
    }
}
