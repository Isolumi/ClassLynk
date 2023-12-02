package ai.classlynk.use_case.AddToCart;

import ai.classlynk.entity.Course;
import ai.classlynk.entity.SClass;

import java.util.List;

public class AddToCartOutputData {
    private boolean success;
    private String message;
    private List<Course> updatedCourseCart;

    public AddToCartOutputData(boolean success, String message, List<Course> updatedCourseCart) {
        this.success = success;
        this.message = message;
        this.updatedCourseCart = updatedCourseCart;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<Course> getUpdatedCourseCart() {
        return updatedCourseCart;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setUpdatedCart(List<Course> updatedCart) {
        this.updatedCourseCart = updatedCart;
    }

}
