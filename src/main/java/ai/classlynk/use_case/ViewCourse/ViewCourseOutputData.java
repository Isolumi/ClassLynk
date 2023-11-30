package ai.classlynk.use_case.ViewCourse;

import ai.classlynk.entity.Course;

import java.util.List;

public class ViewCourseOutputData {
    List<Course> course;

    public ViewCourseOutputData(List<Course> courses) {
        this.course = courses;
    }

    public List<Course> getAllCourses() {
        return course;
    }
}
