package com.example.escriturarapida.model;

public class GameState {

    private int level = 1;
    private int correctWords = 0;
    private int wordsToLevelUp = 5;

    public int getLevel() {
        return level;
    }

    public int getCorrectWords() {
        return correctWords;
    }

    public void addCorrectWord() {
        correctWords++;
    }

    public void resetCorrectWords() {
        correctWords = 0;
    }

    public int getWordsToLevelUp() {
        return wordsToLevelUp;
    }

    public void levelUp(){
        level++
        ;
    }
}
