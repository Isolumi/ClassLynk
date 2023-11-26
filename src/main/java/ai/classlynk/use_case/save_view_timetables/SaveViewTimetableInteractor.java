package ai.classlynk.use_case.save_view_timetables;

import ai.classlynk.data_access.FirebaseRepository;
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
        if (!inputData.loggedIn()) {
            saveViewPresenter.prepareNotLoggedInView();
        } else {
            Map<String, List<SClass>> tt = new HashMap<>();
            SClass a = new SClass(
                    "c1", "lec0102", "10:00:00", "11:00:00",
                    "monday", "bahen", "theresomerwhere", false);
            SClass b = new SClass(
                    "c2", "lec0103", "12:00:00", "14:00:00",
                    "monday", "bahen", "theresomerwhere", false);
            SClass c = new SClass(
                    "c3", "lec0104", "10:00:00", "11:00:00",
                    "tuesday", "bahen", "theresomerwhere", false);
            SClass d = new SClass(
                    "c4", "lec0101", "10:00:00", "11:00:00",
                    "wednesday", "bahen", "theresomerwhere", false);
            SClass e = new SClass(
                    "c5", "lec0105", "10:00:00", "11:00:00",
                    "thursday", "bahen", "theresomerwhere", false);
            SClass f = new SClass(
                    "c6", "lec0106", "10:00:00", "11:00:00",
                    "friday", "bahen", "theresomerwhere", false);
            SClass g = new SClass(
                    "c7", "lec0109", "21:00:00", "22:00:00",
                    "friday", "bahen", "theresomerwhere", false);
            List<SClass> mon = new ArrayList<>();
            mon.add(a);
            mon.add(b);
            List<SClass> tue = new ArrayList<>();
            tue.add(c);
            List<SClass> wed = new ArrayList<>();
            wed.add(d);
            List<SClass> thur = new ArrayList<>();
            thur.add(e);
            List<SClass> fri = new ArrayList<>();
            fri.add(f);
            fri.add(g);
            tt.put("monday", mon);
            tt.put("tuesday", tue);
            tt.put("wednesday", wed);
            tt.put("thursday", thur);
            tt.put("friday", fri);
            List<Timetable> tts = new ArrayList<>();
            Timetable ttt = new Timetable(tt);
            tts.add(ttt);
            SaveViewTimetableOutputData timetables = new SaveViewTimetableOutputData(tts.toArray(new Timetable[0]));
            saveViewPresenter.prepareLoggedInView(timetables);
        }
    }
}
