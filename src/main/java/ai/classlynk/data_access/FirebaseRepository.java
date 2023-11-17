package ai.classlynk.data_access;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import ai.classlynk.entity.ClassBundle;
import ai.classlynk.entity.Course;
import ai.classlynk.entity.SClass;
import org.springframework.stereotype.Repository;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FirebaseRepository {
    @Resource
    private Firestore firestore;

    // Test code for inserting one course into firebase
    public void insertCourse() {
        SClass tempClass = new SClass("mat137", "lec101", "01:00:00",
                "02:00:00", "monday", "ba", "somewhere there",
                false);
        List<SClass> classList = new ArrayList<>();
        classList.add(tempClass);
        ClassBundle tempBundle = new ClassBundle("mat137", classList);
        List<ClassBundle> bundleList = new ArrayList<>();
        bundleList.add(tempBundle);

        ApiFuture<WriteResult> apiFuture = this.firestore.document("courses/mat137")
                .set(new Course("meth", "mat137", "bad", bundleList));
    }

    public void insertCourse(Course course) {
        String courseId = course.getCourseId();
        ApiFuture<WriteResult> apiFuture = this.firestore.document("courses/" + courseId)
                .set(course);
    }

    public void insertCourses() {
        SClass lec2501 = new SClass("ant100", "lec2501", "18:00:00",
        "20:00:00", "monday", "convocation hall",
                "31 King's College Circle" + "Toronto, ON" +
                "M5S 1A1", false);
    }
}