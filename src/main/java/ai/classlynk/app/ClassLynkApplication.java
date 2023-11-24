package ai.classlynk.app;

import ai.classlynk.data_access.FirebaseRepository;
import ai.classlynk.interface_adapter.ViewManagerModel;
import ai.classlynk.interface_adapter.save_view_timetables.SaveViewTimetableController;
import ai.classlynk.interface_adapter.save_view_timetables.SaveViewTimetableViewModel;
import ai.classlynk.view.SaveViewTimetableView;
import ai.classlynk.view.ViewManager;

import javax.annotation.Resource;
import javax.swing.*;
import java.awt.*;

public class ClassLynkApplication {

    @Resource
    private FirebaseRepository firebaseRepository;

    public static void main(String[] args) {
        JFrame application = new JFrame("ClassLynk");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        JPanel views = new JPanel(cardLayout);
        application.add(views);

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        // define view models
        SaveViewTimetableViewModel saveViewTimetableViewModel = new SaveViewTimetableViewModel();

        // define controllers
        SaveViewTimetableController saveViewTimetableController = new SaveViewTimetableController();

        // define views
        SaveViewTimetableView saveViewTimetableView = new SaveViewTimetableView(
                saveViewTimetableViewModel,
                saveViewTimetableController
        );
        views.add(saveViewTimetableView, saveViewTimetableView.viewName);

        viewManagerModel.setActiveView(saveViewTimetableView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}
