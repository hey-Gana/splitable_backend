package splitable.backend.billscanner.service;

import org.springframework.stereotype.Service;
import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GeminiApiService {

    private final Client client;

    public String askGemini(String ocrText) {

        String prompt = buildPrompt(ocrText);

        GenerateContentResponse response =
                client.models.generateContent(
                        "gemini-2.5-flash-lite",
                        prompt,
                        null
                );

        return response.text();
    }

    /**
     * Builds a STRICT prompt so Gemini returns clean JSON only
     */
    private String buildPrompt(String ocrText) {

        return """
        You are an API that extracts grocery bill line items.

        TASK:
        - Extract ONLY purchased items and their FINAL per-item price.
        - Ignore subtotal, tax, total, payment method, savings, store info.
        - Ignore offers like "2 for $6.00" and use the final unit price.

        OUTPUT RULES (CRITICAL):
        - Return ONLY valid JSON.
        - Do NOT use markdown.
        - Do NOT add explanations or text.
        - Output must be a JSON array.
        - Each object must contain:
            - "itemName": string
            - "price": string (numeric only, no currency symbol)

        EXAMPLE OUTPUT:
        [
          { "itemName": "APPLE", "price": "1.99" },
          { "itemName": "MILK", "price": "3.49" }
        ]

        OCR TEXT:
        ----------------
        %s
        ----------------
        """.formatted(ocrText);
    }
}
