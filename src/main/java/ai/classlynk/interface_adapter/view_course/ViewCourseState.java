package ai.classlynk.interface_adapter.ViewCourse;

import ai.classlynk.entity.Course;

import java.util.ArrayList;
import java.util.List;

public class ViewCourseState {
    private List<Course> courses;

    public ViewCourseState(List<Course> courses) {
        this.courses = new ArrayList<>(courses);
    }

    public ViewCourseState() {
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
