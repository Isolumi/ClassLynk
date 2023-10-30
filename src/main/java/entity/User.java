package entity;

import java.sql.Time;
import java.util.List;

public class User {
    private String id;
    private List<Timetable> timetables;
    private List<Course> courseKart;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Timetable> getTimetables() {
        return timetables;
    }

    public void setTimetables(List<Timetable> timetables) {
        this.timetables = timetables;
    }

    public List<Course> getCourseKart() {
        return courseKart;
    }

    public void setCourseKart(List<Course> courseKart) {
        this.courseKart = courseKart;
    }

    public List<Class> getClassKart() {
        return classKart;
    }

    public void setClassKart(List<Class> classKart) {
        this.classKart = classKart;
    }

    private List<Class> classKart;

    public User(String id, List<Timetable> timetables, List<Course> courseKart, List<Class> classKart) {
        this.id = id;
        this.timetables = timetables;
        this.courseKart = courseKart;
        this.classKart = classKart;
    }
}
