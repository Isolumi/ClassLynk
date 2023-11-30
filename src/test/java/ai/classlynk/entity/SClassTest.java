package ai.classlynk.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SClassTest {
    SClass testClass;
    @BeforeEach
    void setUp() {
        testClass = new SClass(
                "tt101", "lec0102", "10:00:00", "11:00:00",
                "Monday", "Ontario Institute for Studies in Education", "252 Bloor St W, Toronto, ON M5S 1V6", false);
    }

    @Test
    void getClassId() {
        assert(testClass.getClassId().equals("lec0102"));
    }

    @Test
    void setClassId() {
        testClass.setClassId("lec0101");
        assert(testClass.getClassId().equals("lec0101"));
    }

    @Test
    void getEndTime() {
        assert(testClass.getEndTime().equals("11:00:00"));
    }

    @Test
    void setEndTime() {
        testClass.setEndTime("12:00:00");
        assert(testClass.getEndTime().equals("12:00:00"));
    }

    @Test
    void getCourseId() {
        assert(testClass.getCourseId().equals("tt101"));
    }

    @Test
    void setCourseId() {
        testClass.setCourseId("tt102");
        assert(testClass.getCourseId().equals("tt102"));
    }

    @Test
    void isTutorial() {
        assert(!testClass.isTutorial());
    }

    @Test
    void setTutorial() {
        testClass.setTutorial(true);
        assert(testClass.isTutorial());
    }

    @Test
    void getStartTime() {
        assert(testClass.getStartTime().equals("10:00:00"));
    }

    @Test
    void setStartTime() {
        testClass.setStartTime("09:00:00");
        assert(testClass.getStartTime().equals("09:00:00"));
    }

    @Test
    void getBuilding() {
        assert(testClass.getBuilding().equals("Ontario Institute for Studies in Education"));
    }

    @Test
    void setBuilding() {
        testClass.setBuilding("OISE");
        assert(testClass.getBuilding().equals("OISE"));
    }

    @Test
    void getLocation() {
        assert(testClass.getLocation().equals("252 Bloor St W, Toronto, ON M5S 1V6"));
    }

    @Test
    void setLocation() {
        testClass.setLocation("252 Bloor St W");
        assert(testClass.getLocation().equals("252 Bloor St W"));
    }

    @Test
    void getWeekday() {
        assert(testClass.getWeekday().equals("Monday"));
    }

    @Test
    void setWeekday() {
        testClass.setWeekday("Tuesday");
        assert(testClass.getWeekday().equals("Tuesday"));
    }
}