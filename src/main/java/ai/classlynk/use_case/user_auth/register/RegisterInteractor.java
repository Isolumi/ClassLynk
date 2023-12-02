package ai.classlynk.use_case.user_auth.register;

import com.google.firebase.auth.FirebaseAuthException;

public class RegisterInteractor implements RegisterInputBoundary{
    final RegisterDataAccessInterface RegisterDAO;
    final RegisterOutputBoundary RegisterOutputBoundary;

    public RegisterInteractor(RegisterDataAccessInterface registerDAO, RegisterOutputBoundary registerOutputBoundary) {
        RegisterDAO = registerDAO;
        RegisterOutputBoundary = registerOutputBoundary;
    }

<<<<<<< HEAD
    public void execute(RegisterInputData inputData) throws FirebaseAuthException {
     if (RegisterDAO.existsByUsername(inputData.getName())){
         RegisterOutputBoundary.fail("Email has been taken");
        } else if (inputData.getPw1().equals(inputData.getPw2())) {
         RegisterOutputBoundary.fail("Passwords do not match");
     }else
=======
    public void execute(RegisterInputData inputData){
     if (RegisterDAO.existedByName(inputData.getName())){
         RegisterOutputBoundary.prepareFailView("This name has been used!");
        } else if (!inputData.getPw1().equals(inputData.getPw2())) {
         RegisterOutputBoundary.prepareFailView("Two Passwords are different!");
     }else{
>>>>>>> main
     RegisterDAO.userCreate(inputData.getName(), inputData.getPw2());
     RegisterOutputBoundary.success();
    }
}}