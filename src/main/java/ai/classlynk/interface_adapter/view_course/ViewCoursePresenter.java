package ai.classlynk.interface_adapter.view_course;


import ai.classlynk.interface_adapter.Presenter;
import ai.classlynk.interface_adapter.ViewManagerModel;
import ai.classlynk.use_case.view_course.ViewCourseOutputBoundary;
import ai.classlynk.use_case.view_course.ViewCourseOutputData;

public class ViewCoursePresenter implements ViewCourseOutputBoundary, Presenter {
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

    @Override
    public ViewManagerModel getViewManagerModel() {
        return this.viewManagerModel;
    }
}

