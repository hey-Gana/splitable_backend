package splitable.backend.billscanner.service;

import com.google.cloud.vision.v1.*;
import com.google.protobuf.ByteString;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class OcrService {

    public String extractTextFromImage(byte[] imageBytes) throws IOException {
        // Step 1: Load Google credentials from environment variable
        String credentialsJson = System.getenv("GOOGLE_APPLICATION_CREDENTIALS_JSON");
        if (credentialsJson != null) {
            // Write JSON to a temporary file
            Path tempFile = Files.createTempFile("vision_key", ".json");
            Files.write(tempFile, credentialsJson.getBytes());

            // Tell Google client library where to find the credentials
            System.setProperty("GOOGLE_APPLICATION_CREDENTIALS", tempFile.toAbsolutePath().toString());
        } else {
            throw new RuntimeException("Google credentials not found in environment variable!");
        }

        // Step 2: Prepare image
        ByteString imgBytes = ByteString.copyFrom(imageBytes);
        Image image = Image.newBuilder().setContent(imgBytes).build();

        // Step 3: Set feature for OCR
        Feature feature = Feature.newBuilder()
                .setType(Feature.Type.DOCUMENT_TEXT_DETECTION)
                .build();

        // Step 4: Build request
        AnnotateImageRequest request = AnnotateImageRequest.newBuilder()
                .addFeatures(feature)
                .setImage(image)
                .build();

        try (ImageAnnotatorClient client = ImageAnnotatorClient.create()) {
            BatchAnnotateImagesResponse response = client.batchAnnotateImages(java.util.List.of(request));
            return response.getResponses(0).getFullTextAnnotation().getText();
        }
    }
}
