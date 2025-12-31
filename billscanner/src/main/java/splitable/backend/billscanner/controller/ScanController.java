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
     * 1️⃣ Accepts bill image from frontend
     * 2️⃣ Extracts OCR text using Vision API
     * 3️⃣ Converts unstructured text → structured JSON using Gemini
     * 4️⃣ Parses & sanitizes JSON
     * 5️⃣ Returns clean numeric data to frontend
     */
    @PostMapping(
            value = "/scan",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<BillItemResponse> scanBill(
            @RequestParam("file") MultipartFile file
    ) throws Exception {

        // 1️⃣ Convert uploaded image to bytes
        byte[] imageBytes = file.getBytes();

        // 2️⃣ OCR: image → raw unstructured text
        String ocrText = ocrService.extractTextFromImage(imageBytes);

        // 3️⃣ Gemini: raw text → structured JSON string
        String geminiRawJson = geminiApiService.askGemini(ocrText);

        // 4️⃣ Clean + parse JSON → safe numeric values
        return billParserService.parseBill(geminiRawJson);
    }
}
