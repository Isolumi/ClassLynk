package use_case.explore_courses;

import entity.Course;

import java.util.ArrayList;

public interface ExploreCoursesDataAccessInterface {

    ArrayList<Course> loadCourses();
}
