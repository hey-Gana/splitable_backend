package splitable.backend.billscanner.service;

import com.google.cloud.vision.v1.*;
import com.google.protobuf.ByteString;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OcrService {

    /**
     * Extracts text from image bytes using Google Vision API
     */
    public String extractTextFromImage(byte[] imageBytes) throws Exception {

        // Convert byte[] → ByteString (Vision API format)
        ByteString imgBytes = ByteString.copyFrom(imageBytes);

        Image image = Image.newBuilder()
                .setContent(imgBytes)
                .build();

        Feature feature = Feature.newBuilder()
                .setType(Feature.Type.DOCUMENT_TEXT_DETECTION)
                .build();

        AnnotateImageRequest request = AnnotateImageRequest.newBuilder()
                .setImage(image)
                .addFeatures(feature)
                .build();

        try (ImageAnnotatorClient client = ImageAnnotatorClient.create()) {

            AnnotateImageResponse response =
                    client.batchAnnotateImages(List.of(request))
                            .getResponses(0);

            if (response.hasError()) {
                throw new RuntimeException(
                        "OCR Error: " + response.getError().getMessage()
                );
            }

            return response.getFullTextAnnotation().getText();
        }
    }
}
