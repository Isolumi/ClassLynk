package entity;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class User {
    private String id;
    private List<Timetable> timetables;
    private List<Course> courseKart;
    private List<Class> classKart;
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



    public User(String id) {
        this.id = id;
        this.courseKart = new ArrayList<>();
        this.classKart = new ArrayList<>();
    }
}
