package com.example.escriturarapida.model;

/**
 * Interface representing the game state and logic.
 * Classes implementing this interface manage levels, correct words, and streaks.
 */
public interface IGame {

    /**
     * Returns the current level of the player.
     *
     * @return current level
     */
    int getLevel();

    /**
     * Returns the number of correct words typed by the player.
     *
     * @return number of correct words
     */
    int getCorrectWords();

    /**
     * Adds a correct word to the current count
     * and updates streaks accordingly.
     */
    void addCorrectWord();

    /**
     * Resets the counter of correct words.
     */
    void resetCorrectWords();

    /**
     * Returns the number of words required to level up.
     *
     * @return words needed to reach next level
     */
    int getWordsToLevelUp();

    /**
     * Increases the player's level by one.
     */
    void levelUp();

    /**
     * Returns the current streak of correct words.
     *
     * @return current streak
     */
    int getCurrentStreak();
}
