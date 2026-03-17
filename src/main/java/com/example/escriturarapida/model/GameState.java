package com.example.escriturarapida.model;

public class GameState implements IGameState{

    private int level = 1;
    private int correctWords = 0;
    private int totalWords = 0;
    private int Streak = 0;
    private int currentStreak = 0;

    public int getLevel() {
        return level;
    }

    public int getCorrectWords() {
        return correctWords;
    }

    public void addCorrectWord(){

        correctWords++;
        totalWords++;

        currentStreak++;

        if(currentStreak > Streak){
            Streak = currentStreak;
        }
    }

    public void resetCorrectWords() {
        correctWords = 0;
    }

    public int getWordsToLevelUp() {
        return 5;
    }

    public void levelUp(){level++;    }

    public int getCurrentStreak(){
        return currentStreak;
    }
}
