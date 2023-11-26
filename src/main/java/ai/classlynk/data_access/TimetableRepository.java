package ai.classlynk.data_access;

import ai.classlynk.entity.*;
import ai.classlynk.use_case.save_view_timetables.SaveViewTimetablesDataAccessInterface;
import ai.classlynk.use_case.user_auth.login.LoginDataAccessInterface;
import ai.classlynk.use_case.user_auth.register.RegisterDataAccessInterface;
import com.google.cloud.spring.data.firestore.FirestoreReactiveRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.Map;

@Repository
public interface TimetableRepository extends
        FirestoreReactiveRepository<Timetable>,
        SaveViewTimetablesDataAccessInterface,
        LoginDataAccessInterface,
        RegisterDataAccessInterface {
    @NotNull
    Flux<Timetable> findAll();
}