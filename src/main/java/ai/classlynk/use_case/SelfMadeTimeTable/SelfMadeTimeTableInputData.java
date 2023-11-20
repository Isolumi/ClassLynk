package ai.classlynk.use_case.SelfMadeTimeTable;

import ai.classlynk.entity.Course;
import ai.classlynk.entity.SClass;

public class SelfMadeTimeTableInputData {
    private Course course;
    private String startTime;
    private String weekday;

    public SelfMadeTimeTableInputData (Course course, String startTime, String weekday) {
        this.course = course;
        this.startTime = startTime;
        this.weekday = weekday;
    }

    public Course getCourse() {
        return course;
    }

    public String getWeekday() {
        return weekday;
    }

    public String getStartTime() {
        return startTime;
    }
}
