package ai.classlynk.use_case.save_view_timetables;

import ai.classlynk.data_access.TimetableRepository;
import ai.classlynk.entity.SClass;
import ai.classlynk.entity.Timetable;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SaveViewTimetableInteractor implements SaveViewTimetableInputBoundary {
    final SaveViewTimetableOutputBoundary saveViewPresenter;

    @Resource
    TimetableRepository firebaseRepository;
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
        if (!inputData.loggedIn()) {
            saveViewPresenter.prepareNotLoggedInView();
        } else {
            Map<String, java.util.List<SClass>> tt = new HashMap<>();
            SClass a = new SClass(
                    "c1", "lec0102", "10:00:00", "11:00:00",
                    "monday", "bahen", "252 Bloor St W, Toronto, ON M5S 1V6", false);
            SClass b = new SClass(
                    "c2", "lec0103", "12:00:00", "14:00:00",
                    "monday", "bahen", "81A St. Mary Street, Toronto, ON M5S1J4", false);
            SClass c = new SClass(
                    "c3", "lec0104", "10:00:00", "11:00:00",
                    "tuesday", "bahen", "31 King's College Cir, Toronto, ON M5S 1A1", false);
            SClass d = new SClass(
                    "c4", "lec0101", "10:00:00", "11:00:00",
                    "wednesday", "bahen", "93 Charles St W, Toronto, ON M5S 2C7", false);
            SClass e = new SClass(
                    "c5", "lec0105", "10:00:00", "11:00:00",
                    "thursday", "bahen", "100 St George St, Toronto, ON M5S 3G3", false);
            SClass f = new SClass(
                    "c6", "lec0106", "10:00:00", "11:00:00",
                    "friday", "bahen", "10 King's College Rd, Toronto, ON M5S 3G4", false);
            SClass g = new SClass(
                    "c7", "lec0109", "21:00:00", "22:00:00",
                    "friday", "bahen", "25 Harbord street, Toronto, ON M5S 3G5", false);
            java.util.List<SClass> mon = new ArrayList<>();
            mon.add(a);
            mon.add(b);
            java.util.List<SClass> tue = new ArrayList<>();
            tue.add(c);
            java.util.List<SClass> wed = new ArrayList<>();
            wed.add(d);
            java.util.List<SClass> thur = new ArrayList<>();
            thur.add(e);
            thur.add(a);
            List<SClass> fri = new ArrayList<>();
            fri.add(f);
            fri.add(g);
            tt.put("monday", mon);
            tt.put("tuesday", tue);
            tt.put("wednesday", wed);
            tt.put("thursday", thur);
            tt.put("friday", fri);
            Timetable ttt = new Timetable(tt);
            List<Timetable> tts = new ArrayList<>();
            tts.add(ttt);
            SaveViewTimetableOutputData timetables = new SaveViewTimetableOutputData(tts);
            saveViewPresenter.prepareLoggedInView(timetables);
        }
    }
}
