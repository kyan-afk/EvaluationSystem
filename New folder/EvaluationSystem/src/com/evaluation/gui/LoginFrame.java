//package com.evaluation.gui;
//
//import javax.swing.*;
//import java.awt.*;
//import java.util.HashMap;
////import com.evaluation.models.Student;
//
//public class LoginFrame extends JFrame {
//  
//    private static final long serialVersionUID = 2L;
//	private JTextField idField;
//    private JPasswordField passwordField;
//    private static HashMap<String, Student> students = new HashMap<>();
//
//    public LoginFrame() {
//        setTitle("Student Evaluation System - Login");
//        setSize(400, 300);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLocationRelativeTo(null);
//
//        JPanel mainPanel = new JPanel(new GridBagLayout());
//        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.insets = new Insets(5, 5, 5, 5);
//
//        // ID Number Field
//        gbc.gridx = 0; gbc.gridy = 0;
//        mainPanel.add(new JLabel("ID Number:"), gbc);
//        gbc.gridx = 1;
//        idField = new JTextField(20);
//        mainPanel.add(idField, gbc);
//
//        // Password Field
//        gbc.gridx = 0; gbc.gridy = 1;
//        mainPanel.add(new JLabel("Password:"), gbc);
//        gbc.gridx = 1;
//        passwordField = new JPasswordField(20);
//        mainPanel.add(passwordField, gbc);
//
//        // Login Button
//        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
//        JButton loginButton = new JButton("Login");
//        loginButton.addActionListener(e -> handleLogin());
//        mainPanel.add(loginButton, gbc);
//
//        // Register Button
//        gbc.gridy = 3;
//        JButton registerButton = new JButton("Register");
//        registerButton.addActionListener(e -> {
//            new RegistrationFrame().setVisible(true);
//            this.setVisible(false);
//        });
//        mainPanel.add(registerButton, gbc);
//
//        add(mainPanel);
//    }
//
//    private void handleLogin() {
//        String id = idField.getText();
//        String password = new String(passwordField.getPassword());
//
//        Student student = students.get(id);
//        if (student != null && student.getPassword().equals(password)) {
//            new GradeInputFrame(student).setVisible(true);
//            this.setVisible(false);
//         // First show grade input frame
//            GradeInputFrame gradeFrame = new GradeInputFrame(student);
//            gradeFrame.setVisible(true);
//            this.dispose(); // Close login frame
//        } else {
//            JOptionPane.showMessageDialog(this, 
//                "Invalid ID or Password", 
//                "Login Error", 
//                JOptionPane.ERROR_MESSAGE);
//        }
//    }
//
//    public static void addStudent(Student student) {
//        students.put(student.getIdNumber(), student);
//    }
//}

package com.evaluation.gui;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class LoginFrame extends JFrame {

    private static final long serialVersionUID = 2L;
    private JTextField idField;
    private JPasswordField passwordField;
    private static HashMap<String, Student> students = new HashMap<>();

    public LoginFrame() {
        setTitle("Student Evaluation System - Login");
        setSize(800, 600);  // Same size as the GradeInputFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // ID Number Field
        gbc.gridx = 0; gbc.gridy = 0;
        mainPanel.add(new JLabel("ID Number:"), gbc);
        gbc.gridx = 1;
        idField = new JTextField(20);
        mainPanel.add(idField, gbc);

        // Password Field
        gbc.gridx = 0; gbc.gridy = 1;
        mainPanel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        passwordField = new JPasswordField(20);
        mainPanel.add(passwordField, gbc);

        // Login Button
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> handleLogin());
        mainPanel.add(loginButton, gbc);

        // Register Button
        gbc.gridy = 3;
        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(e -> {
            new RegistrationFrame().setVisible(true);
            this.setVisible(false);
        });
        mainPanel.add(registerButton, gbc);

        add(mainPanel);
    }

    private void handleLogin() {
        String id = idField.getText();
        String password = new String(passwordField.getPassword());

        Student student = students.get(id);
        if (student != null && student.getPassword().equals(password)) {
            new GradeInputFrame(student).setVisible(true);
            this.setVisible(false);
         // First show grade input frame
            GradeInputFrame gradeFrame = new GradeInputFrame(student);
            gradeFrame.setVisible(true);
            this.dispose(); // Close login frame
        } else {
            JOptionPane.showMessageDialog(this,
                "Invalid ID or Password",
                "Login Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void addStudent(Student student) {
        students.put(student.getIdNumber(), student);
    }
}

