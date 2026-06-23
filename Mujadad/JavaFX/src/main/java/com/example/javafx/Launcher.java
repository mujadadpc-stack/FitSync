package com.example.javafx;

import javafx.application.Application;

public class Launcher
{
    public static void main(String[] args) {
        Application.launch(HelloApplication.class,args);

    }
}


//@FXML
//private Button timerButton;
//@FXML
//private Label timerLabel;
//@FXML
//Timeline timeline;
//int seconds=0;
//boolean running = false;
//@FXML
//public void startTimer(ActionEvent e)
//{
//    if(running)
//        return;
//
//    timeline = new Timeline(
//            new KeyFrame(Duration.seconds(1), event -> {
//                seconds++;
//                running = true;
//                int hours = seconds/3600;
//                seconds %= 3600;
//                int minutes = seconds / 60;
//                seconds %= 60;
//                timerLabel.setText(String.format("%d:%d:%d",hours,minutes,seconds));
//            })
//
//    );
//    timeline.setCycleCount(Timeline.INDEFINITE);
//    timeline.play();
//}
//@FXML
//public void stopTimer(ActionEvent e)
//{
//    if(running)
//    {
//        timeline.stop();
//    }
//}
//public void resetTimer()
//{
//    seconds = 0;
//}
//public Button timerStop;