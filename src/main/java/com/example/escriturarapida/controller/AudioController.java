package com.example.escriturarapida.controller;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.net.URL;

/**
 * Controller responsible for playing background audio in the application.
 * Uses JavaFX MediaPlayer to manage audio playback.
 */
public class AudioController {

    /** Audio player instance */
    private MediaPlayer player;

    /** Path to the audio file */
    private static final String RUTA_AUDIO =
            "/com/example/escriturarapida/media/DonOmarDile.mp3";

    /**
     * Plays the background audio in an infinite loop.
     * If an error occurs (e.g., file not found),
     * a message is printed to the console.
     */
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
}
