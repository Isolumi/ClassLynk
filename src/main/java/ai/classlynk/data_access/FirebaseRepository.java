package ai.classlynk.data_access;

import ai.classlynk.entity.*;
import ai.classlynk.use_case.GenerateStaticImage.GenerateStaticImageDataAccessInterface;
import ai.classlynk.use_case.explore_courses.ExploreCoursesDataAccessInterface;
import ai.classlynk.use_case.generate_timetable.TimetableGeneratorDataAccessInterface;
import ai.classlynk.use_case.save_view_time_tables.SaveViewTimetablesDataAccessInterface;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class FirebaseRepository implements ExploreCoursesDataAccessInterface, TimetableGeneratorDataAccessInterface, GenerateStaticImageDataAccessInterface, SaveViewTimetablesDataAccessInterface {
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

    @Override
    public Map<String, String> getStaticMaps(Timetable Timetable) {
        return null;
    }

    @Override
    public Map<String, Course> loadCourses() {
        return null;
    }

    @Override
    public float getRouteLength(String origin, String destination) {
        return 0;
    }

    @Override
    public void save(User user, Timetable timetable) {

    }

    @Override
    public ArrayList<Timetable> load(User user) {
        return null;
    }

    @Override
    public void delete(User user, Timetable timetable) {

    }



    public void insertCourses() {
        // ant100
        SClass lec2501 = new SClass("ant100", "lec2501", "18:00:00",
                "20:00:00", "thursday", "convocation hall",
                "31 King's College Circle " + "Toronto, ON " + "M5S 1A1", false);
        SClass lec5101 = new SClass("ant100", "lec5101", "18:00:00",
                "20:00:00", "thursday", "convocation hall",
                "31 King's College Circle " + "Toronto, ON " + "M5S 1A1", false);
    }
}