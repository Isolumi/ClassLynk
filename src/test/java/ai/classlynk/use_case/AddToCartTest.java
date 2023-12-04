package ai.classlynk.use_case;

import ai.classlynk.entity.Course;
import ai.classlynk.entity.User;
import ai.classlynk.use_case.add_to_cart.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddToCartTest {
    @Test
    void testAddToCart() {
        Course course = new Course();
        AddToCartInputData inputData = new AddToCartInputData(course);
        AddToCartOutputBoundary outputBoundary = new AddToCartOutputBoundary() {
            @Override
            public void presentResponse(AddToCartOutputData outputData) {

            }
        };

        AddToCartDataAccessInterface dataAccessInterface = new AddToCartDataAccessInterface() {
            @Override
            public User getUser(String username) {
                return null;
            }
        };
        AddToCartInteractor addToCartInteractor = new AddToCartInteractor(outputBoundary, dataAccessInterface);
        addToCartInteractor.addToCart(inputData);
        assertTrue(User.getInstance("", "").getCourseKart().contains(course));
    }
}
