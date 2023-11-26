package ai.classlynk.use_case.AddToCart;

import ai.classlynk.entity.Course;

import java.util.List;

public class AddToCartOutputData {
    private boolean success;
    private String message;
    private List<Course> updatedCart;

    public AddToCartOutputData(boolean success, String message, List<Course> updatedCart) {
        this.success = success;
        this.message = message;
        this.updatedCart = updatedCart;
    }

    // Getters for the properties
    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<Course> getUpdatedCart() {
        return updatedCart;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setUpdatedCart(List<Course> updatedCart) {
        this.updatedCart = updatedCart;
    }
}
