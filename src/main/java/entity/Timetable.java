package entity;

import java.util.List;
import java.util.Map;

import com.google.cloud.firestore.annotation.DocumentId;
import com.google.cloud.spring.data.firestore.Document;

@Document(collectionName = "timetables")
public class Timetable {
    @DocumentId
    private Map<String, List<Class>> classes;

    public Map<String, List<Class>> getClasses() {
        return classes;
    }
    public void setClasses(Map<String, List<Class>> classes) {
        this.classes = classes;
    }
}
