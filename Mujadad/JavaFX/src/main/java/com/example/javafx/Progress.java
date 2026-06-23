package com.example.javafx;

import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;

public class Progress {

    @FXML
    private PieChart caloriesBurned;
    @FXML
    private PieChart stepsTaken;
    private String username;
    @FXML
    public void initialize()
    {
        loadCharts();
    }
    @FXML
    public void loadCharts()
    {
        double calories = JsonLoader.findUser(Session.username).getCalories();
        if(calories>=1000)
            calories = 1000;
        int steps = JsonLoader.findUser(Session.username).getStepsTaken();
        if(steps>=10000)
            steps = 10000;
        caloriesBurned.getData().addAll(
                new PieChart.Data("Burned", calories),
                new PieChart.Data("Remaining", 1000 - calories)
        );
        stepsTaken.getData().addAll(
                new PieChart.Data("Steps Taken",steps),
                new PieChart.Data("Steps Left", 10000 - steps)
        );
    }

}