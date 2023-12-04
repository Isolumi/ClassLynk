package ai.classlynk.view;

import ai.classlynk.entity.Course;
import ai.classlynk.entity.User;
import ai.classlynk.interface_adapter.MenuSwitchingController;
import ai.classlynk.interface_adapter.ViewCourse.ViewCourseController;
import ai.classlynk.interface_adapter.ViewCourse.ViewCourseViewModel;
import ai.classlynk.interface_adapter.addToCart.AddToCartController;
import ai.classlynk.interface_adapter.generate_timetable.GenerateTimetableController;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class ViewCourseView extends JPanel implements PropertyChangeListener {
    private final ViewCourseViewModel courseViewModel;
    private final ViewCourseController courseController;
    private final AddToCartController addToCartController;

    private final GenerateTimetableController generateTimetableController;
    public String viewName = "View Courses";
    private JList<Course> courseList;
    private JButton addToCartButton;
    private JButton viewCartButton;

    private JButton clearCartButton;
    private MenuSwitchingController menuSwitchingController;

    private JButton backButton;

    private JButton generateButton;



    public void setBackButtonController(MenuSwitchingController menuSwitchingController) {
        this.menuSwitchingController = menuSwitchingController;
    }

    public ViewCourseView(ViewCourseController courseController, ViewCourseViewModel courseViewModel, AddToCartController addToCartController, GenerateTimetableController generateTimetableController) {
        this.courseController = courseController;
        this.courseViewModel = courseViewModel;
        this.addToCartController = addToCartController;
        this.generateTimetableController = generateTimetableController;

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
        backButton = new JButton("Go Back");
        viewCartButton = new JButton("View Cart");
        clearCartButton = new JButton("Clear Cart");
        generateButton = new JButton("Generate Timetables");
        updateCourses();
    }

    private void layoutComponents() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(new JLabel("Select Courses:"));
        this.add(new JScrollPane(courseList));
        JPanel bottom = new JPanel();
        bottom.setLayout(new BoxLayout(bottom, BoxLayout.X_AXIS));
        bottom.add(backButton);
        bottom.add(addToCartButton);
        bottom.add(viewCartButton);
        bottom.add(clearCartButton);
        bottom.add(generateButton);
        this.add(bottom);
    }

    private void setupInteractions() {
        addToCartButton.addActionListener(e -> {
            List<Course> selectedCourses = courseList.getSelectedValuesList();
            for (Course course : selectedCourses) {
                addToCartController.addToCart(course);
            }
        });

        clearCartButton.addActionListener(e -> clearCart());

        viewCartButton.addActionListener(
                e ->
                {
                    if(e.getSource().equals(viewCartButton))
                    {
                        JOptionPane.showMessageDialog(this, User.getInstance("", "").formatCart());
                    }
                }
        );
        backButton.addActionListener(
                e -> {
                    if(e.getSource().equals(backButton))
                    {
                        menuSwitchingController.execute();
                    }
                }
        );

        generateButton.addActionListener(e ->
        {
            if(e.getSource().equals(generateButton))
            {
                if(User.getInstance("", "").getCourseKart().size() > 0)
                {
                    generateTimetableController.execute(User.getInstance("", "").getCourseKart());
                }
                else {
                    JOptionPane.showMessageDialog(this, "You have no courses in your cart. Please add some courses and try again.");
                }
            }
        });
    }

    private void clearCart() {
        User.getInstance("", "").setCourseKart(new ArrayList<>());
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
