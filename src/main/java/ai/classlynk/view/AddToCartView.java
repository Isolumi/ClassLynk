package ai.classlynk.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddToCartView extends JFrame {
    private JList<Course> courseList;
    private JList<SClass> sClassList;
    private JButton addButton; // This could be multiple buttons if needed
    private AddToCartController controller;

    public AddToCartView(AddToCartController controller, AddToCartViewModel viewModel) {
        this.controller = controller;
        viewModel.addPropertyChangeListener(evt -> updateLists());

        setTitle("Add to Cart");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        courseList = new JList<>();
        sClassList = new JList<>();

        JPanel listPanel = new JPanel();
        listPanel.setLayout(new GridLayout(1, 2));
        listPanel.add(new JScrollPane(courseList));
        listPanel.add(new JScrollPane(sClassList));

        add(listPanel, BorderLayout.CENTER);

        addButton = new JButton("Add Item");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logic for adding items to the cart
            }
        });
        add(addButton, BorderLayout.SOUTH);
    }

    private void updateLists() {
        // Logic to update the lists based on the ViewModel's state
        AddToCartState state = controller.getViewModel().getState();
        DefaultListModel<Course> courseModel = new DefaultListModel<>();
        for (Course course : state.getCourseCart()) {
            courseModel.addElement(course);
        }
        courseList.setModel(courseModel);

        DefaultListModel<SClass> sClassModel = new DefaultListModel<>();
        for (SClass sClass : state.getsClasses()) {
            sClassModel.addElement(sClass);
        }
        sClassList.setModel(sClassModel);
    }
}
