package com.nonsense.analyzer;
import com.nonsense.model.Word;
import java.util.Arrays; // Aggiungi questa linea
import java.util.List;

public class SimpleAnalyzer {
    public List<Word> analyze(String sentence) {
        return Arrays.stream(sentence.split(" ")) // Ora Arrays è riconosciuto
                    .map(word -> new Word(word, "NOUN"))
                    .toList();
    }
}