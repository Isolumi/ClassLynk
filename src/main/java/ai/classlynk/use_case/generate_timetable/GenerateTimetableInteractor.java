package ai.classlynk.use_case.generate_timetable;

import ai.classlynk.data_access.APIDataAccessObject;
import ai.classlynk.entity.Course;
import ai.classlynk.entity.OptimalTimetableCalculator;
import ai.classlynk.entity.Timetable;
import ai.classlynk.use_case.save_view_timetables.SaveViewTimetableOutputBoundary;
import ai.classlynk.use_case.save_view_timetables.SaveViewTimetableOutputData;

import java.util.ArrayList;
import java.util.List;

public class GenerateTimetableInteractor implements GenerateTimetableInputBoundary{

    final SaveViewTimetableOutputBoundary generateTimetableOutputBoundary;
    final APIDataAccessObject dao;


    public GenerateTimetableInteractor(SaveViewTimetableOutputBoundary generateTimetableOutputBoundary, APIDataAccessObject dao){
        this.generateTimetableOutputBoundary = generateTimetableOutputBoundary;
        this.dao = dao;
    }

    public void execute(GenerateTimetableInputData generateTimetableInputData) {
        Timetable timetable = OptimalTimetableCalculator.generateTimetable(generateTimetableInputData.getCourses(), dao);
        SaveViewTimetableOutputData outputData = new SaveViewTimetableOutputData(timetable);
        generateTimetableOutputBoundary.prepareLoggedInView(outputData);
    }

}
