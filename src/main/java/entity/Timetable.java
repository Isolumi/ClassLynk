package entity;

import java.util.List;

public class Timetable {
    private List<Class> classes;

    public List<Class> getClasses() {
        return classes;
    }

    public void setClasses(List<Class> classes) {
        this.classes = classes;
    }

    public Timetable(List<Class> classes) {
        this.classes = classes;
    }
}
