package ai.classlynk.use_case.save_view_timetables;

import ai.classlynk.entity.Timetable;

public record SaveViewTimetableInputData(boolean loggedIn, String username, Timetable timetable) {
}
