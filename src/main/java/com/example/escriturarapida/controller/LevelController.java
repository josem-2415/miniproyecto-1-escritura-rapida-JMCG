package com.example.escriturarapida.controller;

import com.example.escriturarapida.model.GameModel;

/**
 * Controller responsible for level progression logic.
 */
public class LevelController {

    /** Reference to the current game state. */
    private final GameModel gameState;

    /**
     * Constructor.
     *
     * @param gameState game model
     */
    public LevelController(GameModel gameState){
        this.gameState = gameState;
    }

    /**
     * Checks if the player meets the conditions to level up.
     *
     * @return true if level increases, false otherwise
     */
    public boolean checkLevelUp(){

        if(gameState.getCorrectWords() >= gameState.getWordsToLevelUp()){

            gameState.levelUp();
            gameState.resetCorrectWords();

            return true;
        }

        return false;
    }
}
