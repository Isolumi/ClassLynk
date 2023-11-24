package ai.classlynk.app;

import ai.classlynk.data_access.FirebaseRepository;
import ai.classlynk.interface_adapter.ViewManagerModel;
import ai.classlynk.interface_adapter.save_view_timetables.SaveViewTimetableController;
import ai.classlynk.interface_adapter.save_view_timetables.SaveViewTimetablePresenter;
import ai.classlynk.interface_adapter.save_view_timetables.SaveViewTimetableViewModel;
import ai.classlynk.use_case.save_view_timetables.SaveViewTimetableInteractor;
import ai.classlynk.view.SaveViewTimetableView;
import ai.classlynk.view.ViewManager;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

public class ClassLynkApplication {

    public static void main(String[] args) {
        JFrame application = new JFrame("ClassLynk");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        JPanel views = new JPanel(cardLayout);
        application.add(views);

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        SaveViewTimetableView saveViewTimetableView = getSaveViewTimetableView(viewManagerModel);

        views.add(saveViewTimetableView, saveViewTimetableView.viewName);

        viewManagerModel.setActiveView(saveViewTimetableView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }

    @NotNull
    private static SaveViewTimetableView getSaveViewTimetableView(ViewManagerModel viewManagerModel) {
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

        // define views
        return new SaveViewTimetableView(
                saveViewTimetableViewModel,
                saveViewTimetableController
        );
    }
}
