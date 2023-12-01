package ai.classlynk.data_access;

import ai.classlynk.entity.Course;
import ai.classlynk.entity.Timetable;
import ai.classlynk.use_case.explore_courses.ExploreCoursesDataAccessInterface;
import ai.classlynk.use_case.save_view_timetables.SaveViewTimetablesDataAccessInterface;
import ai.classlynk.use_case.user_auth.login.LoginDataAccessInterface;
import ai.classlynk.use_case.user_auth.register.RegisterDataAccessInterface;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
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
    /**
     *
     * @return all courses stored in firestore
     */
    @Override
    public Map<String, Course> loadCourses() {
        Flux<Course> courses = courseRepository.findAll();
        Mono<Map<String, Course>> courseMap = courses.collectMap(Course::getCourseId);
        courseMap.subscribe();

        return courseMap.block();
        /*
        This is the code to use if we wanted to return a Mono<Map<String, Course>>.
        Blocking is not recommended in reactive programming.
        The below code will return the items in Mono.
        yourService.getCourseMap()
            .subscribe(courseMap -> {
                // Handle the resulting Map<Long, Course>
                System.out.println("Map of courses: " + courseMap);
            });
         */
    }

    /**
     *
     * @param timetable the timetable to be stored in firestore
     */
    @Override
    public void saveTimetable(Timetable timetable) {
        timetableRepository.save(timetable).subscribe();
    }

    /**
     *
     * @param timetable the timetable to be deleted from firestore
     */
    @Override
    public void deleteTimetable(Timetable timetable) {
        timetableRepository.delete(timetable).subscribe();
    }

    /**
     *
     * @return returns all timetables of user in firestore
     */
    @Override
    public List<Timetable> getTimetables() {
        Flux<Timetable> timetables = timetableRepository.findAll();
        timetables.subscribe();
        return timetables.collectList().block();
    }

    @Override
    public boolean existedByName(String Name) {
        return false;
    }

    @Override
    public boolean verifyPassword(String name, String Password) {
        return false;
    }

    @Override
    public void userCreate(String Name, String password) {
    }
}
