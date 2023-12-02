package ai.classlynk.data_access;

import ai.classlynk.entity.Course;
import com.google.cloud.spring.data.firestore.FirestoreReactiveRepository;

public interface CourseRepository extends FirestoreReactiveRepository<Course> {
}
