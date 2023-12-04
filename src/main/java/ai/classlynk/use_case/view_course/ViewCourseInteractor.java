package ai.classlynk.use_case.view_course;

import java.util.ArrayList;

public class ViewCourseInteractor implements ViewCourseInputBoundary{
    final ViewCourseDataAccessInterface viewCourseDataAccessObject;
    final ViewCourseOutputBoundary presenter;

    public ViewCourseInteractor(ViewCourseDataAccessInterface viewCourseDataAccessObject, ViewCourseOutputBoundary presenter) {
        this.viewCourseDataAccessObject = viewCourseDataAccessObject;
        this.presenter = presenter;
    }

    /**
     * Fetches all courses in the database and switches to the view courses view.
     */
    @Override
    public void execute() {
        ViewCourseOutputData outputData = new ViewCourseOutputData(new ArrayList<>(viewCourseDataAccessObject.getAllCourses().values()));
        presenter.presentResponse(outputData);

    }
}
