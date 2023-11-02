package entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Timetable {
    private Map<String, ArrayList<String>> classes;

    public Map<String, ArrayList<String>> getClasses() {
        return classes;
    }

    public void setClasses(Map<String, ArrayList<String>> classes) {
        this.classes = classes;
    }

    public Timetable(Map<String, ArrayList<String>> classes) {
        this.classes = classes;
    }

    //format as an array of Strings with each index being a day of the week, and then comma split details of each course in
    //that day
    public String[] castToStringArray() {
        return new String[]{""};
    }
}
