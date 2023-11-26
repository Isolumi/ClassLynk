package ai.classlynk.interface_adapter.static_maps;

import ai.classlynk.interface_adapter.save_view_timetables.SaveViewTimetableViewModel;

public class BackButtonController {

    private MapsPresenter presenter;

    private SaveViewTimetableViewModel saveViewTimetableViewModel;

    public BackButtonController(MapsPresenter presenter, SaveViewTimetableViewModel saveViewTimetableViewModel)
    {
        this.presenter = presenter;
        this.saveViewTimetableViewModel = saveViewTimetableViewModel;
    }

    public void execute()
    {
        presenter.getViewManagerModel().setActiveView(saveViewTimetableViewModel.getViewName());
        presenter.getViewManagerModel().firePropertyChanged();
        saveViewTimetableViewModel.firePropertyChanged();
    }


}
