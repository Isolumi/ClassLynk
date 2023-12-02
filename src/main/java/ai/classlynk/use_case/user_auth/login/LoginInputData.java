package ai.classlynk.use_case.user_auth.login;

public class LoginInputData {
    private final String name;
    private final String password;
    public LoginInputData(String name, String Pass){
        this.name = name;
        this.password = Pass;
    }
    public String getUsername() {
        return name;
    }
    public String getPassword() {
        return password;
    }
}
