package ai.classlynk.use_case.user_auth.login;

import ai.classlynk.entity.User;

public interface LoginDataAccessInterface {
    boolean existsByUsername(String username);
    User getUser(String username);

    boolean verifyPassword(User user);
}
