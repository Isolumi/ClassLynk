package ai.classlynk.entity;

import java.util.List;

public class ClassBundle {
    String courseId;
    List<SClass> classes;

    public ClassBundle(String courseId, List<SClass> classes) {
        this.courseId = courseId;
        this.classes = classes;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public List<SClass> getClasses() {
        return classes;
    }

    public void setClasses(List<SClass> classes) {
        this.classes = classes;
    }
}
