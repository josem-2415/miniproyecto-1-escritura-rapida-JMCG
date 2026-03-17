package com.example.escriturarapida.controller;

import javafx.scene.control.Label;

public class UIController {

    private Label levelLabel;
    private Label streakLabel;

    public UIController(Label levelLabel, Label streakLabel){
        this.levelLabel = levelLabel;
        this.streakLabel = streakLabel;
    }

    public void updateLevel(int level){
        levelLabel.setText("Nivel " + level);
    }

    public void updateStreak(int streak){
        streakLabel.setText(String.valueOf(streak));
    }
}
