package use_case.save_view_time_tables;

import entity.Timetable;
import entity.User;

public interface SaveViewTimetablesDataAccessInterface {
    //save timetable linked to the user
    void save(User user, Timetable timetable);

    //load timetables linked to the user
    Timetable load(User user);
}
