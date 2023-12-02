package ai.classlynk.data_access;

import ai.classlynk.IntegrationTest;
import ai.classlynk.entity.SClass;
import ai.classlynk.entity.Timetable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class FirebaseDataAccessObjectTest extends IntegrationTest {
    @Autowired
    FirebaseDataAccessObject firebaseDataAccessObject;

    @Test
    void contextLoads() {
        assertThat(firebaseDataAccessObject).isNotNull();
    }
    @Test
    void testDeleteTimetable() {
        String testUsername = "test-user";
        Map<String, List<SClass>> classes = new HashMap<>();
        Timetable testTimetable = new Timetable(testUsername, classes);
        firebaseDataAccessObject.saveTimetable(testTimetable);
        firebaseDataAccessObject.deleteTimetable(testTimetable);
        Timetable returnTimetable = firebaseDataAccessObject.getTimetable(testUsername);
        assertNull(returnTimetable);
    }
    @Test
    void testSaveGetTimetable() {
        Map<String, List<SClass>> classes = new HashMap<>();
        Timetable testTimetable = new Timetable("test-user", classes);
        firebaseDataAccessObject.deleteTimetable(testTimetable);
        firebaseDataAccessObject.saveTimetable(testTimetable);
        Timetable returnTimetable = firebaseDataAccessObject.getTimetable("test-user");
        assertEquals(testTimetable.getUserId(), returnTimetable.getUserId());
        firebaseDataAccessObject.deleteTimetable(returnTimetable);
    }

    @Test
    void testDeleteUser() {
        String testUsername = "test-user";
        firebaseDataAccessObject.userDelete(testUsername);
        boolean exists = firebaseDataAccessObject.existsByName(testUsername);
        assertFalse(exists);
    }

    @Test
    void testUserCreateExistsByUsername() {
        String testUsername = "test-user";
        String testPassword = "no";
        firebaseDataAccessObject.userDelete(testUsername);
        firebaseDataAccessObject.userCreate(testUsername, testPassword);
        boolean exists = firebaseDataAccessObject.existsByName(testUsername);
        assertTrue(exists);
    }

    @Test
    void testVerifyPassword() {
        
    }
}
