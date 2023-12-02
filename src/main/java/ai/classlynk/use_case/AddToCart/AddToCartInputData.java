package ai.classlynk.use_case.AddToCart;

import ai.classlynk.entity.Course;
import ai.classlynk.entity.SClass;
import ai.classlynk.entity.ClassBundle;

public class AddToCartInputData {
    private Course course;
    public AddToCartInputData(Course course) {
        this.course = course;
    }

    public Course getCourses() {return course; }

}
