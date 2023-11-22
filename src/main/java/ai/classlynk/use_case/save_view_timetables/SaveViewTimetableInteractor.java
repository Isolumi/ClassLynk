package ai.classlynk.use_case.save_view_timetables;

import ai.classlynk.entity.Timetable;

public class SaveViewTimetableInteractor implements SaveViewTimetableInputBoundary {
    final SaveViewTimetablesDataAccessInterface firebaseRepo;
    final SaveViewTimetableOutputBoundary saveViewPresenter;

    public SaveViewTimetableInteractor(SaveViewTimetablesDataAccessInterface firebaseRepo,
                                       SaveViewTimetableOutputBoundary saveViewPresenter) {
        this.firebaseRepo = firebaseRepo;
        this.saveViewPresenter = saveViewPresenter;
    }

    @Override
    public void execute(boolean logInStatus) {
        if (logInStatus) {
            saveViewPresenter.prepareNotLoggedInView();
        } else {
            Timetable[] timetables = firebaseRepo.load();
            saveViewPresenter.prepareLoggedInView(timetables);
        }
    }
}
