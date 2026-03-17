package com.example.escriturarapida.model;

public interface IGameState {
    int getLevel();
    int getCorrectWords();
    void addCorrectWord();
    void resetCorrectWords();
    int getWordsToLevelUp();
    void levelUp();
    int getCurrentStreak();
}
