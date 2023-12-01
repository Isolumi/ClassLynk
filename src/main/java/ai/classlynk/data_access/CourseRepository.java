package ai.classlynk.data_access;

import ai.classlynk.entity.Course;
import com.google.cloud.spring.data.firestore.FirestoreReactiveRepository;
import org.jetbrains.annotations.NotNull;
import reactor.core.publisher.Flux;

public interface CourseRepository extends FirestoreReactiveRepository<Course> {
}
