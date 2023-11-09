package ai.classlynk.data_access;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import ai.classlynk.entity.ClassBundle;
import ai.classlynk.entity.Course;
import ai.classlynk.entity.SClass;
import org.springframework.stereotype.Repository;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Repository
public class FirebaseRepository {
    @Resource
    private Firestore firestore;

    // Test code for inserting one course into firebase
    public void insertCourse() throws ExecutionException, InterruptedException {
        SClass tempClass = new SClass("mat137", "lec101", "01:00:00",
                "02:00:00", "monday", "bahen", "somewhere there",
                false);
        List<SClass> classList = new ArrayList<>();
        classList.add(tempClass);
        ClassBundle tempBundle = new ClassBundle("mat137", classList);
        List<ClassBundle> bundleList = new ArrayList<>();
        bundleList.add(tempBundle);

        ApiFuture<WriteResult> apiFuture = this.firestore.document("courses/mat137")
                .set(new Course("meth", "mat137", "bad", bundleList));
    }
}