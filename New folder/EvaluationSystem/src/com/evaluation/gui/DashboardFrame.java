package com.evaluation.gui;

import javax.swing.*;
import java.awt.*;

//import com.evaluation.gui.LoginFrame;
//import com.evaluation.models.*;
import java.util.ArrayList;

public class DashboardFrame extends JFrame {
   
	private static final long serialVersionUID = 4L;
	private Student student;
    private JPanel contentPanel;
    private CardLayout cardLayout;

    public DashboardFrame(Student student) {
        this.student = student;
        setTitle("Student Dashboard");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create main layout
        setLayout(new BorderLayout());

        // Create button panel
        JPanel buttonPanel = createButtonPanel();
        add(buttonPanel, BorderLayout.NORTH);

        // Create content panel with CardLayout
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        add(contentPanel, BorderLayout.CENTER);

        // Add different panels
        contentPanel.add(createAddSubjectPanel(), "ADD_SUBJECT");
        contentPanel.add(createRemoveSubjectPanel(), "REMOVE_SUBJECT");
        contentPanel.add(createStudyLoadPanel(), "STUDY_LOAD");
        contentPanel.add(createRecommendedSubjectsPanel(), "RECOMMENDED");

        cardLayout.show(contentPanel, "STUDY_LOAD");
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout());
        
        String[] buttonLabels = {
            "Add Subject", "Remove Subject", "Show Study Load", 
            "Recommended Subject", "Exit"
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(e -> handleButtonClick(label));
            panel.add(button);
        }

        return panel;
    }

    private JPanel createAddSubjectPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        ArrayList<Subject> availableSubjects = 
            CurriculumData.getSubjectsForYearAndSem(student.getYearLevel(), student.getSemester());

        DefaultListModel<Subject> listModel = new DefaultListModel<>();
        for (Subject subject : availableSubjects) {
            listModel.addElement(subject);
        }

        JList<Subject> subjectList = new JList<>(listModel);
        subjectList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        panel.add(new JScrollPane(subjectList), BorderLayout.CENTER);

        JButton addButton = new JButton("Add Selected Subjects");
        addButton.addActionListener(e -> {
            for (Subject subject : subjectList.getSelectedValuesList()) {
                student.enrollSubject(subject.getCourseCode());
            }
            updateStudyLoadPanel();
        });
        panel.add(addButton, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createRemoveSubjectPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        DefaultListModel<String> listModel = new DefaultListModel<>();
        
        for (String courseCode : student.getEnrolledSubjects()) {
            listModel.addElement(courseCode);
        }

        JList<String> enrolledList = new JList<>(listModel);
        panel.add(new JScrollPane(enrolledList), BorderLayout.CENTER);

        JButton removeButton = new JButton("Remove Selected Subjects");
        removeButton.addActionListener(e -> {
            int choice = JOptionPane.showConfirmDialog(this,
                "Are you certain you want to remove these subjects?",
                "Confirm Removal",
                JOptionPane.YES_NO_OPTION);
                
            if (choice == JOptionPane.YES_OPTION) {
                for (String courseCode : enrolledList.getSelectedValuesList()) {
                    student.removeSubject(courseCode);
                }
                updateStudyLoadPanel();
            }
        });
        panel.add(removeButton, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createStudyLoadPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        String[][] data = new String[student.getEnrolledSubjects().size()][3];
        String[] columns = {"Course Code", "Description", "Grade"};

        int i = 0;
        for (String courseCode : student.getEnrolledSubjects()) {
            Subject subject = CurriculumData.getSubject(courseCode);
            Double grade = student.getGrade(courseCode);
            data[i][0] = courseCode;
            data[i][1] = subject.getDescription();
            data[i][2] = grade != null ? grade.toString() : "Not taken";
            i++;
        }

        JTable table = new JTable(data, columns);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        return panel;
    }

    private JPanel createRecommendedSubjectsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        ArrayList<String> recommended = new ArrayList<>();

        // Add failed subjects
        for (String courseCode : student.getEnrolledSubjects()) {
            if (student.hasFailedSubject(courseCode)) {
                recommended.add(courseCode + " (Failed)");
            }
        }

        // Add subjects with met prerequisites
        for (Subject subject : CurriculumData.getSubjectsForYearAndSem(
                student.getYearLevel(), student.getSemester())) {
            if (!student.getEnrolledSubjects().contains(subject.getCourseCode())) {
                boolean prereqsMet = true;
                for (String prereq : subject.getPrerequisites()) {
                    if (!student.hasPassedSubject(prereq)) {
                        prereqsMet = false;
                        break;
                    }
                }
                if (prereqsMet) {
                    recommended.add(subject.getCourseCode() + " (Available)");
                }
            }
        }

        JList<String> recommendedList = new JList<>(
            recommended.toArray(new String[0]));
        panel.add(new JScrollPane(recommendedList), BorderLayout.CENTER);
        return panel;
    }

    private void handleButtonClick(String buttonLabel) {
        switch (buttonLabel) {
            case "Add Subject":
                cardLayout.show(contentPanel, "ADD_SUBJECT");
                break;
            case "Remove Subject":
                cardLayout.show(contentPanel, "REMOVE_SUBJECT");
                break;
            case "Show Study Load":
                cardLayout.show(contentPanel, "STUDY_LOAD");
                break;
            case "Recommended Subject":
                cardLayout.show(contentPanel, "RECOMMENDED");
                break;
            case "Exit":
                new LoginFrame().setVisible(true);
                this.dispose();
                break;
        }
    }

    private void updateStudyLoadPanel() {
        contentPanel.remove(contentPanel.getComponent(2));
        contentPanel.add(createStudyLoadPanel(), "STUDY_LOAD", 2);
        cardLayout.show(contentPanel, "STUDY_LOAD");
    }
}
