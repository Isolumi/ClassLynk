package ai.classlynk.data_access;

import ai.classlynk.TestClassLynkApplication;
import ai.classlynk.entity.Timetable;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

// @SpringBootTest(classes = TestClassLynkApplication.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestClassLynkApplication.class, initializers = ConfigDataApplicationContextInitializer.class)
public class FirebaseDataAccessObjectTest {
    @Autowired
    FirebaseDataAccessObject firebaseDataAccessObject;

    @Test
    void contextLoads() {
        assertThat(firebaseDataAccessObject).isNotNull();
    }
    @Test
    void testSaveGetTimetable() {
        Timetable testTimetable = new Timetable("test-user", null);
        firebaseDataAccessObject.saveTimetable(testTimetable);
        Timetable returnTimetable = firebaseDataAccessObject.getTimetable("test-user");
        assertEquals(testTimetable, returnTimetable);
    }

    @Test
    void testDeleteTimetable() {
        Timetable testTimetable = new Timetable("test-user", null);
        firebaseDataAccessObject.deleteTimetable(testTimetable);
    }
}
