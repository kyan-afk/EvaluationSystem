import javax.swing.*;
import java.awt.*;
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

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(UIConstants.PANEL_BORDER);

        // Header Panel
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false);
        
        JLabel titleLabel = new JLabel("Enter Grades for Current Semester", SwingConstants.CENTER);
        titleLabel.setFont(UIConstants.HEADER_FONT);
        titleLabel.setForeground(UIConstants.PRIMARY_COLOR);
        
        JLabel subtitleLabel = new JLabel(String.format("Year Level: %d, Semester: %d", 
            student.getYearLevel(), student.getSemester()), SwingConstants.CENTER);
        subtitleLabel.setFont(UIConstants.REGULAR_FONT);
        subtitleLabel.setForeground(UIConstants.LIGHT_TEXT_COLOR);
        
        headerPanel.add(titleLabel, BorderLayout.NORTH);
        headerPanel.add(subtitleLabel, BorderLayout.CENTER);
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // Grade Input Panel
        JPanel gradePanel = createGradeInputPanel();
        JScrollPane scrollPane = new JScrollPane(gradePanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Buttons Panel
        JPanel buttonPanel = createButtonPanel();
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private JPanel createGradeInputPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Headers
        String[] headers = {"Course Code", "Description", "Grade"};
        for (int i = 0; i < headers.length; i++) {
            gbc.gridx = i;
            gbc.gridy = 0;
            JLabel header = new JLabel(headers[i]);
            header.setFont(UIConstants.TITLE_FONT);
            header.setForeground(UIConstants.PRIMARY_COLOR);
            panel.add(header, gbc);
        }

        // Add subjects for current year and semester
        ArrayList<Subject> subjects = CurriculumData.getSubjectsForYearAndSem(
            student.getYearLevel(), student.getSemester());

        int row = 1;
        for (Subject subject : subjects) {
            gbc.gridy = row;
            
            gbc.gridx = 0;
            JLabel codeLabel = new JLabel(subject.getCourseCode());
            codeLabel.setFont(UIConstants.REGULAR_FONT);
            panel.add(codeLabel, gbc);

            gbc.gridx = 1;
            JLabel descLabel = new JLabel(subject.getDescription());
            descLabel.setFont(UIConstants.REGULAR_FONT);
            panel.add(descLabel, gbc);

            gbc.gridx = 2;
            JTextField gradeField = new JTextField(5);
            gradeField.setBorder(UIConstants.createRoundedBorder());
            gradeField.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusLost(java.awt.event.FocusEvent evt) {
                    validateGradeInput(gradeField);
                }
            });
            
            gradeFields.add(gradeField);
            courseCodes.add(subject.getCourseCode());
            panel.add(gradeField, gbc);

            row++;
        }

        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panel.setOpaque(false);

        JButton clearButton = new JButton("Clear All");
        styleButton(clearButton, UIConstants.SECONDARY_COLOR);
        clearButton.addActionListener(e -> clearAllGrades());

        JButton continueButton = new JButton("Continue");
        styleButton(continueButton, UIConstants.PRIMARY_COLOR);
        continueButton.addActionListener(e -> handleGradeSubmission());

        panel.add(clearButton);
        panel.add(continueButton);

        return panel;
    }

    private void styleButton(JButton button, Color bgColor) {
        button.setBackground(bgColor);
        button.setForeground(bgColor == UIConstants.PRIMARY_COLOR ? Color.WHITE : UIConstants.TEXT_COLOR);
        button.setFont(UIConstants.REGULAR_FONT);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(120, 35));
    }

    private void validateGradeInput(JTextField field) {
        String input = field.getText().trim();
        if (!input.isEmpty()) {
            try {
                double grade = Double.parseDouble(input);
                if (grade < 1.0 || grade > 5.0) {
                    showError("Grade must be between 1.0 and 5.0");
                    field.setText("");
                } else {
                    field.setBackground(new Color(240, 255, 240));
                }
            } catch (NumberFormatException ex) {
                showError("Please enter a valid number");
                field.setText("");
            }
        } else {
            field.setBackground(Color.WHITE);
        }
    }

    private void clearAllGrades() {
        for (JTextField field : gradeFields) {
            field.setText("");
            field.setBackground(Color.WHITE);
        }
    }

    private void handleGradeSubmission() {
        boolean hasInvalidGrade = false;
        boolean hasAtLeastOneGrade = false;

        for (int i = 0; i < gradeFields.size(); i++) {
            String gradeText = gradeFields.get(i).getText().trim();
            String courseCode = courseCodes.get(i);

            if (!gradeText.isEmpty()) {
                hasAtLeastOneGrade = true;
                try {
                    double grade = Double.parseDouble(gradeText);
                    if (grade >= 1.0 && grade <= 5.0) {
                        student.addGrade(courseCode, grade);
                    } else {
                        hasInvalidGrade = true;
                        showError("Invalid grade for " + courseCode);
                    }
                } catch (NumberFormatException ex) {
                    hasInvalidGrade = true;
                    showError("Invalid grade format for " + courseCode);
                }
            }
        }

        if (!hasInvalidGrade && hasAtLeastOneGrade) {
            new DashboardFrame(student).setVisible(true);
            this.dispose();
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
