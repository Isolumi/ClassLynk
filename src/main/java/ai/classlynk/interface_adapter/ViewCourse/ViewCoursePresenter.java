package ai.classlynk.interface_adapter.ViewCourse;


import ai.classlynk.interface_adapter.ViewManagerModel;
import ai.classlynk.use_case.ViewCourse.ViewCourseOutputBoundary;
import ai.classlynk.use_case.ViewCourse.ViewCourseOutputData;

public class ViewCoursePresenter implements ViewCourseOutputBoundary {
    private final ViewCourseViewModel viewCourseViewModel;
    private final ViewManagerModel viewManagerModel;

    public ViewCoursePresenter(ViewManagerModel viewManagerModel, ViewCourseViewModel viewCourseViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.viewCourseViewModel = viewCourseViewModel;
    }

    @Override
    public void presentResponse(ViewCourseOutputData response) {
        viewCourseViewModel.setState(new ViewCourseState(response.getAllCourses()));
        viewCourseViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(viewCourseViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}

