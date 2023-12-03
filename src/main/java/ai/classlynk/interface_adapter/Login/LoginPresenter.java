package ai.classlynk.interface_adapter.Login;

import ai.classlynk.entity.User;
import ai.classlynk.interface_adapter.ViewManagerModel;
import ai.classlynk.interface_adapter.save_view_timetables.SaveViewTimetableState;
import ai.classlynk.interface_adapter.save_view_timetables.SaveViewTimetableViewModel;
import ai.classlynk.use_case.user_auth.login.LoginOutputBoundary;
import ai.classlynk.use_case.user_auth.login.LoginOutputData;

public class LoginPresenter implements LoginOutputBoundary {
    private final LoginViewModel loginViewModel;
    private final SaveViewTimetableViewModel saveViewTimetableViewModel;
    private ViewManagerModel viewManagerModel;

    public LoginPresenter(ViewManagerModel viewManagerModel,
                          SaveViewTimetableViewModel loggedInViewModel,
                          LoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.saveViewTimetableViewModel = loggedInViewModel;
        this.loginViewModel = loginViewModel;
    }

    @Override
    public void prepareSuccessView(LoginOutputData response) {
        // On success, switch to the logged in view.

        SaveViewTimetableState saveViewTimetableState = saveViewTimetableViewModel.getState();
        saveViewTimetableState.setTimetables(User.getInstance("", "").getTimetables());
        this.saveViewTimetableViewModel.setState(saveViewTimetableState);
        this.saveViewTimetableViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(saveViewTimetableViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        LoginState loginState = loginViewModel.getState();
        loginState.setUsernameError(error);
        loginViewModel.firePropertyChanged();
    }}

