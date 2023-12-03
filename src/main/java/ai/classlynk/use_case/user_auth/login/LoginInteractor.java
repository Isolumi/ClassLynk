package ai.classlynk.use_case.user_auth.login;

import ai.classlynk.data_access.FirebaseDataAccessObject;
import ai.classlynk.entity.User;
import ai.classlynk.use_case.user_auth.register.RegisterDataAccessInterface;

public class LoginInteractor implements LoginInputBoundary {

    final LoginDataAccessInterface userDataAccessObject;
    final LoginOutputBoundary loginPresenter;

    public LoginInteractor(LoginDataAccessInterface userDataAccessInterface,
                           LoginOutputBoundary loginOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.loginPresenter = loginOutputBoundary;
    }

    @Override
    public void execute(LoginInputData loginInputData) {
        String username = loginInputData.getUsername();
        if (!userDataAccessObject.existsByName(username)) {
            loginPresenter.prepareFailView(username + ": Account does not exist.");
        } else {
            if (!userDataAccessObject.verifyPassword(loginInputData.getUsername(), loginInputData.getPassword())) {
                loginPresenter.prepareFailView("Incorrect password for " + username + ".");
            } else {
                User.getInstance(loginInputData.getUsername(), loginInputData.getPassword());
                User.getInstance("", "").setTimetables(((FirebaseDataAccessObject)userDataAccessObject).getTimetable(loginInputData.getUsername()));
                LoginOutputData loginOutputData = new LoginOutputData(username, false);
                loginPresenter.prepareSuccessView(loginOutputData);
            }
        }
    }
}