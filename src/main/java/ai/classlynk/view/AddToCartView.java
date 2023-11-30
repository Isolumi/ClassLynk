package ai.classlynk.view;

import ai.classlynk.entity.ClassBundle;
import ai.classlynk.entity.Course;
import ai.classlynk.entity.SClass;
import ai.classlynk.interface_adapter.addToCart.AddToCartController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class AddToCartView extends JFrame {
    private final AddToCartController addToCartController;
    private JComboBox<Course> courseComboBox;
    private JComboBox<ClassBundle> classBundleComboBox;
    private JComboBox<SClass> sClassComboBox;
    private JButton addToCartButton;

    public AddToCartView(AddToCartController addToCartController) {
        this.addToCartController = addToCartController;
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Add to Cart");
        setSize(500, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        courseComboBox = new JComboBox<>();
        classBundleComboBox = new JComboBox<>();
        sClassComboBox = new JComboBox<>();
        addToCartButton = new JButton("Add to Cart");

        addToCartButton.addActionListener(this::handleAddToCartAction);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));
        panel.add(new JLabel("Select Course:"));
        panel.add(courseComboBox);
        panel.add(new JLabel("Select Class Bundle:"));
        panel.add(classBundleComboBox);
        panel.add(new JLabel("Select SClass:"));
        panel.add(sClassComboBox);
        panel.add(addToCartButton);

        add(panel, BorderLayout.CENTER);
    }

    private void handleAddToCartAction(ActionEvent event) {
        Course selectedCourse = (Course) courseComboBox.getSelectedItem();
        ClassBundle selectedBundle = (ClassBundle) classBundleComboBox.getSelectedItem();
        SClass selectedSClass = (SClass) sClassComboBox.getSelectedItem();

        if (selectedCourse != null && selectedBundle != null && selectedSClass != null) {
            addToCartController.addToCart(selectedCourse, selectedBundle, selectedSClass);
        }
    }

    public void setCourses(List<Course> courses) {
        courseComboBox.setModel(new DefaultComboBoxModel<>(courses.toArray(new Course[0])));
    }

    public void setClassBundles(List<ClassBundle> classBundles) {
        classBundleComboBox.setModel(new DefaultComboBoxModel<>(classBundles.toArray(new ClassBundle[0])));
    }

    public void setSClasses(List<SClass> sClasses) {
        sClassComboBox.setModel(new DefaultComboBoxModel<>(sClasses.toArray(new SClass[0])));
    }

}
