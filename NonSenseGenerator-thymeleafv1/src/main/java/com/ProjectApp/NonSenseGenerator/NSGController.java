package com.ProjectApp.NonSenseGenerator;

import com.ProjectApp.NonSenseGenerator.service.NonsenseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NSGController {

    private final NonsenseService nonsenseService;

    public NSGController(NonsenseService nonsenseService) {
        this.nonsenseService = nonsenseService;
    }

    @GetMapping("/")
    public String index() {
        return "NSG";
    }

    @PostMapping("/generate")
    public String generate(
        @RequestParam String inputText,
        Model model) {
        
        String result = nonsenseService.generateNonsense(inputText);
        model.addAttribute("nonsense", result);
        model.addAttribute("inputText", inputText); // Mantiene l'input nel form
        return "NSG";
    }
}