package ai.classlynk.entity;

import ai.classlynk.data_access.APIDataAccessObject;
import org.checkerframework.checker.units.qual.A;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class OptimalTimetableCalculator {
    public static Timetable generateTimetable(List<Course> courses) {
        HashMap<String, List<List<ClassBundle>>> validLectureTutorialCombos = new HashMap<>(); //Maps Course Name to valid lecture tutorial pairs of that course
        for (Course course : courses) {
            for (ClassBundle lec : course.getClassBundles()) {
                if(!course.getTutorials().isEmpty()){
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
                            if (!validLectureTutorialCombos.containsKey(course.getCourseName())) {
                                validLectureTutorialCombos.put(course.getCourseName(), new ArrayList<List<ClassBundle>>());
                            }
                            SClass[] tutList = {tut};
                            ClassBundle tutorialBundle = new ClassBundle(tut.getClassId(), Arrays.asList(tutList));


                            ArrayList<ClassBundle> validPair = new ArrayList<>();
                            validPair.add(lec);
                            validPair.add(tutorialBundle);
                            validLectureTutorialCombos.get(course.getCourseName()).add(validPair);
                        }

                    }
                }else{
                    if (!validLectureTutorialCombos.containsKey(course.getCourseName())) {
                        validLectureTutorialCombos.put(course.getCourseName(), new ArrayList<List<ClassBundle>>());
                    }
                    ArrayList<ClassBundle> validPair = new ArrayList<>();
                    validPair.add(lec);
                    validLectureTutorialCombos.get(course.getCourseName()).add(validPair);
                }

            }
        }
        HashMap<String, List<List<ClassBundle>>>  adjacencyMatrix = new HashMap<>();
        for(int i = 0; i < courses.size() - 1; i++){
            for(List<ClassBundle> pairs: validLectureTutorialCombos.get(courses.get(i).getCourseId())){
                StringBuilder id = new StringBuilder();
                for(ClassBundle classBundle : pairs){
                    id.append(classBundle.getLectureId());
                }
                adjacencyMatrix.put(id.toString(), new ArrayList<List<ClassBundle>>());
                for(List<ClassBundle> pair2: validLectureTutorialCombos.get(courses.get(i + 1).getCourseId())){
                    adjacencyMatrix.get(id.toString()).add(pair2);
                }
            }
        }

        List<Timetable> validTimeTables = new ArrayList<>();

        for(List<ClassBundle> pairs: validLectureTutorialCombos.get(courses.get(0).getCourseId())){
            List<List<ClassBundle>> cur = new ArrayList<>();
            cur.add(pairs);
            returnValidTimeTables(validTimeTables, cur, adjacencyMatrix);
        }

        Timetable bestTimetable = validTimeTables.get(0);
        for (Timetable timetable: validTimeTables){
            if(averageDistance(timetable) < averageDistance(bestTimetable)){
                bestTimetable = timetable;
            }
        }
        return bestTimetable;
    }


    private static void returnValidTimeTables(List<Timetable> all, List<List<ClassBundle>> cur, HashMap<String, List<List<ClassBundle>>>  adjacencyMatrix) {
        String id = cur.get(cur.size() - 1).get(0).lectureId + cur.get(cur.size() - 1).get(1).lectureId;
        if(adjacencyMatrix.get(id).isEmpty()){
            Timetable potentialTimetable = classBundlesToTimetable(cur);
            if(!hasTimeConflict(potentialTimetable)){
                all.add(potentialTimetable);
            }
            return;
        }else{
            List<List<ClassBundle>> children = adjacencyMatrix.get(id);
            for (List<ClassBundle> child : children){
                ArrayList<List<ClassBundle>> newCur = new ArrayList<>(cur);
                newCur.add(child);
                returnValidTimeTables(all, newCur, adjacencyMatrix);
            }
        }
    }

    private static Timetable classBundlesToTimetable(List<List<ClassBundle>> inputList){
        HashMap<String, List<SClass>> timeTable = new HashMap<>();
        timeTable.put("Monday", new ArrayList<SClass>());
        timeTable.put("Tuesday", new ArrayList<SClass>());
        timeTable.put("Wednesday", new ArrayList<SClass>());
        timeTable.put("Thursday", new ArrayList<SClass>());
        timeTable.put("Friday", new ArrayList<SClass>());
        timeTable.put("Saturday", new ArrayList<SClass>());
        timeTable.put("Sunday", new ArrayList<SClass>());

        for(List<ClassBundle> lecTutPair : inputList){
            for(ClassBundle classes : lecTutPair){
                for(SClass sclass: classes.getClasses()){
                    timeTable.get(sclass.getWeekday()).add(sclass);
                }
            }
        }

        return new Timetable(timeTable);
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
