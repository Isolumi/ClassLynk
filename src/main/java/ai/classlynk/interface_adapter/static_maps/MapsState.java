package ai.classlynk.interface_adapter.static_maps;


import ai.classlynk.entity.Timetable;

import java.util.Map;

public class MapsState {

    private Map<String, String> imageLocations;



    private Timetable timetable;

    private String apiError;


    public MapsState(MapsState copy) {
        imageLocations = copy.imageLocations;
        timetable = copy.timetable;
        apiError = copy.apiError;
    }

    public MapsState() {}

    public Map<String, String> getImageLocations() {
        return imageLocations;
    }

    public void setImageLocations(Map<String, String> imageLocations) {
        this.imageLocations = imageLocations;
    }
    public Timetable getTimetable() {
        return timetable;
    }

    public void setTimetable(Timetable timetable) {
        this.timetable = timetable;
    }

    public String getApiError() {
        return apiError;
    }

    public void setApiError(String apiError) {
        this.apiError = apiError;
    }
}
