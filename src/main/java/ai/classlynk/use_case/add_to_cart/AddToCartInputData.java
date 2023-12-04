package ai.classlynk.use_case.add_to_cart;

import ai.classlynk.entity.Course;

public class AddToCartInputData {
    private Course course;
    public AddToCartInputData(Course course) {
        this.course = course;
    }

    public Course getCourses() {return course; }

}