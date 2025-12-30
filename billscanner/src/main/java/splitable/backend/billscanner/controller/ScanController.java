package splitable.backend.billscanner.controller;

import org.springframework.web.bind.annotation.*;
import splitable.backend.billscanner.service.OcrService;

import java.nio.file.Paths;
import java.io.File;


@RestController
@RequestMapping("/api/bill")
public class ScanController {

    private final OcrService ocrService;

    public ScanController(OcrService ocrService) {
        this.ocrService = ocrService;
    }

    @GetMapping("/test")
    public void testBillParsing() throws Exception {
        String imagePath = Paths.get(
                System.getProperty("user.home"),
                "Downloads",
                "Sample-Bills",
                "Test.jpg").toString();

        File file = new File(imagePath);
        if (!file.exists()) {
            System.err.println("File not found: " + imagePath);
            return;
        }

        System.out.println("Sending image to Google Vision API...");
        String text = ocrService.extractTextFromImage(imagePath);
        System.out.println("OCR Result:\n" + text);

        System.out.println("Parsing items...");
    }
}
