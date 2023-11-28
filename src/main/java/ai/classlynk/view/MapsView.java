package ai.classlynk.view;

import ai.classlynk.interface_adapter.BackButtonController;
import ai.classlynk.interface_adapter.static_maps.MapsState;
import ai.classlynk.interface_adapter.static_maps.MapsViewModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Map;
import java.util.Objects;


public class MapsView extends JPanel implements PropertyChangeListener {

    public final String viewName = "view maps";

    JPanel menus;

    JButton backButton;

    BackButtonController backButtonController;

    public void setBackButtonController(BackButtonController backButtonController) {
        this.backButtonController = backButtonController;
    }
    public MapsView(MapsViewModel mapsViewModel) {
        mapsViewModel.addPropertyChangeListener(this);

        this.setLayout(new BorderLayout());
        menus = new JPanel(new CardLayout());

        MapsState state = mapsViewModel.getState();

        Map<String, String> formattedTimetable = state.getTimetable().getFormattedTimetable();
        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
        for (String day : days) {
            JPanel dayPanel = new JPanel();
//            dayPanel.add(new JLabel(new ImageIcon("../../../../resources/Images/" + day + "Route.jpg")));
            //TODO: if below doesent work, use above line to manually get paths and can make generation function not return data
            dayPanel.add(new JLabel(new ImageIcon(state.getImageLocations().get(day))));
            JTextArea timetableText = new JTextArea(formattedTimetable.get(day));
            timetableText.setWrapStyleWord(true);
            timetableText.setLineWrap(true);
            timetableText.setEditable(false);
            timetableText.setFont(new Font("Arial", Font.PLAIN, 30));
            timetableText.setPreferredSize(new Dimension(1000, 1000));
            dayPanel.add(timetableText);
            menus.add(dayPanel, day);
        }

        JPanel topPanel = new JPanel(new BorderLayout());

        JPanel daySwitcherButtons = new JPanel(new FlowLayout(FlowLayout.CENTER));
        for (String day : days) {
            JButton button = new JButton(day);
            button.addActionListener(e -> {
                CardLayout layout = (CardLayout) (menus.getLayout());
                layout.show(menus, day);
            });
            daySwitcherButtons.add(button);
        }
        backButton = new JButton("Go Back");

        backButton.addActionListener(
                e -> {
                    if(e.getSource().equals(backButton))
                    {
                        backButtonController.execute();
                    }
                }
        );

        topPanel.add(backButton, BorderLayout.WEST);
        topPanel.add(daySwitcherButtons, BorderLayout.CENTER);

        this.add(topPanel, BorderLayout.NORTH);
        this.add(menus, BorderLayout.CENTER);
//        frame.add(topPanel, BorderLayout.NORTH);
//        frame.add(menus, BorderLayout.CENTER);

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        MapsState state = (MapsState) evt.getNewValue();
        if(Objects.equals(state.getApiError(), "Unable to generate images. Please try again."))
        {
            JOptionPane.showMessageDialog(this, state.getApiError());
            //brings user back to previous menu
            backButton.doClick();
        }
        else
        {
            updateFields(state);
        }
    }

    private void updateFields(MapsState state) {
        Map<String, String> formattedTimetable = state.getTimetable().getFormattedTimetable();
        Map<String, String> imageLocations = state.getImageLocations();
        for (int i = 0; i < 5; i++) {
            JPanel parent = (JPanel) menus.getComponent(i);
            JLabel child = (JLabel) parent.getComponent(1);
            switch (i) {
                case 0 -> child.setText(formattedTimetable.get("Monday"));
                case 1 -> child.setText(formattedTimetable.get("Tuesday"));
                case 2 -> child.setText(formattedTimetable.get("Wednesday"));
                case 3 -> child.setText(formattedTimetable.get("Thursday"));
                case 4 -> child.setText(formattedTimetable.get("Friday"));
            }
            child = (JLabel) parent.getComponent(0);
            switch (i) {
                case 0 -> child.setIcon(new ImageIcon(imageLocations.get("Monday")));
                case 1 -> child.setIcon(new ImageIcon(imageLocations.get("Tuesday")));
                case 2 -> child.setIcon(new ImageIcon(imageLocations.get("Wednesday")));
                case 3 -> child.setIcon(new ImageIcon(imageLocations.get("Thursday")));
                case 4 -> child.setIcon(new ImageIcon(imageLocations.get("Friday")));
            }
        }
    }
}
