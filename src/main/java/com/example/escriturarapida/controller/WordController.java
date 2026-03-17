package com.example.escriturarapida.controller;

import com.example.escriturarapida.model.Words;

public class WordController {

    private final Words words;

    public WordController(int nivel){
        words = new Words(nivel);
    }

    public String newWord(){
        return words.generateWord();
    }

    public boolean validate(String word){
        return words.validateWord(word);
    }

    public void updateLevel(int nivel){
        words.setNivel(nivel);
    }
}