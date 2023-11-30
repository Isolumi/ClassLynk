package ai.classlynk.view;

import ai.classlynk.entity.ClassBundle;
import ai.classlynk.entity.Course;
import ai.classlynk.entity.SClass;
import ai.classlynk.interface_adapter.ViewCourse.ViewCourseController;
import ai.classlynk.interface_adapter.ViewCourse.ViewCourseViewModel;
import ai.classlynk.interface_adapter.addToCart.AddToCartController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class ViewCourseView extends JPanel implements PropertyChangeListener {
    private final ViewCourseViewModel courseViewModel;
    private final ViewCourseController courseController;
    private final AddToCartController addToCartController;

    private JComboBox<Course> courseComboBox;
    private JComboBox<ClassBundle> classBundleComboBox;
    private JComboBox<SClass> tutorialComboBox;
    private JButton addToCartButton;

    public ViewCourseView(ViewCourseController courseController, ViewCourseViewModel courseViewModel, AddToCartController addToCartController) {
        this.courseController = courseController;
        this.courseViewModel = courseViewModel;
        this.addToCartController = addToCartController;

        courseViewModel.addPropertyChangeListener(this);
        initializeComponents();
        layoutComponents();
        setupInteractions();
    }

    private void initializeComponents() {
        courseComboBox = new JComboBox<>();
        classBundleComboBox = new JComboBox<>();
        tutorialComboBox = new JComboBox<>();
        addToCartButton = new JButton("Add to Cart");

        updateCourses();
    }

    private void layoutComponents() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(new JLabel("Select Course:"));
        this.add(courseComboBox);
        this.add(new JLabel("Select Class Bundle:"));
        this.add(classBundleComboBox);
        this.add(new JLabel("Select Tutorial:"));
        this.add(tutorialComboBox);
        this.add(addToCartButton);
    }

    private void setupInteractions() {
        courseComboBox.addActionListener(e -> {
            Course selectedCourse = (Course) courseComboBox.getSelectedItem();
            updateClassBundlesAndTutorials(selectedCourse);
        });

        addToCartButton.addActionListener(e -> {
            Course selectedCourse = (Course) courseComboBox.getSelectedItem();
            ClassBundle selectedBundle = (ClassBundle) classBundleComboBox.getSelectedItem();
            SClass selectedTutorial = (SClass) tutorialComboBox.getSelectedItem();
            addToCartController.addToCart(selectedCourse, selectedBundle, selectedTutorial);
        });
    }

    private void updateCourses() {
        courseComboBox.removeAllItems();
        List<Course> courses = courseViewModel.getState().getCourses();
        for (Course course : courses) {
            courseComboBox.addItem(course);
        }
    }

    private void updateClassBundlesAndTutorials(Course selectedCourse) {
        classBundleComboBox.removeAllItems();
        tutorialComboBox.removeAllItems();

        if (selectedCourse != null) {
            for (ClassBundle bundle : selectedCourse.getClassBundles()) {
                classBundleComboBox.addItem(bundle);
            }
            for (SClass tutorial : selectedCourse.getTutorials()) {
                tutorialComboBox.addItem(tutorial);
            }
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("state".equals(evt.getPropertyName())) {
            updateCourses();
        }
    }
}
