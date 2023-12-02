package ai.classlynk.use_case;

import ai.classlynk.IntegrationTest;
import ai.classlynk.data_access.FirebaseDataAccessObject;
import ai.classlynk.entity.SClass;
import ai.classlynk.entity.Timetable;
import ai.classlynk.interface_adapter.save_view_timetables.SaveViewTimetablePresenter;
import ai.classlynk.use_case.save_view_timetables.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SaveViewTimetableInteractorTest extends IntegrationTest {
    @Autowired
    FirebaseDataAccessObject firebaseDataAccessObject;
    @Test
    void testExecute() {
        Map<String, List<SClass>> classes = new HashMap<>();
        Timetable testTimetable = new Timetable("test-user", classes);
        firebaseDataAccessObject.saveTimetable(testTimetable);
        SaveViewTimetableOutputBoundary presenter = new SaveViewTimetableOutputBoundary() {
            @Override
            public void prepareLoggedInView(SaveViewTimetableOutputData timetable) {
                assertEquals(timetable.timetable().getUserId(), testTimetable.getUserId());
            }

            @Override
            public void prepareNotLoggedInView() {

            }
        };

        SaveViewTimetableInputBoundary interactor = new SaveViewTimetableInteractor(presenter,
                firebaseDataAccessObject);
        interactor.execute(new SaveViewTimetableInputData(true, "test-user"));
        firebaseDataAccessObject.deleteTimetable(testTimetable);
    }
}
