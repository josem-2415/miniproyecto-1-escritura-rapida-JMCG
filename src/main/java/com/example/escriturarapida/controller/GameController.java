package com.example.escriturarapida.controller;

import com.example.escriturarapida.model.GameState;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.net.URL;



public class GameController {

    @FXML
    private Label wordLabel;

    @FXML
    private Label levelLabel;

    @FXML
    private Label messageLabel;

    @FXML
    private Label messageLabel1;

    @FXML
    private TextField wordTextField;

    @FXML
    private Arc timeArc;

    @FXML
    private Label timeLabel;

    @FXML
    private Rectangle levelUpBox;

    @FXML
    private Label levelUpLabel;

    private MediaPlayer reproductorAudio;

    private static final String RUTA_AUDIO =
            "/com/example/escriturarapida/media/DonOmarDile.mp3";

    private WordController wordController;
    private TimerController timerController;
    private LevelController levelController;

    private GameState gameState;

    private void mediaConfig() {
        try {
            URL urlAudio = getClass().getResource(RUTA_AUDIO);
            if (urlAudio != null) {
                Media mediaAudio = new Media(urlAudio.toExternalForm());
                reproductorAudio = new MediaPlayer(mediaAudio);
                reproductorAudio.setCycleCount(MediaPlayer.INDEFINITE);
                reproductorAudio.setVolume(0.5);
                reproductorAudio.play();
            }
        } catch (Exception e) {
            System.out.println("Archivo de audio no encontrado. Continuando sin música.");
        }
    }

    @FXML
    public void initialize(){

        gameState = new GameState();

        wordController = new WordController();
        timerController = new TimerController(timeLabel, timeArc);
        levelController = new LevelController(gameState);

        wordTextField.requestFocus();
        mediaConfig();

        // Primera palabra
        wordLabel.setText(wordController.newWord());

        // Iniciar temporizador
        timerController.startTimer();

        // ENTER valida palabra
        wordTextField.setOnAction(this::onHandlevalidate);
    }

    @FXML
    public void onHandlevalidate(ActionEvent event){

        String text = getInputText();

        if(text.isEmpty()) return;

        if(wordController.validate(text)){
            handleCorrectWord();
        }else{
            handleIncorrectWord();
        }

        clearInput();
    }

    private String getInputText(){
        return wordTextField.getText();
    }

    private void handleCorrectWord(){

        showCorrectMessage();

        gameState.addCorrectWord();

        checkLevelUp();

        wordLabel.setText(wordController.newWord());

        timerController.reset();
    }

    private void handleIncorrectWord(){

        showIncorrectMessage();

        timerController.reset();
    }

    @FXML
    private void checkLevelUp(){

        if(levelController.checkLevelUp()){

            levelLabel.setText("Nivel " + gameState.getLevel());

            timerController.stop();

            showLevelUpMessage(); // aquí se mostrará la animación
        }
    }


    private void clearInput(){
        wordTextField.clear();
    }

    private void showCorrectMessage(){

        messageLabel.setOpacity(1);
        messageLabel.setVisible(true);

        FadeTransition fade = new FadeTransition(Duration.seconds(2), messageLabel);
        fade.setFromValue(1);
        fade.setToValue(0);

        fade.setOnFinished(e -> messageLabel.setVisible(false));

        fade.play();
    }

    private void showIncorrectMessage(){

        messageLabel1.setOpacity(1);
        messageLabel1.setVisible(true);

        FadeTransition fade = new FadeTransition(Duration.seconds(2), messageLabel1);
        fade.setFromValue(1);
        fade.setToValue(0);

        fade.setOnFinished(e -> messageLabel1.setVisible(false));

        fade.play();
    }

    private void showLevelUpMessage(){

        levelUpBox.setOpacity(0);
        levelUpBox.setVisible(true);

        levelUpLabel.setOpacity(0);
        levelUpLabel.setVisible(true);

        FadeTransition fadeInBox = new FadeTransition(Duration.seconds(0.5), levelUpBox);
        fadeInBox.setFromValue(0);
        fadeInBox.setToValue(0.7);

        FadeTransition fadeInText = new FadeTransition(Duration.seconds(0.5), levelUpLabel);
        fadeInText.setFromValue(0);
        fadeInText.setToValue(1);

        javafx.animation.PauseTransition pause =
                new javafx.animation.PauseTransition(Duration.seconds(1.5));

        FadeTransition fadeOutBox = new FadeTransition(Duration.seconds(0.8), levelUpBox);
        fadeOutBox.setFromValue(0.7);
        fadeOutBox.setToValue(0);

        FadeTransition fadeOutText = new FadeTransition(Duration.seconds(0.8), levelUpLabel);
        fadeOutText.setFromValue(1);
        fadeOutText.setToValue(0);

        fadeOutBox.setOnFinished(e -> {

            levelUpBox.setVisible(false);
            levelUpLabel.setVisible(false);

            // Aquí el juego vuelve a empezar
            timerController.reset();
            timerController.startTimer();
        });

        javafx.animation.SequentialTransition sequence =
                new javafx.animation.SequentialTransition(
                        new javafx.animation.ParallelTransition(fadeInBox, fadeInText),
                        pause,
                        new javafx.animation.ParallelTransition(fadeOutBox, fadeOutText)
                );

        sequence.play();
    }
}



