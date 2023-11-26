package ai.classlynk.use_case.static_maps;

import ai.classlynk.entity.Timetable;

import java.util.Map;

public class MapsOutputData {
    private final Map<String, String> imageLocations;

    private Timetable timetable;

    private boolean useCaseFailed;

    public Map<String, String> getImageLocations() {
        return imageLocations;
    }

    public Timetable getTimetable() {
        return timetable;
    }

    public MapsOutputData(Map<String, String> imageLocations, boolean useCaseFailed, Timetable timetable)
    {
        this.imageLocations = imageLocations;
        this.useCaseFailed = useCaseFailed;
        this.timetable = timetable;
    }
}
