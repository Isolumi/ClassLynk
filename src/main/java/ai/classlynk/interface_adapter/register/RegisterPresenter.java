package ai.classlynk.interface_adapter.register;

import ai.classlynk.interface_adapter.login.LoginState;
import ai.classlynk.interface_adapter.login.LoginViewModel;
import ai.classlynk.interface_adapter.Presenter;
import ai.classlynk.interface_adapter.ViewManagerModel;
import ai.classlynk.use_case.user_auth.register.RegisterOutputBoundary;

public class RegisterPresenter implements RegisterOutputBoundary, Presenter {


    public ViewManagerModel getViewManagerModel() {
        return viewManagerModel;
    }
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

    public void success() {
        // On success, switch to the login view.
        LoginState loginState = loginViewModel.getState();
        this.loginViewModel.setState(loginState);
        loginViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        RegisterState registerState = registerViewModel.getState();
        registerState.setUsernameError(error);
        registerViewModel.firePropertyChanged();
    }}
