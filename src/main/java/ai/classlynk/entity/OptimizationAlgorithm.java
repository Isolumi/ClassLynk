package ai.classlynk.entity;

import ai.classlynk.data_access.APIDataAccessObject;

import java.util.List;

public interface OptimizationAlgorithm {
    public Timetable generateTimetable(List<Course> courses, APIDataAccessObject dao);
}
