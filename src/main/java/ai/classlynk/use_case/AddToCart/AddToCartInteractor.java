package ai.classlynk.use_case.AddToCart;

import ai.classlynk.entity.ClassBundle;
import ai.classlynk.entity.Course;
import ai.classlynk.entity.SClass;
import ai.classlynk.entity.User;
import java.util.List;

public class AddToCartInteractor implements AddToCartInputBoundary {
    final AddToCartOutputBoundary presenter;
    final AddToCartDataAccessInterface addToCartDataAccessObject;

    public AddToCartInteractor(AddToCartOutputBoundary presenter, AddToCartDataAccessInterface addToCartDataAccessObject) {
        this.presenter = presenter;
        this.addToCartDataAccessObject = addToCartDataAccessObject;
    }

    @Override
    public void addToCart(AddToCartInputData inputData) {
        Course course = inputData.getCourse();
        ClassBundle classBundle = inputData.getClassBundle();
        SClass tutorial = inputData.getSClass();
        List<Course> userCourseCart = addToCartDataAccessObject.getUserCourseCart();
        List<SClass> userSClassCart = addToCartDataAccessObject.getUserSClassCart();
        if (userCourseCart.contains(course)) {
            if (userSClassCart.contains(classBundle.getClasses().get(1))) {
                if (userSClassCart.contains(tutorial)) {
                    presenter.presentResponse(new AddToCartOutputData(false, "CourseBundle and SClass Already Added", userCourseCart, userSClassCart));
                } else {
                    for (SClass addedSClass : course.getTutorials()) {
                        if (userSClassCart.contains(addedSClass)) {
                            userSClassCart.remove(addedSClass);
                        }
                    }
                    userSClassCart.add(tutorial);
                    presenter.presentResponse(new AddToCartOutputData(true, "SClass Successfully Added", userCourseCart, userSClassCart));
                }
            } else {
                for (ClassBundle addedBundle : course.getClassBundles()) {
                    if (userSClassCart.contains(addedBundle.getClasses().get(1))) {
                        for (SClass sClass : addedBundle.getClasses()) {
                            userSClassCart.remove(sClass);
                        }
                    }
                }
                userSClassCart.addAll(classBundle.getClasses());
                userSClassCart.add(tutorial);
                presenter.presentResponse(new AddToCartOutputData(true, "CourseBundle and SClass Successfully Added", userCourseCart, userSClassCart));
            }
        } else {
            userCourseCart.add(inputData.getCourse());
            if (classBundle == null) {
                presenter.presentResponse(new AddToCartOutputData(false, "Please select a lecture section", userCourseCart, userSClassCart));
            } else {
                userSClassCart.addAll(classBundle.getClasses());
                if (tutorial == null) {
                    presenter.presentResponse(new AddToCartOutputData(true, "Course and CourseBundle Successfully Added", userCourseCart, userSClassCart));
                } else {
                    userSClassCart.add(tutorial);
                    presenter.presentResponse(new AddToCartOutputData(true, "Course, CourseBundle and SClass Successfully Added", userCourseCart, userSClassCart));
                }
            }
        }
    }
}
