package ai.classlynk.data_access;

import ai.classlynk.entity.Course;
import ai.classlynk.entity.Timetable;
import ai.classlynk.use_case.explore_courses.ExploreCoursesDataAccessInterface;
import ai.classlynk.use_case.save_view_timetables.SaveViewTimetablesDataAccessInterface;
import ai.classlynk.use_case.user_auth.login.LoginDataAccessInterface;
import ai.classlynk.use_case.user_auth.register.RegisterDataAccessInterface;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class FirebaseDataAccessObject implements
        ExploreCoursesDataAccessInterface,
        SaveViewTimetablesDataAccessInterface,
        LoginDataAccessInterface,
        RegisterDataAccessInterface {

    @Resource
    private CourseRepository courseRepository;
    @Resource
    private TimetableRepository timetableRepository;
    @Resource
    private UserRepository userRepository;

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
        return new Timetable[0];
    }

    @Override
    public boolean existedByName(String Name) {
        return false;
    }

    @Override
    public void userCreate(String Name, String password) {

    }
}
