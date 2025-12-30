package splitable.backend.billscanner.service;

import com.google.cloud.vision.v1.*;
import com.google.protobuf.ByteString;
import org.springframework.stereotype.Service;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class OcrService {

    public String extractTextFromImage(String imagePath) throws Exception {
        ByteString imgBytes = ByteString.copyFrom(Files.readAllBytes(Path.of(imagePath)));
        Image image = Image.newBuilder().setContent(imgBytes).build();

        Feature feature = Feature.newBuilder().setType(Feature.Type.DOCUMENT_TEXT_DETECTION).build();
        AnnotateImageRequest request = AnnotateImageRequest.newBuilder()
                .addFeatures(feature)
                .setImage(image)
                .build();

        try (ImageAnnotatorClient client = ImageAnnotatorClient.create()) {
            AnnotateImageResponse response = client.batchAnnotateImages(java.util.List.of(request))
                    .getResponsesList().get(0);
            return response.getFullTextAnnotation().getText();
        }
    }
}
