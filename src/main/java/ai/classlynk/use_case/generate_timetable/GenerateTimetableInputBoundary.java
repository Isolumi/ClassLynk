package ai.classlynk.use_case.generate_timetable;

import ai.classlynk.use_case.save_view_timetables.SaveViewTimetableOutputBoundary;

public interface GenerateTimetableInputBoundary {
    void execute(GenerateTimetableInputData generateTimetableInputData);
}
