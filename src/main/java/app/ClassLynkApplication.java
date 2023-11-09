package app;

import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutures;
import com.google.cloud.firestore.*;
import entity.ClassBundle;
import entity.Course;
import entity.SClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@SpringBootApplication
public class ClassLynkApplication {

    @Autowired
    private Firestore firestore;
    public static void main(String[] args) {
        SpringApplication.run(ClassLynkApplication.class, args);
    }
    private void insertCourse() throws ExecutionException, InterruptedException {
        SClass tempClass = new SClass("hsb100", "lec101", 14.5F, LocalTime.of(1, 0, 0), "monday", "bahen", "somewhere there", false);
        List<SClass> classList = new ArrayList<>();
        classList.add(tempClass);
        ClassBundle tempBundle = new ClassBundle("hsb100", classList);
        List<ClassBundle> bundleList = new ArrayList<>();
        bundleList.add(tempBundle);

        ApiFuture<WriteResult> apiFuture = this.firestore.document("courses/cnc").set(new Course("cnc", "hsb", "bad", bundleList));
    }
}
