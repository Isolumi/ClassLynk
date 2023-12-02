package ai.classlynk.use_case.ViewCourse;

public class ViewCourseInteractor implements ViewCourseInputBoundary{
    final ViewCourseDataAccessInterface viewCourseDataAccessObject;
    final ViewCourseOutputBoundary presenter;

    public ViewCourseInteractor(ViewCourseDataAccessInterface viewCourseDataAccessObject, ViewCourseOutputBoundary presenter) {
        this.viewCourseDataAccessObject = viewCourseDataAccessObject;
        this.presenter = presenter;
    }

    @Override
    public void execute() {
        ViewCourseOutputData outputData = new ViewCourseOutputData(viewCourseDataAccessObject.getAllCourses());
        presenter.presentResponse(outputData);

    }
}
