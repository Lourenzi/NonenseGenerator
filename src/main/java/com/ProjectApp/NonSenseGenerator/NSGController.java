package com.ProjectApp.NonSenseGenerator;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NSGController {

    @GetMapping("/")
    public String showGeneratorForm(Model model) {
        // Add default values for the form
        model.addAttribute("input.placeholder", "Input sentence");
        model.addAttribute("app.title", "NonSenseGen");
        model.addAttribute("default.output", "Generated sentence");
        
        // If you want to pre-populate any fields or set default states
        return "NSG"; // This corresponds to NSG.html in templates directory
    }

    @PostMapping("/generate")
    public String generateNonsense(
            @RequestParam String inputText,
            @RequestParam(defaultValue = "1") int sentenceCount,
            @RequestParam(required = false) boolean syntacticTree,
            Model model) {
        
        // Process the input and generate nonsense text
        String generatedText = generateNonsenseText(inputText, sentenceCount);
        
        // Add results to the model
        model.addAttribute("generatedText", generatedText);
        model.addAttribute("originalInput", inputText);
        model.addAttribute("sentenceCount", sentenceCount);
        model.addAttribute("syntacticTreeEnabled", syntacticTree);
        
        // If you generated a syntactic tree, add it to the model
        if (syntacticTree) {
            String treeVisualization = generateSyntacticTree(inputText);
            model.addAttribute("syntacticTree", treeVisualization);
        }
        
        return "NSG";
    }

    @GetMapping("/error")
    public String handleError(Model model) {
        // Add error information to the model
        model.addAttribute("errorMessage", "An unexpected error occurred. Please try again.");
        model.addAttribute("showError", true);
        
        // Return to the main page with error state
        return "NSG";
    }

    private String generateNonsenseText(String input, int count) {
        // Implement your nonsense generation logic here
        // This is just a placeholder implementation
        if (input == null || input.trim().isEmpty()) {
            return "The monkey wore a tiny top hat while the giraffe tried to juggle pineapples on roller skates";
        }
        
        String[] words = input.split(",\\s*");
        StringBuilder result = new StringBuilder();
        
        for (int i = 0; i < count; i++) {
            result.append("The ")
                 .append(words[0])
                 .append(" danced with the ")
                 .append(words.length > 1 ? words[1] : "elephant")
                 .append(" while eating spaghetti. ");
        }
        
        return result.toString();
    }

    private String generateSyntacticTree(String input) {
        // Implement your syntactic tree generation logic here
        // This could call your Google Cloud Natural Language API integration
        return "Syntactic tree visualization for: " + input;
    }
}
