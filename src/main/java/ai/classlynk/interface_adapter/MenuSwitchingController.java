package ai.classlynk.interface_adapter;

import ai.classlynk.interface_adapter.save_view_timetables.SaveViewTimetableState;
import ai.classlynk.interface_adapter.save_view_timetables.SaveViewTimetableViewModel;

public class MenuSwitchingController {

    private Presenter presenter;

    private ViewModel previousMenuViewModel;

    public MenuSwitchingController(Presenter presenter, ViewModel viewModel)
    {
        this.presenter = presenter;
        this.previousMenuViewModel = viewModel;
    }

    public void execute()
    {
        presenter.getViewManagerModel().setActiveView(previousMenuViewModel.getViewName());
        presenter.getViewManagerModel().firePropertyChanged();
        if(previousMenuViewModel.getViewName().equals("Your Timetables"))
        {
            SaveViewTimetableState state = ((SaveViewTimetableViewModel)previousMenuViewModel).getState();
            state.setError(null);
            ((SaveViewTimetableViewModel)previousMenuViewModel).setState(state);
        }
        previousMenuViewModel.firePropertyChanged();
        System.out.println(previousMenuViewModel.getViewName());
    }


}
