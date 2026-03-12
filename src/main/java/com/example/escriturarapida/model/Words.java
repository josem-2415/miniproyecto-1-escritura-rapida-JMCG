package com.example.escriturarapida.model;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class Words implements IWord {

    private final String[] words;
    private String currentWord;
    private final Random random = new Random();

    public Words(){

        ArrayList<String> list = new ArrayList<>();

        try{

            InputStream is = getClass().getResourceAsStream("/com/example/escriturarapida/words/words.txt");
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

    @Override
    public String generateWord(){

        int index = random.nextInt(words.length);

        currentWord = words[index];

        return currentWord;
    }

    @Override
    public Boolean validateWord(String word){
        return word.equals(currentWord);
    }
}
