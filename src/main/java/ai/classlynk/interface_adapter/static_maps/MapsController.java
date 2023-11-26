package ai.classlynk.interface_adapter.static_maps;

import ai.classlynk.entity.Timetable;
import ai.classlynk.use_case.static_maps.MapsInputBoundary;
import ai.classlynk.use_case.static_maps.MapsInputData;
import ai.classlynk.use_case.static_maps.MapsOutputBoundary;

public class MapsController {

    final MapsInputBoundary mapsUseCaseInteractor;

    public MapsController(MapsInputBoundary mapsInputBoundary)
    {
        mapsUseCaseInteractor = mapsInputBoundary;
    }

    public void execute(Timetable timetable)
    {
        MapsInputData data = new MapsInputData(timetable);

        mapsUseCaseInteractor.execute(data);
    }
}
