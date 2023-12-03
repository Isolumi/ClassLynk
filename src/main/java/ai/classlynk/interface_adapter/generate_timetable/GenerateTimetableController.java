package ai.classlynk.interface_adapter.generate_timetable;

import ai.classlynk.entity.Course;
import ai.classlynk.use_case.generate_timetable.GenerateTimetableInputBoundary;
import ai.classlynk.use_case.generate_timetable.GenerateTimetableInputData;

import java.util.List;

public class GenerateTimetableController {

    final GenerateTimetableInputBoundary generateUseCaseInteractor;

    public GenerateTimetableController(GenerateTimetableInputBoundary generateInputBoundary){generateUseCaseInteractor = generateInputBoundary;}

    public void execute(List<Course> courses)
    {
        GenerateTimetableInputData data = new GenerateTimetableInputData(courses);

        generateUseCaseInteractor.execute(data);
    }
}
