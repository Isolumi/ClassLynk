package entity;
import java.util.List;
import java.util.Map;

public class Timetable {
    private Map<String, List<Class>> classes;

    public Map<String, List<Class>> getClasses() {
        return classes;
    }

    public void setClasses(Map<String, List<Class>> classes) {
        this.classes = classes;
    }

    public Timetable(Map<String, List<Class>> classes) {
        this.classes = classes;
    }
}
