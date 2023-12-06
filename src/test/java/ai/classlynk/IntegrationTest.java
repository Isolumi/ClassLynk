package ai.classlynk;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestClassLynkApplication.class, initializers = ConfigDataApplicationContextInitializer.class)
public class IntegrationTest {
}
