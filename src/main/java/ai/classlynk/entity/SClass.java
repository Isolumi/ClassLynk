package ai.classlynk.entity;

public class SClass {
    private String courseId;
    private String classId;
    private String startTime;
    private String endTime;
    private String weekday;
    private String building;
    private String location;

    public SClass(String courseId, String classId, String endTime, String startTime, String weekday, String building, String location, boolean isTutorial) {
        this.courseId = courseId;
        this.classId = classId;
        this.endTime = endTime;
        this.startTime = startTime;
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

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
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

    public String getWeekday() {
        return weekday;
    }

    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }
}