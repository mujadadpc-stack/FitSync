package com.example.javafx;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MusicManager {
    private static MediaPlayer mp;

    public static void playMusic(String path)
    {
        if(mp!=null)
        {
            mp.pause();
        }
        Media media = new Media(MusicManager.class.getResource(path).toExternalForm());
        mp = new MediaPlayer(media);
        mp.setCycleCount(MediaPlayer.INDEFINITE);
        mp.play();
    }
    public static void stopMusic()
    {
        if(mp != null)
        {
            mp.pause();
        }
    }
}
