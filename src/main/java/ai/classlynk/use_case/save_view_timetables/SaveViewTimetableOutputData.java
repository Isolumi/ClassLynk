package ai.classlynk.use_case.save_view_timetables;

import ai.classlynk.entity.Timetable;

public class SaveViewTimetableOutputData {
    private final Timetable[] timetables;

    public SaveViewTimetableOutputData(Timetable[] timetables) {
        this.timetables = timetables;
    }

    public Timetable[] getTimetables() {
        return this.timetables;
    }
}
