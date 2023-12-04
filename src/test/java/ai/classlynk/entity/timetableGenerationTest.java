package ai.classlynk.entity;

import ai.classlynk.app.ClassLynkApplication;
import ai.classlynk.data_access.FirebaseDataAccessObject;
import com.google.cloud.spring.data.firestore.repository.config.EnableReactiveFirestoreRepositories;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@SpringBootApplication(scanBasePackages = "ai.classlynk")
public class timetableGenerationTest {
    @Resource
    FirebaseDataAccessObject firebaseDataAccessObject;

    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(timetableGenerationTest.class);
        builder.headless(false);
        builder.run(args);
    }

//    @Bean
//    public CommandLineRunner testRunner() {
//        return args -> {
//            random5CourseTest();
//        };
//    }
//    @Test
//    public void random5CourseTest(){
//        Map<String, Course> allCourses = firebaseDataAccessObject.getAllCourses();
//        List<Course> first5 = new ArrayList<>();
//        int i = 0;
//        for(Course course: allCourses.values()){
//            first5.add(course);
//            if(i == 4){
//                break;
//            }
//            i++;
//        }
//        Timetable optimal = OptimalTimetableCalculator.generateTimetable(first5);
//        for(String key: optimal.getClasses().keySet()){
//            System.out.println(key);
//            for(SClass sclass: optimal.getClasses().get(key)){
//                System.out.print(sclass.getCourseId() + ": " + sclass.getClassId() + " ");
//            }
//            System.out.println();
//        }
//    }

    public void nonConflictTest(){

    }
}
