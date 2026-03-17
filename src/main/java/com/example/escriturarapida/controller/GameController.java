package com.example.escriturarapida.controller;

import com.example.escriturarapida.model.GameState;
import com.example.escriturarapida.model.TimerModel;
import com.example.escriturarapida.view.FinalStage;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameController {

    @FXML private Label wordLabel;
    @FXML private Label levelLabel;
    @FXML private TextField wordTextField;
    @FXML private Arc timeArc;
    @FXML private Label timeLabel;
    @FXML private Label streakValueLabel;
    @FXML private Label messageLabel;
    @FXML private Label messageLabel1;
    @FXML private Rectangle levelUpBox;
    @FXML private Label levelUpLabel;

    private GameState gameState;
    private TimerModel timerModel;

    private WordController wordController;
    private LevelController levelController;

    @FXML
    private AudioController audioController;
    private UIController uiController;

    @FXML
    public void initialize(){

        // MODELO
        gameState = new GameState();
        timerModel = new TimerModel(20);

        // 🔥 CONTROLADORES
        wordController = new WordController(gameState.getLevel());
        levelController = new LevelController(gameState);
        audioController = new AudioController();
        uiController = new UIController(levelLabel, streakValueLabel);

        // 🔊 audio
        audioController.playBackground();

        // ⏱ timer UI update
        timerModel.setOnTick(() -> {
            timeLabel.setText(String.valueOf((int)Math.ceil(timerModel.getTime())));
            timeArc.setLength((360 * timerModel.getTime()) / timerModel.getMaxTime());
        });

        timerModel.setOnTimeEnd(this::endGame);

        // 🎮 iniciar juego
        wordLabel.setText(wordController.newWord());
        timerModel.startTimer();

        wordTextField.setOnAction(this::onHandlevalidate);
        wordTextField.requestFocus();
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

        String text = wordTextField.getText().trim();
        if(text.isEmpty()) return;

        if(wordController.validate(text)){
            handleCorrect();
        } else {
            handleIncorrect();
        }

        wordTextField.clear();
    }

    private void handleCorrect(){

        showCorrectMessage();

        gameState.addCorrectWord();

        if(levelController.checkLevelUp()){

            uiController.updateLevel(gameState.getLevel());

            wordController.updateLevel(gameState.getLevel());

            timerModel.stop();
            timerModel.decreaseTime(2);

            showLevelUpMessage(() -> {
                timerModel.reset();
                timerModel.startTimer();
                wordLabel.setText(wordController.newWord());
            });

        } else {

            wordLabel.setText(wordController.newWord());
            timerModel.reset();
        }

        uiController.updateStreak(gameState.getCurrentStreak());
    }

    private void handleIncorrect(){

        showIncorrectMessage();
        uiController.updateStreak(gameState.getCurrentStreak());

        endGame();
    }

    public void showCorrectMessage(){

        messageLabel.setOpacity(1);
        messageLabel.setVisible(true);

        FadeTransition fade = new FadeTransition(Duration.seconds(1.2), messageLabel);
        fade.setFromValue(1);
        fade.setToValue(0);

        fade.setOnFinished(e -> messageLabel.setVisible(false));

        fade.play();
    }

    public void showIncorrectMessage(){

        messageLabel1.setOpacity(1);
        messageLabel1.setVisible(true);

        FadeTransition fade = new FadeTransition(Duration.seconds(1.2), messageLabel1);
        fade.setFromValue(1);
        fade.setToValue(0);

        fade.setOnFinished(e -> messageLabel1.setVisible(false));

        fade.play();
    }

    public void showLevelUpMessage(Runnable onFinished){
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

        PauseTransition pause = new PauseTransition(Duration.seconds(1.5));

        FadeTransition fadeOutBox = new FadeTransition(Duration.seconds(0.8), levelUpBox);
        fadeOutBox.setFromValue(0.7);
        fadeOutBox.setToValue(0);

        FadeTransition fadeOutText = new FadeTransition(Duration.seconds(0.8), levelUpLabel);
        fadeOutText.setFromValue(1);
        fadeOutText.setToValue(0);

        fadeOutBox.setOnFinished(e -> {

            levelUpBox.setVisible(false);
            levelUpLabel.setVisible(false);

            // aquí solo ejecuta lo que le manden
            if(onFinished != null){
                onFinished.run();
            }
        });

        SequentialTransition sequence = new SequentialTransition(
                new ParallelTransition(fadeInBox, fadeInText),
                pause,
                new ParallelTransition(fadeOutBox, fadeOutText)
        );

        sequence.play();
    }
}