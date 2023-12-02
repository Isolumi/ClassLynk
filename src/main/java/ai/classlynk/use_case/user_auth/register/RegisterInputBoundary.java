package ai.classlynk.use_case.user_auth.register;

import com.google.firebase.auth.FirebaseAuthException;

public interface RegisterInputBoundary {
    public void execute(RegisterInputData Inputdata);
}
