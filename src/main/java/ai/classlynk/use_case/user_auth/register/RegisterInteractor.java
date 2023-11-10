package ai.classlynk.use_case.user_auth.register;

public class RegisterInteractor {
    final RegisterDAO RegisterDAO;
    final RegisterOutputBoundary RegisterOutputBoundary;

    public RegisterInteractor(ai.classlynk.use_case.user_auth.register.RegisterDAO registerDAO, ai.classlynk.use_case.user_auth.register.RegisterOutputBoundary registerOutputBoundary) {
        RegisterDAO = registerDAO;
        RegisterOutputBoundary = registerOutputBoundary;
    }

    public void execute(RegisterInputData inputData){
     if (RegisterDAO.existedByName(inputData.name())){
         RegisterOutputBoundary.fail("This name has been used!");
        } else if (inputData.getPw1.equalto(inputData.getPw2)) {
         RegisterOutputBoundary.fail("Two Passwords are different!");
     }else
     //user = 创建一个user
     RegisterDAO.save(user);
        RegisterOutputBoundary.success();
    }
}