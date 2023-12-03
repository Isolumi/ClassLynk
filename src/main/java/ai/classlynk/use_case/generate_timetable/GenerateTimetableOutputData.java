package ai.classlynk.use_case.generate_timetable;

import ai.classlynk.entity.Timetable;

public class GenerateTimetableOutputData{
    final private Timetable optimalTimetable;

    public GenerateTimetableOutputData(Timetable optimalTimetable){
        this.optimalTimetable = optimalTimetable;
    }

    Timetable getOptimalTimetable(){
        return optimalTimetable;
    }
}
