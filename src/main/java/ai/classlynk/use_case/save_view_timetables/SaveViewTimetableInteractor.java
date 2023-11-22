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
    public void execute(SaveViewTimetableInputData inputData) {
        if (inputData.isLoggedIn()) {
            saveViewPresenter.prepareNotLoggedInView();
        } else {
            SaveViewTimetableOutputData timetables = new SaveViewTimetableOutputData(firebaseRepo.load());
            saveViewPresenter.prepareLoggedInView(timetables);
        }
    }
}
