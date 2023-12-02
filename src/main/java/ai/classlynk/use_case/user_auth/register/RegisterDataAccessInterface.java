package ai.classlynk.use_case.user_auth.register;

import com.google.firebase.auth.FirebaseAuthException;

public interface RegisterDataAccessInterface {
    boolean existsByname(String Name);
    void userCreate(String Name, String password);
}
