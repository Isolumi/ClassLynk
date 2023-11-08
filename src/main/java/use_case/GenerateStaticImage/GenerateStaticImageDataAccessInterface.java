package use_case.GenerateStaticImage;

import entity.Timetable;

import java.util.Map;

public interface GenerateStaticImageDataAccessInterface {

    //read documentation for static maps image api and put method in
    Map<String, String> getStaticMaps(Timetable Timetable);
}
