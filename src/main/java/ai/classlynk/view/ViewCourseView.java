package ai.classlynk.view;

import ai.classlynk.entity.Course;
import ai.classlynk.interface_adapter.MenuSwitchingController;
import ai.classlynk.interface_adapter.ViewCourse.ViewCourseController;
import ai.classlynk.interface_adapter.ViewCourse.ViewCourseViewModel;
import ai.classlynk.interface_adapter.addToCart.AddToCartController;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class ViewCourseView extends JPanel implements PropertyChangeListener {
    private final ViewCourseViewModel courseViewModel;
    private final ViewCourseController courseController;
    private final AddToCartController addToCartController;
    public String viewName = "View Courses";
    private JList<Course> courseList;
    private JButton addToCartButton;
    private JButton clearSelectionButton;
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
        courseList = new JList<>();
        courseList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        addToCartButton = new JButton("Add to Cart");
        clearSelectionButton = new JButton("Clear Selection");
        backButton = new JButton("Go back");

        updateCourses();
    }

    private void layoutComponents() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(new JLabel("Select Courses:"));
        this.add(new JScrollPane(courseList));
        this.add(addToCartButton);
        this.add(clearSelectionButton);
        this.add(backButton);
    }

    private void setupInteractions() {
        addToCartButton.addActionListener(e -> {
            List<Course> selectedCourses = courseList.getSelectedValuesList();
            for (Course course : selectedCourses) {
                addToCartController.addToCart(course);
            }
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
        courseList.clearSelection();
    }

    private void updateCourses() {
        DefaultListModel<Course> model = new DefaultListModel<>();
        List<Course> courses = courseViewModel.getState().getCourses();
        for (Course course : courses) {
            model.addElement(course);
        }
        courseList.setModel(model);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("state".equals(evt.getPropertyName())) {
            updateCourses();
        }
    }
}
