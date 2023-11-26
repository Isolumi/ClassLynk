package ai.classlynk.interface_adapter.Register;

import ai.classlynk.interface_adapter.Login.LoginState;
import ai.classlynk.interface_adapter.Login.LoginViewModel;
import ai.classlynk.interface_adapter.ViewManagerModel;
import ai.classlynk.use_case.user_auth.register.RegisterOutputBoundary;
import use_case.user_auth.register.RegisterOutputData;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RegisterPresenter implements RegisterOutputBoundary {

    private final RegisterViewModel registerViewModel;
    private final LoginViewModel loginViewModel;
    private ViewManagerModel viewManagerModel;

    public RegisterPresenter(ViewManagerModel ViewManagerModel,
                           RegisterViewModel RegisterViewModel,
                           LoginViewModel LoginViewModel) {
        this.viewManagerModel = ViewManagerModel;
        this.registerViewModel = RegisterViewModel;
        this.loginViewModel = LoginViewModel;
    }

    @Override
    public void success(RegisterOutputData response) {
        // On success, switch to the login view.
        LocalDateTime responseTime = LocalDateTime.parse(response.getCreationTime());
        response.setCreationTime(responseTime.format(DateTimeFormatter.ofPattern("hh:mm:ss")));

        LoginState loginState = loginViewModel.getState();
        loginState.setUsername(response.getUsername());
        this.loginViewModel.setState(loginState);
        loginViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void fail(String error) {
        RegisterState registerState = registerViewModel.getState();
        registerState.setUsernameError(error);
        registerViewModel.firePropertyChanged();
    }}
