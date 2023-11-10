package ai.classlynk.use_case.user_auth.register;

public class RegisterInputData {
    String Name;
    String Password1;
    String Password2;
    public RegisterInputData(String name, String password, String pass2){
        Name = name;
        Password1 = password;
        Password2 = pass2;
    }
    public String getName(){
        return Name;
    }
    public String getPw1(){
        return Password1;
    }
    public String getPw2(){
        return Password2;
    }
}
