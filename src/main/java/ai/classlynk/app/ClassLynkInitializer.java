package ai.classlynk.app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = "ai.classlynk")
public class ClassLynkInitializer {

    public static void main(String[] args) {
        SpringApplication.run(ClassLynkInitializer.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            ClassLynkApplication.main(null);
        };
    }

}
