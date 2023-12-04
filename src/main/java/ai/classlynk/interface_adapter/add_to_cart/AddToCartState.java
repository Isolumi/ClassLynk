package ai.classlynk.interface_adapter.add_to_cart;

import ai.classlynk.entity.Course;

import java.util.List;

public class AddToCartState {
    private boolean success;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String message;

    public List<Course> getUpdatedCourseCart() {
        return updatedCourseCart;
    }

    public void setUpdatedCourseCart(List<Course> updatedCourseCart) {
        this.updatedCourseCart = updatedCourseCart;
    }

    private List<Course> updatedCourseCart;


    public AddToCartState(boolean success, String message, List<Course> updatedCourseCart) {
        this.success = success;
        this.message = message;
        this.updatedCourseCart = updatedCourseCart;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

}
