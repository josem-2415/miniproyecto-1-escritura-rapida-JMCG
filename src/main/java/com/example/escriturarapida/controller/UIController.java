package com.example.escriturarapida.controller;

import javafx.scene.control.Label;

/**
 * Controller responsible for updating UI elements.
 */
public class UIController {

    /** Label showing the current level. */
    private final Label levelLabel;

    /** Label showing the current streak. */
    private final Label streakLabel;

    /**
     * Constructor.
     *
     * @param levelLabel label displaying the level
     * @param streakLabel label displaying the streak
     */
    public UIController(Label levelLabel, Label streakLabel){
        this.levelLabel = levelLabel;
        this.streakLabel = streakLabel;
    }

    /**
     * Updates the level display.
     *
     * @param level current level
     */
    public void updateLevel(int level){
        levelLabel.setText("Nivel " + level);
    }

    /**
     * Updates the player's streak.
     *
     * @param streak current streak
     */
    public void updateStreak(int streak){
        streakLabel.setText(String.valueOf(streak));
    }
}
