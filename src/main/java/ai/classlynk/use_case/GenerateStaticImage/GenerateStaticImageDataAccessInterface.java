package ai.classlynk.use_case.GenerateStaticImage;

import java.util.ArrayList;
import ai.classlynk.entity.Timetable;

public interface GenerateStaticImageDataAccessInterface {

    //read documentation for static maps image api and put method in
    ArrayList<String> getStaticMaps(Timetable Timetable);
}
