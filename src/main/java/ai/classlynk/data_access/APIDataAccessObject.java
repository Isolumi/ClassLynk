package ai.classlynk.data_access;

import ai.classlynk.entity.SClass;
import ai.classlynk.entity.Timetable;
import ai.classlynk.use_case.generate_timetable.TimetableGeneratorDataAccessInterface;
import com.google.maps.*;
import com.google.maps.model.*;
import com.google.maps.errors.ApiException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;

public class APIDataAccessObject implements TimetableGeneratorDataAccessInterface {

    GeoApiContext context;

    //ONLY use for this is to shut down the context on close
    public GeoApiContext getContext() {
        return context;
    }

    public APIDataAccessObject()
    {
        //TODO: set api key with variable name "MAPS_API_KEY" in intellij environment variables
        context = new GeoApiContext.Builder()
                .apiKey(System.getenv("MAPS_API_KEY"))
                .build();
    }
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

            for(String location : classAddresses.subList(1, classAddresses.size() - 1))
            {
                request.waypoints(location);
            }
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
                ImageIO.write(output, "jpg", new File(day + "Route" + ".jpg"));
                mapLinks.put(day, day + "Route" + ".jpg");
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