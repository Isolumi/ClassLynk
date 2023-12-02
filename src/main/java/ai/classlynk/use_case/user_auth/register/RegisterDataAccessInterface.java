package ai.classlynk.use_case.user_auth.register;

public interface RegisterDataAccessInterface {
    boolean existsByName(String Name);
    void userCreate(String Name, String password);
}
