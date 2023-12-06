package ai.classlynk.view;

import ai.classlynk.interface_adapter.login.LoginController;
import ai.classlynk.interface_adapter.login.LoginState;
import ai.classlynk.interface_adapter.login.LoginViewModel;
import ai.classlynk.interface_adapter.register.RegisterState;
import ai.classlynk.interface_adapter.register.RegisterViewModel;
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

        public final String viewName = "log in";

        private final LoginViewModel loginViewModel;

        private final RegisterViewModel registerViewModel;
        private final JTextField usernameInputField = new JTextField(15);
        private final JPasswordField passwordInputField = new JPasswordField(15);
        private final LoginController loginController;
        private final ViewManagerModel viewManagerModel;
        private final JButton Login;
        private final JButton GoRegister;

    public LoginView(LoginController controller, LoginViewModel lginViewModel, RegisterViewModel rgisterViewModel, ViewManagerModel viwManagerModel) {

        this.setLayout(new GridBagLayout());
        this.loginController = controller;
        this.loginViewModel = lginViewModel;
        this.registerViewModel = rgisterViewModel;
        this.viewManagerModel = viwManagerModel;
        loginViewModel.addPropertyChangeListener(this);

        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = GridBagConstraints.RELATIVE;
        constraints.gridy = GridBagConstraints.RELATIVE;
        constraints.anchor = GridBagConstraints.CENTER;



        JPanel title = new JPanel();
        title.add(new JLabel(loginViewModel.TITLE_LABEL));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel usernameInput = new JPanel();
        usernameInput.add(new JLabel(loginViewModel.USERNAME_LABEL));
        usernameInput.add(usernameInputField);

        JPanel passwordInput = new JPanel();
        passwordInput.add(new JLabel(loginViewModel.PASSWORD_LABEL));
        passwordInput.add(passwordInputField);

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
                            if(registerState.getUsernameError() != null)
                            {
                                registerState.setUsernameError(null);
                            }
                            registerViewModel.setState(registerState);
                            registerViewModel.firePropertyChanged();
                            viewManagerModel.setActiveView(registerViewModel.getViewName());
                            viewManagerModel.firePropertyChanged();
                        }
                    }
                });
        Login.addActionListener(e ->
                {
                    if (e.getSource().equals(Login)) {

                        LoginState currentState = loginViewModel.getState();
                        if(currentState.getUsername().isEmpty() || currentState.getPassword().isEmpty())
                        {
                            JOptionPane.showMessageDialog(this,"One or more of your fields are empty!");
                        }
                        else
                        {
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

        main.add(title);
        main.add(usernameInput);
        main.add(passwordInput);
        main.add(buttons);
        this.add(main);
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

        if(state.getUsernameError() != null)
        {
            JOptionPane.showMessageDialog(this, state.getUsernameError());
        }
        setFields(state);
    }

    private void setFields(LoginState state) {
        if(state.getUsernameError() == null)
        {
            usernameInputField.setText(state.getUsername());
        }
        else
        {
            usernameInputField.setText("");
        }
        passwordInputField.setText("");
    }

}
