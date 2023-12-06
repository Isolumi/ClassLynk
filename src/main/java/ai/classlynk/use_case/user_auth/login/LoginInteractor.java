package ai.classlynk.use_case.user_auth.login;

import ai.classlynk.entity.User;
import ai.classlynk.use_case.save_view_timetables.SaveViewTimetablesDataAccessInterface;

public class LoginInteractor implements LoginInputBoundary {

    private final LoginDataAccessInterface userDataAccessObject;
    private final LoginOutputBoundary loginPresenter;
    private final SaveViewTimetablesDataAccessInterface saveViewTimetablesDataAccessInterface;

    public LoginInteractor(LoginDataAccessInterface userDataAccessInterface,
                           SaveViewTimetablesDataAccessInterface saveViewTimetablesDataAccessInterface,
                           LoginOutputBoundary loginOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.loginPresenter = loginOutputBoundary;
        this.saveViewTimetablesDataAccessInterface = saveViewTimetablesDataAccessInterface;
    }

    /**
     * Validates the user's credentials, and then logs them in, switching to the save/view timetable view if successfully validated.
     * @param loginInputData The credentials inputted by the user.
     */
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
                User.getInstance("", "")
                        .setTimetables(saveViewTimetablesDataAccessInterface
                                .getTimetable(loginInputData.getUsername()));
                LoginOutputData loginOutputData = new LoginOutputData(username, false);
                loginPresenter.prepareSuccessView(loginOutputData);
            }
        }
    }
}