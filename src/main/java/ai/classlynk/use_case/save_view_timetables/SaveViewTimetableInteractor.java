package ai.classlynk.use_case.save_view_timetables;

import ai.classlynk.data_access.FirebaseDataAccessObject;

public class SaveViewTimetableInteractor implements SaveViewTimetableInputBoundary {
    final SaveViewTimetableOutputBoundary saveViewPresenter;
    final SaveViewTimetablesDataAccessInterface firebaseDataAccessObject;

    public SaveViewTimetableInteractor(SaveViewTimetableOutputBoundary saveViewPresenter,
                                       FirebaseDataAccessObject firebaseDataAccessObject) {
        this.saveViewPresenter = saveViewPresenter;
        this.firebaseDataAccessObject = firebaseDataAccessObject;
    }

    @Override
    public void execute(SaveViewTimetableInputData inputData) {
        if (inputData.loggedIn()) {
            saveViewPresenter.prepareNotLoggedInView();
        } else {
            SaveViewTimetableOutputData timetables = new SaveViewTimetableOutputData(
                    firebaseDataAccessObject.getTimetable(inputData.username()));
            saveViewPresenter.prepareLoggedInView(timetables);
        }
    }
}