package com.example.escriturarapida.controller;

import com.example.escriturarapida.model.GameModel;
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

/**
 * Main controller of the game.
 * Manages the interaction between model, view, and helper controllers.
 */
public class GameController {

    /** Label showing the current word */
    @FXML private Label wordLabel;

    /** Label showing the level */
    @FXML private Label levelLabel;

    /** User input field */
    @FXML private TextField wordTextField;

    /** Visual arc representing time */
    @FXML private Arc timeArc;

    /** Time label */
    @FXML private Label timeLabel;

    /** Streak label */
    @FXML private Label streakValueLabel;

    /** Feedback messages */
    @FXML private Label messageLabel;
    @FXML private Label messageLabel1;

    /** Level-up UI elements */
    @FXML private Rectangle levelUpBox;
    @FXML private Label levelUpLabel;


    /** Model holding the game state. */
    private GameModel gameModel;

    /** Timer model managing countdowns. */
    private TimerModel timerModel;

    /** Controller managing word logic. */
    private WordController wordController;

    /** Controller managing level progression. */
    private LevelController levelController;

    /** Controller for updating UI elements. */
    private UIController uiController;

    /**
     * Initializes the game by setting up models, controllers, and events.
     */
    @FXML
    public void initialize(){

        // MODEL
        gameModel = new GameModel();
        timerModel = new TimerModel(20);

        // CONTROLLERS
        wordController = new WordController(gameModel.getLevel());
        levelController = new LevelController(gameModel);
        AudioController audioController = new AudioController();
        uiController = new UIController(levelLabel, streakValueLabel);

        // MEDIA
        audioController.playBackground();

        // TIMER UI UPDATE
        timerModel.setOnTick(() -> {
            timeLabel.setText(String.valueOf((int)Math.ceil(timerModel.getTime())));
            timeArc.setLength((360 * timerModel.getTime()) / timerModel.getMaxTime());
        });

        timerModel.setOnTimeEnd(this::endGame);

        // START GAME
        wordLabel.setText(wordController.newWord());
        timerModel.startTimer();

        wordTextField.setOnAction(this::onHandlevalidate);
        wordTextField.requestFocus();
    }

    /**
     * Ends the game and switches to the final view.
     */
    private void endGame(){

        timerModel.stop();

        Stage stage = (Stage) wordLabel.getScene().getWindow();

        try {
            new FinalStage(stage, gameModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles validation of the user input.
     *
     * @param event action event
     */
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

    /**
     * Logic executed when the user types a correct word.
     */
    private void handleCorrect(){

        showCorrectMessage();

        gameModel.addCorrectWord();

        if(levelController.checkLevelUp()){

            uiController.updateLevel(gameModel.getLevel());

            wordController.updateLevel(gameModel.getLevel());

            timerModel.stop();
            timerModel.decreaseTime(2);

            showLevelUpMessage(() -> {

                // CAMBIO DE NIVEL + NUEVA PALABRA
                String newWord = wordController.changeLevelAndGetWord(gameModel.getLevel());
                wordLabel.setText(newWord);

                timerModel.reset();
                timerModel.startTimer();
            });

        } else {

            wordLabel.setText(wordController.newWord());
            timerModel.reset();
        }

        uiController.updateStreak(gameModel.getCurrentStreak());
    }

    /**
     * Logic executed when the user types an incorrect word.
     */
    private void handleIncorrect(){

        showIncorrectMessage();
        uiController.updateStreak(gameModel.getCurrentStreak());

        endGame();
    }

    /**
     * Displays a correct answer animation.
     */
    public void showCorrectMessage(){

        messageLabel.setOpacity(1);
        messageLabel.setVisible(true);

        FadeTransition fade = new FadeTransition(Duration.seconds(1.2), messageLabel);
        fade.setFromValue(1);
        fade.setToValue(0);

        fade.setOnFinished(e -> messageLabel.setVisible(false));

        fade.play();
    }

    /**
     * Displays an incorrect answer animation.
     */
    public void showIncorrectMessage(){

        messageLabel1.setOpacity(1);
        messageLabel1.setVisible(true);

        FadeTransition fade = new FadeTransition(Duration.seconds(1.2), messageLabel1);
        fade.setFromValue(1);
        fade.setToValue(0);

        fade.setOnFinished(e -> messageLabel1.setVisible(false));

        fade.play();
    }

    /**
     * Displays a level-up animation.
     *
     * @param onFinished action executed after animation ends
     */
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