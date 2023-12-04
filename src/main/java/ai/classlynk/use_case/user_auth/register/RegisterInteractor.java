package ai.classlynk.use_case.user_auth.register;

import ai.classlynk.entity.User;
import com.google.firebase.auth.FirebaseAuthException;

public class RegisterInteractor implements RegisterInputBoundary{
    final RegisterDataAccessInterface RegisterDAO;
    final RegisterOutputBoundary RegisterOutputBoundary;

    public RegisterInteractor(RegisterDataAccessInterface registerDAO, RegisterOutputBoundary registerOutputBoundary) {
        RegisterDAO = registerDAO;
        RegisterOutputBoundary = registerOutputBoundary;
    }

    /**
     * Takes given credentials and registers user, then brings them to the login screen if the credentials are valid.
     * @param inputData The credentials inputted by the user.
     */
    public void execute(RegisterInputData inputData){
     if (RegisterDAO.existsByName(inputData.getName())){
         RegisterOutputBoundary.prepareFailView("This name has been used!");
        } else if (!inputData.getPw1().equals(inputData.getPw2())) {
         RegisterOutputBoundary.prepareFailView("Two Passwords are different!");
     }else{
         User.getInstance(inputData.getName(), inputData.getPw2());
         RegisterDAO.userCreate(inputData.getName(), inputData.getPw2());
         RegisterOutputBoundary.success();
    }
}}
