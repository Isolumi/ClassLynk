package ai.classlynk.view;

import ai.classlynk.entity.ClassBundle;
import ai.classlynk.entity.Course;
import ai.classlynk.entity.SClass;
import ai.classlynk.interface_adapter.MenuSwitchingController;
import ai.classlynk.interface_adapter.ViewCourse.ViewCourseController;
import ai.classlynk.interface_adapter.ViewCourse.ViewCourseViewModel;
import ai.classlynk.interface_adapter.addToCart.AddToCartController;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.List;

public class ViewCourseView extends JPanel implements PropertyChangeListener {
    public final String viewName = "View Courses";
    private final ViewCourseViewModel courseViewModel;
    private final ViewCourseController courseController;
    private final AddToCartController addToCartController;

    public JComboBox<Course> getCourseComboBox() {
        return courseComboBox;
    }

    private JComboBox<Course> courseComboBox;

    public JComboBox<ClassBundle> getClassBundleComboBox() {
        return classBundleComboBox;
    }

    private JComboBox<ClassBundle> classBundleComboBox;

    public JComboBox<SClass> getTutorialComboBox() {
        return tutorialComboBox;
    }

    private JComboBox<SClass> tutorialComboBox;

    public JButton getAddToCartButton() {
        return addToCartButton;
    }

    private JButton addToCartButton;

    public JButton getClearSelectionButton() {
        return clearSelectionButton;
    }

    private JButton clearSelectionButton;
    private Course currentlySelectedCourse;

    MenuSwitchingController menuSwitchingController;

    JButton backButton;

    public void setBackButtonController(MenuSwitchingController menuSwitchingController) {
        this.menuSwitchingController = menuSwitchingController;
    }

    public ViewCourseView(ViewCourseController courseController, ViewCourseViewModel courseViewModel, AddToCartController addToCartController) {
        this.courseController = courseController;
        this.courseViewModel = courseViewModel;
        this.addToCartController = addToCartController;

        loadCourses();
        courseViewModel.addPropertyChangeListener(this);
        initializeComponents();
        layoutComponents();
        setupInteractions();
    }

    private void loadCourses() {
        courseController.execute();
    }

    private void initializeComponents() {
        courseComboBox = new JComboBox<>();
        classBundleComboBox = new JComboBox<>();
        tutorialComboBox = new JComboBox<>();
        addToCartButton = new JButton("Add to Cart");
        clearSelectionButton = new JButton("Clear Selection");
        backButton = new JButton("Go back");

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
        this.add(clearSelectionButton);
        this.add(backButton);
    }

    private void setupInteractions() {
        courseComboBox.addActionListener(e -> {
            Course selectedCourse = (Course) courseComboBox.getSelectedItem();
            if (currentlySelectedCourse != null && currentlySelectedCourse.equals(selectedCourse)) {
                clearSelections();
                currentlySelectedCourse = null;
            } else {
                updateClassBundlesAndTutorials(selectedCourse);
                currentlySelectedCourse = selectedCourse;
            }
        });

        addToCartButton.addActionListener(e -> {
            Course selectedCourse = (Course) courseComboBox.getSelectedItem();
            ClassBundle selectedBundle = (ClassBundle) classBundleComboBox.getSelectedItem();
            SClass selectedTutorial = (SClass) tutorialComboBox.getSelectedItem();
            addToCartController.addToCart(selectedCourse, selectedBundle, selectedTutorial);
        });

        clearSelectionButton.addActionListener(e -> clearSelections());
        backButton.addActionListener(
                e -> {
                    if(e.getSource().equals(backButton))
                    {
                        menuSwitchingController.execute();
                    }
                }
        );
    }

    private void clearSelections() {
        courseComboBox.setSelectedIndex(-1);
        classBundleComboBox.removeAllItems();
        tutorialComboBox.removeAllItems();
        currentlySelectedCourse = null;
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

