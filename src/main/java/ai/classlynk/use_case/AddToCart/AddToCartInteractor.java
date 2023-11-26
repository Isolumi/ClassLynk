package ai.classlynk.use_case.AddToCart;

import ai.classlynk.entity.ClassBundle;
import ai.classlynk.entity.Course;
import ai.classlynk.entity.SClass;
import ai.classlynk.entity.User;
import java.util.List;

public class AddToCartInteractor implements AddToCartInputBoundary {
    final User user;
    final AddToCartOutputBoundary presenter;
    public AddToCartInteractor(User user, AddToCartOutputBoundary presenter) {
        this.user = user;
        this.presenter = presenter;
    }

    @Override
    public void addToCart(AddToCartInputData inputData) {
        Course course = inputData.getCourse();
        ClassBundle classBundle = inputData.getClassBundle();
        SClass tutorial = inputData.getSClass();
        if (user.getCourseKart().contains(course)) {
            if (user.getClassKart().contains(classBundle.getClasses().get(1))) {
                if (user.getClassKart().contains(tutorial)) {
                    presenter.presentResponse(new AddToCartOutputData(false, "CourseBundle and SClass Already Added", user.getCourseKart()));
                } else {
                    for (SClass addedSClass : course.getTutorials()) {
                        if (user.getClassKart().contains(addedSClass)) {
                            List<SClass> updatedClassKart = user.getClassKart();
                            updatedClassKart.remove(addedSClass);
                            user.setClassKart(updatedClassKart);
                        }
                    }
                    user.getClassKart().add(tutorial);
                    presenter.presentResponse(new AddToCartOutputData(true, "SClass Successfully Added", user.getCourseKart()));
                }
            } else {
                for (ClassBundle addedBundle : course.getClassBundles()) {
                    if (user.getClassKart().contains(addedBundle.getClasses().get(1))) {
                        List<SClass> updatedClassKart = user.getClassKart();
                        for (SClass sClass : addedBundle.getClasses()) {
                            updatedClassKart.remove(sClass);
                        }
                        user.setClassKart(updatedClassKart);
                    }
                }
                for (SClass newSClass : classBundle.getClasses()) {
                    user.getClassKart().add(newSClass);
                }
                user.getClassKart().add(tutorial);
                presenter.presentResponse(new AddToCartOutputData(true, "CourseBundle and SClass Successfully Added", user.getCourseKart()));
            }
        } else {
            List<Course> updatedCourseKart = user.getCourseKart();
            updatedCourseKart.add(inputData.getCourse());
            user.setCourseKart(updatedCourseKart);
            for (SClass sClass : classBundle.getClasses()) {
                user.getClassKart().add(sClass);
            }
            if (tutorial == null) {
                presenter.presentResponse(new AddToCartOutputData(true, "Course and CourseBundle Successfully Added", user.getCourseKart()));
            } else {
                List<SClass> updatedSClassKart = user.getClassKart();
                updatedSClassKart.add(tutorial);
                user.setCourseKart(updatedCourseKart);
                presenter.presentResponse(new AddToCartOutputData(true, "Course, CourseBundle and SClass Successfully Added", user.getCourseKart()));
            }
        }

    }

}
