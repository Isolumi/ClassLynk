package ai.classlynk.use_case.user_auth.login;

import ai.classlynk.entity.User;
import ai.classlynk.use_case.user_auth.login.LoginDataAccessInterface;
import ai.classlynk.use_case.user_auth.login.LoginInputBoundary;
import ai.classlynk.use_case.user_auth.login.LoginInputData;

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
            User user = userDataAccessObject.getUser(username);
            if (!userDataAccessObject.verifyPassword(user)) {
                loginPresenter.prepareFailView("Incorrect password for " + username + ".");
            } else {
                LoginOutputData loginOutputData = new LoginOutputData(username, false);
                loginPresenter.prepareSuccessView(loginOutputData);
            }
        }
    }
}