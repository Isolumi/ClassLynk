package ai.classlynk.data_access;

import ai.classlynk.entity.SClass;
import ai.classlynk.entity.Timetable;
import ai.classlynk.use_case.GenerateStaticImage.GenerateStaticImageDataAccessInterface;
import ai.classlynk.use_case.generate_timetable.TimetableGeneratorDataAccessInterface;

import com.google.maps.*;
import com.google.maps.model.*;
import com.google.maps.errors.ApiException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Component
public class APIDataAccessObject implements TimetableGeneratorDataAccessInterface, GenerateStaticImageDataAccessInterface {

    GeoApiContext context;


    //ONLY use for this is to shut down the context on close
    public GeoApiContext getContext() {
        return context;
    }

    public APIDataAccessObject(@Value("${spring.cloud.gcp.maps-api-key}") String key)
    {
        context = new GeoApiContext.Builder()
                .apiKey(key)
                .build();
    }
    @Override
    public Map<String, String> getStaticMaps(Timetable timetable) {
        /**
         * Gets daily routes for every day in the timetable
         * @return a map with keys of the days of the week and values of filepaths to the images
         */
        Map<String, String> mapLinks = new HashMap<>();
        DirectionsResult res;
        EncodedPolyline polyline;
        ArrayList<StaticMapsRequest.Markers> mapMarkers = new ArrayList<>();
        for(String day : timetable.getClasses().keySet())
        {
            ArrayList<String> classAddresses = new ArrayList<>();
            for(SClass oneClass : timetable.getClasses().get(day))
            {
                classAddresses.add(oneClass.getLocation());
            }

            DirectionsApiRequest request =  DirectionsApi.newRequest(context)
                    .origin(classAddresses.get(0))
                    .destination(classAddresses.get(classAddresses.size() - 1))
                    .mode(TravelMode.WALKING);
            String waypoints = "";
            for(String location : classAddresses.subList(1, classAddresses.size() - 1))
            {
                waypoints += location + "|";
            }

            request.waypoints(waypoints);
            try {
                res = request.await();
                polyline = res.routes[0].overviewPolyline;
            } catch (ApiException | InterruptedException | IOException e) {
                throw new RuntimeException(e);
            }

            for(int i = 0; i < classAddresses.size(); i++)
            {
                StaticMapsRequest.Markers marker = new StaticMapsRequest.Markers();
                marker.addLocation(classAddresses.get(i));
                marker.label(Integer.toString(i + 1));
                mapMarkers.add(marker);
            }

            StaticMapsRequest imgReq = StaticMapsApi.newRequest(context, new Size(1000, 1000))
                    .path(polyline);

            for(StaticMapsRequest.Markers marker : mapMarkers)
            {
                imgReq.markers(marker);
            }

            try {
                byte[] data = imgReq.await().imageData;
                ByteArrayInputStream dataStream = new ByteArrayInputStream(data);
                BufferedImage output = ImageIO.read(dataStream);
                ImageIO.write(output, "jpg", new File("src/main/resources/images/" + day + "Route" + ".jpg"));
                mapLinks.put(day, "src/main/resources/images/" + day + "Route" + ".jpg");
            } catch (ApiException | InterruptedException | IOException e) {
                throw new RuntimeException(e);
            }
        }
        return mapLinks;
    }

    @Override
    public float getRouteLength(String origin, String destination) {
        DirectionsResult res;
        try {
            DirectionsApiRequest req = DirectionsApi.newRequest(context)
                    .origin(origin)
                    .destination(destination)
                    .mode(TravelMode.WALKING);
            res = req.await();
        } catch (ApiException | InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
        return res.routes[0].legs[0].distance.inMeters;
    }
}