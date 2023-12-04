package ai.classlynk.use_case.ViewCourse;

import ai.classlynk.entity.Course;

import java.util.List;
import java.util.Map;

public interface ViewCourseDataAccessInterface {
    Map<String, Course> getAllCourses();
}
