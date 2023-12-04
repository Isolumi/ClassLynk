package ai.classlynk.view;

import ai.classlynk.entity.SClass;
import ai.classlynk.entity.Timetable;
import ai.classlynk.entity.User;
import ai.classlynk.interface_adapter.MenuSwitchingController;
import ai.classlynk.interface_adapter.save_view_timetables.SaveViewTimetableController;
import ai.classlynk.interface_adapter.save_view_timetables.SaveViewTimetableState;
import ai.classlynk.interface_adapter.save_view_timetables.SaveViewTimetableViewModel;
import ai.classlynk.interface_adapter.static_maps.MapsController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.List;

public class SaveViewTimetableView extends JPanel implements PropertyChangeListener {
    public final String viewName = "Your Timetables";

    private MenuSwitchingController menuSwitchingController;

    private final JPanel timetablePanel = new JPanel(new GridBagLayout());

    private final JButton viewCoursesButton = new JButton("View Courses");

    private GridBagConstraints gbc = new GridBagConstraints();

    private final JButton generateMapsButton;

    private final JButton saveTimetableButton;

    JLabel titleLabel = new JLabel(SaveViewTimetableViewModel.TITLE_LABEL);

    JPanel title = new JPanel();

    private Timetable timetable;

    String[] daysOfWeek = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};

    MapsController mapsController;

    SaveViewTimetableController saveViewTimetableController;

    SaveViewTimetableViewModel saveViewTimetableViewModel;

    public void setMenuSwitchingController(MenuSwitchingController menuSwitchingController) {
        this.menuSwitchingController = menuSwitchingController;
    }

    public SaveViewTimetableView(SaveViewTimetableViewModel saveViewTimetableViewModel, SaveViewTimetableController saveViewTimetableController, MapsController mapsController) {
        saveViewTimetableViewModel.addPropertyChangeListener(this);
        this.mapsController = mapsController;
        this.saveViewTimetableController = saveViewTimetableController;
        this.saveViewTimetableViewModel = saveViewTimetableViewModel;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));


        title.setLayout(new BoxLayout(title, BoxLayout.X_AXIS));

        title.add(Box.createHorizontalGlue());


        title.add(titleLabel);

        title.add(Box.createHorizontalGlue());

        generateMapsButton = new JButton("View Maps");
        saveTimetableButton = new JButton("save");




    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SaveViewTimetableState state = (SaveViewTimetableState) evt.getNewValue();
        timetable = state.getTimetables();
        timetablePanel.removeAll();

        timetablePanel.add(viewCoursesButton);

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


        timetablePanel.add(generateMapsButton);

        timetablePanel.add(saveTimetableButton);

        viewCoursesButton.addActionListener(
                e -> {
                    if (e.getSource().equals(viewCoursesButton)) {
                        menuSwitchingController.execute();
                    }
                }
        );

        generateMapsButton.addActionListener(
                e -> {
                    if (e.getSource().equals(generateMapsButton)) {
                        mapsController.execute(timetable);
                    }
                }
        );
        saveTimetableButton.addActionListener(
                e -> {
                    if (e.getSource().equals(saveTimetableButton)) {
                        String username = User.getInstance("", "").getUsername();
                        Timetable t = saveViewTimetableViewModel.getState().getTimetables();
                        try {
                            saveViewTimetableController.execute(true, username, t);
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(this, "You haven't generated your timetable. Please make a timetable and try again.");
                        }
                    }
                }
        );

        this.add(title);
        this.add(timetablePanel);

        GridBagConstraints gbc = new GridBagConstraints();
        for (int i = 0; i < 5; i++) {
            List<SClass> classes = timetable.getClasses().get(daysOfWeek[i]);
            if (classes != null) {
                for (SClass aClass : classes) {
                    JLabel clas = new JLabel(aClass.getCourseId());
                    clas.setBackground(Color.blue);
                    clas.setOpaque(true);
                    gbc.fill = GridBagConstraints.BOTH;
                    gbc.gridx = i + 1;
                    gbc.gridy = Integer.parseInt(aClass.getStartTime().substring(0, 2));
                    gbc.gridheight = Integer.parseInt(aClass.getEndTime().substring(0, 2))
                            - Integer.parseInt(aClass.getStartTime().substring(0, 2));
                    timetablePanel.add(clas, gbc);
                }
            }
        }
        timetablePanel.revalidate();
        timetablePanel.repaint();
    }
}
