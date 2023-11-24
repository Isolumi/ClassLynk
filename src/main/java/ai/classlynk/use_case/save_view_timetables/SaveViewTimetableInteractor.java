package ai.classlynk.use_case.save_view_timetables;

import ai.classlynk.data_access.FirebaseRepository;

import javax.annotation.Resource;

public class SaveViewTimetableInteractor implements SaveViewTimetableInputBoundary {
    final SaveViewTimetableOutputBoundary saveViewPresenter;

    @Resource
    FirebaseRepository firebaseRepository;
    public SaveViewTimetableInteractor(SaveViewTimetableOutputBoundary saveViewPresenter) {
        this.saveViewPresenter = saveViewPresenter;
    }

    @Override
    public void execute(SaveViewTimetableInputData inputData) {
        if (inputData.loggedIn()) {
            saveViewPresenter.prepareNotLoggedInView();
        } else {
            SaveViewTimetableOutputData timetables = new SaveViewTimetableOutputData(firebaseRepository.getTimetables());
            saveViewPresenter.prepareLoggedInView(timetables);
        }
    }
}
