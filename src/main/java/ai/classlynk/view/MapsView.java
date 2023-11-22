package ai.classlynk.view;

import ai.classlynk.interface_adapter.static_maps.MapsController;
import ai.classlynk.interface_adapter.static_maps.MapsViewModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class MapsView extends JPanel implements PropertyChangeListener {


    //this view is completely independent and only displays data since the api calls are done elsewhere
    // (no controller is needed)
    public MapsView(MapsViewModel mapsViewModel)
    {

        JFrame frame = new JFrame("Daily Routes");

        JPanel menus = new JPanel(new CardLayout());

        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        for (String day : days) {
            JPanel dayPanel = new JPanel();
//            dayPanel.add(new JLabel(new ImageIcon("../../../../resources/Images/" + day + "Route.jpg")));
            //TODO: if below doesent work, use above line to manually get paths and can make generation function not return data
            dayPanel.add(new JLabel(new ImageIcon(mapsViewModel.getState().getImageLocations().get(day))));
            //TODO: create timetable to string so this text will contain the class data
            dayPanel.add(new JLabel("placeholder"));
            menus.add(dayPanel, day);
        }

        JPanel daySwitcherButtons = new JPanel(new FlowLayout(FlowLayout.CENTER));
        for (String day : days) {
            JButton button = new JButton(day);
            button.addActionListener(e -> {
                CardLayout layout = (CardLayout) (menus.getLayout());
                layout.show(menus, day);
            });
            daySwitcherButtons.add(button);
        }

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        //update timetable images and text
    }
}
