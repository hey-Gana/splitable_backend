package splitable.backend.billscanner.service;

import com.google.cloud.vision.v1.*;
import com.google.protobuf.ByteString;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
public class OcrService {

    //Reads an image from a local file path and extracts text using Google Vision OCR
     
    public String extractTextFromImage(String imagePath) throws IOException {

        //Read image bytes from local file
        byte[] imageBytes = Files.readAllBytes(Path.of(imagePath));
        ByteString imgBytes = ByteString.copyFrom(imageBytes);

        //Create Vision Image object
        Image image = Image.newBuilder()
                .setContent(imgBytes)
                .build();

        //Specify OCR feature for receipts & bills
        Feature feature = Feature.newBuilder()
                .setType(Feature.Type.DOCUMENT_TEXT_DETECTION)
                .build();

        //Build the Vision API request
        AnnotateImageRequest request = AnnotateImageRequest.newBuilder()
                .setImage(image)
                .addFeatures(feature)
                .build();

        //Create client & send request
        try (ImageAnnotatorClient client = ImageAnnotatorClient.create()) {

            List<AnnotateImageResponse> responses =
                    client.batchAnnotateImages(List.of(request)).getResponsesList();

            if (responses.isEmpty()) {
                throw new RuntimeException("No response from Vision API");
            }

            AnnotateImageResponse response = responses.get(0);

            //Handle API errors
            if (response.hasError()) {
                throw new RuntimeException(
                        "Vision API Error: " + response.getError().getMessage()
                );
            }

            //Extract and return full OCR text
            return response.getFullTextAnnotation().getText();
        }
    }
}
