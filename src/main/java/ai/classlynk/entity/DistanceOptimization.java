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

    /**
     * Initializes a new distance optimization parameter, calculating the average, standard deviation, and maximum standard deviation.
     * @param timetables The list of timetables to create the distance optimization parameter for.
     */
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
        standardDeviation = calculateStandardDeviation(averageDistances);
        float maxStandardDeviationPos = (Collections.max(averageDistances) - averageDistance) / standardDeviation;
        float maxStandardDeviationNeg = (Collections.min(averageDistances) - averageDistance) / standardDeviation;
        if(Math.abs(maxStandardDeviationNeg) > maxStandardDeviationPos)
        {
            maxStandardDeviation = Math.abs(maxStandardDeviationNeg);
        }
        else {
            maxStandardDeviation = maxStandardDeviationPos;
        }

    }

    /**
     * Calculates a non-normalized score for the timetable.
     * @param timetable The timetable to calculate the non-normalized score for.
     * @return The non-normalized score.
     */
    @Override
    public float calculateNonNormalizedScore(Timetable timetable) {
        float totalDistance = 0;
        int totalCourseLoad = 0;
        for (String day : timetable.getClasses().keySet()) {
            List<SClass> classesForDay = timetable.getClasses().get(day);
            totalCourseLoad += classesForDay.size();
            for (int i = 0; i < timetable.getClasses().get(day).size() - 2; i++) {
                totalDistance += getDistance(classesForDay.get(i).getLocation(), classesForDay.get(i + 1).getLocation());
            }
        }
        return totalDistance / totalCourseLoad;
    }

    /**
     * Gets the route length between the two locations.
     * @param loc1 The starting location.
     * @param loc2 THe ending location.
     * @return The distance in meters.
     */
    private float getDistance(String loc1, String loc2) {
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


    public static float calculateStandardDeviation(ArrayList<Float> list) {
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

    /**
     * Calculates a normalized score for the timetable from 0-100.
     * @param value Non-normalizedscore to normalize.
     * @return The normalized score.
     */
    @Override
    public float getScore(float value) {
        float numberStandardDeviations = (value - averageDistance) / standardDeviation;
        return (numberStandardDeviations / maxStandardDeviation) * 100;
    }
}
