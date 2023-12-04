package ai.classlynk.use_case.add_to_cart;

import ai.classlynk.entity.User;

public interface AddToCartDataAccessInterface {
    public User getUser(String username);
}
