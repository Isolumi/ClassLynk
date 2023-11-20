package ai.classlynk.use_case.static_maps;

import java.util.Map;

public class MapsOutputData {
    private final Map<String, String> imageLocations;

    private boolean useCaseFailed;

    public Map<String, String> getImageLocations() {
        return imageLocations;
    }

    public MapsOutputData(Map<String, String> imageLocations, boolean useCaseFailed)
    {
        this.imageLocations = imageLocations;
        this.useCaseFailed = useCaseFailed;
    }
}
