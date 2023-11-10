package ai.classlynk.use_case.user_auth.register;

public interface RegisterOutputBoundary {
    void fail(String error);
    void success(use_case.user_auth.register.RegisterOutputData output);

}
