package ai.classlynk.view;

import ai.classlynk.interface_adapter.Login.LoginController;
import ai.classlynk.interface_adapter.Login.LoginState;
import ai.classlynk.interface_adapter.Login.LoginViewModel;
import ai.classlynk.interface_adapter.Register.RegisterState;
import ai.classlynk.interface_adapter.Register.RegisterViewModel;
import ai.classlynk.interface_adapter.ViewManagerModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LoginView extends JPanel implements ActionListener, PropertyChangeListener {
        public final String viewName = "Login";

        private final LoginViewModel loginViewModel;

        private final RegisterViewModel registerViewModel;
        private final JTextField usernameInputField = new JTextField(15);
        private final JPasswordField passwordInputField = new JPasswordField(15);
        private final JLabel usernameErrorField = new JLabel();
        private final JLabel passwordErrorField = new JLabel();
        private final LoginController loginController;
        private final ViewManagerModel viewManagerModel;
        private final JButton Login;
        private final JButton GoRegister;
    public LoginView(LoginController controller, LoginViewModel loginViewModel, RegisterViewModel registerViewModel, ViewManagerModel viewManagerModel) {

        this.loginController = controller;
        this.loginViewModel = loginViewModel;
        this.registerViewModel = registerViewModel;
        this.viewManagerModel = viewManagerModel;
        loginViewModel.addPropertyChangeListener(this);
        JLabel title = new JLabel(loginViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel usernameInfo = new JLabel(loginViewModel.USERNAME_LABEL);
        usernameInfo.setText(String.valueOf(usernameInputField));
        JLabel passwordInfo = new JLabel(loginViewModel.PASSWORD_LABEL);
        usernameInfo.setText(String.valueOf(passwordInputField));

        JPanel buttons = new JPanel();
        Login = new JButton(LoginViewModel.Login_BUTTON_LABEL);
        buttons.add(Login);
        GoRegister = new JButton(LoginViewModel.GoRegister_BUTTON_LABEL);
        buttons.add(GoRegister);
        GoRegister.addActionListener(
                new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
            if (evt.getSource().equals(GoRegister)) {
                    RegisterState registerState = registerViewModel.getState();
                    registerViewModel.setState(registerState);
                    registerViewModel.firePropertyChanged();
                    viewManagerModel.setActiveView(registerViewModel.getViewName());
                    viewManagerModel.firePropertyChanged();
            }
        }});
        Login.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(Login)) {
                            LoginState currentState = loginViewModel.getState();

                            loginController.execute(
                                    currentState.getUsername(),
                                    currentState.getPassword());
                        }
                    }
                }
        );
        usernameInputField.addKeyListener(new KeyListener() {
        @Override
        public void keyTyped(KeyEvent e) {
            LoginState currentState = loginViewModel.getState();
            currentState.setUsername(usernameInputField.getText() + e.getKeyChar());
            loginViewModel.setState(currentState);
        }

        @Override
        public void keyPressed(KeyEvent e) {
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
    });
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        passwordInputField.addKeyListener(
                new KeyListener() {
        @Override
        public void keyTyped(KeyEvent e) {
            LoginState currentState = loginViewModel.getState();
            currentState.setPassword(passwordInputField.getText() + e.getKeyChar());
            loginViewModel.setState(currentState);
        }

        @Override
        public void keyPressed(KeyEvent e) {
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
    });

        this.add(title);
        this.add(usernameInfo);
        this.add(usernameErrorField);
        this.add(passwordInfo);
        this.add(passwordErrorField);
        this.add(buttons);
}

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        LoginState state = (LoginState) evt.getNewValue();
        setFields(state);
    }

    private void setFields(LoginState state) {
        usernameInputField.setText(state.getUsername());
    }

}
