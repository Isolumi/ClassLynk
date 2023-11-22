package ai.classlynk.use_case.save_view_timetables;

public class SaveViewTimetableInputData {
    private final boolean loggedIn;

    public SaveViewTimetableInputData(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    boolean isLoggedIn() {
        return loggedIn;
    }
}
