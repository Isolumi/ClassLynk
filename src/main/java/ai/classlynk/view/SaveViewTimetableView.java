package ai.classlynk.view;

import ai.classlynk.interface_adapter.save_view_timetables.SaveViewTimetableController;
import ai.classlynk.interface_adapter.save_view_timetables.SaveViewTimetableViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SaveViewTimetableView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "save-view-timetable";
    private final SaveViewTimetableViewModel saveViewTimetableViewModel;
    private final SaveViewTimetableController saveViewTimetableController;

    public SaveViewTimetableView(SaveViewTimetableViewModel saveViewTimetableViewModel, SaveViewTimetableController saveViewTimetableController) {
        this.saveViewTimetableViewModel = saveViewTimetableViewModel;
        this.saveViewTimetableController = saveViewTimetableController;
        saveViewTimetableViewModel.addPropertyChangeListener(this);

        JLabel title=  new JLabel(SaveViewTimetableViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
