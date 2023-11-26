package ai.classlynk.use_case.static_maps;

import ai.classlynk.data_access.APIDataAccessObject;
import com.google.maps.errors.ApiException;

import java.io.IOException;
import java.util.Map;

public class MapsInteractor implements MapsInputBoundary{
    final MapsDataAccessInterface mapsDataAccessObject;

    final MapsOutputBoundary mapsPresenter;

    public MapsInteractor(MapsDataAccessInterface apiDataAccessObject, MapsOutputBoundary mapsOutputBoundary)
    {
        mapsDataAccessObject = apiDataAccessObject;
        mapsPresenter = mapsOutputBoundary;
    }
    @Override
    public void execute(MapsInputData mapsInputData) {
        Map<String, String> data = null;
        try {
            data = mapsDataAccessObject.getStaticMaps(mapsInputData.getTimetable());
        } catch (ApiException | InterruptedException | IOException e) {
            mapsPresenter.prepareFailView("Unable to generate images. Please try again.");
        }
        MapsOutputData mapsOutputData = new MapsOutputData(data, false, mapsInputData.getTimetable());
        mapsPresenter.prepareSuccessView(mapsOutputData);
    }
}
