package com.example.escriturarapida.controller;

import com.example.escriturarapida.model.GameState;
import com.example.escriturarapida.model.TimerModel;
import com.example.escriturarapida.view.FinalStage;
import com.example.escriturarapida.view.MessageView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.shape.Arc;
import javafx.stage.Stage;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.net.URL;



public class GameController {

    @FXML
    private Label wordLabel;

    @FXML
    private Label levelLabel;

    @FXML
    private TextField wordTextField;

    @FXML
    private Arc timeArc;

    @FXML
    private Label timeLabel;

    @FXML
    private Label streakValueLabel;

    // Media object
    private static final String RUTA_AUDIO =
            "/com/example/escriturarapida/media/DonOmarDile.mp3";

    // Construction of objects
    private WordController wordController;
    private TimerModel timerModel;
    private LevelController levelController;
    private MessageView messageView;
    private GameState gameState;


    private void mediaConfig() {
        try {
            URL urlAudio = getClass().getResource(RUTA_AUDIO);
            if (urlAudio != null) {
                Media mediaAudio = new Media(urlAudio.toExternalForm());
                MediaPlayer reproducerAudio = new MediaPlayer(mediaAudio);
                reproducerAudio.setCycleCount(MediaPlayer.INDEFINITE);
                reproducerAudio.setVolume(0.5);
                reproducerAudio.play();
            }
        } catch (Exception e) {
            System.out.println("Archivo de audio no encontrado. Continuando sin música.");
        }
    }

    @FXML
    public void initialize(){

        gameState = new GameState();

        wordController = new WordController();
        timerModel = new TimerModel(timeLabel, timeArc);
        levelController = new LevelController(gameState);

        wordTextField.requestFocus();
        mediaConfig();

        // Primera palabra
        wordLabel.setText(wordController.newWord());

        // Iniciar temporizador
        timerModel.startTimer();

        // ENTER valida palabra
        wordTextField.setOnAction(this::onHandlevalidate);

        timerModel.setOnTimeEnd(this::endGame);
    }

    private void endGame(){

        timerModel.stop();

        Stage stage = (Stage) wordLabel.getScene().getWindow();

        try {
            new FinalStage(stage, gameState);
        } catch (Exception e) {
            e.printStackTrace();
        }
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

        messageView.showCorrectMessage();

        gameState.addCorrectWord();

        checkLevelUp();

        updateStats();

        wordLabel.setText(wordController.newWord());

        timerModel.reset();
    }

    private void handleIncorrectWord(){

        messageView.showIncorrectMessage();

        updateStats();

        endGame();
    }


    @FXML
    private void checkLevelUp(){
        if(levelController.checkLevelUp()){

            levelLabel.setText("Nivel " + gameState.getLevel());

            timerModel.stop();

            timerModel.decreaseTime(2); // reduce 2 segundos

            messageView.showLevelUpMessage();
        }
    }

    private void updateStats(){

        int streak = gameState.getCurrentStreak();

        streakValueLabel.setText(String.valueOf(streak));
    }

    private void clearInput(){
        wordTextField.clear();
    }
}



