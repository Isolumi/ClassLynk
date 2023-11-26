package ai.classlynk.use_case.user_auth.register;

public interface RegisterDAO{
    boolean existedByName(String Name);
    void save();
    void userCreate(String Name, String password);
}
