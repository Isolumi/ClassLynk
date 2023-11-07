package data_access;

import entity.Timetable;
import use_case.GenerateStaticImage.GenerateStaticImageDataAccessInterface;
import use_case.generate_timetable.TimetableGeneratorDataAccessInterface;

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
