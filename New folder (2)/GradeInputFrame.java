//package com.evaluation.gui;
//
//import javax.swing.*;
//import java.awt.*;
////import com.evaluation.models.*;
//import java.util.ArrayList;
//
//public class GradeInputFrame extends JFrame {
//   
//	private static final long serialVersionUID = 1L;
//	private Student student;
//    private ArrayList<JTextField> gradeFields;
//    private ArrayList<String> courseCodes;
//
//    public GradeInputFrame(Student student) {
//        this.student = student;
//        this.gradeFields = new ArrayList<>();
//        this.courseCodes = new ArrayList<>();
//
//        setTitle("Grade Input - " + student.getName());
//        setSize(800, 600);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLocationRelativeTo(null);
//
//        JPanel mainPanel = new JPanel(new BorderLayout());
//        JPanel headerPanel = new JPanel();
//        headerPanel.add(new JLabel("<html><h2>Enter grades (1.0 to 5.0) for your subjects</h2>" +
//                "<p>Year Level: " + student.getYearLevel() + ", Semester: " + student.getSemester() + "</p></html>"));
//        mainPanel.add(headerPanel, BorderLayout.NORTH);
//        // Create grade input panel
//        JPanel gradePanel = new JPanel(new GridBagLayout());
//        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.insets = new Insets(5, 5, 5, 5);
//        gbc.fill = GridBagConstraints.HORIZONTAL;
//
//        // Headers
//        gbc.gridx = 0; gbc.gridy = 0;
//        gradePanel.add(new JLabel("Course Code"), gbc);
//        gbc.gridx = 1;
//        gradePanel.add(new JLabel("Description"), gbc);
//        gbc.gridx = 2;
//        gradePanel.add(new JLabel("Grade"), gbc);
//
//        // Add subjects for current year and semester
//        ArrayList<Subject> subjects = 
//            CurriculumData.getSubjectsForYearAndSem(student.getYearLevel(), student.getSemester());
//
//        System.out.println("Loading subjects for Year: " + student.getYearLevel() + 
//                ", Semester: " + student.getSemester());
//        System.out.println("Found " + subjects.size() + " subjects");
//        int row = 1;
//        for (Subject subject : subjects) {
//        	
//        	System.out.println("Adding subject: " + subject.getCourseCode() + 
//                    " - " + subject.getDescription());
//            gbc.gridx = 0; gbc.gridy = row;
//            gradePanel.add(new JLabel(subject.getCourseCode()), gbc);
//            
//            gbc.gridx = 1;
//            gradePanel.add(new JLabel(subject.getDescription()), gbc);
//            
//            gbc.gridx = 2;
//            JTextField gradeField = new JTextField(5);
//         // Add real-time grade validation
//            gradeField.addFocusListener(new java.awt.event.FocusAdapter() {
//                public void focusLost(java.awt.event.FocusEvent evt) {
//                    validateGradeInput(gradeField);
//                }
//            });
//            gradeFields.add(gradeField);
//            courseCodes.add(subject.getCourseCode());
//            gradePanel.add(gradeField, gbc);
//            
//            row++;
//        }
//
//        JScrollPane scrollPane = new JScrollPane(gradePanel);
//        mainPanel.add(scrollPane, BorderLayout.CENTER);
//        
//     // Add buttons panel
//        JPanel buttonPanel = new JPanel();
//        JButton clearButton = new JButton("Clear All");
//        clearButton.addActionListener(e -> clearAllGrades());
//        buttonPanel.add(clearButton);
//        
//        JButton continueButton = new JButton("Continue");
//        continueButton.addActionListener(e -> {
//            // Validate all fields before submission
//            for (JTextField field : gradeFields) {
//                validateGradeInput(field);
//            }
//            handleGradeSubmission();
//        });
//        buttonPanel.add(continueButton);
//
//        
//        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
//        add(mainPanel);
//     // Debug message
//        System.out.println("Grade Input Frame initialized for student: " + student.getName() + 
//                         " (Year: " + student.getYearLevel() + 
//                         ", Semester: " + student.getSemester() + ")");
//    }
//
//    private void validateGradeInput(JTextField field) {
//        String input = field.getText().trim();
//        System.out.println("Validating grade input for field " + field.getName() + ": " + input);
//        if (!input.isEmpty()) {
//            try {
//                double grade = Double.parseDouble(input);
//                if (grade < 1.0 || grade > 5.0) {
//                	System.out.println("Invalid grade value: " + grade);
//                    JOptionPane.showMessageDialog(this,
//                        "Grade must be between 1.0 and 5.0",
//                        "Invalid Grade",
//                        JOptionPane.ERROR_MESSAGE);
//                    field.setText("");
//                    field.requestFocus();
//                }else {
//                    System.out.println("Valid grade: " + grade);
//                    // Visual feedback for valid grade
//                    field.setBackground(new Color(230, 255, 230)); // Light green
//              }
//            } catch (NumberFormatException ex) {
//            	System.out.println("Invalid number format: " + input);
//                JOptionPane.showMessageDialog(this,
//                    "Please enter a valid number",
//                    "Invalid Input",
//                    JOptionPane.ERROR_MESSAGE);
//                field.setText("");
//                field.requestFocus();
//            }
//        } else {
//            field.setBackground(Color.WHITE);
//        }    
//    }
//    private void clearAllGrades() {
//    	System.out.println("Clearing all grades");
//        for (JTextField field : gradeFields) {
//            field.setText("");
//            field.setBackground(Color.WHITE);
//        }
//    }
//    
//    private void handleGradeSubmission() {
//        boolean hasInvalidGrade = false;
//        boolean hasAtLeastOneGrade = false;
//        
//        // Debug message
//        System.out.println("\nProcessing grade submission...");
//        
//        for (int i = 0; i < gradeFields.size(); i++) {
//            String gradeText = gradeFields.get(i).getText().trim();
//            String courseCode = courseCodes.get(i);
//            
//            System.out.println("Processing " + courseCode + ": " + gradeText);
//            
//            if (!gradeText.isEmpty()) {
//            	 hasAtLeastOneGrade = true;
//                try {
//                    double grade = Double.parseDouble(gradeText);
//                    if (grade >= 1.0 && grade <= 5.0) {
//                        student.addGrade(courseCode, grade);
//                        student.enrollSubject(courseCode);
//                        System.out.println("Added grade " + grade + " for course " + courseCode);
//                    } else {
//                        hasInvalidGrade = true;
//                        System.out.println("Invalid grade value: " + grade);
//                        JOptionPane.showMessageDialog(this,
//                            "Invalid grade for " + courseCode + ". Grade must be between 1.0 and 5.0",
//                            "Invalid Grade",
//                            JOptionPane.ERROR_MESSAGE);
//                    }
//                } catch (NumberFormatException ex) {
//                	System.out.println("Invalid number format for " + courseCode + ": " + gradeText);
//                    hasInvalidGrade = true;
//                    JOptionPane.showMessageDialog(this,
//                        "Invalid grade format for " + courseCode,
//                        "Invalid Input",
//                        JOptionPane.ERROR_MESSAGE);
//                }
//            }
//        }
//
//        if (!hasInvalidGrade) {
//        	System.out.println("All grades processed successfully. Opening Dashboard...");
//            new DashboardFrame(student).setVisible(true);
//            this.dispose();
//        }
//    }
//}



