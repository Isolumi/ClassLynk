package ai.classlynk.entity;


import org.junit.Test;
import org.junit.Before;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class CourseTest {
    List<ClassBundle> testCourseClasses;

    List<SClass> testTutorials;
    ClassBundle testBundle;

    Course testCourse;
    @Before
    public void setUp() {
        List<SClass> testClasses;
        SClass a = new SClass(
                "tt101", "lec0102", "10:00:00", "11:00:00",
                "monday", "Ontario Institute for Studies in Education", "252 Bloor St W, Toronto, ON M5S 1V6", false);
        SClass b = new SClass(
                "tt101", "lec0103", "12:00:00", "14:00:00",
                "monday", "St. Michaels College", "81A St. Mary Street, Toronto, ON M5S1J4", false);
        SClass c = new SClass(
                "tt101", "lec0104", "10:00:00", "11:00:00",
                "tuesday", "Convocation Hall", "31 King's College Cir, Toronto, ON M5S 1A1", false);
        testClasses = new ArrayList<>();
        testClasses.add(a);
        testClasses.add(b);
        testClasses.add(c);
        SClass d = new SClass(
                "tt101", "lec0104", "10:00:00", "11:00:00",
                "tuesday", "Convocation Hall", "31 King's College Cir, Toronto, ON M5S 1A1", true);
        testBundle = new ClassBundle("tt101", testClasses);

        testCourseClasses = new ArrayList<>();
        testCourseClasses.add(testBundle);

        testTutorials = new ArrayList<>();
        testTutorials.add(d);

        testCourse = new Course("test course", "tt101", "a test course", testCourseClasses, testTutorials);
    }

    @Test
    public void getTutorials() {
        assert(Objects.equals(testCourse.getTutorials(), testTutorials));
    }

    @Test
    public void setTutorials() {
        List<SClass> newTestTutorials = new ArrayList<>();
                newTestTutorials.add(testTutorials.remove(0));
        testCourse.setTutorials(newTestTutorials);
        assert(testCourse.getTutorials().equals(newTestTutorials));
    }

    @Test
    public void getClassBundles() {
        assert(testCourse.getClassBundles().equals(testCourseClasses));
    }

    @Test
    public void setClassBundles() {
        List<ClassBundle> newTestBundles = new ArrayList<>();
        newTestBundles.add(testCourse.getClassBundles().remove(0));
        testCourse.setClassBundles(newTestBundles);
        assert(testCourse.getClassBundles().equals(newTestBundles));
    }

    @Test
    public void getCourseName() {
        assert(testCourse.getCourseName().equals("test course"));
    }

    @Test
    public void setCourseName() {
        testCourse.setCourseName("test course2");
        assert(testCourse.getCourseName().equals("test course2"));
    }

    @Test
    public void getCourseId() {
        assert(testCourse.getCourseId().equals("tt101"));
    }

    @Test
    public void setCourseId() {
        testCourse.setCourseId("tt102");
        assert(testCourse.getCourseId().equals("tt102"));
    }

    @Test
    public void getCourseDescription() {
        assert(testCourse.getCourseDescription().equals("a test course"));
    }

    @Test
    public void setCourseDescription() {
        testCourse.setCourseDescription("another test course");
        assert(testCourse.getCourseDescription().equals("another test course"));
    }
}