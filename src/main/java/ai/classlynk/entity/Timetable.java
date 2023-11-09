package ai.classlynk.entity;

import java.util.List;
import java.util.Map;

import com.google.cloud.firestore.annotation.DocumentId;
import com.google.cloud.spring.data.firestore.Document;

@Document(collectionName = "timetables")
public class Timetable {
    public Timetable(Map<String, List<SClass>> classes) {
        this.classes = classes;
    }

    @DocumentId
    private Map<String, List<SClass>> classes;

    public Map<String, List<SClass>> getClasses() {
        return classes;
    }
    public void setClasses(Map<String, List<SClass>> classes) {
        this.classes = classes;
    }
}
