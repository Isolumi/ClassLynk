package ai.classlynk.use_case.static_maps;

import com.google.maps.errors.ApiException;
import org.springframework.stereotype.Component;

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

    /**
     * Generates route images and sends image urls and new timetable to presenter.
     *
     * @param mapsInputData The timetable to generate the routes for.
     */
    @Override
    public void execute(MapsInputData mapsInputData) {
        Map<String, String> data = null;
        boolean fail = false;
        try {
            data = mapsDataAccessObject.getStaticMaps(mapsInputData.getTimetable());
        } catch (ApiException | InterruptedException | IOException e) {
            mapsPresenter.prepareFailView("Unable to generate images. Please try again.");
            fail = true;
        }
        if(!fail)
        {
            MapsOutputData mapsOutputData = new MapsOutputData(data, false, mapsInputData.getTimetable());
            mapsPresenter.prepareSuccessView(mapsOutputData);
        }

    }
}
