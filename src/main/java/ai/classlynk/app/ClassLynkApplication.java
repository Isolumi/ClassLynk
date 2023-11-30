package ai.classlynk.app;

import ai.classlynk.data_access.APIDataAccessObject;
import ai.classlynk.data_access.FirebaseDataAccessObject;
import ai.classlynk.entity.SClass;
import ai.classlynk.entity.Timetable;
import ai.classlynk.interface_adapter.BackButtonController;
import ai.classlynk.interface_adapter.ViewManagerModel;
import ai.classlynk.interface_adapter.save_view_timetables.SaveViewTimetableController;
import ai.classlynk.interface_adapter.save_view_timetables.SaveViewTimetablePresenter;
import ai.classlynk.interface_adapter.save_view_timetables.SaveViewTimetableViewModel;
import ai.classlynk.interface_adapter.static_maps.*;
import ai.classlynk.use_case.save_view_timetables.SaveViewTimetableInteractor;
import ai.classlynk.use_case.static_maps.MapsInteractor;
import ai.classlynk.view.MapsView;
import ai.classlynk.view.SaveViewTimetableView;
import ai.classlynk.view.ViewManager;
import com.google.cloud.spring.data.firestore.repository.config.EnableReactiveFirestoreRepositories;
import com.google.maps.errors.ApiException;
import jakarta.annotation.Resource;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication(scanBasePackages = "ai.classlynk")
@EnableReactiveFirestoreRepositories(basePackages = "ai.classlynk")
public class ClassLynkApplication {
    @Resource
    FirebaseDataAccessObject firebaseDataAccessObject;

    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(ClassLynkApplication.class);
        builder.headless(false);
        builder.run(args);
    }
    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            start();
        };
    }
    public void start() {
        JFrame application = new JFrame("ClassLynk");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


    }

    @NotNull
    private static SaveViewTimetableView getSaveViewTimetableView(ViewManagerModel viewManagerModel, MapsController mapsController) {
        // define view models
        SaveViewTimetableViewModel saveViewTimetableViewModel = new SaveViewTimetableViewModel();

        // define presenters
        SaveViewTimetablePresenter saveViewTimetablePresenter = new SaveViewTimetablePresenter(
                saveViewTimetableViewModel,
                viewManagerModel);

        // define interactors
        SaveViewTimetableInteractor saveViewTimetableInteractor = new SaveViewTimetableInteractor(
                saveViewTimetablePresenter
        );

        // define controllers
        SaveViewTimetableController saveViewTimetableController = new SaveViewTimetableController(
                saveViewTimetableInteractor);
        saveViewTimetableController.execute(true);
        SaveViewTimetableView saveViewTimetableView = new SaveViewTimetableView(
                saveViewTimetableViewModel,
                saveViewTimetableController, mapsController
        );
        saveViewTimetableView.setBackButtonController(new BackButtonController(saveViewTimetablePresenter, new MapsViewModel()));
        // define views
        return saveViewTimetableView;
    }

}
//        CardLayout cardLayout = new CardLayout();
//
//        JPanel views = new JPanel(cardLayout);
//        application.add(views);
//
//        ViewManagerModel viewManagerModel = new ViewManagerModel();
//        new ViewManager(views, cardLayout, viewManagerModel);
//
//
//        APIDataAccessObject apa = new APIDataAccessObject();
//        MapsViewModel mapsViewModel = new MapsViewModel();
//        MapsPresenter mapsPresenter = new MapsPresenter(mapsViewModel, viewManagerModel);
//        MapsInteractor mapsInteractor = new MapsInteractor(apa, mapsPresenter);
//        MapsController mapsController = MapsUseCaseFactory.createMapsUseCase(viewManagerModel, apa, mapsViewModel);
//
//        SaveViewTimetableView saveViewTimetableView = getSaveViewTimetableView(viewManagerModel, mapsController);
//
//        views.add(saveViewTimetableView, saveViewTimetableView.viewName);
//
//        BackButtonController backButtonController = new BackButtonController(mapsPresenter, new SaveViewTimetableViewModel());
//
//
//
//        Map<String, java.util.List<SClass>> tt = new HashMap<>();
//        SClass a = new SClass(
//                "c1", "lec0102", "10:00:00", "11:00:00",
//                "monday", "bahen", "252 Bloor St W, Toronto, ON M5S 1V6", false);
//        SClass b = new SClass(
//                "c2", "lec0103", "12:00:00", "14:00:00",
//                "monday", "bahen", "81A St. Mary Street, Toronto, ON M5S1J4", false);
//        SClass c = new SClass(
//                "c3", "lec0104", "10:00:00", "11:00:00",
//                "tuesday", "bahen", "31 King's College Cir, Toronto, ON M5S 1A1", false);
//        SClass d = new SClass(
//                "c4", "lec0101", "10:00:00", "11:00:00",
//                "wednesday", "bahen", "93 Charles St W, Toronto, ON M5S 2C7", false);
//        SClass e = new SClass(
//                "c5", "lec0105", "10:00:00", "11:00:00",
//                "thursday", "bahen", "100 St George St, Toronto, ON M5S 3G3", false);
//        SClass f = new SClass(
//                "c6", "lec0106", "10:00:00", "11:00:00",
//                "friday", "bahen", "10 King's College Rd, Toronto, ON M5S 3G4", false);
//        SClass g = new SClass(
//                "c7", "lec0109", "21:00:00", "22:00:00",
//                "friday", "bahen", "25 Harbord street, Toronto, ON M5S 3G5", false);
//        java.util.List<SClass> mon = new ArrayList<>();
//        mon.add(a);
//        mon.add(b);
//        java.util.List<SClass> tue = new ArrayList<>();
//        tue.add(c);
//        java.util.List<SClass> wed = new ArrayList<>();
//        wed.add(d);
//        java.util.List<SClass> thur = new ArrayList<>();
//        thur.add(e);
//        thur.add(a);
//        List<SClass> fri = new ArrayList<>();
//        fri.add(f);
//        fri.add(g);
//        tt.put("monday", mon);
//        tt.put("tuesday", tue);
//        tt.put("wednesday", wed);
//        tt.put("thursday", thur);
//        tt.put("friday", fri);
//        Timetable ttt = new Timetable(tt);
//
//        MapsState teststate = new MapsState();
//        teststate.setTimetable(ttt);
//        try {
//            teststate.setImageLocations(apa.getStaticMaps(ttt));
//        } catch (ApiException | InterruptedException | IOException ex) {
//            ex.printStackTrace();
//            throw new RuntimeException(ex);
//        }
//        mapsViewModel.setState(teststate);
//
//        MapsView mapsView = new MapsView(mapsViewModel);
//
//        mapsView.setBackButtonController(backButtonController);
//
//        views.add(mapsView, mapsView.viewName);
//
//
//        viewManagerModel.setActiveView(mapsView.viewName);
//        viewManagerModel.firePropertyChanged();
//
//        application.pack();
////        application.setLocationRelativeTo(null);
//        application.setVisible(true);