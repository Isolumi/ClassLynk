package ai.classlynk.view;

import ai.classlynk.entity.ClassBundle;
import ai.classlynk.entity.Course;
import ai.classlynk.entity.SClass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class ViewCourseView extends JFrame {
    private JPanel mainPanel;
    private JList<Course> courseList;
    private JPanel detailsPanel;
    private DefaultListModel<Course> courseListModel;
    private JLabel courseNameLabel;
    private JLabel courseDescriptionLabel;
    private JPanel classBundlesPanel;
    private JPanel tutorialsPanel;

    public ViewCourseView() {
        setTitle("View Courses");
        setSize(800, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainPanel = new JPanel(new BorderLayout());
        courseListModel = new DefaultListModel<>();
        courseList = new JList<>(courseListModel);
        courseList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        courseList.addListSelectionListener(e -> showCourseDetails(courseList.getSelectedValue()));
        mainPanel.add(new JScrollPane(courseList), BorderLayout.WEST);

        detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        courseNameLabel = new JLabel();
        courseDescriptionLabel = new JLabel();
        classBundlesPanel = new JPanel(new FlowLayout());
        tutorialsPanel = new JPanel(new FlowLayout());

        detailsPanel.add(courseNameLabel);
        detailsPanel.add(courseDescriptionLabel);
        detailsPanel.add(new JLabel("Class Bundles:"));
        detailsPanel.add(classBundlesPanel);
        detailsPanel.add(new JLabel("Tutorials:"));
        detailsPanel.add(tutorialsPanel);

        mainPanel.add(detailsPanel, BorderLayout.CENTER);
        add(mainPanel);
    }

    public void setCourses(List<Course> courses) {
        courseListModel.clear();
        for (Course course : courses) {
            courseListModel.addElement(course);
        }
    }

    private void showCourseDetails(Course course) {
        if (course == null) return;
        courseNameLabel.setText("Name: " + course.getCourseName());
        courseDescriptionLabel.setText("Description: " + course.getCourseDescription());

        classBundlesPanel.removeAll();
        for (ClassBundle bundle : course.getClassBundles()) {
            JButton bundleButton = new JButton(bundle.getLectureId());
            bundleButton.addActionListener(e -> selectClassBundle(bundle));
            classBundlesPanel.add(bundleButton);
        }

        tutorialsPanel.removeAll();
        for (SClass tutorial : course.getTutorials()) {
            JButton tutorialButton = new JButton(tutorial.getClassId());
            tutorialButton.addActionListener(e -> selectTutorial(tutorial));
            tutorialsPanel.add(tutorialButton);
        }

        detailsPanel.revalidate();
        detailsPanel.repaint();
    }

    private void selectClassBundle(ClassBundle bundle) {
        // Implement the logic for when a classBundle is selected
        JOptionPane.showMessageDialog(this, "Selected Class Bundle: " + bundle.getLectureId());
    }

    private void selectTutorial(SClass tutorial) {
        // Implement the logic for when a tutorial is selected
        JOptionPane.showMessageDialog(this, "Selected Tutorial: " + tutorial.getClassId());
    }

}