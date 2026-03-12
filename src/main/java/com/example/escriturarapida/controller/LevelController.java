package com.example.escriturarapida.controller;

import com.example.escriturarapida.model.GameState;

public class LevelController {

    private final GameState gameState;

    public LevelController(GameState gameState){
        this.gameState = gameState;
    }

    public boolean checkLevelUp(){

        if(gameState.getCorrectWords() >= gameState.getWordsToLevelUp()){

            gameState.levelUp();
            gameState.resetCorrectWords();

            return true;
        }

        return false;
    }
}
