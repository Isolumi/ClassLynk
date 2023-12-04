package ai.classlynk.entity;

import com.google.cloud.firestore.annotation.DocumentId;
import com.google.cloud.spring.data.firestore.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collectionName = "users")
public class User {

    private static User instance;

    @DocumentId
    private String username;

    private String password;
    private Timetable timetables;
    private List<Course> courseKart;


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

    public Timetable getTimetables() {
        return timetables;
    }

    public void setTimetables(Timetable timetables) {
        this.timetables = timetables;
    }

    public List<Course> getCourseKart() {
        return courseKart;
    }

    public void setCourseKart(List<Course> courseKart) {
        this.courseKart = courseKart;
    }

    public User(String id, String password) {
            this.username = id;
            this.password = password;
            this.courseKart = new ArrayList<>();
    }

    public static User getInstance(String id, String password)
    {
        if(instance == null)
        {
            instance = new User(id, password);
        }
        return instance;
    }


    public String formatCart()
    {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < instance.getCourseKart().size(); i++) {
            builder.append(instance.getCourseKart().get(i).getCourseId() + ", " + instance.getCourseKart().get(i).getCourseName());
            if (i < instance.getCourseKart().size() - 1) {
                builder.append("\n");
            }
        }
        return builder.toString();
    }


    /**
     * DO NOT USE, THIS IS ONLY FOR DATA PERSISTENCE
     */
    public User(){}
}
