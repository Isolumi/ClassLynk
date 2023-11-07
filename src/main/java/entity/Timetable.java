package entity;

import java.util.List;
import java.util.Map;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Timetable {
    @Id
    private Map<String, List<Class>> classes;

    public Map<String, List<Class>> getClasses() {
        return classes;
    }
    public void setClasses(Map<String, List<Class>> classes) {
        this.classes = classes;
    }
}
