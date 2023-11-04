package entity;

import java.time.LocalDateTime;

public class Class {
    private String courseId;

    private String classId;

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
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

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
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

    public Class(String courseId, String classId, boolean isTutorial, LocalDateTime time, String building, String location, int duration) {
        this.courseId = courseId;
        this.classId = classId;
        this.isTutorial = isTutorial;
        this.time = time;
        this.building = building;
        this.location = location;
        this.duration = duration;
    }

    private LocalDateTime time;
    private String building;
    private String location;

    private int duration;
}
