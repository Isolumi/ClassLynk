package ai.classlynk.use_case.AddToCart;

import ai.classlynk.entity.Course;
import ai.classlynk.entity.SClass;
import ai.classlynk.entity.User;

import java.util.List;

public interface AddToCartDataAccessInterface {
    public User getUser(String username);
}
