package ai.classlynk.use_case.save_view_timetables;

public interface SaveViewTimetableOutputBoundary {
    void prepareLoggedInView(SaveViewTimetableOutputData timetable);
    void prepareNotLoggedInView();

    void prepareLoggedInViewError(String s);
}
