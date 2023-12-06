package ai.classlynk.data_access;

import ai.classlynk.entity.User;
import com.google.cloud.spring.data.firestore.FirestoreReactiveRepository;
import org.jetbrains.annotations.NotNull;
import reactor.core.publisher.Mono;

public interface UserRepository extends FirestoreReactiveRepository<User> {
    @NotNull
    Mono<User> findById(@NotNull String id);
}
