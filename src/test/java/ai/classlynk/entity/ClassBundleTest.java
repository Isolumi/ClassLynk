package ai.classlynk.entity;


import org.junit.Before;
import org.junit.Test;



import java.util.ArrayList;
import java.util.List;


public class ClassBundleTest {

    List<SClass> testClasses;
    ClassBundle testBundle;
    @Before
    public void setUp() {
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

        testBundle = new ClassBundle("tt101", testClasses);
    }

    @Test
    public void getLectureId() {
        assert(testBundle.getLectureId().equals("tt101"));
    }

    @Test
    public void setLectureId() {
        testBundle.setLectureId("tt102");
        assert(testBundle.getLectureId().equals("tt102"));
    }

    @Test
    public void getClasses() {
        assert(testBundle.getClasses().equals(testClasses));
    }

    @Test
    public void setClasses() {
        List<SClass> newTestClasses = new ArrayList<>();
        newTestClasses.add(testClasses.remove(0));
        testBundle.setClasses(newTestClasses);
        assert(testBundle.getClasses().equals(newTestClasses));
    }
}