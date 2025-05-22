package com.nonsense;
import com.nonsense.analyzer.SimpleAnalyzer;
import com.nonsense.generator.TemplateEngine;
import com.nonsense.model.Word;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SimpleAnalyzer analyzer = new SimpleAnalyzer();
        TemplateEngine engine = new TemplateEngine();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Inserisci una frase:");
        String input = scanner.nextLine();
        
        List<Word> words = analyzer.analyze(input);
        String nonsense = engine.generate(words);
        
        System.out.println("Frase generata:\n" + nonsense);
    }
}