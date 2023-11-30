package ai.classlynk.use_case.user_auth.login;

public interface LoginOutputBoundary {
    void prepareFailView(String s);

    void prepareSuccessView(LoginOutputData loginOutputData);
}
