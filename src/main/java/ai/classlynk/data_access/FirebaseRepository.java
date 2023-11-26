package ai.classlynk.data_access;

import ai.classlynk.entity.*;
import ai.classlynk.use_case.explore_courses.ExploreCoursesDataAccessInterface;
import ai.classlynk.use_case.save_view_timetables.SaveViewTimetablesDataAccessInterface;
import ai.classlynk.use_case.user_auth.login.LoginDataAccessInterface;
import ai.classlynk.use_case.user_auth.register.RegisterDataAccessInterface;
import com.google.cloud.firestore.Firestore;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Map;

@Repository
public class FirebaseRepository implements
        ExploreCoursesDataAccessInterface,
        SaveViewTimetablesDataAccessInterface,
        LoginDataAccessInterface,
        RegisterDataAccessInterface {
    @Resource
    private Firestore firestore;

    @Override
    public Map<String, Course> loadCourses() {
        return null;
    }

    @Override
    public void saveTimetable(Timetable timetable) {

    }

    @Override
    public void deleteTimetable(Timetable timetable) {

    }

    @Override
    public Timetable[] getTimetables() {
        return null;
    }

    @Override
    public boolean existedByName(String Name) {
        return false;
    }

    @Override
    public void userCreate(String Name, String password) {

    }
}