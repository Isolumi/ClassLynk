package ai.classlynk.view;

import ai.classlynk.interface_adapter.Login.LoginState;
import ai.classlynk.interface_adapter.Login.LoginViewModel;
import ai.classlynk.interface_adapter.Register.RegisterController;
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


public class RegisterView extends JPanel implements ActionListener, PropertyChangeListener {
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
        registerViewModel.addPropertyChangeListener(this);
        JLabel title = new JLabel(RegisterViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        Label usernameInfo = new Label(
                new JLabel(RegisterViewModel.USERNAME_LABEL), usernameInputField);
        Label passwordInfo = new Label(
                new JLabel(RegisterViewModel.PASSWORD_LABEL), passwordInputField);
        Label repeatPasswordInfo = new Label(
                new JLabel(RegisterViewModel.REPEAT_PASSWORD_LABEL), repeatPasswordInputField);

        JPanel buttons = new JPanel();
        Register = new JButton(RegisterViewModel.Register_BUTTON_LABEL);
        buttons.add(Register);
        GoLogIn = new JButton(RegisterViewModel.GoLogIn_BUTTON_LABEL);
        buttons.add(GoLogIn);
        GoLogIn.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(GoLogIn)) {
                        LoginState loginState = loginViewModel.getState();
                        loginViewModel.setState(loginState);
                        loginViewModel.firePropertyChanged();
                        viewManagerModel.setActiveView(loginViewModel.getViewName());
                        viewManagerModel.firePropertyChanged();
                        }
                    }}
        );
        Register.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(Register)) {
                            RegisterState currentState = registerViewModel.getState();

                            registerController.execute(
                                    currentState.getUsername(),
                                    currentState.getPassword(),
                                    currentState.getRepeatPassword()
                            );
                        }
                    }
                }
        );
        usernameInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        RegisterState currentState = registerViewModel.getState();
                        String text = usernameInputField.getText() + e.getKeyChar();
                        currentState.setUsername(text);
                        registerViewModel.setState(currentState);
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
                        RegisterState currentState = registerViewModel.getState();
                        currentState.setPassword(passwordInputField.getText() + e.getKeyChar());
                        registerViewModel.setState(currentState);
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
                        RegisterState currentState = registerViewModel.getState();
                        currentState.setRepeatPassword(repeatPasswordInputField.getText() + e.getKeyChar());
                        registerViewModel.setState(currentState); // Hmm, is this necessary?
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(usernameInfo);
        this.add(passwordInfo);
        this.add(repeatPasswordInfo);
        this.add(buttons);
    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showConfirmDialog(this, "Cancel not implemented yet.");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        RegisterState state = (RegisterState) evt.getNewValue();
        if (state.getUsernameError() != null) {
            JOptionPane.showMessageDialog(this, state.getUsernameError());
        }
    } }
