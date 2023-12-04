package ai.classlynk.interface_adapter.login;

import ai.classlynk.use_case.user_auth.login.LoginInputBoundary;
import ai.classlynk.use_case.user_auth.login.LoginInputData;

public class LoginController {
    final LoginInputBoundary interactor;
    public LoginController(LoginInputBoundary boundary){
        interactor = boundary;
    }
    public void execute(String name, String Pass){
        LoginInputData data = new LoginInputData(name, Pass);
        interactor.execute(data);
    }
}
