package ai.classlynk.use_case.user_auth.register;

import com.google.firebase.auth.FirebaseAuthException;

public interface RegisterDataAccessInterface {
    boolean existsByUsername(String Name) throws FirebaseAuthException;
    void userCreate(String Name, String password) throws FirebaseAuthException;
}
