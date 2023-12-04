package ai.classlynk.interface_adapter.add_to_cart;

import ai.classlynk.entity.Course;
import ai.classlynk.use_case.add_to_cart.AddToCartInputBoundary;
import ai.classlynk.use_case.add_to_cart.AddToCartInputData;

public class AddToCartController {
    private final AddToCartInputBoundary addToCartInteractor;

    public AddToCartController(AddToCartInputBoundary interactor) {
        this.addToCartInteractor = interactor;
    }

    public void addToCart(Course course) {
        AddToCartInputData inputData = new AddToCartInputData(course);
        addToCartInteractor.addToCart(inputData);
    }
}