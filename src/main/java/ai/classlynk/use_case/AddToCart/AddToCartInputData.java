package ai.classlynk.use_case.AddToCart;

import ai.classlynk.entity.Course;
import ai.classlynk.entity.SClass;

public class AddToCartInputData {
    private Course course;
    private SClass sClass;

    public AddToCartInputData(Course course, SClass sClass) {
        this.course = course;
        this.sClass = sClass;
    }

    public Course getCourse() {return course; }
    public SClass getsClass() {return sClass; }

}
