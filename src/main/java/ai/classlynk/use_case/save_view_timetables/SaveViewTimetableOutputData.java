package ai.classlynk.use_case.save_view_timetables;

import ai.classlynk.entity.Timetable;

import java.util.List;

public record SaveViewTimetableOutputData(List<Timetable> timetables) {
}
