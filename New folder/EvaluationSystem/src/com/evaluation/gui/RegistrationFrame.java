
package com.evaluation.gui;

import javax.swing.*;
import java.awt.*;
//import com.evaluation.models.Student;

public class RegistrationFrame extends JFrame {
    
	private static final long serialVersionUID = 3L;
	private JTextField nameField, idField, programField, emailField;
    private JComboBox<String> yearCombo, semesterCombo;
    private JPasswordField passwordField;

    public RegistrationFrame() {
        setTitle("Student Registration");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Name Field
        addField(mainPanel, gbc, "Name:", nameField = new JTextField(20), 0);

        // ID Number Field
        addField(mainPanel, gbc, "ID Number:", idField = new JTextField(20), 1);

        // Program Field
        addField(mainPanel, gbc, "Program:", programField = new JTextField(20), 2);

        // Year Level Combo
        String[] years = {"1st Year", "2nd Year", "3rd Year", "4th Year"};
        yearCombo = new JComboBox<>(years);
        addField(mainPanel, gbc, "Year Level:", yearCombo, 3);

        // Semester Combo
        String[] semesters = {"First Semester", "Second Semester"};
        semesterCombo = new JComboBox<>(semesters);
        addField(mainPanel, gbc, "Semester:", semesterCombo, 4);

        // Email Field
        addField(mainPanel, gbc, "Email:", emailField = new JTextField(20), 5);

        // Password Field
        addField(mainPanel, gbc, "Password:", passwordField = new JPasswordField(20), 6);

        // Register Button
        gbc.gridx = 0; gbc.gridy = 7; gbc.gridwidth = 2;
        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(e -> handleRegistration());
        mainPanel.add(registerButton, gbc);

        add(mainPanel);
    }

    private void addField(JPanel panel, GridBagConstraints gbc, 
                         String label, JComponent component, int y) {
        gbc.gridx = 0; gbc.gridy = y;
        panel.add(new JLabel(label), gbc);
        gbc.gridx = 1;
        panel.add(component, gbc);
    }

    private void handleRegistration() {
        String name = nameField.getText();
        String id = idField.getText();
        String program = programField.getText();
        int year = yearCombo.getSelectedIndex() + 1;
        int semester = semesterCombo.getSelectedIndex() + 1;
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());

        if (name.isEmpty() || id.isEmpty() || program.isEmpty() || email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "All fields are required", 
                "Registration Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        Student student = new Student(name, id, program, year, semester, email, password);
        LoginFrame.addStudent(student);

        JOptionPane.showMessageDialog(this, 
            "Registration successful!", 
            "Success", 
            JOptionPane.INFORMATION_MESSAGE);

        new LoginFrame().setVisible(true);
        this.dispose();
    }
}
