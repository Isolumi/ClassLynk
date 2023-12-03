package ai.classlynk.use_case.generate_timetable;

import ai.classlynk.entity.Course;
import ai.classlynk.entity.OptimalTimetableCalculator;
import ai.classlynk.entity.Timetable;
import ai.classlynk.use_case.save_view_timetables.SaveViewTimetableOutputBoundary;
import ai.classlynk.use_case.save_view_timetables.SaveViewTimetableOutputData;

import java.util.ArrayList;
import java.util.List;

public class GenerateTimetableInteractor implements GenerateTimetableInputBoundary{

    final SaveViewTimetableOutputBoundary generateTimetableOutputBoundary;


    public GenerateTimetableInteractor(SaveViewTimetableOutputBoundary generateTimetableOutputBoundary){
        this.generateTimetableOutputBoundary = generateTimetableOutputBoundary;
    }

    public void execute(GenerateTimetableInputData generateTimetableInputData) {
        List<Timetable> timetable = new ArrayList<>();
        timetable.add(OptimalTimetableCalculator.generateTimetable(generateTimetableInputData.getCourses()));
        SaveViewTimetableOutputData outputData = new SaveViewTimetableOutputData(timetable);
        generateTimetableOutputBoundary.prepareLoggedInView(outputData);
    }

}
