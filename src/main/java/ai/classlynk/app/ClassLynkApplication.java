package ai.classlynk.app;

import ai.classlynk.data_access.FirebaseDataAccessObject;
import ai.classlynk.entity.SClass;
import ai.classlynk.entity.Timetable;
import ai.classlynk.entity.User;
import ai.classlynk.interface_adapter.BackButtonController;
import ai.classlynk.interface_adapter.ViewManagerModel;
import ai.classlynk.interface_adapter.save_view_timetables.SaveViewTimetableController;
import ai.classlynk.interface_adapter.save_view_timetables.SaveViewTimetablePresenter;
import ai.classlynk.interface_adapter.save_view_timetables.SaveViewTimetableViewModel;
import ai.classlynk.interface_adapter.static_maps.*;
import ai.classlynk.use_case.save_view_timetables.SaveViewTimetableInteractor;
import ai.classlynk.view.SaveViewTimetableView;
import ai.classlynk.view.ViewManager;
import com.google.cloud.spring.data.firestore.repository.config.EnableReactiveFirestoreRepositories;
import com.google.firebase.auth.FirebaseAuthException;
import jakarta.annotation.Resource;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;

import javax.swing.*;
import java.awt.*;
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
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        JFrame application = new JFrame("ClassLynk");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        JPanel views = new JPanel(cardLayout);
        application.add(views);

        new ViewManager(views, cardLayout, viewManagerModel);

        SaveViewTimetableView saveViewTimetableView = getSaveViewTimetableView(viewManagerModel);

        views.add(saveViewTimetableView, saveViewTimetableView.viewName);

        viewManagerModel.setActiveView(saveViewTimetableView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setLocationRelativeTo(null);
        application.setVisible(true);
    }

    @NotNull
    private SaveViewTimetableView getSaveViewTimetableView(ViewManagerModel viewManagerModel) {
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
                saveViewTimetableController
        );
        saveViewTimetableView.setBackButtonController(new BackButtonController(saveViewTimetablePresenter, new MapsViewModel()));
        // define views
        return saveViewTimetableView;
    }

}