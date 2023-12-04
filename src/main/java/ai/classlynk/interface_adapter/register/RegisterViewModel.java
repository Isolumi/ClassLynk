package ai.classlynk.interface_adapter.Register;

import ai.classlynk.interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class RegisterViewModel extends ViewModel {
    public static final String TITLE_LABEL = "Register";
    public static final String USERNAME_LABEL = "Choose username";
    public static final String PASSWORD_LABEL = "Choose password";
    public static final String REPEAT_PASSWORD_LABEL = "Enter password again";

    public static final String Register_BUTTON_LABEL = "Register";
    public static final String GoLogIn_BUTTON_LABEL = "Go to log in";

    private RegisterState state = new RegisterState();

    public RegisterViewModel() {
        super("Register");
    }

    public void setState(RegisterState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // This is what the Register Presenter will call to let the ViewModel know
    // to alert the View
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public RegisterState getState() {
        return state;
    }
}
