package app;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class FirebaseConfig {
    private final Dotenv dotenv;
    public FirebaseConfig(Dotenv dotenv) {
        this.dotenv = dotenv;
    }
    @Bean
    public FirebaseApp firebaseApp() throws IOException {
        String serviceAccountJsonPath = dotenv.get("SERVICE_ACCOUNT_JSON_PATH");
        assert serviceAccountJsonPath != null;
        FileInputStream serviceAccount = new FileInputStream(serviceAccountJsonPath);
        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setProjectId("timetablegenerator-e55f3")
                .build();
        return FirebaseApp.initializeApp(options);
    }
}
