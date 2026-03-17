package com.example.escriturarapida.controller;

import com.example.escriturarapida.model.GameState;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class FinalController {

    @FXML
    private Label wpmLabel;

    @FXML
    private Label accuracyLabel;

    @FXML
    private Label streakLabel;

    public void setResults(GameState gameState){

        int streak = gameState.getCurrentStreak();
        streakLabel.setText(String.valueOf(streak));
    }
}

