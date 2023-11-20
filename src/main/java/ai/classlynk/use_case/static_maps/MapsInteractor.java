package ai.classlynk.use_case.static_maps;

import ai.classlynk.data_access.APIDataAccessObject;
import com.google.maps.errors.ApiException;

import java.io.IOException;
import java.util.Map;

public class MapsInteractor implements MapsInputBoundary{
    final APIDataAccessObject mapsDataAccessObject;

    final MapsOutputBoundary mapsPresenter;

    public MapsInteractor(APIDataAccessObject apiDataAccessObject, MapsOutputBoundary mapsOutputBoundary)
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
            mapsPresenter.prepareFailView("Unable to generate timetables. Please try again.");
        }
        MapsOutputData mapsOutputData = new MapsOutputData(data, false);
        mapsPresenter.prepareSuccessView(mapsOutputData);
    }
}
