package ai.classlynk.use_case.user_auth.login;

import ai.classlynk.entity.User;

public interface LoginDataAccessInterface {
    boolean existedByName(String username);

    boolean verifyPassword(String user);
}
