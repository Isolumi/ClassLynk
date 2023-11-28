package ai.classlynk.view;

import ai.classlynk.interface_adapter.BackButtonController;
import ai.classlynk.interface_adapter.static_maps.MapsState;
import ai.classlynk.interface_adapter.static_maps.MapsViewModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
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
                        for (String path : mapsViewModel.getState().getImageLocations().values()) {
                            File file = new File(path);
                            if (file.exists()) {
                                Image image = Toolkit.getDefaultToolkit().getImage(path);
                                image.flush();
                                file.delete();
                            }
                        }
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

    private ImageIcon getImageIcon(String path) {
        Image image = Toolkit.getDefaultToolkit().getImage(path);
        return new ImageIcon(image);
    }

    private void updateFields(MapsState state) {
        Map<String, String> formattedTimetable = state.getTimetable().getFormattedTimetable();
        Map<String, String> imageLocations = state.getImageLocations();

        for (int i = 0; i < 5; i++) {
            JPanel parent = (JPanel) menus.getComponent(i);
            JLabel child = (JLabel) parent.getComponent(0);

            ImageIcon newIcon = getImageIcon(imageLocations.get(getDayOfWeek(i)));
            child.setIcon(newIcon);

            JTextArea child2 = (JTextArea) parent.getComponent(1);
            child2.setText(formattedTimetable.get(getDayOfWeek(i)));

            parent.revalidate();
            parent.repaint();
        }
    }

    private String getDayOfWeek(int i) {
        return switch (i) {
            case 0 -> "Monday";
            case 1 -> "Tuesday";
            case 2 -> "Wednesday";
            case 3 -> "Thursday";
            case 4 -> "Friday";
            default -> "";
        };
    }
}
