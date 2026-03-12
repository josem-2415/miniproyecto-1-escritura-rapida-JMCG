package com.example.escriturarapida.model;

public interface IWord {
    String generateWord();
    Boolean validateWord();

    Boolean validateWord(String word);
}
