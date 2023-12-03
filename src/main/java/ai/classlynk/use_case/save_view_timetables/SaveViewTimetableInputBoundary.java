package ai.classlynk.use_case.save_view_timetables;

public interface SaveViewTimetableInputBoundary {
    void executeDatabaseFetch(SaveViewTimetableInputData inputData);
    void executeNewTimetable(SaveViewTimetableInputData inputData);
    void executeSaveTimetable(SaveViewTimetableInputData inputData);
}
