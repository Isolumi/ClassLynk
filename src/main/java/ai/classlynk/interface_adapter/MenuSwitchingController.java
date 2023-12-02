package ai.classlynk.interface_adapter;

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
        previousMenuViewModel.firePropertyChanged();
        System.out.println(previousMenuViewModel.getViewName());
    }


}
