package ai.classlynk.use_case.SelfMadeTimeTable;

import ai.classlynk.entity.*;

import java.util.List;
import java.util.Map;

public class SelfMadeTimeTable implements SelfMadeTimeTableInputBoundary {
    User user;
    Timetable selfMadeTimeTable;
    SelfMadeTimeTableOutputBoundary presenter;

    public SelfMadeTimeTable (User user, Timetable selfMadeTimeTable, SelfMadeTimeTableOutputBoundary presenter) {
        this.user = user;
        this.selfMadeTimeTable = selfMadeTimeTable;
        this.presenter = presenter;
    }

    @Override
    public void AddToTimeTable(SelfMadeTimeTableInputData InputData) {
        Course course = InputData.getCourse();
        Boolean success = false;
        for (ClassBundle classBundle : course.getClassBundles()) {
            for (SClass sClass : classBundle.getClasses()) {
                if (InputData.getStartTime().equals(sClass.getStartTime()) && InputData.getWeekday().equals(sClass.getWeekday())) {
                    success = true;
                    Map<String, List<SClass>> newMap = selfMadeTimeTable.getClasses();
                    newMap.get(InputData.getWeekday()).add(sClass);
                    selfMadeTimeTable.setClasses(newMap);
                    String message = course.getCourseId() + " is added";
                    presenter.presentResponse(new SelfMadeTimeTableOutputData(success, message, selfMadeTimeTable));
                    return;
                }
            }
        }
        if (!success) {
            String message = "There is no class available at this time block";
            presenter.presentResponse(new SelfMadeTimeTableOutputData(success, message, selfMadeTimeTable));
        }
    }
}
