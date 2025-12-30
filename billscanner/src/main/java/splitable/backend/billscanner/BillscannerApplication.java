package splitable.backend.billscanner;

import java.io.File;
import java.nio.file.Paths;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import splitable.backend.billscanner.service.OcrService;

@SpringBootApplication
public class BillscannerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillscannerApplication.class, args);
    }

    @Bean
    CommandLineRunner run(OcrService ocrService) {
        return args -> {
            // Build the path safely using Paths.get()
            String imagePath = Paths.get(
                    System.getProperty("user.home"), // /Users/ganapathisubramaniam
                    "Downloads",
                    "Sample-Bills",
                    "Test.jpg"
            ).toString();

            File file = new File(imagePath);
            System.out.println("Absolute path: " + file.getAbsolutePath());
            System.out.println("Does file exist? " + file.exists());

            if (!file.exists()) {
                System.err.println("File not found! Please check the path.");
                return;
            }

            System.out.println("Sending image to Google Vision API...");
            String text = ocrService.extractTextFromImage(imagePath);
            System.out.println("OCR Result:\n" + text);
        };
    }
}
