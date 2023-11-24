package ai.classlynk.app;

import ai.classlynk.data_access.FirebaseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

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
