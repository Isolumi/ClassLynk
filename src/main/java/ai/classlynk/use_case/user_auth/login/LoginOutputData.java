package ai.classlynk.use_case.user_auth.login;

public class LoginOutputData {
    private final String username;
    private boolean useCaseFailed;

    public LoginOutputData(String username, boolean useCaseFailed) {
        this.username = username;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUsername() {
        return username;
    }
}
