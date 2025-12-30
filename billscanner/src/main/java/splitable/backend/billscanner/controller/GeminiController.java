package splitable.backend.billscanner.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import splitable.backend.billscanner.service.GeminiApiService;

@RestController
@RequestMapping("/api/gemini")
@RequiredArgsConstructor
public class GeminiController {

    private final GeminiApiService geminiApiService;

    @PostMapping("/ask")
    public String askGemini(@RequestBody String prompt) {
        return geminiApiService.askGemini(prompt);
    }
}
