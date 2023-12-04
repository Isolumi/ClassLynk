package ai.classlynk.interface_adapter.view_course;

import ai.classlynk.use_case.view_course.ViewCourseInputBoundary;

public class ViewCourseController {
    final ViewCourseInputBoundary interactor;

    public ViewCourseController(ViewCourseInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void execute() {
        interactor.execute();
    }
}
