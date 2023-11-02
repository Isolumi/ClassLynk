package entity;

import java.util.Map;

public class Timetable {
    private Map<String, Map<String, String>> classes;

    public Map<String, Map<String, String>> getClasses() {
        return classes;
    }

    public void setClasses(Map<String, Map<String, String>> classes) {
        this.classes = classes;
    }

    public Timetable(Map<String, Map<String, String>> classes) {
        this.classes = classes;
    }
}
