package ai.classlynk.interface_adapter.ViewCourse;

import ai.classlynk.use_case.ViewCourse.ViewCourseInputBoundary;

public class ViewCourseController {
    final ViewCourseInputBoundary interactor;

    public ViewCourseController(ViewCourseInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void execute() {
        interactor.execute();
    }
}
