package ai.classlynk.use_case.static_maps;

import ai.classlynk.entity.Timetable;

public class MapsInputData {

    final private Timetable timetable;

    public Timetable getTimetable() {
        return timetable;
    }

    public MapsInputData(Timetable timetable)
    {
        this.timetable = timetable;
    }
}
