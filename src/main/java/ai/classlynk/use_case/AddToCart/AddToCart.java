package ai.classlynk.use_case.AddToCart;

import ai.classlynk.entity.Course;
import ai.classlynk.entity.SClass;
import ai.classlynk.entity.User;
import java.util.List;

public class AddToCart implements AddToCartInputBoundary {
    final User user;
    final AddToCartOutputBoundary presenter;
    public AddToCart(User user, AddToCartOutputBoundary presenter) {
        this.user = user;
        this.presenter = presenter;
    }

    @Override
    public void addToCart(AddToCartInputData inputData) {
        if (user.getCourseKart().contains(inputData.getCourse())) {
            presenter.presentResponse(new AddToCartOutputData(false, "Course Already Added", user.getCourseKart()));
        } else {
            List<Course> updatedCourseKart = user.getCourseKart();
            updatedCourseKart.add(inputData.getCourse());
            user.setCourseKart(updatedCourseKart);
            if (inputData.getSClass() == null) {
                presenter.presentResponse(new AddToCartOutputData(true, "Course Successfully Added", user.getCourseKart()));
            } else {
                List<SClass> updatedSClassKart = user.getClassKart();
                updatedSClassKart.add(inputData.getSClass());
                user.setCourseKart(updatedCourseKart);
                presenter.presentResponse(new AddToCartOutputData(true, "Course and SClass Successfully Added", user.getCourseKart()));
            }
        }
    }
}
