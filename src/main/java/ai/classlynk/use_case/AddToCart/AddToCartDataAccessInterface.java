package ai.classlynk.use_case.AddToCart;

import ai.classlynk.entity.Course;
import ai.classlynk.entity.SClass;

import java.util.List;

public interface AddToCartDataAccessInterface {
    List<Course> getUserCourseCart();
    List<SClass> getUserSClassCart();
}
