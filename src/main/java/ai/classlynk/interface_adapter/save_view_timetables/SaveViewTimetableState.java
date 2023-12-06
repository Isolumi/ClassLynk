package ai.classlynk.interface_adapter.save_view_timetables;

import ai.classlynk.entity.Timetable;


public class SaveViewTimetableState {
    private Timetable timetables;



    private String error;

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
    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
