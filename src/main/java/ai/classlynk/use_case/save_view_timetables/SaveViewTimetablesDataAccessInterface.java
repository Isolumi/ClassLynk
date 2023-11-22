package ai.classlynk.use_case.save_view_timetables;

import ai.classlynk.entity.Timetable;

import java.util.ArrayList;

public interface SaveViewTimetablesDataAccessInterface {
    //save timetable linked to the user
    void save(Timetable timetable);

    //load timetables linked to the user
    Timetable[] load();

    //delete a timetable linked to the user
    void delete(Timetable timetable);
}