package com.example.escriturarapida.model;

/**
 * Interface representing word management logic.
 * Classes implementing this interface generate, validate, and load words.
 */
public interface IWord {

    /**
     * Generates a new random word for the current level.
     *
     * @return generated word
     */
    String generateWord();

    /**
     * Validates if the given word matches the current word.
     *
     * @param word user input word
     * @return true if the word is correct, false otherwise
     */
    Boolean validateWord(String word);

    /**
     * Sets the current level and reloads words accordingly.
     *
     * @param level new level
     */
    void setLevel(int level);

    /**
     * Loads the words for the current level from the corresponding file.
     */
    void loadWords();
}
