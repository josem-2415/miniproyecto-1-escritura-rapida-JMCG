package com.example.escriturarapida.controller;

import com.example.escriturarapida.model.Words;

public class WordController {

    private Words words;

    public WordController(){
        words = new Words();
    }

    public String newWord(){
        return words.generateWord();
    }

    public boolean validate(String word){
        return words.validateWord(word);
    }
}
