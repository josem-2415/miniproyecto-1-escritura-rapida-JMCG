package com.example.escriturarapida.model;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

/**
 * Model responsible for managing words by level.
 */
public class WordModel implements IWord {

    /** Array containing words for the current level. */
    private String[] words;

    /** Current word that the user has to type. */
    private String currentWord;

    /** Random generator used to pick words. */
    private final Random random = new Random();

    /** Current game level for word generation. */
    private int nivel;

    /**
     * Constructor.
     *
     * @param level initial level
     */
    public WordModel(int level){
        this.nivel = level;
        loadWords();
    }

    /**
     * Loads words from file based on level.
     */
    public void loadWords(){

        ArrayList<String> list = new ArrayList<>();

        try{
            String path = "/com/example/escriturarapida/words/nivel_" + nivel + ".txt";

            InputStream is = getClass().getResourceAsStream(path);
            if (is == null) {
                throw new RuntimeException("No se encontró el archivo del nivel: " + nivel);
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            String line;

            while((line = reader.readLine()) != null){
                list.add(line);
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        words = list.toArray(new String[0]);
    }

    /**
     * Sets the level.
     *
     * @param level new level
     */
    @Override
    public void setLevel(int level){
        this.nivel = level;
        loadWords();
    }

    /**
     * Generates a random word.
     *
     * @return generated word
     */
    @Override
    public String generateWord(){
        int index = random.nextInt(words.length);
        currentWord = words[index];
        return currentWord;
    }

    /**
     * Validates user input.
     *
     * @param word user word
     * @return true if matches
     */
    @Override
    public Boolean validateWord(String word){
        return word.trim().equalsIgnoreCase(currentWord);
    }
}