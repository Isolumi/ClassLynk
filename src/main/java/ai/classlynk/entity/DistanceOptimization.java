package ai.classlynk.entity;

import ai.classlynk.data_access.APIDataAccessObject;

import java.util.*;

public class DistanceOptimization implements OptimizationParameter
{
    private static Map<Set<String>, Float> distances = new HashMap<>();
    public static float averageDistance(Timetable timetable, APIDataAccessObject dao) {
        float totalDistance = 0;
        int totalCourseLoad = 0;
        for (String day : timetable.getClasses().keySet()) {
            List<SClass> classesForDay = timetable.getClasses().get(day);
            totalCourseLoad += classesForDay.size();
            for (int i = 0; i < timetable.getClasses().get(day).size() - 2; i++) {
                totalDistance += getDistance(dao, distances, classesForDay.get(i).getLocation(), classesForDay.get(i + 1).getLocation());
            }
        }
        return totalDistance / totalCourseLoad;
    }

    private static float getDistance(APIDataAccessObject dao, Map<Set<String>, Float> distances, String loc1, String loc2) {
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

}
