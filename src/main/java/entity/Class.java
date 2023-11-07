package entity;

import java.time.LocalTime;

public class Class {
    private String courseId;

    private String classId;

    private float duration;
    private LocalTime time;

    public String getWeekday() {
        return weekday;
    }

    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }

    private String weekday;

    private String building;
    private String location;

    public Class(String courseId, String classId, float duration, LocalTime time, String weekday, String building, String location, boolean isTutorial) {
        this.courseId = courseId;
        this.classId = classId;
        this.duration = duration;
        this.time = time;
        this.weekday = weekday;
        this.building = building;
        this.location = location;
        this.isTutorial = isTutorial;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    private boolean isTutorial;

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public boolean isTutorial() {
        return isTutorial;
    }

    public void setTutorial(boolean tutorial) {
        isTutorial = tutorial;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}