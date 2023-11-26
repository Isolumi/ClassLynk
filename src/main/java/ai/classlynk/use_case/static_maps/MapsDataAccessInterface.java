package ai.classlynk.use_case.static_maps;

import ai.classlynk.entity.Timetable;
import com.google.maps.errors.ApiException;

import java.io.IOException;
import java.util.Map;

public interface MapsDataAccessInterface {

    //read documentation for static maps image api and put method in
    Map<String, String> getStaticMaps(Timetable Timetable) throws ApiException, InterruptedException, IOException;
}
