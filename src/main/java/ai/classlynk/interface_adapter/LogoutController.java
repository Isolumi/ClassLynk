package ai.classlynk.interface_adapter;

import ai.classlynk.use_case.user_auth.login.LogoutInputBoundary;

public class LogoutController {
    private LogoutInputBoundary interactor;
    public LogoutController(LogoutInputBoundary Inter){
        interactor = Inter;
    }
    public void execute(){
        interactor.execute();
    }

}
