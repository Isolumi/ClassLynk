package ai.classlynk.use_case.user_auth.register;

public interface RegisterDataAccessInterface {
    boolean existedByName(String Name);
    void userCreate(String Name, String password);
}
