package ai.classlynk.data_access;

import ai.classlynk.entity.*;
import ai.classlynk.use_case.explore_courses.ExploreCoursesDataAccessInterface;
import ai.classlynk.use_case.generate_timetable.TimetableGeneratorDataAccessInterface;
import ai.classlynk.use_case.save_view_timetables.SaveViewTimetablesDataAccessInterface;
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
import java.util.Map;

@Repository
public class FirebaseRepository implements ExploreCoursesDataAccessInterface, TimetableGeneratorDataAccessInterface, SaveViewTimetablesDataAccessInterface {
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
    public Map<String, Course> loadCourses() {
        return null;
    }

    @Override
    public float getRouteLength(String origin, String destination) {
        return 0;
    }


    public void save(User user, Timetable timetable) {

    }


    public ArrayList<Timetable> load(User user) {
        return null;
    }


    public void delete(User user, Timetable timetable) {

    }

    public void insertCourses() {
        List<Course> courses = new ArrayList<>();
        String id = "mat136";

        SClass aa = new SClass(id, "lec0101", "10:00:00",
                "11:00:00", "tuesday", "mclennan physical laboratories",
                "255 Huron Street, Toronto, ON M5S 1A7", false);
        SClass ab = new SClass(id, "lec0101", "10:00:00",
                "12:00:00", "thursday", "mclennan physical laboratories",
                "255 Huron Street, Toronto, ON M5S 1A7", false);

        SClass ba = new SClass(id, "lec5101", "17:00:00",
                "16:00:00", "tuesday", "bahen center for information technology",
                "40 St. George Street, Toronto, ON M5S2E4", false);
        SClass bb = new SClass(id, "lec5101", "17:00:00",
                "19:00:00", "thursday", "bahen center for information technology",
                "40 St. George Street, Toronto, ON M5S2E4", false);

        SClass tut1 = new SClass(id, "tut0101", "9:00:00",
                "10:00:00", "wednesday", "university college",
                "15 King's College Circle, Toronto, On M5S 3H7", true);
        SClass tut2 = new SClass(id, "tut0201", "11:00:00",
                "12:00:00", "wednesday", "claude t. bissell building",
                "140 St. George Street, Toronto, ON M5S 3G6", true);
        SClass tut3 = new SClass(id, "tut0301", "12:00:00",
                "13:00:00", "wednesday", "earth sciences center",
                "22 Ursula Franklin Street, Toronto, ON M5S 3G6", true);
        SClass tut4 = new SClass(id, "tut0401", "16:00:00",
                "17:00:00", "wednesday", "wallberg building",
                "40 St. George Street, Toronto, ON M5S2E4", true);
        SClass tut5 = new SClass(id, "tut0501", "13:00:00",
                "14:00:00", "wednesday", "anthropology building",
                "184-200 College Street, Toronto, ON M5S 2S2", true);
        SClass tut6 = new SClass(id, "tut5101", "17:00:00",
                "18:00:00", "wednesday", "anthropology building",
                "15 King's College Circle, Toronto, On M5S 3H7", true);

        List<SClass> classesOne = new ArrayList<>();
        classesOne.add(aa);
        classesOne.add(ab);
        List<SClass> classesTwo = new ArrayList<>();
        classesTwo.add(ba);
        classesTwo.add(bb);

        ClassBundle classBundleOne = new ClassBundle("lec0101", classesOne);
        ClassBundle classBundleTwo = new ClassBundle("lec5101", classesTwo);
        List<ClassBundle> classBundles = new ArrayList<>();
        classBundles.add(classBundleOne);
        classBundles.add(classBundleTwo);

        classesOne.clear();
        classesOne.add(tut1);
        classesOne.add(tut2);
        classesOne.add(tut3);
        classesOne.add(tut4);
        classesOne.add(tut5);
        classesOne.add(tut6);

        Course course = new Course("calculus ii", "mat136",
                "This second part of the introductory Calculus sequence focuses on integral calculus beginning with the Fundamental Theorem of Calculus, the connection between two seemingly unrelated problems: measuring changing quantities and finding areas of curved shapes. Students will develop a deep understanding of the integral, and use it to: unpack equations involving derivatives; to make sense of infinite sums; to write complicated functions as 'infinite polynomials'; and to compute areas, volumes, and totals in applied problems. This course will further develop students' abilities to translate between algebraic, graphical, numerical, and verbal descriptions of mathematics in a variety of applied contexts. This course is a continuation of MAT135H1 and will be useful for students interested in learning applied calculus in relation to future studies in economics, life science, and physical and mathematical science programs. The following concepts will be studied: Integration, basic techniques of integration (substitution and by parts), improper integrals, using computer algebra systems (CAS) for integration, Taylor polynomials and Taylor series, ratio test for power series, radius of convergence of power series, first-order differential equations and systems of differential equations: modelling, separable DEs, and using CAS to study and find solutions.",
                classBundles, classesOne);

        insertCourse(course);
    }

    @Override
    public void save(Timetable timetable) {

    }

    @Override
    public Timetable[] getTimetables() {
        return new Timetable[0];
    }


    public ArrayList<Timetable> load() {
        return null;
    }

    @Override
    public void delete(Timetable timetable) {

    }
}