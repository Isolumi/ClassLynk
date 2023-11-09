package entity;

import java.util.List;

public class ClassBundle {
    String id;
    List<SClass> classes;

    public ClassBundle(String id, List<SClass> classes) {
        this.id = id;
        this.classes = classes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<SClass> getClasses() {
        return classes;
    }

    public void setClasses(List<SClass> classes) {
        this.classes = classes;
    }
}
