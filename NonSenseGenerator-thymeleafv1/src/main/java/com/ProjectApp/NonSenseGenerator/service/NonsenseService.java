package com.ProjectApp.NonSenseGenerator.service;

import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class NonsenseService {

    private final List<String> verbs = Arrays.asList("mangia", "dorme", "salta", "canta", "corre");
    private final List<String> adjectives = Arrays.asList("veloce", "grosso", "piccolo", "felice", "strano");

    public String generateNonsense(String input) {
        List<String> words = Arrays.asList(input.split("\\s*,\\s*"));
        Random rand = new Random();
        
        String[] templates = {
            "Il {0} {1} il {2} {3}",
            "Perché il {0} ha {1} un {2}?",
            "Quando il {0} {1}, il {2} {3}"
        };
        
        String template = templates[rand.nextInt(templates.length)];
        
        return template
            .replace("{0}", words.get(0))
            .replace("{1}", verbs.get(rand.nextInt(verbs.size())))
            .replace("{2}", adjectives.get(rand.nextInt(adjectives.size())))
            .replace("{3}", words.size() > 1 ? words.get(1) : "oggetto");
    }
}