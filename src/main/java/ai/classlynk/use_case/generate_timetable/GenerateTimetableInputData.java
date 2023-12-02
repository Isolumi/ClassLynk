package ai.classlynk.use_case.generate_timetable;

import ai.classlynk.entity.Course;

import java.util.List;

public class GenerateTimetableInputData {

    final private List<Course> courses;

    public GenerateTimetableInputData(List<Course> courses){
        this.courses = courses;
    }

    List<Course> getCourses(){
        return courses;
    }
}
