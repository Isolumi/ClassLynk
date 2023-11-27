package ai.classlynk.data_access;

import ai.classlynk.entity.*;
import com.google.cloud.spring.data.firestore.FirestoreReactiveRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface TimetableRepository extends FirestoreReactiveRepository<Timetable> {
    @NotNull
    Flux<Timetable> findAll();
}