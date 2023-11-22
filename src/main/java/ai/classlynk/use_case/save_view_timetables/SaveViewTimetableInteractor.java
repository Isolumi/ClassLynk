package ai.classlynk.use_case.save_view_timetables;

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
        if (inputData.loggedIn()) {
            saveViewPresenter.prepareNotLoggedInView();
        } else {
            SaveViewTimetableOutputData timetables = new SaveViewTimetableOutputData(firebaseRepo.getTimetables());
            saveViewPresenter.prepareLoggedInView(timetables);
        }
    }
}
