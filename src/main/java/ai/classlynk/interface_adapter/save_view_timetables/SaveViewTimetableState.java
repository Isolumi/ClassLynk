package ai.classlynk.interface_adapter.save_view_timetables;

import ai.classlynk.entity.Timetable;

import java.util.List;

public class SaveViewTimetableState {
    private List<Timetable> timetables;

    public SaveViewTimetableState(SaveViewTimetableState copy) {
        this.timetables = copy.timetables;
    }
    public SaveViewTimetableState() {
    }

    public List<Timetable> getTimetables() {
        return timetables;
    }

    public void setTimetables(List<Timetable> timetables) {
        this.timetables = timetables;
    }
}
