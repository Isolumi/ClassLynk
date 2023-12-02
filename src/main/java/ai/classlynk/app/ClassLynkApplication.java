package ai.classlynk.app;

import ai.classlynk.data_access.APIDataAccessObject;
import ai.classlynk.data_access.FirebaseDataAccessObject;
import ai.classlynk.entity.SClass;
import ai.classlynk.entity.Timetable;
import ai.classlynk.interface_adapter.MenuSwitchingController;
import ai.classlynk.interface_adapter.Register.RegisterPresenter;
import ai.classlynk.interface_adapter.Register.RegisterViewModel;
import ai.classlynk.interface_adapter.ViewManagerModel;
import ai.classlynk.interface_adapter.save_view_timetables.SaveViewTimetableController;
import ai.classlynk.interface_adapter.save_view_timetables.SaveViewTimetablePresenter;
import ai.classlynk.interface_adapter.save_view_timetables.SaveViewTimetableViewModel;
import ai.classlynk.interface_adapter.static_maps.*;
import ai.classlynk.use_case.save_view_timetables.SaveViewTimetableInteractor;
import ai.classlynk.view.*;
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
import java.util.Map;

@SpringBootApplication(scanBasePackages = "ai.classlynk")
@EnableReactiveFirestoreRepositories(basePackages = "ai.classlynk")
public class ClassLynkApplication {
    @Resource
    FirebaseDataAccessObject firebaseDataAccessObject;

    APIDataAccessObject apiDataAccessObject;
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
        apiDataAccessObject = new APIDataAccessObject();

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        JFrame application = new JFrame("ClassLynk");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        JPanel views = new JPanel(cardLayout);
        application.add(views);
        new ViewManager(views, cardLayout, viewManagerModel);

        MapsViewModel mapsViewModel = new MapsViewModel();

        MapsController mapsController = MapsUseCaseFactory.createMapsUseCase(viewManagerModel, apiDataAccessObject, mapsViewModel);

        SaveViewTimetableView saveViewTimetableView = getSaveViewTimetableView(viewManagerModel, mapsController);

        views.add(saveViewTimetableView, saveViewTimetableView.viewName);

        MapsView mapsView = getMapsView(viewManagerModel, mapsViewModel);

        views.add(mapsView, mapsView.viewName);


        RegisterView registerView;





        viewManagerModel.setActiveView(saveViewTimetableView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setLocationRelativeTo(null);
        application.setVisible(true);
    }

    @NotNull
    private SaveViewTimetableView getSaveViewTimetableView(ViewManagerModel viewManagerModel, MapsController mapsController) {
        // define view models
        SaveViewTimetableViewModel saveViewTimetableViewModel = new SaveViewTimetableViewModel();

        // define presenters
        SaveViewTimetablePresenter saveViewTimetablePresenter = new SaveViewTimetablePresenter(
                saveViewTimetableViewModel,
                viewManagerModel);

        // define interactors
        SaveViewTimetableInteractor saveViewTimetableInteractor = new SaveViewTimetableInteractor(
                saveViewTimetablePresenter, firebaseDataAccessObject
        );

        // define controllers
        SaveViewTimetableController saveViewTimetableController = new SaveViewTimetableController(
                saveViewTimetableInteractor);
        saveViewTimetableController.execute(true, "user1");
        SaveViewTimetableView saveViewTimetableView = new SaveViewTimetableView(
                saveViewTimetableViewModel,
                saveViewTimetableController, mapsController
        );
        saveViewTimetableView.setBackButtonController(new MenuSwitchingController(saveViewTimetablePresenter, new MapsViewModel()));
        // define views
        return saveViewTimetableView;
    }

    private MapsView getMapsView(ViewManagerModel viewManagerModel, MapsViewModel mapsViewModel)
    {
        //initialing empty data to intiialize swing components
        Map<String, java.util.List<SClass>> tt = new HashMap<>();

        java.util.List<SClass> mon = new ArrayList<>();
        java.util.List<SClass> tue = new ArrayList<>();
        java.util.List<SClass> wed = new ArrayList<>();
        java.util.List<SClass> thur = new ArrayList<>();
        java.util.List<SClass> fri = new ArrayList<>();

        tt.put("monday", mon);
        tt.put("tuesday", tue);
        tt.put("wednesday", wed);
        tt.put("thursday", thur);
        tt.put("friday", fri);
        Timetable ttt = new Timetable("fakeuuid", tt);

        MapsState testState = new MapsState();
        testState.setTimetable(ttt);
        try {
            testState.setImageLocations(apiDataAccessObject.getStaticMaps(ttt));
        } catch (ApiException | InterruptedException | IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }

        mapsViewModel.setState(testState);

        MapsPresenter mapsPresenter = new MapsPresenter(mapsViewModel, viewManagerModel);

        MapsView mapsView = new MapsView(mapsViewModel);
        mapsView.setBackButtonController(new MenuSwitchingController(mapsPresenter, new SaveViewTimetableViewModel()));

        return mapsView;
    }

    private RegisterView getRegisterView()
    {
        RegisterViewModel registerViewModel = new RegisterViewModel();

    }

    private LoginView getLoginView()
    {

    }

    private ViewCourseView getViewCourseView()
    {

    }

}