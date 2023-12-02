package ai.classlynk.interface_adapter.save_view_timetables;

import ai.classlynk.use_case.save_view_timetables.SaveViewTimetableInputBoundary;
import ai.classlynk.use_case.save_view_timetables.SaveViewTimetableInputData;

public class SaveViewTimetableController {
    final SaveViewTimetableInputBoundary saveViewTimetableInputBoundary;
    public SaveViewTimetableController(SaveViewTimetableInputBoundary saveViewTimetableInputBoundary) {
        this.saveViewTimetableInputBoundary = saveViewTimetableInputBoundary;
    }

    public void execute(boolean logInStatus, String username) {
        SaveViewTimetableInputData saveViewTimetableInputData = new SaveViewTimetableInputData(logInStatus, username);
        saveViewTimetableInputBoundary.executeDatabaseFetch(saveViewTimetableInputData);
    }
}
