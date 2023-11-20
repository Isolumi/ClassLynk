package ai.classlynk.interface_adapter.static_maps;

import ai.classlynk.use_case.static_maps.MapsOutputBoundary;
import ai.classlynk.use_case.static_maps.MapsOutputData;

public class MapsPresenter implements MapsOutputBoundary {

    private final MapsViewModel mapsViewModel;
    //TODO: implement
    private final ViewManagerModel viewManagerModel;


    public MapsPresenter(MapsViewModel mapsViewModel, ViewManagerModel viewManagerModel)
    {
        this.mapsViewModel = mapsViewModel;
        this.viewManagerModel = viewManagerModel;
    }


    @Override
    public void prepareSuccessView(MapsOutputData imageLocations) {

    }

    @Override
    public void prepareFailView(String error) {

    }
}
