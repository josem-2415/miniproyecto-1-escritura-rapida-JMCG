package com.example.escriturarapida.model;

/**
 * Model representing the game state.
 */
public class GameModel implements IGame {

    /** Current player level. */
    private int level = 1;

    /** Number of correct words typed. */
    private int correctWords = 0;

    /** Maximum streak reached by the player. */
    private int Streak = 0;

    /** Current streak of correct words. */
    private int currentStreak = 0;


    /**
     * Returns the current level.
     */
    @Override
    public int getLevel() {
        return level;
    }

    /**
     * Returns the number of correct words.
     */
    @Override
    public int getCorrectWords() {
        return correctWords;
    }

    /**
     * Adds a correct word and updates streak.
     */
    @Override
    public void addCorrectWord() {

        correctWords++;

        currentStreak++;

        if (currentStreak > Streak) {
            Streak = currentStreak;
        }
    }

    /**
     * Resets correct words counter.
     */
    @Override
    public void resetCorrectWords() {
        correctWords = 0;
    }

    /**
     * Returns words required to level up.
     */
    @Override
    public int getWordsToLevelUp() {
        return 5;
    }

    /**
     * Increases the level.
     */
    @Override
    public void levelUp(){level++;    }

    /**
     * Returns current streak.
     */
    @Override
    public int getCurrentStreak(){
        return currentStreak;
    }
}
