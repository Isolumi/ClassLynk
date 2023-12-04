package ai.classlynk.app;

import ai.classlynk.data_access.APIDataAccessObject;
import ai.classlynk.data_access.FirebaseDataAccessObject;
import ai.classlynk.entity.SClass;
import ai.classlynk.entity.Timetable;
import ai.classlynk.interface_adapter.Login.LoginController;
import ai.classlynk.interface_adapter.Login.LoginPresenter;
import ai.classlynk.interface_adapter.Login.LoginViewModel;
import ai.classlynk.interface_adapter.MenuSwitchingController;
import ai.classlynk.interface_adapter.Register.RegisterController;
import ai.classlynk.interface_adapter.Register.RegisterPresenter;
import ai.classlynk.interface_adapter.Register.RegisterViewModel;
import ai.classlynk.interface_adapter.ViewCourse.ViewCourseController;
import ai.classlynk.interface_adapter.ViewCourse.ViewCoursePresenter;
import ai.classlynk.interface_adapter.ViewCourse.ViewCourseViewModel;
import ai.classlynk.interface_adapter.ViewManagerModel;
import ai.classlynk.interface_adapter.addToCart.AddToCartController;
import ai.classlynk.interface_adapter.addToCart.AddToCartPresenter;
import ai.classlynk.interface_adapter.addToCart.AddToCartViewModel;
import ai.classlynk.interface_adapter.generate_timetable.GenerateTimetableController;
import ai.classlynk.interface_adapter.save_view_timetables.SaveViewTimetableController;
import ai.classlynk.interface_adapter.save_view_timetables.SaveViewTimetablePresenter;
import ai.classlynk.interface_adapter.save_view_timetables.SaveViewTimetableViewModel;
import ai.classlynk.interface_adapter.static_maps.*;
import ai.classlynk.use_case.AddToCart.AddToCartInteractor;
import ai.classlynk.use_case.ViewCourse.ViewCourseInteractor;
import ai.classlynk.use_case.generate_timetable.GenerateTimetableInteractor;
import ai.classlynk.use_case.save_view_timetables.SaveViewTimetableInteractor;
import ai.classlynk.use_case.static_maps.MapsInputBoundary;
import ai.classlynk.use_case.static_maps.MapsInteractor;
import ai.classlynk.use_case.static_maps.MapsOutputBoundary;
import ai.classlynk.use_case.user_auth.login.LoginInteractor;
import ai.classlynk.use_case.user_auth.register.RegisterInteractor;
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

        Timetable ttt = createPlaceHolderData();
        MapsState testState = new MapsState();
        testState.setTimetable(ttt);
        try {
            testState.setImageLocations(apiDataAccessObject.getStaticMaps(ttt));
        } catch (ApiException | InterruptedException | IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }

        MapsViewModel mapsViewModel = new MapsViewModel();
        mapsViewModel.setState(testState);
        SaveViewTimetableViewModel saveViewTimetableViewModel = new SaveViewTimetableViewModel();
        RegisterViewModel registerViewModel = new RegisterViewModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        ViewCourseViewModel viewCourseViewModel = new ViewCourseViewModel();
        AddToCartViewModel addToCartViewModel = new AddToCartViewModel();

        MapsPresenter mapsPresenter = new MapsPresenter(mapsViewModel, viewManagerModel);
        SaveViewTimetablePresenter saveViewTimetablePresenter = new SaveViewTimetablePresenter(
                saveViewTimetableViewModel,
                viewManagerModel);
        RegisterPresenter registerPresenter = new RegisterPresenter(viewManagerModel, registerViewModel, loginViewModel);
        LoginPresenter loginPresenter = new LoginPresenter(viewManagerModel, saveViewTimetableViewModel, loginViewModel);
        ViewCoursePresenter viewCoursePresenter = new ViewCoursePresenter(viewManagerModel, viewCourseViewModel);
        AddToCartPresenter addToCartPresenter = new AddToCartPresenter(addToCartViewModel, viewManagerModel);


        SaveViewTimetableInteractor saveViewTimetableInteractor = new SaveViewTimetableInteractor(
                saveViewTimetablePresenter, firebaseDataAccessObject
        );
        LoginInteractor loginInteractor = new LoginInteractor(firebaseDataAccessObject, firebaseDataAccessObject, loginPresenter);
        RegisterInteractor registerInteractor = new RegisterInteractor(firebaseDataAccessObject, registerPresenter);
        AddToCartInteractor addToCartInteractor = new AddToCartInteractor(addToCartPresenter, firebaseDataAccessObject);
        ViewCourseInteractor viewCourseInteractor = new ViewCourseInteractor(firebaseDataAccessObject, viewCoursePresenter);
        GenerateTimetableInteractor generateTimetableInteractor = new GenerateTimetableInteractor(saveViewTimetablePresenter, apiDataAccessObject);
        MapsInteractor mapsInteractor = new MapsInteractor(apiDataAccessObject, mapsPresenter);

        MapsController mapsController = new MapsController(mapsInteractor);
        SaveViewTimetableController saveViewTimetableController = new SaveViewTimetableController(
                saveViewTimetableInteractor);
        RegisterController registerController = new RegisterController(registerInteractor);
        LoginController loginController = new LoginController(loginInteractor);
        ViewCourseController viewCourseController = new ViewCourseController(viewCourseInteractor);
        AddToCartController addToCartController = new AddToCartController(addToCartInteractor);
        GenerateTimetableController generateTimetableController = new GenerateTimetableController(generateTimetableInteractor);



        SaveViewTimetableView saveViewTimetableView = new SaveViewTimetableView(
                saveViewTimetableViewModel,
                saveViewTimetableController, mapsController
        );
        saveViewTimetableView.setMenuSwitchingController(new MenuSwitchingController(saveViewTimetablePresenter, viewCourseViewModel));
        LoginView loginView = new LoginView(loginController, loginViewModel, registerViewModel, viewManagerModel);
        RegisterView registerView = new RegisterView(registerController, registerViewModel, loginViewModel, viewManagerModel);
        ViewCourseView viewCourseView = new ViewCourseView(viewCourseController, viewCourseViewModel, addToCartController, generateTimetableController);
        viewCourseView.setBackButtonController(new MenuSwitchingController(viewCoursePresenter, saveViewTimetableViewModel));
        MapsView mapsView = new MapsView(mapsViewModel);
        mapsView.setBackButtonController(new MenuSwitchingController(mapsPresenter, saveViewTimetableViewModel));

        views.add(mapsView, mapsView.viewName);
        views.add(saveViewTimetableView, saveViewTimetableView.viewName);
        views.add(loginView, loginView.viewName);
        views.add(registerView, registerView.viewName);
        views.add(viewCourseView, viewCourseView.viewName);


        viewManagerModel.setActiveView(loginView.viewName);
        viewManagerModel.firePropertyChanged();

        application.setPreferredSize(new Dimension(1500, 1000));
        application.pack();
        application.setLocationRelativeTo(null);
        application.setVisible(true);
    }

    @NotNull
    private static Timetable createPlaceHolderData() {
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
        return ttt;
    }
}