package com.example.escriturarapida.model;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class Words implements IWord {

    private String[] words;
    private String currentWord;
    private final Random random = new Random();

    private int nivel = 1;

    public Words(int nivel){
        this.nivel = nivel;
        loadWords();
    }

    private void loadWords(){

        ArrayList<String> list = new ArrayList<>();

        try{
            String path = "/com/example/escriturarapida/words/nivel_" + nivel + ".txt";

            InputStream is = getClass().getResourceAsStream(path);
            assert is != null;

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

    public void setNivel(int nivel){
        this.nivel = nivel;
        loadWords();
    }

    @Override
    public String generateWord(){
        int index = random.nextInt(words.length);
        currentWord = words[index];
        return currentWord;
    }

    @Override
    public Boolean validateWord(String word){
        return word.trim().equalsIgnoreCase(currentWord);
    }
}