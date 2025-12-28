package splitable.backend.billscanner.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.auth.oauth2.GoogleCredentials;

@Service
public class VisionService {
    // public String extractText(MultipartFile bill) throws IOException {

    // }
    public static GoogleCredentials getCredentials() throws IOException {
        // Read JSON string from environment variable
        String jsonKey = System.getenv("GOOGLE_APPLICATION_CREDENTIALS_JSON");

        if (jsonKey == null || jsonKey.isEmpty()) {
            throw new RuntimeException("Environment variable GOOGLE_APPLICATION_CREDENTIALS_JSON is not set!");
        }

        // Convert string to InputStream
        ByteArrayInputStream credentialsStream = new ByteArrayInputStream(jsonKey.getBytes());

        // Create GoogleCredentials object with proper scope
        return GoogleCredentials.fromStream(credentialsStream)
                .createScoped(List.of("https://www.googleapis.com/auth/cloud-platform"));
    }
}
