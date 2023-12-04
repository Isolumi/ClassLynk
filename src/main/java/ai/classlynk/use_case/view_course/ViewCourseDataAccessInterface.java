package ai.classlynk.use_case.view_course;

import ai.classlynk.entity.Course;

import java.util.Map;

public interface ViewCourseDataAccessInterface {
    Map<String, Course> getAllCourses();
}
