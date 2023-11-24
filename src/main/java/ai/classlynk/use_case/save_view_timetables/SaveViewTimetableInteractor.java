package ai.classlynk.use_case.save_view_timetables;

import ai.classlynk.data_access.FirebaseRepository;
import ai.classlynk.entity.SClass;
import ai.classlynk.entity.Timetable;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SaveViewTimetableInteractor implements SaveViewTimetableInputBoundary {
    final SaveViewTimetableOutputBoundary saveViewPresenter;

    @Resource
    FirebaseRepository firebaseRepository;
    public SaveViewTimetableInteractor(SaveViewTimetableOutputBoundary saveViewPresenter) {
        this.saveViewPresenter = saveViewPresenter;
    }

//    @Override
//    public void execute(SaveViewTimetableInputData inputData) {
//        if (inputData.loggedIn()) {
//            saveViewPresenter.prepareNotLoggedInView();
//        } else {
//            SaveViewTimetableOutputData timetables = new SaveViewTimetableOutputData(firebaseRepository.getTimetables());
//            saveViewPresenter.prepareLoggedInView(timetables);
//        }
//    }
    // TODO: test code, remove later
    @Override
    public void execute(SaveViewTimetableInputData inputData) {
        if (inputData.loggedIn()) {
            saveViewPresenter.prepareNotLoggedInView();
        } else {
            Timetable[] tts;
            Map<String, List<SClass>> tt = new HashMap<>();
            SaveViewTimetableOutputData timetables = new SaveViewTimetableOutputData(tts);
            saveViewPresenter.prepareLoggedInView(timetables);
        }
    }
}
