package ai.classlynk.use_case.save_view_timetables;

import ai.classlynk.entity.Timetable;

import java.io.IOException;
import java.util.List;
public interface SaveViewTimetablesDataAccessInterface {
    //save timetable linked to the user
    void saveTimetable(Timetable timetable) throws IOException;

    //delete a timetable linked to the user
    void deleteTimetable(Timetable timetable);

    //load timetables linked to the user
    Timetable getTimetable(String userId);
}