package ai.classlynk.entity;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OptimalTimetableCalculator {
    public static Timetable generateTimetable(List<Course> courses) {
        HashMap<String, ArrayList<ClassBundle[]> > validLectureTutorialCombos = new HashMap<>();
        for(Course course: courses){
            for(ClassBundle lec: course.getClassBundles()){
                if(!lec.getClasses().get(0).isTutorial()){
                    for(ClassBundle tut: course.getClassBundles()){
                        if(tut.getClasses().get(0).isTutorial()){
                            HashMap<String, List<SClass>> test = new HashMap<>();
                            test.put("Monday", new ArrayList<SClass>());
                            test.put("Tuesday", new ArrayList<SClass>());
                            test.put("Wednesday", new ArrayList<SClass>());
                            test.put("Thursday", new ArrayList<SClass>());
                            test.put("Friday", new ArrayList<SClass>());
                            test.put("Saturday", new ArrayList<SClass>());
                            test.put("Sunday", new ArrayList<SClass>());

                            for(SClass sclass: lec.getClasses()){
                                test.get(sclass.getWeekday()).add(sclass);
                            }

                            for(SClass sclass: tut.getClasses()){
                                test.get(sclass.getWeekday()).add(sclass);
                            }
                            Timetable conflictTest = new Timetable(test);
                            if(!hasTimeConflict(conflictTest)){
                                if(validLectureTutorialCombos.containsKey(course.getCourseName())){
                                    validLectureTutorialCombos.put(course.getCourseName(), new ArrayList<ClassBundle[]>());
                                }
                                ClassBundle[] validPair = {lec, tut};
                                validLectureTutorialCombos.get(course.getCourseName()).add(validPair);
                            }
                        }
                    }
                }
            }
        }
        return null;

    }

    private static boolean hasTimeConflict(Timetable timetable){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        for(String day: timetable.getClasses().keySet()){
            for(SClass sClass1: timetable.getClasses().get(day)){
                LocalTime startTime1 = LocalTime.parse(sClass1.getStartTime(), formatter);
                LocalTime endTime1 = LocalTime.parse(sClass1.getEndTime(), formatter);
                for(SClass sClass2: timetable.getClasses().get(day)){
                    if (!sClass1.equals(sClass2)){
                        LocalTime startTime2 = LocalTime.parse(sClass2.getStartTime(), formatter);
                        LocalTime endTime2 = LocalTime.parse(sClass2.getEndTime(), formatter);
                        if(!(endTime1.isBefore(startTime2) || endTime2.isBefore(startTime1))){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
