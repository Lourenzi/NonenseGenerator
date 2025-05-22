package com.nonsense.generator;
import com.nonsense.model.Word;
import java.util.List;
import java.util.Random;

public class TemplateEngine {
    private static final String[] TEMPLATES = {
        "The [NOUN] [VERB] the [ADJ] [NOUN]",
        "Why did the [NOUN] [ADJ] [VERB]?"
    };

    public String generate(List<Word> words) {
        Random rand = new Random();
        String template = TEMPLATES[rand.nextInt(TEMPLATES.length)];
        
        for (Word w : words) {
            template = template.replaceFirst("\\[" + w.pos() + "\\]", w.text());
        }
        return template;
    }
}