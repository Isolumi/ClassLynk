package ai.classlynk.entity;

import java.util.List;
import java.util.Objects;

public class ClassBundle {
    // id for the specific lecture section (ex. lec0101)
    String lectureId;
    List<SClass> classes;

    public ClassBundle(String lectureId, List<SClass> classes) {
        this.lectureId = lectureId;
        this.classes = classes;
    }
    public ClassBundle(){}

    public String getLectureId() {
        return lectureId;
    }

    public void setLectureId(String courseId) {
        this.lectureId = courseId;
    }

    public List<SClass> getClasses() {
        return classes;
    }

    public void setClasses(List<SClass> classes) {
        this.classes = classes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassBundle bundle = (ClassBundle) o;
        return Objects.equals(lectureId, bundle.lectureId) && Objects.equals(classes, bundle.classes);
    }
}
