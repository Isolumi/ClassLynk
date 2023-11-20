package ai.classlynk.entity;

import ai.classlynk.data_access.APIDataAccessObject;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OptimalTimetableCalculator {
    public static Timetable generateTimetable(List<Course> courses) {
        HashMap<String, List<ClassBundle[]>> validLectureTutorialCombos = new HashMap<>(); //Maps Course Name to valid lecture tutorial pairs of that course
        for (Course course : courses) {
            for (ClassBundle lec : course.getClassBundles()) {
                for (SClass tut : course.getTutorials()) {
                        HashMap<String, List<SClass>> test = new HashMap<>();
                        test.put("Monday", new ArrayList<SClass>());
                        test.put("Tuesday", new ArrayList<SClass>());
                        test.put("Wednesday", new ArrayList<SClass>());
                        test.put("Thursday", new ArrayList<SClass>());
                        test.put("Friday", new ArrayList<SClass>());
                        test.put("Saturday", new ArrayList<SClass>());
                        test.put("Sunday", new ArrayList<SClass>());

                        for (SClass sclass : lec.getClasses()) {
                            test.get(sclass.getWeekday()).add(sclass);
                        }

                        test.get(tut.getWeekday()).add(tut);

                        Timetable conflictTest = new Timetable(test);
                        if (!hasTimeConflict(conflictTest)) {
                            if (validLectureTutorialCombos.containsKey(course.getCourseName())) {
                                validLectureTutorialCombos.put(course.getCourseName(), new ArrayList<ClassBundle[]>());
                            }
                            List<SClass> tutorial = new ArrayList<>();
                            tutorial.add(tut);

                            ClassBundle tutorialBundle = new ClassBundle(tutorial)
                            ClassBundle[] validPair = {lec, };
                            validLectureTutorialCombos.get(course.getCourseName()).add(validPair);
                        }

                }
            }
        }
        List<Timetable> validTimeTables = new ArrayList<>();
        for (String courseName : validLectureTutorialCombos.keySet()) {

        }
        return null;
    }






    private static List<Timetable> returnValidTimeTables(List<ClassBundle> cur, HashMap<List<ClassBundle>, HashMap<String, List<ClassBundle>>>  adjacencyMatrix, HashMap<String, List<ClassBundle[]>> validCombos, List<Timetable> acc, HashMap<String, Boolean> vis) {
        String id = cur.get(0).getCourseId();
        vis.get(id);
        return null
    }

    private static boolean hasTimeConflict(Timetable timetable) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        for (String day : timetable.getClasses().keySet()) {
            for (SClass sClass1 : timetable.getClasses().get(day)) {
                LocalTime startTime1 = LocalTime.parse(sClass1.getStartTime(), formatter);
                LocalTime endTime1 = LocalTime.parse(sClass1.getEndTime(), formatter);
                for (SClass sClass2 : timetable.getClasses().get(day)) {
                    if (!sClass1.equals(sClass2)) {
                        LocalTime startTime2 = LocalTime.parse(sClass2.getStartTime(), formatter);
                        LocalTime endTime2 = LocalTime.parse(sClass2.getEndTime(), formatter);
                        if (!(endTime1.isBefore(startTime2) || endTime2.isBefore(startTime1))) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private static float averageDistance(Timetable timetable) {
        APIDataAccessObject distanceCalc = new APIDataAccessObject();
        float totalDistance = 0;
        int totalCourseLoad = 0;
        for (String day : timetable.getClasses().keySet()) {
            List<SClass> classesForDay = timetable.getClasses().get(day);
            totalCourseLoad += classesForDay.size();
            for (int i = 0; i < timetable.getClasses().get(day).size() - 1; i++) {
                totalDistance += distanceCalc.getRouteLength(classesForDay.get(i).getLocation(), classesForDay.get(i + 1).getLocation());
            }
        }
        return totalDistance / totalCourseLoad;
    }
}
