package splitable.backend.billscanner.service;

import org.springframework.stereotype.Service;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GeminiApiService {

    private final Client client;

    public String askGemini(String prompt) {
        GenerateContentResponse response =
                client.models.generateContent(
                        "gemini-2.5-flash-lite",
                        prompt,
                        null);

        return response.text();
    }
}
