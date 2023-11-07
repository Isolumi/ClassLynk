package use_case.explore_courses;

import entity.Course;

import java.util.Map;

public interface ExploreCoursesDataAccessInterface {

    Map<String, Course> loadCourses();
}
