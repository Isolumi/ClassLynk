package entity;

import java.time.LocalDateTime;

public class Class {
    private String id;
    private boolean isTutorial;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isTutorial() {
        return isTutorial;
    }

    public void setTutorial(boolean tutorial) {
        isTutorial = tutorial;
    }

    //the database is only storing doy of the week and hrs:mins, so make sure you are using that
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

    public Class(String id, boolean isTutorial, LocalDateTime time, String building, String location, int duration) {
        this.id = id;
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
