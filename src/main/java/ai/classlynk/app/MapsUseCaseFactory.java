package ai.classlynk.app;

import ai.classlynk.interface_adapter.ViewManagerModel;
import ai.classlynk.interface_adapter.static_maps.MapsController;
import ai.classlynk.interface_adapter.static_maps.MapsPresenter;
import ai.classlynk.interface_adapter.static_maps.MapsViewModel;
import ai.classlynk.use_case.static_maps.*;

import java.util.Map;

public class MapsUseCaseFactory {

    private MapsUseCaseFactory(){}

    public static MapsController createMapsUseCase(ViewManagerModel viewManagerModel, MapsDataAccessInterface mapsDataAccessInterface, MapsViewModel mapsViewModel)
    {
        MapsOutputBoundary mapsOutputBoundary = new MapsPresenter(mapsViewModel, viewManagerModel);
        MapsInputBoundary mapsInputBoundary = new MapsInteractor(mapsDataAccessInterface, mapsOutputBoundary);

        return new MapsController(mapsInputBoundary);
    }
}
