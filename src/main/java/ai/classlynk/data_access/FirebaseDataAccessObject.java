package ai.classlynk.data_access;

import ai.classlynk.entity.Course;
import ai.classlynk.entity.Timetable;
import ai.classlynk.entity.User;
import ai.classlynk.use_case.explore_courses.ExploreCoursesDataAccessInterface;
import ai.classlynk.use_case.save_view_timetables.SaveViewTimetablesDataAccessInterface;
import ai.classlynk.use_case.user_auth.login.LoginDataAccessInterface;
import ai.classlynk.use_case.user_auth.register.RegisterDataAccessInterface;
import com.google.cloud.spring.data.firestore.FirestoreReactiveRepository;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.auth.UserRecord.CreateRequest;
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

    private FirebaseAuth fireAuth;
    FirebaseApp.initializeApp();

    /**
     *
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
     *
     * @param timetable the timetable to be stored in firestore
     */
    @Override
    public void saveTimetable(Timetable timetable) {
        timetableRepository.save(timetable).block();
    }

    /**
     *
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

    @Override
    public boolean existedByName(String Name) {
        return false;
    }

    @Override
    public void userCreate(String email, String password) throws FirebaseAuthException {
        fireAuth = FirebaseAuth.getInstance();
        CreateRequest request = new CreateRequest()
                .setEmail(email)
                .setPassword(password);
        fireAuth.createUser(request);
    }

    @Override
    public boolean existsByName(String username) {
        return false;
    }

    @Override
    public User getUser(String username) {
        return null;
    }

    @Override
    public boolean verifyPassword(User user) {
        return false;
    }
}
