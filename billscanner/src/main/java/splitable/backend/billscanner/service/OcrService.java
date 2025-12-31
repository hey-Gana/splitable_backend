package splitable.backend.billscanner.service;

import com.google.cloud.vision.v1.*;
import com.google.protobuf.ByteString;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.List;

@Service
public class OcrService {

    /**
     * Loads Google service account credentials from
     * GOOGLE_APPLICATION_CREDENTIALS_B64 env variable
     */
    private void loadGoogleCredentialsFromEnv() throws IOException {
        String base64Json = System.getenv("GOOGLE_APPLICATION_CREDENTIALS_B64");

        if (base64Json == null || base64Json.isEmpty()) {
            throw new RuntimeException("Missing GOOGLE_APPLICATION_CREDENTIALS_B64 environment variable");
        }

        // Decode base64 JSON
        byte[] decodedJson = Base64.getDecoder().decode(base64Json);

        // Write to temporary file
        Path tempFile = Files.createTempFile("gcp-vision-key", ".json");
        Files.write(tempFile, decodedJson);

        // Tell Google SDK where credentials are
        System.setProperty(
                "GOOGLE_APPLICATION_CREDENTIALS",
                tempFile.toAbsolutePath().toString()
        );
    }

    public String extractTextFromImage(byte[] imageBytes) throws IOException {

        // called before ImageAnnotatorClient.create()
        loadGoogleCredentialsFromEnv();

        ByteString imgBytes = ByteString.copyFrom(imageBytes);
        Image image = Image.newBuilder().setContent(imgBytes).build();

        Feature feature = Feature.newBuilder()
                .setType(Feature.Type.DOCUMENT_TEXT_DETECTION)
                .build();

        AnnotateImageRequest request = AnnotateImageRequest.newBuilder()
                .addFeatures(feature)
                .setImage(image)
                .build();

        try (ImageAnnotatorClient client = ImageAnnotatorClient.create()) {
            BatchAnnotateImagesResponse response =
                    client.batchAnnotateImages(List.of(request));

            return response
                    .getResponses(0)
                    .getFullTextAnnotation()
                    .getText();
        }
    }
}
