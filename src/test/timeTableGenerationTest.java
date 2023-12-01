import ai.classlynk.data_access.FirebaseDataAccessObject;
import ai.classlynk.entity.Course;
import ai.classlynk.entity.*;
import java.util.*;

public class timeTableGenerationTest {

    public static void main(String[] args) {
        ArrayList<ClassBundle> classbundleList = new ArrayList<>();
        FirebaseDataAccessObject dao = new FirebaseDataAccessObject();
        Map<String, Course> test = dao.loadCourses();
        for(Course course: test.values()){
            System.out.println(course.getCourseId());
        }


    }
}
