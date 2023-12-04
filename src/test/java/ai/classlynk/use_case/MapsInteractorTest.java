package ai.classlynk.use_case;

import ai.classlynk.data_access.APIDataAccessObject;
import ai.classlynk.entity.SClass;
import ai.classlynk.entity.Timetable;
import ai.classlynk.use_case.static_maps.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class MapsInteractorTest {

    @Test
    void execute() {
        Map<String, List<SClass>> testClasses = new HashMap<>();
        SClass a = new SClass(
                "c1", "lec0102", "10:00:00", "11:00:00",
                "Monday", "bahen", "252 Bloor St W, Toronto, ON M5S 1V6", false);
        SClass b = new SClass(
                "c2", "lec0103", "12:00:00", "14:00:00",
                "Monday", "bahen", "81A St. Mary Street, Toronto, ON M5S1J4", false);
        SClass c = new SClass(
                "c3", "lec0104", "10:00:00", "11:00:00",
                "Tuesday", "bahen", "31 King's College Cir, Toronto, ON M5S 1A1", false);
        SClass d = new SClass(
                "c4", "lec0101", "10:00:00", "11:00:00",
                "Wednesday", "bahen", "93 Charles St W, Toronto, ON M5S 2C7", false);
        SClass e = new SClass(
                "c5", "lec0105", "10:00:00", "11:00:00",
                "Thursday", "bahen", "100 St George St, Toronto, ON M5S 3G3", false);
        SClass f = new SClass(
                "c6", "lec0106", "10:00:00", "11:00:00",
                "Friday", "bahen", "10 King's College Rd, Toronto, ON M5S 3G4", false);
        SClass g = new SClass(
                "c7", "lec0109", "21:00:00", "22:00:00",
                "Friday", "bahen", "25 Harbord street, Toronto, ON M5S 3G5", false);
        java.util.List<SClass> mon = new ArrayList<>();
        mon.add(a);
        mon.add(b);
        java.util.List<SClass> tue = new ArrayList<>();
        tue.add(c);
        java.util.List<SClass> wed = new ArrayList<>();
        wed.add(d);
        java.util.List<SClass> thur = new ArrayList<>();
        thur.add(e);
        thur.add(a);
        List<SClass> fri = new ArrayList<>();
        fri.add(f);
        fri.add(g);
        testClasses.put("Monday", mon);
        testClasses.put("Tuesday", tue);
        testClasses.put("Wednesday", wed);
        testClasses.put("Thursday", thur);
        testClasses.put("Friday", fri);
        Timetable testTimetable = new Timetable("user1", testClasses);

        APIDataAccessObject apa = new APIDataAccessObject();



        MapsOutputBoundary successPresenter = new MapsOutputBoundary() {
            @Override
            public void prepareSuccessView(MapsOutputData imageLocations) {
                assert(imageLocations.getImageLocations().get("Monday").matches("^.*\\\\src\\\\main\\\\resources\\\\images\\\\MondayRoute\\.jpg$") || imageLocations.getImageLocations().get("Monday").matches("^.*/src/main/resources/images/MondayRoute.jpg$"));
                assert(imageLocations.getImageLocations().get("Tuesday").equals("no image"));
                assert(imageLocations.getImageLocations().get("Wednesday").equals("no image"));
                assert(imageLocations.getImageLocations().get("Thursday").matches("^.*\\\\src\\\\main\\\\resources\\\\images\\\\ThursdayRoute\\.jpg$") || imageLocations.getImageLocations().get("Thursday").matches("^.*/src/main/resources/images/ThursdayRoute.jpg$"));
                assert(imageLocations.getImageLocations().get("Friday").matches("^.*\\\\src\\\\main\\\\resources\\\\images\\\\FridayRoute\\.jpg$") || imageLocations.getImageLocations().get("Friday").matches("^.*/src/main/resources/images/FridayRoute.jpg$"));
            }

            @Override
            public void prepareFailView(String error) {
                assert(false);
            }
        };

        MapsInputBoundary interactor = new MapsInteractor(apa,successPresenter);
        interactor.execute(new MapsInputData(testTimetable));
    }
}