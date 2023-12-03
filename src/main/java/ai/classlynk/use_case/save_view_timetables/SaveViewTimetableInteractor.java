package ai.classlynk.use_case.save_view_timetables;

import ai.classlynk.data_access.FirebaseDataAccessObject;
import ai.classlynk.entity.Timetable;
import ai.classlynk.entity.User;

import java.io.IOException;
import java.sql.Time;

public class SaveViewTimetableInteractor implements SaveViewTimetableInputBoundary {
    final SaveViewTimetableOutputBoundary saveViewPresenter;
    final SaveViewTimetablesDataAccessInterface firebaseDataAccessObject;

    public SaveViewTimetableInteractor(SaveViewTimetableOutputBoundary saveViewPresenter,
                                       FirebaseDataAccessObject firebaseDataAccessObject) {
        this.saveViewPresenter = saveViewPresenter;
        this.firebaseDataAccessObject = firebaseDataAccessObject;
    }

    @Override
    public void executeDatabaseFetch(SaveViewTimetableInputData inputData) {
        if (!inputData.loggedIn()) {
            saveViewPresenter.prepareNotLoggedInView();
        } else {
            Timetable timetable = firebaseDataAccessObject.getTimetable(inputData.username());
            SaveViewTimetableOutputData outputData = new SaveViewTimetableOutputData(timetable);
            saveViewPresenter.prepareLoggedInView(outputData);
        }
    }

    @Override
    public void executeNewTimetable(SaveViewTimetableInputData inputData) {
        if (!inputData.loggedIn()) {
            saveViewPresenter.prepareNotLoggedInView();
        } else {
            SaveViewTimetableOutputData outputData = new SaveViewTimetableOutputData(inputData.timetable());
            saveViewPresenter.prepareLoggedInView(outputData);
        }
    }

    @Override
    public void executeSaveTimetable(SaveViewTimetableInputData inputData) throws IOException {
        if (!inputData.loggedIn()) {
            saveViewPresenter.prepareNotLoggedInView();
        } else {
            firebaseDataAccessObject.saveTimetable(inputData.timetable());
            SaveViewTimetableOutputData outputData = new SaveViewTimetableOutputData(inputData.timetable());
            //update local user instance
            User.getInstance("", "").setTimetables(outputData.timetable());
            saveViewPresenter.prepareLoggedInView(outputData);
        }
    }
}