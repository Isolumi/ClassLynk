package ai.classlynk.data_access;

import ai.classlynk.entity.*;
import ai.classlynk.use_case.GenerateStaticImage.GenerateStaticImageDataAccessInterface;
import ai.classlynk.use_case.explore_courses.ExploreCoursesDataAccessInterface;
import ai.classlynk.use_case.generate_timetable.TimetableGeneratorDataAccessInterface;
import ai.classlynk.use_case.save_view_time_tables.SaveViewTimetablesDataAccessInterface;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import ai.classlynk.entity.ClassBundle;
import ai.classlynk.entity.Course;
import ai.classlynk.entity.SClass;
import org.checkerframework.checker.units.qual.C;
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
        ClassBundle tutBundle = new ClassBundle("testing", classList);


        ApiFuture<WriteResult> apiFuture = this.firestore.document("courses/mat137")
                .set(new Course("meth", "mat137", "bad", bundleList, classList));
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
        List<Course> courses = new ArrayList<>();

        SClass lec2501 = new SClass("ant100", "lec2501", "18:00:00",
        "20:00:00", "thursday", "convocation hall",
                "31 King's College Circle" + "Toronto, ON" +
                "M5S 1A1", false);
        SClass lec5101 = new SClass("ant100", "lec5101", "18:00:00",
                "20:00:00", "thursday", "convocation hall",
                "31 King's College Circle, Toronto, ON M5S 1A1", false);

        SClass tut0101 = new SClass("ant100", "tut0101", "10:00:00",
                "11:00:00", "tuesday", "anthropology building",
                "19 Russell St, Toronto, ON M5S 2S2", true);
        SClass tut0601 = new SClass("ant100", "tut0601", "15:00:00",
                "16:00:00", "tuesday", "anthropology building",
                "19 Russell St, Toronto, ON M5S 2S2", true);
        SClass tut0801 = new SClass("ant100", "tut0801", "10:00:00",
                "11:00:00", "wednesday", "anthropology building",
                "19 Russell St, Toronto, ON M5S 2S2", true);
        SClass tut1301 = new SClass("ant100", "tut1301", "15:00:00",
                "16:00:00", "wednesday", "anthropology building",
                "19 Russell St, Toronto, ON M5S 2S2", true);
        SClass tut2001 = new SClass("ant100", "tut2001", "15:00:00",
                "16:00:00", "thursday", "anthropology building",
                "19 Russell St, Toronto, ON M5S 2S2", true);
        SClass tut2102 = new SClass("ant100", "tut2102", "16:00:00",
                "17:00:00", "thursday", "anthropology building",
                "19 Russell St, Toronto, ON M5S 2S2", true);

        List<SClass> classes = new ArrayList<>();
        classes.add(lec2501);
        List<SClass> classses = new ArrayList<>();
        classses.add(lec5101);
        ClassBundle classBundle = new ClassBundle("ant100", classes);
        ClassBundle classsBundle = new ClassBundle("ant100", classses);
        List<ClassBundle> classBundles = new ArrayList<>();
        classBundles.add(classBundle);
        classBundles.add(classsBundle);

        classes.clear();
        classes.add(tut0101);
        classes.add(tut0601);
        classes.add(tut0801);
        classes.add(tut1301);
        classes.add(tut2001);
        classes.add(tut2102);

        Course course = new Course("introduction to anthropology", "ant100",
                "Society and culture from various anthropological perspectives: socio-cultural, evolutionary, archaeological, and linguistic. ",
                classBundles, classes);
    }
}