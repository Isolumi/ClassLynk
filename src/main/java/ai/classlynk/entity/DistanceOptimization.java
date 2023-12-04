package ai.classlynk.entity;

import ai.classlynk.data_access.APIDataAccessObject;

import java.util.*;

public class DistanceOptimization implements OptimizationParameter
{
    private final Map<Set<String>, Float> distances = new HashMap<>();

    private final float standardDeviation;
    private final float averageDistance;
    private final float maxStandardDeviation;

    private final APIDataAccessObject dao;

    public DistanceOptimization(List<Timetable> timetables, APIDataAccessObject dao)
    {
        ArrayList<Float> averageDistances = new ArrayList<>();
        this.dao = dao;
        float averageDistanceSum = 0;
        for(Timetable timetable: timetables)
        {
            float averageDistance = calculateNonNormalizedScore(timetable);
            averageDistanceSum += averageDistance;
            averageDistances.add(averageDistance);
        }
        averageDistance = averageDistanceSum / timetables.size();
        standardDeviation = calculateSD(averageDistances);
        maxStandardDeviation = (Collections.max(averageDistances) - averageDistance) / standardDeviation;

    }
    @Override
    public float calculateNonNormalizedScore(Timetable timetable) {
        float totalDistance = 0;
        int totalCourseLoad = 0;
        for (String day : timetable.getClasses().keySet()) {
            List<SClass> classesForDay = timetable.getClasses().get(day);
            totalCourseLoad += classesForDay.size();
            for (int i = 0; i < timetable.getClasses().get(day).size() - 2; i++) {
                totalDistance += getDistance(distances, classesForDay.get(i).getLocation(), classesForDay.get(i + 1).getLocation());
            }
        }
        return totalDistance / totalCourseLoad;
    }

    private float getDistance(Map<Set<String>, Float> distances, String loc1, String loc2) {
        Set<String> loc = new HashSet<>();
        loc.add(loc1);
        loc.add(loc2);
        if (distances.containsKey(loc)) {
            return distances.get(loc);
        } else {
            float distance = dao.getRouteLength(loc1, loc2);
            distances.put(loc, distance);
            return distance;
        }
    }

    public static float calculateSD(ArrayList<Float> list) {
        double sum = 0.0;
        double standardDeviation = 0.0;
        int length = list.size();

        for (Float num : list) {
            sum += num;
        }

        double mean = sum / length;

        for (Float num : list) {
            standardDeviation += Math.pow(num - mean, 2);
        }

        return (float) Math.sqrt(standardDeviation / length);
    }
    @Override
    public float getScore(float value) {
        float numberStandardDeviations = (value - averageDistance) / standardDeviation;
        return (numberStandardDeviations / maxStandardDeviation) * 100;
    }
}
