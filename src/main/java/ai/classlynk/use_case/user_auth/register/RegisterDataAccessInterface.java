package ai.classlynk.use_case.user_auth.register;

import ai.classlynk.entity.User;
import com.google.firebase.auth.FirebaseAuthException;

public interface RegisterDataAccessInterface {
    boolean existsByName(String Name);
    User userCreate(String Name, String password);
}
