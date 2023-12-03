package ai.classlynk.entity;

import ai.classlynk.data_access.APIDataAccessObject;

public interface OptimizationParameter {

    public float getScore(float value);

    public float calculateNonNormalizedScore(Timetable timetable);
}
