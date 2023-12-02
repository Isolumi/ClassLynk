package ai.classlynk.interface_adapter.addToCart;

import ai.classlynk.entity.ClassBundle;
import ai.classlynk.entity.Course;
import ai.classlynk.entity.SClass;
import ai.classlynk.use_case.AddToCart.AddToCartInputBoundary;
import ai.classlynk.use_case.AddToCart.AddToCartInputData;

public class AddToCartController {
    private final AddToCartInputBoundary addToCartInteractor;

    public AddToCartController(AddToCartInputBoundary interactor) {
        this.addToCartInteractor = interactor;
    }

    public void addToCart(Course course, ClassBundle classBundle, SClass sClass) {
        AddToCartInputData inputData = new AddToCartInputData(course, classBundle, sClass);
        addToCartInteractor.addToCart(inputData);
    }
}
