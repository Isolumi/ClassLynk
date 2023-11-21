package ai.classlynk.use_case.save_view_timetables;

import ai.classlynk.entity.Timetable;

public interface SaveViewTimetableOutputBoundary {
    void prepareLoggedInView(Timetable[] timetables);
    void prepareNotLoggedInView();
}
