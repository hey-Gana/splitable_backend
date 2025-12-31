package splitable.backend.billscanner.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import splitable.backend.billscanner.model.BillItem;
import splitable.backend.billscanner.model.BillItemResponse;

import java.io.IOException;
import java.util.List;

@Service
public class BillParserService {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<BillItemResponse> parseBill(String cleanedJson) throws IOException {

        // Step 1: Parse Gemini JSON safely
        List<BillItem> rawItems = objectMapper.readValue(
                cleanedJson,
                new TypeReference<List<BillItem>>() {}
        );

        // Step 2: Convert to clean response
        return rawItems.stream()
                .map(item -> new BillItemResponse(
                        item.getItemName(),
                        parsePrice(item.getPrice())
                ))
                .toList();
    }

    // Converts "$3.29" → 3.29
    private double parsePrice(String price) {
        if (price == null) return 0.0;

        String cleaned = price.replaceAll("[^0-9.]", "");

        try {
            return Double.parseDouble(cleaned);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
}
