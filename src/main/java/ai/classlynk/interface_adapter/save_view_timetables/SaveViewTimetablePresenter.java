package ai.classlynk.interface_adapter.save_view_timetables;

import ai.classlynk.interface_adapter.ViewManagerModel;
import ai.classlynk.use_case.save_view_timetables.SaveViewTimetableOutputBoundary;
import ai.classlynk.use_case.save_view_timetables.SaveViewTimetableOutputData;

public class SaveViewTimetablePresenter implements SaveViewTimetableOutputBoundary {
    private final SaveViewTimetableViewModel saveViewTimetableViewModel;
    private final ViewManagerModel viewManagerModel;

    public SaveViewTimetablePresenter(SaveViewTimetableViewModel saveViewTimetableViewModel, ViewManagerModel viewManagerModel) {
        this.saveViewTimetableViewModel = saveViewTimetableViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareLoggedInView(SaveViewTimetableOutputData timetables) {
        SaveViewTimetableState saveViewTimetableState = saveViewTimetableViewModel.getState();
    }

    @Override
    public void prepareNotLoggedInView() {

    }
}
