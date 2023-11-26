package ai.classlynk.data_access;

import ai.classlynk.entity.*;
import ai.classlynk.use_case.explore_courses.ExploreCoursesDataAccessInterface;
import ai.classlynk.use_case.generate_timetable.TimetableGeneratorDataAccessInterface;
import ai.classlynk.use_case.save_view_timetables.SaveViewTimetablesDataAccessInterface;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import ai.classlynk.use_case.explore_courses.ExploreCoursesDataAccessInterface;
import ai.classlynk.use_case.generate_timetable.TimetableGeneratorDataAccessInterface;
import ai.classlynk.use_case.save_view_timetables.SaveViewTimetablesDataAccessInterface;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class FirebaseRepository implements ExploreCoursesDataAccessInterface, TimetableGeneratorDataAccessInterface, SaveViewTimetablesDataAccessInterface {
    @Resource
    private Firestore firestore;

    // Test code for inserting one course into firebase

    public void insertCourse() throws ExecutionException, InterruptedException {


//        this.firestore.document("courses/ESS105")
//                .set(new Course("Our home planet", "ESS105", "The nature and evolution of the Earth; plate tectonics; rocks and minerals; volcanism; geological time; fossils; geology of Ontario; environmental issues; and human interactions with the planet.", bundleList, tutorialList6));
    }

    @Override
    public Map<String, Course> loadCourses() {
        return null;
    }

    @Override
    public float getRouteLength(String origin, String destination) {
        return 0;
    }


    public void save(User user, Timetable timetable) {

    }


    public ArrayList<Timetable> load(User user) {
        return null;
    }


    public void delete(User user, Timetable timetable) {

    }

    @Override
    public void save(Timetable timetable) {

    }

    @Override
    public Timetable[] getTimetables() {
        return new Timetable[0];
    }


    public ArrayList<Timetable> load() {
        return null;
    }

    @Override
    public void delete(Timetable timetable) {

    }
}