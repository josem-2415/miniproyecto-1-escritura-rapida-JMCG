package com.example.escriturarapida.view;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import com.example.escriturarapida.model.TimerModel;


public class MessageView {

    @FXML
    private Label messageLabel;

    @FXML
    private Label messageLabel1;

    @FXML
    private Rectangle levelUpBox;

    @FXML
    private Label levelUpLabel;

    private TimerModel timerModel;

    public void showCorrectMessage(){

        messageLabel.setOpacity(1);
        messageLabel.setVisible(true);

        FadeTransition fade = new FadeTransition(Duration.seconds(2), messageLabel);
        fade.setFromValue(1);
        fade.setToValue(0);

        fade.setOnFinished(e -> messageLabel.setVisible(false));

        fade.play();
    }

    public void showIncorrectMessage(){

        messageLabel1.setOpacity(1);
        messageLabel1.setVisible(true);

        FadeTransition fade = new FadeTransition(Duration.seconds(2), messageLabel1);
        fade.setFromValue(1);
        fade.setToValue(0);

        fade.setOnFinished(e -> messageLabel1.setVisible(false));

        fade.play();
    }

    public void showLevelUpMessage(){

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
            timerModel.reset();
            timerModel.startTimer();
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