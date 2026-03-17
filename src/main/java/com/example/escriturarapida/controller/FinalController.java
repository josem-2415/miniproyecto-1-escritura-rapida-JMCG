package com.example.escriturarapida.controller;

import com.example.escriturarapida.model.GameModel;
import com.example.escriturarapida.view.GameStage;
import com.example.escriturarapida.view.MenuStage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Controller for the final game view.
 * Handles result display and navigation.
 */
public class FinalController {

    /** Button to return to the main menu */
    @FXML private Button btnMainMenu;

    /** Button to restart the game */
    @FXML private Button btnPlayAgain;

    /** Label that displays the player's streak */
    @FXML private Label streakLabel;

    /**
     * Navigates back to the main menu.
     */
    @FXML
    private void goToMenu() {
        try {
            Stage stage = (Stage) btnMainMenu.getScene().getWindow();

            new MenuStage(stage); // LOAD MENU

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Restarts the game from the beginning.
     */
    @FXML
    private void restartGame() {
        try {
            Stage stage = (Stage) btnPlayAgain.getScene().getWindow();

            new GameStage(stage); //RESTART GAME

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Displays the final results of the player.
     *
     * @param gameState current game state
     */
    public void setResults(GameModel gameState){
        int streak = gameState.getCurrentStreak();
        streakLabel.setText(String.valueOf(streak));
    }
}

