package ai.classlynk.use_case.SelfMadeTimeTable;

import ai.classlynk.entity.SClass;

public class SelfMadeTimeTableInputData {
    private SClass customSClass;

    public SelfMadeTimeTableInputData (String courseId, String classId, String startTime, String endTime, String weekday, String building, String location, boolean isTutorial) {
        this.customSClass = new SClass(courseId, classId, startTime, endTime, weekday, building, location, isTutorial);
    }

    public SClass getCustomSClass() {
        return customSClass;
    }
}
