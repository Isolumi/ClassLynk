package ai.classlynk.interface_adapter.addToCart;

import ai.classlynk.entity.Course;
import ai.classlynk.entity.SClass;

import java.util.List;

public class AddToCartState {
    private List<Course> courseCart;
    private List<SClass> sClasses;

    public AddToCartState() {
    }

    public AddToCartState(AddToCartState copy) {
        courseCart = copy.courseCart;
        sClasses = copy.sClasses;
    }

    public List<Course> getCourseCart() {
        return courseCart;
    }

    public void setCourseCart(List<Course> courseCart) {
        this.courseCart = courseCart;
    }

    public List<SClass> getsClasses() {
        return sClasses;
    }

    public void setsClasses(List<SClass> sClasses) {
        this.sClasses = sClasses;
    }

}
