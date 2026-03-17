package com.example.escriturarapida.controller;

import com.example.escriturarapida.model.WordModel;

/**
 * Controller responsible for word management.
 */
public class WordController {

    /** Model that generates and validates words. */
    private final WordModel wordModel;

    /**
     * Constructor.
     *
     * @param level initial level
     */
    public WordController(int level){
        wordModel = new WordModel(level);
    }

    /**
     * Generates a new word.
     *
     * @return generated word
     */
    public String newWord(){
        return wordModel.generateWord();
    }

    /**
     * Validates a user-entered word.
     *
     * @param word user input
     * @return true if correct
     */
    public boolean validate(String word){
        return wordModel.validateWord(word);
    }

    /**
     * Updates the level.
     *
     * @param level new level
     */
    public void updateLevel(int level){
        wordModel.setLevel(level);
    }

    /**
     * Changes level and returns a new word.
     *
     * @param level new level
     * @return new word
     */
    public String changeLevelAndGetWord(int level){
        wordModel.setLevel(level);
        return wordModel.generateWord();
    }
}