package ai.classlynk.use_case.explore_courses;

import ai.classlynk.entity.Course;

import java.util.Map;

public interface ExploreCoursesDataAccessInterface {

    Map<String, Course> loadCourses();
}
