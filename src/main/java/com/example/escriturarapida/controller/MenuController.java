package com.example.escriturarapida.controller;

import com.example.escriturarapida.view.GameStage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MenuController {

    @FXML private Button btnPlay;
    @FXML private Button btnExit;

    @FXML
    private void exitGame() {
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void initialize(){

        btnPlay.setOnAction(e -> {

            Stage stage = (Stage) btnPlay.getScene().getWindow();

            try {
                new GameStage(stage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        });
    }
}
