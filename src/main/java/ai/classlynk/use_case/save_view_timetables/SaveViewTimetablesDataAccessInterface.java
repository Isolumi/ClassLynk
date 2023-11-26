package ai.classlynk.use_case.save_view_timetables;

import ai.classlynk.entity.Timetable;

public interface SaveViewTimetablesDataAccessInterface {
    //save timetable linked to the user
    void saveTimetable(Timetable timetable);

    //delete a timetable linked to the user
    void deleteTimetable(Timetable timetable);

    //load timetables linked to the user
    Timetable[] getTimetables();
}