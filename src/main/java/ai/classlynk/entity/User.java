package ai.classlynk.entity;

import com.google.cloud.firestore.annotation.DocumentId;
import com.google.cloud.spring.data.firestore.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collectionName = "users")
public class User {
    @DocumentId
    private String username;

    private String password;
    private List<Timetable> timetables;
    private List<Course> courseKart;
    private List<SClass> classKart;


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public List<SClass> getClassKart() {
        return classKart;
    }

    public void setClassKart(List<SClass> classKart) {
        this.classKart = classKart;
    }



    public User(String id, String password) {
        this.username = id;
        this.password = password;
        this.courseKart = new ArrayList<>();
        this.classKart = new ArrayList<>();
    }
    public User(){}
}
