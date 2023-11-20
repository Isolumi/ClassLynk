package ai.classlynk.entity;

import java.util.List;

public class ClassBundle {
    // id for the specific lecture section (ex. lec0101)
    String lectureId;
    List<SClass> classes;

    public ClassBundle(String courseId, List<SClass> classes) {
        this.lectureId = courseId;
        this.classes = classes;
    }

    public String getCourseId() {
        return lectureId;
    }

    public void setCourseId(String courseId) {
        this.lectureId = courseId;
    }

    public List<SClass> getClasses() {
        return classes;
    }

    public void setClasses(List<SClass> classes) {
        this.classes = classes;
    }
}
