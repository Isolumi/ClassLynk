package ai.classlynk.use_case.user_auth.register;

import ai.classlynk.entity.User;

public class RegisterInteractor implements RegisterInputBoundary{
    final RegisterDAO RegisterDAO;
    final RegisterOutputBoundary RegisterOutputBoundary;

    public RegisterInteractor(RegisterDAO registerDAO, RegisterOutputBoundary registerOutputBoundary) {
        RegisterDAO = registerDAO;
        RegisterOutputBoundary = registerOutputBoundary;
    }

    public void execute(RegisterInputData inputData){
     if (RegisterDAO.existedByName(inputData.getName())){
         RegisterOutputBoundary.fail("This name has been used!");
        } else if (inputData.getPw1().equals(inputData.getPw2())) {
         RegisterOutputBoundary.fail("Two Passwords are different!");
     }else
     RegisterDAO.userCreate(inputData.getName(), inputData.getPw2());
     RegisterOutputBoundary.success();
    }
}