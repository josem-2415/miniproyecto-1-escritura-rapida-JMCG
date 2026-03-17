package com.example.escriturarapida.controller;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;

public class AudioController {

    private MediaPlayer player;

    private static final String RUTA_AUDIO =
            "/com/example/escriturarapida/media/DonOmarDile.mp3";

    public void playBackground(){

        try {
            URL url = getClass().getResource(RUTA_AUDIO);

            if(url != null){
                Media media = new Media(url.toExternalForm());
                player = new MediaPlayer(media);

                player.setCycleCount(MediaPlayer.INDEFINITE);
                player.setVolume(0.5);
                player.play();
            }

        } catch (Exception e){
            System.out.println("Audio no disponible");
        }
    }

    public void stop(){
        if(player != null){
            player.stop();
        }
    }
}
