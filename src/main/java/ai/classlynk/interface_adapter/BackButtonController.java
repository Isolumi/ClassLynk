package ai.classlynk.interface_adapter;

import ai.classlynk.interface_adapter.Presenter;
import ai.classlynk.interface_adapter.ViewModel;

public class BackButtonController {

    private Presenter presenter;

    private ViewModel previousMenuViewModel;

    public BackButtonController(Presenter presenter, ViewModel saveViewTimetableViewModel)
    {
        this.presenter = presenter;
        this.previousMenuViewModel = saveViewTimetableViewModel;
    }

    public void execute()
    {
        presenter.getViewManagerModel().setActiveView(previousMenuViewModel.getViewName());
        presenter.getViewManagerModel().firePropertyChanged();
        previousMenuViewModel.firePropertyChanged();
    }


}
