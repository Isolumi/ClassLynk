package ai.classlynk.view;

import ai.classlynk.entity.SClass;
import ai.classlynk.entity.Timetable;
import ai.classlynk.interface_adapter.save_view_timetables.SaveViewTimetableController;
import ai.classlynk.interface_adapter.save_view_timetables.SaveViewTimetableViewModel;
import ai.classlynk.use_case.save_view_timetables.SaveViewTimetableInteractor;

import java.util.List;
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
        String[] daysOfWeek = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel title = new JLabel(SaveViewTimetableViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel timetablePanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        // printing time labels
        for (int i = 8; i < 23; i++) {
            JLabel label = new JLabel(Integer.toString(i) + ":00");
            gbc.gridx = 0;
            gbc.gridy = i + 1;
            timetablePanel.add(label, gbc);
        }
        // printing days of the week labels
        for (int i = 0; i < 5; i++) {
            JLabel weekday = new JLabel(daysOfWeek[i]);
            gbc.gridx = i + 1;
            gbc.gridy = 0;
            timetablePanel.add(weekday, gbc);
        }

        Timetable timetable = saveViewTimetableViewModel.getState().getTimetables()[0];  // TODO: this only returns first timetable right now
        for (int i = 0; i < 5; i ++) {
            List<SClass> classes = timetable.getClasses().get(daysOfWeek[i].toLowerCase());
            for (SClass aClass : classes) {
                JLabel clas = new JLabel(aClass.getCourseId());
                clas.setBackground(Color.red);
                clas.setOpaque(true);
                gbc.fill = GridBagConstraints.BOTH;
                gbc.gridx = i + 1;
                gbc.gridy = Integer.parseInt(aClass.getStartTime().substring(0, 2));
                gbc.gridheight = Integer.parseInt(aClass.getEndTime().substring(0, 2))
                        - Integer.parseInt(aClass.getStartTime().substring(0, 2));
                timetablePanel.add(clas, gbc);
            }
        }

        this.add(title);
        this.add(timetablePanel);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
