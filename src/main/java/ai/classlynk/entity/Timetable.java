package ai.classlynk.entity;

import com.google.cloud.firestore.annotation.DocumentId;
import com.google.cloud.spring.data.firestore.Document;

import java.beans.Transient;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Document(collectionName = "timetables")
public class Timetable {
    @DocumentId
    private String userId;
    private Map<String, List<SClass>> classes;
    public Timetable(String userId, Map<String, List<SClass>> classes) {
        this.userId = userId;
        this.classes = classes;
    }
    public Timetable(){}

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Map<String, List<SClass>> getClasses() {
        return classes;
    }
    public void setClasses(Map<String, List<SClass>> classes) {
        this.classes = classes;
    }

    public Map<String, String> getFormattedTimetable()
    {
        StringBuilder singleDayFormat;
        Map<String, String> formattedClasses = new HashMap<>();
        for(String day : classes.keySet())
        {
            String capitalizedDay = day.substring(0,1).toUpperCase() + day.substring(1);
            singleDayFormat = new StringBuilder();
            for(SClass singleClass: classes.get(day))
            {
               singleDayFormat.append(singleClass.toString()).append("\n");
            }
            formattedClasses.put(capitalizedDay, singleDayFormat.toString());
        }
        return formattedClasses;
    }
}
