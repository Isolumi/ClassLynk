package ai.classlynk.entity;

import ai.classlynk.data_access.APIDataAccessObject;
import org.checkerframework.checker.units.qual.A;

import java.sql.SQLOutput;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class OptimalTimetableCalculator {
    private static Map<Set<String>, Float> distances = new HashMap<>();

    public static Timetable generateTimetable(List<Course> courses, APIDataAccessObject dao) {
        HashMap<String, List<Node>> validLectureTutorialCombos = new HashMap<>(); //Maps Course Name to valid lecture tutorial pairs of that course
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

                        for (SClass sclass : lec.getClasses()) {
                            test.get(sclass.getWeekday()).add(sclass);
                        }
                        test.get(tut.getWeekday()).add(tut);

                        Timetable conflictTest = new Timetable("application",test);
                        if (!hasTimeConflict(conflictTest)) {
                            if (!validLectureTutorialCombos.containsKey(course.getCourseId())) {
                                validLectureTutorialCombos.put(course.getCourseId(), new ArrayList<Node>());
                            }else{

                            }
                            SClass[] tutList = {tut};
                            ClassBundle tutorialBundle = new ClassBundle(tut.getClassId(), Arrays.asList(tutList));


                            ArrayList<ClassBundle> validPair = new ArrayList<>();
                            String id = course.getCourseId() + lec.lectureId + tutorialBundle.lectureId;
                            validPair.add(lec);
                            validPair.add(tutorialBundle);
                            validLectureTutorialCombos.get(course.getCourseId()).add(new Node(id, validPair));
                        }

                    }
                }else{
                    if (!validLectureTutorialCombos.containsKey(course.getCourseId())) {
                        validLectureTutorialCombos.put(course.getCourseId(), new ArrayList<Node>());
                    }
                    ArrayList<ClassBundle> validPair = new ArrayList<>();
                    String id = course.getCourseId() + lec.lectureId;
                    validPair.add(lec);
                    validLectureTutorialCombos.get(course.getCourseId()).add(new Node(id, validPair));
                }

            }
        }

        for (String name : validLectureTutorialCombos.keySet()) {
            System.out.println(name);
            for(Node pair : validLectureTutorialCombos.get(name)){
                System.out.print("|");
                for(ClassBundle sclass : pair.info) {
                    System.out.print(sclass.lectureId);
                }
                System.out.print("|");
            }
            System.out.println();

        }

        for(int i = 0; i < courses.size() - 1; i++){
            for(Node pairs: validLectureTutorialCombos.get(courses.get(i).getCourseId())){
                pairs.adj.addAll(validLectureTutorialCombos.get(courses.get(i + 1).getCourseId()));
            }
        }

        List<Timetable> validTimeTables = new ArrayList<>();

        for(Node pairs: validLectureTutorialCombos.get(courses.get(0).getCourseId())){
            List<List<ClassBundle>> cur = new ArrayList<>();
            cur.add(pairs.info);
            returnValidTimeTables(validTimeTables, cur, pairs);
        }

        Timetable bestTimetable = validTimeTables.get(0);
        float bestTimetableDistance = averageDistance(bestTimetable, dao);
        if(validTimeTables.size() == 1)
        {
            return bestTimetable;
        }
        else {
            for (Timetable timetable: validTimeTables.subList(1, validTimeTables.size() - 1)){
                float currTimetableDistance = averageDistance(timetable, dao);
                if(currTimetableDistance < bestTimetableDistance){
                    bestTimetable = timetable;
                    bestTimetableDistance = currTimetableDistance;
                }
            }
            return bestTimetable;
        }
    }


    private static void returnValidTimeTables(List<Timetable> all, List<List<ClassBundle>> cur, Node curNode) {
        if(curNode.adj.isEmpty()){
            Timetable potentialTimetable = classBundlesToTimetable(cur);
            if(!hasTimeConflict(potentialTimetable)){
                all.add(potentialTimetable);
            }
            return;
        }else{
            for (Node child : curNode.adj){
                ArrayList<List<ClassBundle>> newCur = new ArrayList<>(cur);
                newCur.add(child.info);
                returnValidTimeTables(all, newCur, child);
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

        for(List<ClassBundle> lecTutPair : inputList){
            for(ClassBundle classes : lecTutPair){
                for(SClass sclass: classes.getClasses()){
                    timeTable.get(sclass.getWeekday()).add(sclass);
                }
            }
        }

        return new Timetable(User.getInstance("", "").getUsername(), timeTable);
    }

    private static boolean hasTimeConflict(Timetable timetable) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm:ss");
        for (String day : timetable.getClasses().keySet()) {
            for (SClass sClass1 : timetable.getClasses().get(day)) {
                LocalTime startTime1 = LocalTime.parse(sClass1.getStartTime(), formatter);
                LocalTime endTime1 = LocalTime.parse(sClass1.getEndTime(), formatter);
                for (SClass sClass2 : timetable.getClasses().get(day)) {
                    if (!(sClass1.getCourseId() + sClass1.getClassId()).equals(sClass2.getCourseId() + sClass2.getClassId())) {
                        LocalTime startTime2 = LocalTime.parse(sClass2.getStartTime(), formatter);
                        LocalTime endTime2 = LocalTime.parse(sClass2.getEndTime(), formatter);
                        if (!(endTime1.isBefore(startTime2)) && startTime1.isBefore(startTime2) || !(endTime2.isBefore(startTime1)) && startTime2.isBefore(startTime1) || startTime1.equals(startTime2) && endTime1.equals(endTime2)) {
                            if(!(endTime1.equals(startTime2) || endTime2.equals(startTime1)) ){
                                System.out.println(day+sClass1.getCourseId()+sClass1.getClassId() + " and " + sClass2.getCourseId()+sClass2.getClassId() +" conflicts");
                                System.out.println(startTime1 + " " + endTime1);
                                System.out.println(startTime2 + " " + endTime2);
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    private static float averageDistance(Timetable timetable, APIDataAccessObject dao) {
//        Map<Set<String>, Float> distances = new HashMap<>();
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
