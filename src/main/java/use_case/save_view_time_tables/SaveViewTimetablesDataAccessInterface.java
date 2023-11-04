package use_case.save_view_time_tables;

import entity.Timetable;
import entity.User;

import java.util.ArrayList;

public interface SaveViewTimetablesDataAccessInterface {
    //save timetable linked to the user
    void save(User user, Timetable timetable);

    //load timetables linked to the user
    ArrayList<Timetable> load(User user);
}
