package ai.classlynk.use_case.save_view_timetables;

import ai.classlynk.entity.Timetable;

public interface SaveViewTimetablesDataAccessInterface {
    //save timetable linked to the user
    void save(Timetable timetable);

    //load timetables linked to the user
    Timetable[] getTimetables();

    //delete a timetable linked to the user
    void delete(Timetable timetable);
}