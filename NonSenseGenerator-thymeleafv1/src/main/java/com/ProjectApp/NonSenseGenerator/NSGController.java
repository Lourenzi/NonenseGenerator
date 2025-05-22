package com.ProjectApp.NonSenseGenerator;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NSGController {

    // Constants for attribute names
    private static final String INPUT_PLACEHOLDER = "Input sentence";
    private static final String DEFAULT_OUTPUT = "Generated sentence will appear here";
    
    @GetMapping("/")
    public String showGeneratorForm(Model model) {
        // Initialize default model attributes
        model.addAttribute("inputText", "");
        model.addAttribute("generatedText", DEFAULT_OUTPUT);
        model.addAttribute("sentenceCount", 5); // Default value
        model.addAttribute("syntacticTreeEnabled", false);
        return "NSG";
    }

    @PostMapping("/generate")
    public String generateNonsense(
            @RequestParam String inputText,
            @RequestParam(defaultValue = "5") int sentenceCount,
            @RequestParam(required = false) boolean syntacticTree,
            Model model) {
        
        try {
            // Validate input
            if (inputText == null || inputText.trim().isEmpty()) {
                throw new IllegalArgumentException("Input text cannot be empty");
            }
            
            // Process generation
            String generatedText = generateNonsenseText(inputText, sentenceCount);
            String treeVisualization = syntacticTree ? generateSyntacticTree(inputText) : null;
            
            // Prepare model response
            model.addAttribute("inputText", inputText);
            model.addAttribute("generatedText", generatedText);
            model.addAttribute("sentenceCount", sentenceCount);
            model.addAttribute("syntacticTreeEnabled", syntacticTree);
            
            if (syntacticTree) {
                model.addAttribute("syntacticTree", treeVisualization);
            }
            
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error: " + e.getMessage());
            model.addAttribute("generatedText", DEFAULT_OUTPUT);
        }
        
        return "NSG";
    }

    private String generateNonsenseText(String input, int count) {
        // Implement your actual business logic here
        String[] words = input.split(",\\s*");
        StringBuilder result = new StringBuilder();
        
        for (int i = 0; i < count; i++) {
            result.append("The ")
                 .append(words[0 % words.length])
                 .append(" ")
                 .append(getRandomVerb())
                 .append(" the ")
                 .append(words[(i+1) % words.length])
                 .append(" in a ")
                 .append(getRandomAdjective())
                 .append(" way. ");
        }
        
        return result.toString();
    }

    private String generateSyntacticTree(String input) {
        // TODO: Integrate with Google Cloud Natural Language API
        return "Syntactic tree for: " + input + "\n(Tree visualization will appear here)";
    }

    // Helper methods (could be moved to a service class)
    private String getRandomVerb() {
        String[] verbs = {"danced with", "jumped over", "ate", "sang to", "painted"};
        return verbs[(int) (Math.random() * verbs.length)];
    }

    private String getRandomAdjective() {
        String[] adjectives = {"funny", "bizarre", "whimsical", "peculiar", "absurd"};
        return adjectives[(int) (Math.random() * adjectives.length)];
    }
}