import javax.swing.*;
import java.awt.*;
//import com.evaluation.models.Student;
//import com.evaluation.models.Subject;
import java.util.ArrayList;

public class GradeInputFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private Student student;
    private ArrayList<JTextField> gradeFields;
    private ArrayList<String> courseCodes;

    public GradeInputFrame(Student student) {
        this.student = student;
        this.gradeFields = new ArrayList<>();
        this.courseCodes = new ArrayList<>();

        setTitle("Grade Input - " + student.getName());
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel headerPanel = new JPanel();
        headerPanel.add(new JLabel("<html><h2>Enter grades (1.0 to 5.0) for your subjects</h2>" +
                "<p>Year Level: " + student.getYearLevel() + ", Semester: " + student.getSemester() + "</p></html>"));
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // Create grade input panel
        JPanel gradePanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Headers
        gbc.gridx = 0;
        gbc.gridy = 0;
        gradePanel.add(new JLabel("Course Code"), gbc);
        gbc.gridx = 1;
        gradePanel.add(new JLabel("Description"), gbc);
        gbc.gridx = 2;
        gradePanel.add(new JLabel("Grade"), gbc);

        // Add subjects for current year and semester
        ArrayList<Subject> subjects =
            CurriculumData.getSubjectsForYearAndSem(student.getYearLevel(), student.getSemester());

        System.out.println("Loading subjects for Year: " + student.getYearLevel() +
                ", Semester: " + student.getSemester());
        System.out.println("Found " + subjects.size() + " subjects");
        int row = 1;
        for (Subject subject : subjects) {

            System.out.println("Adding subject: " + subject.getCourseCode() +
                    " - " + subject.getDescription());
            gbc.gridx = 0;
            gbc.gridy = row;
            gradePanel.add(new JLabel(subject.getCourseCode()), gbc);

            gbc.gridx = 1;
            gradePanel.add(new JLabel(subject.getDescription()), gbc);

            gbc.gridx = 2;
            JTextField gradeField = new JTextField(5);
            // Add real-time grade validation
            gradeField.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusLost(java.awt.event.FocusEvent evt) {
                    validateGradeInput(gradeField);
                }
            });
            gradeFields.add(gradeField);
            courseCodes.add(subject.getCourseCode());
            gradePanel.add(gradeField, gbc);

            row++;
        }

        JScrollPane scrollPane = new JScrollPane(gradePanel);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Add buttons panel
        JPanel buttonPanel = new JPanel();
        JButton clearButton = new JButton("Clear All");
        clearButton.addActionListener(e -> clearAllGrades());
        buttonPanel.add(clearButton);

        JButton continueButton = new JButton("Continue");
        continueButton.addActionListener(e -> {
            // Validate all fields before submission
            for (JTextField field : gradeFields) {
                validateGradeInput(field);
            }
            handleGradeSubmission();
        });
        buttonPanel.add(continueButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel);

        // Debug message
        System.out.println("Grade Input Frame initialized for student: " + student.getName() +
                         " (Year: " + student.getYearLevel() +
                         ", Semester: " + student.getSemester() + ")");
    }

    private void validateGradeInput(JTextField field) {
        String input = field.getText().trim();
        System.out.println("Validating grade input for field " + field.getName() + ": " + input);
        if (!input.isEmpty()) {
            try {
                double grade = Double.parseDouble(input);
                if (grade < 1.0 || grade > 5.0) {
                    System.out.println("Invalid grade value: " + grade);
                    JOptionPane.showMessageDialog(this,
                        "Grade must be between 1.0 and 5.0",
                        "Invalid Grade",
                        JOptionPane.ERROR_MESSAGE);
                    field.setText("");
                    field.requestFocus();
                } else {
                    System.out.println("Valid grade: " + grade);
                    // Visual feedback for valid grade
                    field.setBackground(new Color(230, 255, 230)); // Light green
                }
            } catch (NumberFormatException ex) {
                System.out.println("Invalid number format: " + input);
                JOptionPane.showMessageDialog(this,
                    "Please enter a valid number",
                    "Invalid Input",
                    JOptionPane.ERROR_MESSAGE);
                field.setText("");
                field.requestFocus();
            }
        } else {
            field.setBackground(Color.WHITE);
        }
    }

    private void clearAllGrades() {
        System.out.println("Clearing all grades");
        for (JTextField field : gradeFields) {
            field.setText("");
            field.setBackground(Color.WHITE);
        }
    }

    private void handleGradeSubmission() {
        boolean hasInvalidGrade = false;
        boolean hasAtLeastOneGrade = false;

        // Debug message
        System.out.println("\nProcessing grade submission...");

        for (int i = 0; i < gradeFields.size(); i++) {
            String gradeText = gradeFields.get(i).getText().trim();
            String courseCode = courseCodes.get(i);

            System.out.println("Processing " + courseCode + ": " + gradeText);

            if (!gradeText.isEmpty()) {
                hasAtLeastOneGrade = true;
                try {
                    double grade = Double.parseDouble(gradeText);
                    if (grade >= 1.0 && grade <= 5.0) {
                        student.addGrade(courseCode, grade);
                        student.enrollSubject(courseCode);
                        System.out.println("Added grade " + grade + " for course " + courseCode);
                    } else {
                        hasInvalidGrade = true;
                        System.out.println("Invalid grade value: " + grade);
                        JOptionPane.showMessageDialog(this,
                            "Invalid grade for " + courseCode + ". Grade must be between 1.0 and 5.0",
                            "Invalid Grade",
                            JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    System.out.println("Invalid number format for " + courseCode + ": " + gradeText);
                    hasInvalidGrade = true;
                    JOptionPane.showMessageDialog(this,
                        "Invalid grade format for " + courseCode,
                        "Invalid Input",
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        if (!hasInvalidGrade) {
            System.out.println("All grades processed successfully. Opening Dashboard...");
            new DashboardFrame(student).setVisible(true);
            this.dispose();  // Close the Grade Input Frame after successful submission
        }
    }
}

