package ai.classlynk.use_case.generate_timetable;

import ai.classlynk.IntegrationTest;
import ai.classlynk.data_access.APIDataAccessObject;
import ai.classlynk.data_access.FirebaseDataAccessObject;
import ai.classlynk.entity.ClassBundle;
import ai.classlynk.entity.Course;
import ai.classlynk.entity.SClass;
import ai.classlynk.entity.Timetable;
import ai.classlynk.use_case.save_view_timetables.SaveViewTimetableOutputBoundary;
import ai.classlynk.use_case.save_view_timetables.SaveViewTimetableOutputData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class GenerateTimetableInteractorTest extends IntegrationTest {
    @Autowired
    FirebaseDataAccessObject database;
    @Test
    void execute() {
        APIDataAccessObject dao = new APIDataAccessObject();
        Collection<Course> courses = database.getAllCourses().values();
        List<Course> classList = new ArrayList<>(courses);
        classList = classList.subList(0, 2);

        ClassBundle bundleOne = classList.get(0).getClassBundles().get(0);
        ClassBundle bundleTwo = classList.get(1).getClassBundles().get(1);
        SClass tutOne = classList.get(0).getTutorials().get(0);
        SClass tutTwo = classList.get(1).getTutorials().get(0);

        Map<String, List<SClass>> expectedClasses = new HashMap<>();
        List<SClass> mon = new ArrayList<>();
        mon.add(bundleOne.getClasses().get(0));
        mon.add(tutOne);
        List<SClass> tues = new ArrayList<>();
        tues.add(bundleTwo.getClasses().get(0));
        tues.add(tutTwo);
        List<SClass> wed = new ArrayList<>();
        wed.add(bundleOne.getClasses().get(1));
        List<SClass> thurs = new ArrayList<>();
        List<SClass> fri = new ArrayList<>();

        expectedClasses.put("Monday", mon);
        expectedClasses.put("Tuesday", tues);
        expectedClasses.put("Wednesday", wed);
        expectedClasses.put("Thursday", thurs);
        expectedClasses.put("Friday", fri);


        Timetable expectedTimetable = new Timetable("", expectedClasses);


        SaveViewTimetableOutputBoundary presenter = new SaveViewTimetableOutputBoundary() {
            @Override
            public void prepareLoggedInView(SaveViewTimetableOutputData timetable) {
                assert (expectedTimetable.getClasses().equals(timetable.timetable().getClasses()));
            }

            @Override
            public void prepareNotLoggedInView() {

            }

            @Override
            public void prepareLoggedInViewError(String s) {

            }
        };

        GenerateTimetableInteractor generateTimetableInteractor = new GenerateTimetableInteractor(presenter, dao);
        generateTimetableInteractor.execute(new GenerateTimetableInputData(classList));
    }
}
