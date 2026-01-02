package splitable.backend.billscanner.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import splitable.backend.billscanner.model.BillItemResponse;
import splitable.backend.billscanner.service.BillParserService;
import splitable.backend.billscanner.service.OcrService;
import splitable.backend.billscanner.service.GeminiApiService;

import java.util.List;

@RestController
@RequestMapping("/api")
//@CrossOrigin(origins = "https://hey-gana.github.io") // applies to all endpoints in this controller
public class ScanController {

    private final OcrService ocrService;
    private final GeminiApiService geminiApiService;
    private final BillParserService billParserService;

    public ScanController(
            OcrService ocrService,
            GeminiApiService geminiApiService,
            BillParserService billParserService
    ) {
        this.ocrService = ocrService;
        this.geminiApiService = geminiApiService;
        this.billParserService = billParserService;
    }

    /**
     * accepts bill image from frontend
     * Extracts OCR text using Vision API
     * Converts unstructured text → structured JSON using Gemini
     * Parses & sanitizes JSON
     * Returns clean numeric data to frontend
     */
    @PostMapping(
            value = "/scan",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<BillItemResponse> scanBill(
            @RequestParam("file") MultipartFile file
    ) throws Exception {

        // Convert uploaded image to bytes
        byte[] imageBytes = file.getBytes();

        // OCR: image → raw unstructured text
        String ocrText = ocrService.extractTextFromImage(imageBytes);

        // Gemini: raw text → structured JSON string
        String geminiRawJson = geminiApiService.askGemini(ocrText);

        // Clean + parse JSON → safe numeric values
        return billParserService.parseBill(geminiRawJson);
    }
}
