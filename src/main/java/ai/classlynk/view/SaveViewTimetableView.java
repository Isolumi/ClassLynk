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

    BackButtonController backButtonController;

    JButton backButton;

    JButton generateMapsButton;

    public void setBackButtonController(BackButtonController backButtonController) {
        this.backButtonController = backButtonController;
    }
    public SaveViewTimetableView(SaveViewTimetableViewModel saveViewTimetableViewModel, SaveViewTimetableController saveViewTimetableController) {
        saveViewTimetableViewModel.addPropertyChangeListener(this);
        String[] daysOfWeek = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
        backButton = new JButton("Go Back");

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel title = new JPanel();
        title.setLayout(new BoxLayout(title, BoxLayout.X_AXIS));

        title.add(backButton);
        title.add(Box.createHorizontalGlue());

        JLabel titleLabel = new JLabel(SaveViewTimetableViewModel.TITLE_LABEL);
        title.add(titleLabel);

        title.add(Box.createHorizontalGlue());

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

        Timetable timetable = saveViewTimetableViewModel.getState().getTimetables();  // TODO: this only returns first timetable right now
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
        generateMapsButton = new JButton("View Maps");
        timetablePanel.add(generateMapsButton);

        backButton.addActionListener(
                e -> {
                    if(e.getSource().equals(backButton))
                    {
                        backButtonController.execute();
                    }
                }
        );

//        TODO: this and the creation of the button will need to be in a for loop if multiple timetables are displayed at once
//        generateMapsButton.addActionListener(
//                e -> {
//                        if(e.getSource().equals(generateMapsButton))
//                        {
//                            mapsController.execute(timetable);
//                        }
//                }
//        );

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
