package ai.classlynk.use_case.SelfMadeTimeTable;

import ai.classlynk.entity.SClass;
import ai.classlynk.entity.Timetable;
import ai.classlynk.entity.User;

import java.util.List;
import java.util.Map;

public class SelfMadeTimeTable implements SelfMadeTimeTableInputBoundary {
    User user;
    Timetable selfMadeTimeTable;
    SClass customSClass;
    SelfMadeTimeTableOutputBoundary presenter;

    public SelfMadeTimeTable (User user, Timetable selfMadeTimeTable, SClass customSClass, SelfMadeTimeTableOutputBoundary presenter) {
        this.user = user;
        this.selfMadeTimeTable = selfMadeTimeTable;
        this.customSClass = customSClass;
        this.presenter = presenter;
    }

    @Override
    public void AddToTimeTable(SelfMadeTimeTableInputData InputData) {
        List<SClass> updatedSClassCart = user.getClassKart();
        updatedSClassCart.add(InputData.getCustomSClass());
        Map<String, List<SClass>> newMap = selfMadeTimeTable.getClasses();
        SClass addingSClass = InputData.getCustomSClass();
        newMap.get(InputData.getCustomSClass().getWeekday()).add(addingSClass);
        selfMadeTimeTable.setClasses(newMap);
        String message = addingSClass.getClassId() + " is added";
        presenter.presentResponse(new SelfMadeTimeTableOutputData(true, message, selfMadeTimeTable));
    }
}
