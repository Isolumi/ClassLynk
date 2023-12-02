package ai.classlynk.interface_adapter.addToCart;

import ai.classlynk.entity.Course;
import ai.classlynk.entity.SClass;

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

    public List<SClass> getUpdatedClassCart() {
        return updatedClassCart;
    }

    public void setUpdatedClassCart(List<SClass> updatedClassCart) {
        this.updatedClassCart = updatedClassCart;
    }

    private List<SClass> updatedClassCart;

    public AddToCartState(boolean success, String message, List<Course> updatedCourseCart, List<SClass> updatedClassCart) {
        this.success = success;
        this.message = message;
        this.updatedCourseCart = updatedCourseCart;
        this.updatedClassCart = updatedClassCart;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

}