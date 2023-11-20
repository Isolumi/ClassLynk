package ai.classlynk.use_case.static_maps;

public interface MapsOutputBoundary {
    void prepareSuccessView(MapsOutputData imageLocations);

    void prepareFailView(String error);
}
