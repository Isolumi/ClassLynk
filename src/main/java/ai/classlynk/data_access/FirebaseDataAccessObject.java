package ai.classlynk.data_access;

import ai.classlynk.entity.Course;
import ai.classlynk.entity.Timetable;
import ai.classlynk.entity.User;
import ai.classlynk.use_case.explore_courses.ExploreCoursesDataAccessInterface;
import ai.classlynk.use_case.save_view_timetables.SaveViewTimetablesDataAccessInterface;
import ai.classlynk.use_case.user_auth.login.LoginDataAccessInterface;
import ai.classlynk.use_case.user_auth.register.RegisterDataAccessInterface;
import com.google.cloud.spring.data.firestore.FirestoreReactiveRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
public class FirebaseDataAccessObject implements
        ExploreCoursesDataAccessInterface,
        SaveViewTimetablesDataAccessInterface,
        LoginDataAccessInterface,
        RegisterDataAccessInterface {

    @Resource
    private FirestoreReactiveRepository<Course> courseRepository;
    @Resource
    private FirestoreReactiveRepository<Timetable> timetableRepository;
    @Resource
    private FirestoreReactiveRepository<User> userRepository;


    public FirebaseDataAccessObject() {
    }

    /**
     * @return all courses stored in firestore
     */
    @Override
    public Map<String, Course> loadCourses() {
        // retrieve Flux of courses from firebase
        Flux<Course> courses = courseRepository.findAll();
        // converts return data into a Mono of type Map
        Mono<Map<String, Course>> courseMap = courses.collectMap(Course::getCourseId);
        // converts type Mono into Map<String, Course> and returns
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
     * @param timetable the timetable to be stored in firestore
     */
    @Override
    public void saveTimetable(Timetable timetable) {
        timetableRepository.save(timetable).block();
    }

    /**
     * @param timetable the timetable to be deleted from firestore
     */
    @Override
    public void deleteTimetable(Timetable timetable) {
        timetableRepository.delete(timetable).block();
    }

    /**
     * @param userId the ID of the user whose timetable is being retrieved
     * @return returns all timetables of user in firestore
     */
    @Override
    public Timetable getTimetable(String userId) {
        return timetableRepository.findById(userId).block();
    }

    /**
     * @param username username to check if it exists in database
     * @return whether the email exists in database
     */
    @Override
    public boolean existsByUsername(String username) {
        return Boolean.TRUE.equals(userRepository.existsById(username).block());
    }

    /**
     * @param username username of new user
     * @param password password of new user
     */
    @Override
    public void userCreate(String username, String password) {
        userRepository.save(new User(username, password)).block();
    }

    /**
     *
     * @param username username of user to delete
     */
    public void userDelete(String username) {
        userRepository.deleteById(username).block();
    }

    /**
     *
     * @param username username of user to fetch from database
     * @return user that was fetched
     */
    @Override
    public User getUser(String username) {
        return userRepository.findById(username).block();
    }

    /**
     *
     * @param user user that is trying to log in
     * @param password user input from password field
     * @return returns whether passwords match
     */
    @Override
    public boolean verifyPassword(User user, String password) {
        return user.getPassword().equals(password);
    }
}
