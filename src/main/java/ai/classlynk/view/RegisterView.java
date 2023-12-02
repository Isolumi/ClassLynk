package ai.classlynk.view;

import ai.classlynk.interface_adapter.Login.LoginState;
import ai.classlynk.interface_adapter.Login.LoginViewModel;
import ai.classlynk.interface_adapter.Register.RegisterController;
import ai.classlynk.interface_adapter.Register.RegisterState;
import ai.classlynk.interface_adapter.Register.RegisterViewModel;
import ai.classlynk.interface_adapter.ViewManagerModel;
import com.google.firebase.auth.FirebaseAuthException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class RegisterView extends JPanel implements PropertyChangeListener {
    public final String viewName = "Register";

    private final RegisterViewModel registerViewModel;
    private final LoginViewModel loginViewModel;
    private final JTextField usernameInputField = new JTextField(15);
    private final JPasswordField passwordInputField = new JPasswordField(15);
    private final JPasswordField repeatPasswordInputField = new JPasswordField(15);
    private final RegisterController registerController;
    private final ViewManagerModel viewManagerModel;
    private final JButton Register;
    private final JButton GoLogIn;


    public RegisterView(RegisterController controller, RegisterViewModel registerViewModel, LoginViewModel loginViewModel, ViewManagerModel viewManagerModel) {
        this.registerController = controller;
        this.registerViewModel = registerViewModel;
        this.loginViewModel = loginViewModel;
        this.viewManagerModel = viewManagerModel;
        this.registerViewModel.addPropertyChangeListener(this);
        this.setLayout(new GridBagLayout());
        JLabel title = new JLabel(RegisterViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = GridBagConstraints.RELATIVE;
        constraints.gridy = GridBagConstraints.RELATIVE;
        constraints.anchor = GridBagConstraints.CENTER;

        JPanel usernameInput = new JPanel();
        usernameInput.add(new JLabel(RegisterViewModel.USERNAME_LABEL));
        usernameInput.add(usernameInputField);

        JPanel passwordInput = new JPanel();
        passwordInput.add(new JLabel(RegisterViewModel.PASSWORD_LABEL));
        passwordInput.add(passwordInputField);

        JPanel repeatPasswordInput = new JPanel();
        repeatPasswordInput.add(new JLabel(RegisterViewModel.REPEAT_PASSWORD_LABEL));
        repeatPasswordInput.add(repeatPasswordInputField);


        JPanel buttons = new JPanel();
        Register = new JButton(RegisterViewModel.Register_BUTTON_LABEL);
        buttons.add(Register);
        GoLogIn = new JButton(RegisterViewModel.GoLogIn_BUTTON_LABEL);
        buttons.add(GoLogIn);
        GoLogIn.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(GoLogIn)) {
                        LoginState loginState = RegisterView.this.loginViewModel.getState();
                        RegisterView.this.loginViewModel.setState(loginState);
                        RegisterView.this.loginViewModel.firePropertyChanged();
                        RegisterView.this.viewManagerModel.setActiveView(RegisterView.this.loginViewModel.getViewName());
                        RegisterView.this.viewManagerModel.firePropertyChanged();
                        }
                    }}
        );
        Register.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(Register)) {
                            RegisterState currentState = RegisterView.this.registerViewModel.getState();

                            try {
                                registerController.execute(
                                        currentState.getUsername(),
                                        currentState.getPassword(),
                                        currentState.getRepeatPassword()
                                );
                            } catch (FirebaseAuthException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                }
        );
        usernameInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        RegisterState currentState = RegisterView.this.registerViewModel.getState();
                        String text = usernameInputField.getText() + e.getKeyChar();
                        currentState.setUsername(text);
                        RegisterView.this.registerViewModel.setState(currentState);
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
                        RegisterState currentState = RegisterView.this.registerViewModel.getState();
                        currentState.setPassword(passwordInputField.getText() + e.getKeyChar());
                        RegisterView.this.registerViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );

        repeatPasswordInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        RegisterState currentState = RegisterView.this.registerViewModel.getState();
                        currentState.setRepeatPassword(repeatPasswordInputField.getText() + e.getKeyChar());
                        RegisterView.this.registerViewModel.setState(currentState); // Hmm, is this necessary?
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );

        main.add(title);
        main.add(usernameInput);
        main.add(passwordInput);
        main.add(repeatPasswordInput);
        main.add(buttons);
        this.add(main);
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        RegisterState state = (RegisterState) evt.getNewValue();
        if (state.getUsernameError() != null) {
            JOptionPane.showMessageDialog(this, state.getUsernameError());
        }
    } }
