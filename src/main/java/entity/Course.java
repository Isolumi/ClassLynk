package entity;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Course {
    @Id
    private String courseId;
    private String courseName;
    private String courseDescription;
    @ElementCollection
    private List<ClassBundle> classBundles;

    public Course(String courseName, String courseId, String courseDescription, List<Class> classes) {
        this.courseName = courseName;
        this.courseId = courseId;
        this.courseDescription = courseDescription;
        this.classes = classes;
    }

    public Course() {

    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public List<Class> getClasses() {
        return classes;
    }

    public void setClasses(List<Class> classes) {
        this.classes = classes;
    }
}