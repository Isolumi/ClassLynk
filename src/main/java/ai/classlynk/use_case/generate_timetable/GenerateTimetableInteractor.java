package ai.classlynk.use_case.generate_timetable;

import ai.classlynk.data_access.APIDataAccessObject;
import ai.classlynk.entity.BruteForceAlgorithm;
import ai.classlynk.entity.OptimizationAlgorithm;
import ai.classlynk.entity.Timetable;
import ai.classlynk.use_case.save_view_timetables.SaveViewTimetableOutputBoundary;
import ai.classlynk.use_case.save_view_timetables.SaveViewTimetableOutputData;

import java.util.NoSuchElementException;

public class GenerateTimetableInteractor implements GenerateTimetableInputBoundary{

    final SaveViewTimetableOutputBoundary generateTimetableOutputBoundary;
    final APIDataAccessObject dao;


    public GenerateTimetableInteractor(SaveViewTimetableOutputBoundary generateTimetableOutputBoundary, APIDataAccessObject dao){
        this.generateTimetableOutputBoundary = generateTimetableOutputBoundary;
        this.dao = dao;
    }

    /**
     * Generates an optimal timetable, if one is possible with the given classes, and displays it in the save/view timetable view
     * @param generateTimetableInputData The list of courses to make an optimal timetable for
     */
    public void execute(GenerateTimetableInputData generateTimetableInputData) {
        try
        {
            OptimizationAlgorithm algorithm = new BruteForceAlgorithm();
            Timetable timetable = algorithm.generateTimetable(generateTimetableInputData.getCourses(), dao);
            SaveViewTimetableOutputData outputData = new SaveViewTimetableOutputData(timetable);
            generateTimetableOutputBoundary.prepareLoggedInView(outputData);
        }
        catch (NoSuchElementException e)
        {
            generateTimetableOutputBoundary.prepareLoggedInViewError("Couldn't generate timetable. Please try a different set of courses.");
        }
    }

}
