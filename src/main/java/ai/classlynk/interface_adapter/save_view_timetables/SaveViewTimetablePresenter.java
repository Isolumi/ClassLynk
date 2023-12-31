package ai.classlynk.interface_adapter.save_view_timetables;

import ai.classlynk.interface_adapter.Presenter;
import ai.classlynk.interface_adapter.ViewManagerModel;
import ai.classlynk.use_case.save_view_timetables.SaveViewTimetableOutputBoundary;
import ai.classlynk.use_case.save_view_timetables.SaveViewTimetableOutputData;

public class SaveViewTimetablePresenter implements SaveViewTimetableOutputBoundary, Presenter {

    public ViewManagerModel getViewManagerModel() {
        return viewManagerModel;
    }
    private final SaveViewTimetableViewModel saveViewTimetableViewModel;
    private final ViewManagerModel viewManagerModel;

    public SaveViewTimetablePresenter(SaveViewTimetableViewModel saveViewTimetableViewModel, ViewManagerModel viewManagerModel) {
        this.saveViewTimetableViewModel = saveViewTimetableViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareLoggedInView(SaveViewTimetableOutputData outputData) {
        SaveViewTimetableState saveViewTimetableState = saveViewTimetableViewModel.getState();
        saveViewTimetableState.setTimetables(outputData.timetable());
        saveViewTimetableState.setError(null);
        this.saveViewTimetableViewModel.setState(saveViewTimetableState);
        this.saveViewTimetableViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(saveViewTimetableViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    public void prepareLoggedInViewError(String error) {
        SaveViewTimetableState saveViewTimetableState = saveViewTimetableViewModel.getState();
            saveViewTimetableState.setError(error);
        this.saveViewTimetableViewModel.setState(saveViewTimetableState);
        this.saveViewTimetableViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(saveViewTimetableViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareNotLoggedInView() {
        // TODO: go to login view
    }
}
