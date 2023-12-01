package ai.classlynk.use_case.user_auth.register;

public interface RegisterOutputBoundary {
    void prepareFailView(String error);
    void success();

}
