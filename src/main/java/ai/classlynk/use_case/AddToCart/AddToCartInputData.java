package ai.classlynk.use_case.AddToCart;

import ai.classlynk.entity.ClassBundle;
import ai.classlynk.entity.Course;
import ai.classlynk.entity.SClass;

public class AddToCartInputData {
    private Course course;
    private SClass sClass;
    private ClassBundle classBundle;

    public AddToCartInputData(Course course, ClassBundle classBundle, SClass sClass) {
        this.course = course;
        this.classBundle = classBundle;
        this.sClass = sClass;
    }

    public AddToCartInputData(Course course, ClassBundle classBundle) {
        this.course = course;
        this.classBundle = classBundle;
        this.sClass = null;
    }

    public Course getCourse() {return course; }
    public SClass getSClass() {return sClass; }
    public ClassBundle getClassBundle() {return classBundle; }

}
