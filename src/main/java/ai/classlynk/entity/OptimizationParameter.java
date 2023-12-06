package ai.classlynk.entity;


public interface OptimizationParameter {

    public float getScore(float value);

    public float calculateNonNormalizedScore(Timetable timetable);
}
