package ai.classlynk;

import com.google.cloud.spring.data.firestore.repository.config.EnableReactiveFirestoreRepositories;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "ai.classlynk")
@EnableReactiveFirestoreRepositories(basePackages = "ai.classlynk")
public class TestClassLynkApplication {

}
