package ai.classlynk.interface_adapter.save_view_timetables;

import ai.classlynk.entity.Timetable;

import java.util.List;

public class SaveViewTimetableState {
    private Timetable timetables;

    public SaveViewTimetableState(SaveViewTimetableState copy) {
        this.timetables = copy.timetables;
    }
    public SaveViewTimetableState() {
    }

    public Timetable getTimetables() {
        return timetables;
    }

    public void setTimetables(Timetable timetables) {
        this.timetables = timetables;
    }
}
