package app;

import data_access.DatabaseDataAccessObject;
import entity.Class;
import entity.Course;
import entity.Timetable;
import entity.User;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Map;

public class Main {

    public static void main(String[] args)
    {
        DatabaseDataAccessObject dba = new DatabaseDataAccessObject();
//        ArrayList<Timetable> tt = dba.load(new User("asdf123:)"));
        ArrayList<Class> wai = new ArrayList<>();
//        Class temp =  tt.get(0).getClasses().get("Monday").get(0);
//        wai.add(temp);
//        temp.setBuilding("dave's chicken");
//        wai.add(temp);


        wai.add(new Class("tt101", "lec0101", 60, LocalTime.now(), "Monday", "Bahen", "40 St George St, Toronto, ON M5S 2E4", false));
        wai.add(new Class("tt101", "tut0101", 60, LocalTime.now(), "Monday", "Bahen", "40 St George St, Toronto, ON M5S 2E4", true));
        Course temp2 = new Course("test course", "tt101", "idk", wai);
        dba.saveCourse(temp2);

        Map<String, Course> test =  dba.loadCourses();

    }
}
