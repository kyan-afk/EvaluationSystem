import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class DashboardFrame extends JFrame {
    private static final long serialVersionUID = 4L;
    private Student student;
    private JPanel contentPanel;
    private CardLayout cardLayout;

    public DashboardFrame(Student student) {
        this.student = student;
        setTitle("Student Dashboard - " + student.getName());
        setSize(1200, 800); //setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main layout using BorderLayout
        setLayout(new BorderLayout(15, 15));
        
        // Navigation Panel
        JPanel navPanel = createNavigationPanel();
        add(navPanel, BorderLayout.WEST);

        // Content Panel with CardLayout
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        contentPanel.setBackground(Color.WHITE);
        
        // Add different panels
        contentPanel.add(createStudyLoadPanel(), "STUDY_LOAD");
        contentPanel.add(createAddSubjectPanel(), "ADD_SUBJECT");
        contentPanel.add(createRemoveSubjectPanel(), "REMOVE_SUBJECT");
        contentPanel.add(createRecommendedSubjectsPanel(), "RECOMMENDED");
        
        add(contentPanel, BorderLayout.CENTER);
        
        // Show Study Load by default
        cardLayout.show(contentPanel, "STUDY_LOAD");
    }

    private JPanel createNavigationPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setPreferredSize(new Dimension(200, 0));
        panel.setBackground(UIConstants.PRIMARY_COLOR);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 0, 5, 0);

        String[] buttons = {
            "Study Load", "Add Subject", "Remove Subject", 
            "Recommended Subjects", "Logout"
        };

        for (String label : buttons) {
            JButton button = createNavButton(label);
            panel.add(button, gbc);
        }

        return panel;
    }

    private JButton createNavButton(String label) {
        JButton button = new JButton(label);
        button.setFont(UIConstants.REGULAR_FONT);
        button.setForeground(Color.WHITE);
        button.setBackground(UIConstants.PRIMARY_COLOR);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addActionListener(e -> handleNavigation(label));
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(71, 122, 224));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(UIConstants.PRIMARY_COLOR);
            }
        });

        return button;
    }

    private JPanel createStudyLoadPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(UIConstants.PANEL_BORDER);
        panel.setBackground(Color.WHITE);

        // Header
        JLabel titleLabel = new JLabel("Current Study Load", SwingConstants.CENTER);
        titleLabel.setFont(UIConstants.HEADER_FONT);
        titleLabel.setForeground(UIConstants.PRIMARY_COLOR);
        panel.add(titleLabel, BorderLayout.NORTH);

        // Table
        String[] columns = {"Course Code", "Description", "Grade"};
        ArrayList<String> enrolled = student.getEnrolledSubjects();
        Object[][] data = new Object[enrolled.size()][3];

        for (int i = 0; i < enrolled.size(); i++) {
            String courseCode = enrolled.get(i);
            Subject subject = CurriculumData.getSubject(courseCode);
            Double grade = student.getGrade(courseCode);
            
            data[i][0] = courseCode;
            data[i][1] = subject.getDescription();
            data[i][2] = grade != null ? String.format("%.1f", grade) : "Not taken";
        }

        JTable table = new JTable(data, columns);
        table.setFont(UIConstants.REGULAR_FONT);
        table.getTableHeader().setFont(UIConstants.REGULAR_FONT);
        table.setRowHeight(25);
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createAddSubjectPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(UIConstants.PANEL_BORDER);
        panel.setBackground(Color.WHITE);

        // Get next semester's subjects
        int nextSemester = student.getNextSemester();
        int nextYear = student.getNextYearLevel();
        ArrayList<Subject> availableSubjects = 
            CurriculumData.getSubjectsForYearAndSem(nextYear, nextSemester);

        // Header
        JLabel titleLabel = new JLabel(String.format("Available Subjects for Year %d, Semester %d", 
            nextYear, nextSemester), SwingConstants.CENTER);
        titleLabel.setFont(UIConstants.HEADER_FONT);
        titleLabel.setForeground(UIConstants.PRIMARY_COLOR);
        panel.add(titleLabel, BorderLayout.NORTH);

        // Subjects List
        DefaultListModel<Subject> listModel = new DefaultListModel<>();
        for (Subject subject : availableSubjects) {
            if (checkPrerequisites(subject)) {
                listModel.addElement(subject);
            }
        }

        JList<Subject> subjectList = new JList<>(listModel);
        subjectList.setFont(UIConstants.REGULAR_FONT);
        subjectList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        
        JScrollPane scrollPane = new JScrollPane(subjectList);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        panel.add(scrollPane, BorderLayout.CENTER);

        // Add Button
        JButton addButton = new JButton("Add Selected Subjects");
        styleButton(addButton);
        addButton.addActionListener(e -> {
            for (Subject subject : subjectList.getSelectedValuesList()) {
                student.enrollSubject(subject.getCourseimport javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DashboardFrame extends JFrame {
    private static final long serialVersionUID = 4L;
    private Student student;
    private JPanel contentPanel;
    private CardLayout cardLayout;

    public DashboardFrame(Student student) {
        this.student = student;
        setTitle("Student Dashboard - " + student.getName());
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create main layout
        setLayout(new BorderLayout(10, 10));
        
        // Add header panel
        add(createHeaderPanel(), BorderLayout.NORTH);
        
        // Create content panel with CardLayout
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        contentPanel.setBackground(Color.WHITE);
        
        // Add different panels
        contentPanel.add(createStudyLoadPanel(), "STUDY_LOAD");
        contentPanel.add(createAddSubjectPanel(), "ADD_SUBJECT");
        contentPanel.add(createRemoveSubjectPanel(), "REMOVE_SUBJECT");
        contentPanel.add(createRecommendedSubjectsPanel(), "RECOMMENDED");
        
        // Wrap content in a scroll pane
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        add(scrollPane, BorderLayout.CENTER);
        
        // Add navigation sidebar
        add(createNavigationPanel(), BorderLayout.WEST);
    }

    private JPanel createHeaderPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(UIConstants.PRIMARY_COLOR);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));
        
        // Student Info Panel
        JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 0));
        infoPanel.setOpaque(false);
        
        JLabel nameLabel = new JLabel(student.getName());
        nameLabel.setFont(UIConstants.HEADER_FONT);
        nameLabel.setForeground(Color.WHITE);
        panel.add(nameLabel, BorderLayout.WEST);

        JLabel idLabel = new JLabel("ID: " + student.getIdNumber());
        idLabel.setFont(UIConstants.REGULAR_FONT);
        idLabel.setForeground(Color.WHITE);
        JLabel programLabel = new JLabel(student.getProgram());
        programLabel.setFont(UIConstants.TITLE_FONT);
        programLabel.setForeground(Color.WHITE);
        infoPanel.add(nameLabel);
        infoPanel.add(new JSeparator(SwingConstants.VERTICAL));
        infoPanel.add(idLabel);
        infoPanel.add(new JSeparator(SwingConstants.VERTICAL));
        infoPanel.add(programLabel);
        
        // Academic Info Panel
        JPanel academicPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 5));
        academicPanel.setOpaque(false);
        JLabel yearLabel = new JLabel(String.format("Year Level: %d", student.getYearLevel()));
        yearLabel.setFont(UIConstants.REGULAR_FONT);
        yearLabel.setForeground(Color.WHITE);
        JLabel semLabel = new JLabel(String.format("Semester: %d", student.getSemester()));
        semLabel.setFont(UIConstants.REGULAR_FONT);
        semLabel.setForeground(Color.WHITE);
        academicPanel.add(yearLabel);
        academicPanel.add(semLabel);
        panel.add(infoPanel);
        panel.add(Box.createVerticalStrut(10));
        panel.add(academicPanel);
        
        return panel;
    }

    private JPanel createNavigationPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));        
        panel.setBackground(UIConstants.SECONDARY_COLOR);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 15, 20, 15));
        panel.setPreferredSize(new Dimension(220, 0));

        String[] buttons = {
            "Study Load", "Add Subject", "Remove Subject", 
            "Recommended Subjects", "Logout"
        };

        for (String buttonText : buttons) {
            JButton button = new JButton(buttonText);
            styleNavigationButton(button);
            button.addActionListener(e -> handleNavigation(buttonText));
            panel.add(button);
            panel.add(Box.createVerticalStrut(10));
        }

        return panel;
    }

    private JPanel createStudyLoadPanel() {
        JPanel panel = new JPanel(new BorderLayout(15, 15));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Header
        JLabel headerLabel = new JLabel("Current Study Load", SwingConstants.LEFT);
        headerLabel.setFont(UIConstants.TITLE_FONT);
        headerLabel.setForeground(UIConstants.PRIMARY_COLOR);
        panel.add(headerLabel, BorderLayout.NORTH);

        // Table
        String[] columns = {"Course Code", "Description", "Grade"};
        ArrayList<String> enrolled = student.getEnrolledSubjects();
        
         DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        for (String courseCode : enrolled)
            Subject subject = CurriculumData.getSubject(courseCode);
            Double grade = student.getGrade(courseCode);

             model.addRow(new Object[]{
                courseCode,
                subject.getDescription(),
                grade != null ? String.format("%.1f", grade) : "Not taken"
            });
        }
        JTable table = new JTable(model);
        table.setFont(UIConstants.REGULAR_FONT);
        table.getTableHeader().setFont(UIConstants.REGULAR_FONT);
        table.setRowHeight(30);
        table.setShowGrid(true);
        table.setGridColor(UIConstants.SECONDARY_COLOR);
        JScrollPane tableScroll = new JScrollPane(table);
        tableScroll.setBorder(BorderFactory.createLineBorder(UIConstants.SECONDARY_COLOR));
        panel.add(tableScroll, BorderLayout.CENTER);
        
        return panel;
    }

    private JPanel createAddSubjectPanel() {
        JPanel panel = new JPanel(new BorderLayout(15, 15));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Get next semester's subjects
        int nextSemester = student.getNextSemester();
        int nextYear = student.getNextYearLevel();
        JLabel headerLabel = new JLabel(
            String.format("Available Subjects for Year %d, Semester %d", 
                nextYear, nextSemester), 
            SwingConstants.LEFT
        );
        headerLabel.setFont(UIConstants.TITLE_FONT);
        headerLabel.setForeground(UIConstants.PRIMARY_COLOR);
        panel.add(headerLabel, BorderLayout.NORTH);

        ArrayList<Subject> availableSubjects = 
            CurriculumData.getSubjectsForYearAndSem(nextYear, nextSemester);

        DefaultListModel<Subject> listModel = new DefaultListModel<>();
        for (Subject subject : availableSubjects) {
            if (arePrerequisitesMet(subject)) {
                listModel.addElement(subject);
            }
        }

        JList<Subject> subjectList = new JList<>(listModel);
        subjectList.setFont(UIConstants.REGULAR_FONT);
        subjectList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        
        JScrollPane scrollPane = new JScrollPane(subjectList);
        scrollPane.setBorder(BorderFactory.createLineBorder(UIConstants.SECONDARY_COLOR));
        panel.add(scrollPane, BorderLayout.CENTER);

        JButton addButton = new JButton("Enroll in Selected Subjects");
        styleButton(addButton);
        addButton.addActionListener(e -> {
            for (Subject subject : subjectList.getSelectedValuesList()) {
                student.enrollSubject(subject.getCourseCode());
            }
            // Refresh panels
            refreshPanels();
            
            // Show success message
            JOptionPane.showMessageDialog(this,
                "Successfully enrolled in selected subjects",
                "Success",
                JOptionPane.INFORMATION_MESSAGE);
                
            // Switch to study load view
            cardLayout.show(contentPanel, "STUDY_LOAD");
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setOpaque(false);
        buttonPanel.add(addButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        return panel;
    }

    private JPanel createRemoveSubjectPanel() {
        JPanel panel = new JPanel(new BorderLayout(15, 15));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel headerLabel = new JLabel("Remove Enrolled Subjects", SwingConstants.LEFT);
        headerLabel.setFont(UIConstants.TITLE_FONT);
        headerLabel.setForeground(UIConstants.PRIMARY_COLOR);
        panel.add(headerLabel, BorderLayout.NORTH);

        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (String courseCode : student.getEnrolledSubjects()) {
            Subject subject = CurriculumData.getSubject(courseCode);
            listModel.addElement(courseCode + " - " + subject.getDescription());
        }

        JList<String> enrolledList = new JList<>(listModel);
        enrolledList.setBorder(BorderFactory.createLineBorder(UIConstants.SECONDARY_COLOR));
        enrolledList.setFont(UIConstants.REGULAR_FONT);
        panel.add(new JScrollPane(enrolledList), BorderLayout.CENTER);

        JButton removeButton = new JButton("Remove Selected Subjects");
        styleButton(removeButton);
        removeButton.addActionListener(e -> {
            if (enrolledList.getSelectedValue() != null) {
                int choice = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to remove these subjects?",
                    "Confirm Removal",
                    JOptionPane.YES_NO_OPTION);
                    
                if (choice == JOptionPane.YES_OPTION) {
                    for (String item : enrolledList.getSelectedValuesList()) {
                        String courseCode = item.split(" - ")[0];
                        student.removeSubject(courseCode);
                    }
                    refreshPanels();
                    cardLayout.show(contentPanel, "STUDY_LOAD");
                }
            }
        });
        panel.add(removeButton, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createRecommendedSubjectsPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(Color.WHITE);
        panel.setBorder(UIConstants.PANEL_BORDER);

        JLabel headerLabel = new JLabel("Recommended Subjects", SwingConstants.CENTER);
        headerLabel.setFont(UIConstants.TITLE_FONT);
        headerLabel.setForeground(UIConstants.PRIMARY_COLOR);
        panel.add(headerLabel, BorderLayout.NORTH);

        DefaultListModel<String> listModel = new DefaultListModel<>();
        
        // Add failed subjects
        for (String courseCode : student.getEnrolledSubjects()) {
            if (student.hasFailedSubject(courseCode)) {
                Subject subject = CurriculumData.getSubject(courseCode);
                listModel.addElement(courseCode + " - " + subject.getDescription() + " (Failed)");
            }
        }

        // Add subjects with met prerequisites
        int nextSemester = student.getNextSemester();
        int nextYear = student.getNextYearLevel();
        ArrayList<Subject> nextSubjects = 
            CurriculumData.getSubjectsForYearAndSem(nextYear, nextSemester);

        for (Subject subject : nextSubjects) {
            if (arePrerequisitesMet(subject) && 
                !student.getEnrolledSubjects().contains(subject.getCourseCode())) {
                listModel.addElement(subject.getCourseCode() + " - " + 
                                   subject.getDescription() + " (Available)");
            }
        }

        JList<String> recommendedList = new JList<>(listModel);
        recommendedList.setFont(UIConstants.REGULAR_FONT);
        panel.add(new JScrollPane(recommendedList), BorderLayout.CENTER);

        return panel;
    }

    private boolean arePrerequisitesMet(Subject subject) {
        for (String prereq : subject.getPrerequisites()) {
            if (!student.hasPassedSubject(prereq)) {
                return false;
            }
        }
        return true;
    }

    private void styleNavigationButton(JButton button) {
        button.setBackground(UIConstants.PRIMARY_COLOR);
        button.setForeground(Color.WHITE);
        button.setFont(UIConstants.REGULAR_FONT);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(180, 40));
    }

    private void styleButton(JButton button) {
        button.setBackground(UIConstants.PRIMARY_COLOR);
        button.setForeground(Color.WHITE);
        button.setFont(UIConstants.REGULAR_FONT);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(200, 40));
    }

    private void handleNavigation(String buttonText) {
        switch (buttonText) {
            case "Study Load":
                cardLayout.show(contentPanel, "STUDY_LOAD");
                break;
            case "Add Subject":
                cardLayout.show(contentPanel, "ADD_SUBJECT");
                break;
            case "Remove Subject":
                cardLayout.show(contentPanel, "REMOVE_SUBJECT");
                break;
            case "Recommended Subjects":
                cardLayout.show(contentPanel, "RECOMMENDED");
                break;
            case "Logout":
                new LoginFrame().setVisible(true);
                this.dispose();
                break;
        }
    }
}
