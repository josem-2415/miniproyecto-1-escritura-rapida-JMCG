package com.example.escriturarapida.controller;

import com.example.escriturarapida.model.GameState;
import com.example.escriturarapida.view.GameStage;
import com.example.escriturarapida.view.MenuStage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class FinalController {

    @FXML private Button btnMainMenu;
    @FXML private Button btnPlayAgain;
    @FXML private Label streakLabel;

    @FXML
    private void goToMenu() {
        try {
            Stage stage = (Stage) btnMainMenu.getScene().getWindow();

            new MenuStage(stage); // 👈 cargas el menú

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void restartGame() {
        try {
            Stage stage = (Stage) btnPlayAgain.getScene().getWindow();

            new GameStage(stage); // 👈 reinicias el juego

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setResults(GameState gameState){
        int streak = gameState.getCurrentStreak();
        streakLabel.setText(String.valueOf(streak));
    }
}

