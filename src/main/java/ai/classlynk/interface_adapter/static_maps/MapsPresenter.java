package ai.classlynk.interface_adapter.static_maps;

import ai.classlynk.interface_adapter.ViewManagerModel;
import ai.classlynk.use_case.static_maps.MapsOutputBoundary;
import ai.classlynk.use_case.static_maps.MapsOutputData;

public class MapsPresenter implements MapsOutputBoundary {

    private final MapsViewModel mapsViewModel;

    private final ViewManagerModel viewManagerModel;


    public ViewManagerModel getViewManagerModel() {
        return viewManagerModel;
    }

    public MapsPresenter(MapsViewModel mapsViewModel, ViewManagerModel viewManagerModel)
    {
        this.mapsViewModel = mapsViewModel;
        this.viewManagerModel = viewManagerModel;
    }


    @Override
    public void prepareSuccessView(MapsOutputData output) {
        MapsState mapsState = mapsViewModel.getState();
        mapsState.setImageLocations(output.getImageLocations());
        mapsState.setTimetable(output.getTimetable());
        this.mapsViewModel.setState(mapsState);
        this.mapsViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(mapsViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        MapsState mapsState = mapsViewModel.getState();
        mapsState.setApiError(error);
        mapsViewModel.firePropertyChanged();

        //TODO to display error msg, may need to move user to the mapsview
    }
}
