package ai.classlynk.data_access;

import ai.classlynk.entity.*;
import ai.classlynk.use_case.save_view_timetables.SaveViewTimetablesDataAccessInterface;
import com.google.cloud.spring.data.firestore.FirestoreReactiveRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface TimetableRepository extends
        FirestoreReactiveRepository<Timetable>,
        SaveViewTimetablesDataAccessInterface {
    @NotNull
    Flux<Timetable> findAll();
}