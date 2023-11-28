package ai.classlynk.view;

import ai.classlynk.entity.SClass;
import ai.classlynk.entity.Timetable;
import ai.classlynk.interface_adapter.BackButtonController;
import ai.classlynk.interface_adapter.save_view_timetables.SaveViewTimetableController;
import ai.classlynk.interface_adapter.save_view_timetables.SaveViewTimetableViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class SaveViewTimetableView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "Your Timetables";
    private final SaveViewTimetableViewModel saveViewTimetableViewModel;
    private final SaveViewTimetableController saveViewTimetableController;

    BackButtonController backButtonController;

    JButton backButton;

    public void setBackButtonController(BackButtonController backButtonController) {
        this.backButtonController = backButtonController;
    }
    public SaveViewTimetableView(SaveViewTimetableViewModel saveViewTimetableViewModel, SaveViewTimetableController saveViewTimetableController) {
        this.saveViewTimetableViewModel = saveViewTimetableViewModel;
        this.saveViewTimetableController = saveViewTimetableController;
        saveViewTimetableViewModel.addPropertyChangeListener(this);
        String[] daysOfWeek = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
        backButton = new JButton("Go Back");

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel title = new JPanel(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        JButton backButton = new JButton("Back");
        buttonPanel.add(backButton);

        title.add(buttonPanel, BorderLayout.WEST);

        JLabel titleLabel = new JLabel(SaveViewTimetableViewModel.TITLE_LABEL);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        title.add(titleLabel, BorderLayout.CENTER);


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

        Timetable timetable = saveViewTimetableViewModel.getState().getTimetables().get(0);  // TODO: this only returns first timetable right now
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

        backButton.addActionListener(
                e -> {
                    if(e.getSource().equals(backButton))
                    {
                        backButtonController.execute();
                    }
                }
        );

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
