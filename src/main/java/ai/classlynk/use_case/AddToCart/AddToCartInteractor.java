package ai.classlynk.use_case.AddToCart;

import ai.classlynk.entity.Course;
import ai.classlynk.entity.User;
import java.util.List;

public class AddToCartInteractor implements AddToCartInputBoundary {
    final AddToCartOutputBoundary presenter;

    public AddToCartInteractor(AddToCartOutputBoundary presenter) {
        this.presenter = presenter;
    }

    @Override
    public void addToCart(AddToCartInputData inputData) {
        Course course = inputData.getCourses();
        List<Course> userCourseCart = User.getInstance("", "").getCourseKart();
        if (userCourseCart.contains(course)) {
            presenter.presentResponse(new AddToCartOutputData(false, course.getCourseId() + " Already Added", userCourseCart));
        } else {
            userCourseCart.add(course);
            User.getInstance("", "").setCourseKart(userCourseCart);
            presenter.presentResponse(new AddToCartOutputData(true, course.getCourseId() + " Successfully Added", userCourseCart));
        }
    }
}