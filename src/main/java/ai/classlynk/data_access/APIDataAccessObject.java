package ai.classlynk.data_access;

import ai.classlynk.entity.Timetable;
import ai.classlynk.use_case.GenerateStaticImage.GenerateStaticImageDataAccessInterface;
import ai.classlynk.use_case.generate_timetable.TimetableGeneratorDataAccessInterface;

import java.util.ArrayList;

public class APIDataAccessObject implements TimetableGeneratorDataAccessInterface, GenerateStaticImageDataAccessInterface {
    @Override
    public ArrayList<String> getStaticMaps(Timetable Timetable) {
        return null;
    }

    @Override
    public float getRouteLength(String origin, String destination) {
        return 0;
    }

    @Override
    public String geoCodeAddress(String address) {
        return null;
    }
}
