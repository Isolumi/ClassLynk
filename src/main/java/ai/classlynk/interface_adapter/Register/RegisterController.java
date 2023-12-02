package ai.classlynk.interface_adapter.Register;

import ai.classlynk.use_case.user_auth.register.RegisterInputBoundary;
import ai.classlynk.use_case.user_auth.register.RegisterInputData;
import com.google.firebase.auth.FirebaseAuthException;

public class RegisterController {
    final RegisterInputBoundary interactor;
    public RegisterController(RegisterInputBoundary boundary){
        interactor = boundary;
    }
    public void execute(String name, String Pass1, String Pass2) throws FirebaseAuthException {
        RegisterInputData inputdata = new RegisterInputData(name, Pass1, Pass2);
        interactor.execute(inputdata);
    }
}
