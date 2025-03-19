import javax.swing.*;
import java.awt.*;

public class RegistrationFrame extends JFrame {
    private static final long serialVersionUID = 3L;
    private JTextField nameField, idField, programField, emailField;
    private JComboBox<String> yearCombo, semesterCombo;
    private JPasswordField passwordField;

    public RegistrationFrame() {
        setTitle("Student Registration");
        setSize(500, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(UIConstants.PANEL_BORDER);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Header
        JLabel headerLabel = new JLabel("Student Registration", SwingConstants.CENTER);
        headerLabel.setFont(UIConstants.HEADER_FONT);
        headerLabel.setForeground(UIConstants.PRIMARY_COLOR);
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        mainPanel.add(headerLabel, gbc);

        // Form Fields
        nameField = addFormField(mainPanel, gbc, "Name:", 1);
        idField = addFormField(mainPanel, gbc, "ID Number:", 2);
        programField = addFormField(mainPanel, gbc, "Program:", 3);
        
        // Year Level Combo
        String[] years = {"1st Year", "2nd Year", "3rd Year", "4th Year"};
        yearCombo = new JComboBox<>(years);
        addFormComponent(mainPanel, gbc, "Year Level:", yearCombo, 4);

        // Semester Combo
        String[] semesters = {"First Semester", "Second Semester"};
        semesterCombo = new JComboBox<>(semesters);
        addFormComponent(mainPanel, gbc, "Semester:", semesterCombo, 5);

        emailField = addFormField(mainPanel, gbc, "Email:", 6);
        passwordField = new JPasswordField(20);
        addFormComponent(mainPanel, gbc, "Password:", passwordField, 7);

        // Register Button
        JButton registerButton = new JButton("Register");
        styleButton(registerButton);
        registerButton.addActionListener(e -> handleRegistration());
        
        gbc.gridx = 0; gbc.gridy = 8; gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 10, 10, 10);
        mainPanel.add(registerButton, gbc);

        add(mainPanel);
    }

    private JTextField addFormField(JPanel panel, GridBagConstraints gbc, 
                                  String label, int row) {
        JTextField field = new JTextField(20);
        addFormComponent(panel, gbc, label, field, row);
        return field;
    }

    private void addFormComponent(JPanel panel, GridBagConstraints gbc, 
                                String labelText, JComponent component, int row) {
        gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 1;
        JLabel label = new JLabel(labelText);
        label.setFont(UIConstants.REGULAR_FONT);
        panel.add(label, gbc);

        gbc.gridx = 1;
        component.setPreferredSize(new Dimension(200, 30));
        if (component instanceof JTextField || component instanceof JPasswordField) {
            component.setBorder(UIConstants.createRoundedBorder());
        }
        panel.add(component, gbc);
    }

    private void styleButton(JButton button) {
        button.setBackground(UIConstants.PRIMARY_COLOR);
        button.setForeground(Color.WHITE);
        button.setFont(UIConstants.REGULAR_FONT);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(200, 40));
    }

    private void handleRegistration() {
        String name = nameField.getText();
        String id = idField.getText();
        String program = programField.getText();
        int year = yearCombo.getSelectedIndex() + 1;
        int semester = semesterCombo.getSelectedIndex() + 1;
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());

        if (name.isEmpty() || id.isEmpty() || program.isEmpty() || 
            email.isEmpty() || password.isEmpty()) {
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
