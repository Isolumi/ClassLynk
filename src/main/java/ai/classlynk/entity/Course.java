package ai.classlynk.entity;

import com.google.cloud.firestore.annotation.DocumentId;
import com.google.cloud.spring.data.firestore.Document;

import java.util.List;
import java.util.Objects;

@Document(collectionName = "courses")
public class Course {
    @DocumentId
    private String courseId;
    private String courseName;
    private String courseDescription;
    private List<ClassBundle> classBundles;
    private List<SClass> tutorials;

    public Course(String courseName, String courseId, String courseDescription, List<ClassBundle> classBundles, List<SClass> tutorialBundle) {
        this.courseName = courseName;
        this.courseId = courseId;
        this.courseDescription = courseDescription;
        this.classBundles = classBundles;
        this.tutorials = tutorialBundle;
    }
    public Course(){}

    public List<SClass> getTutorials() {
        return tutorials;
    }

    public void setTutorials(List<SClass> tutorials) {
        this.tutorials = tutorials;
    }

    public List<ClassBundle> getClassBundles() {
        return classBundles;
    }

    public void setClassBundles(List<ClassBundle> classBundles) {
        this.classBundles = classBundles;
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

    @Override
    public String toString() {
        return courseId + ", " + courseName + ": " + courseDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(courseId, course.courseId) && Objects.equals(courseName, course.courseName) && Objects.equals(courseDescription, course.courseDescription) && Objects.equals(classBundles, course.classBundles) && Objects.equals(tutorials, course.tutorials);
    }
}