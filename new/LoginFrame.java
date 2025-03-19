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
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(UIConstants.PANEL_BORDER);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Header
        JLabel headerLabel = new JLabel("Student Login", SwingConstants.CENTER);
        headerLabel.setFont(UIConstants.HEADER_FONT);
        headerLabel.setForeground(UIConstants.PRIMARY_COLOR);
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        mainPanel.add(headerLabel, gbc);

        // ID Number Field
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 1;
        JLabel idLabel = new JLabel("ID Number:");
        idLabel.setFont(UIConstants.REGULAR_FONT);
        mainPanel.add(idLabel, gbc);

        gbc.gridx = 1;
        idField = new JTextField(15);
        idField.setBorder(UIConstants.createRoundedBorder());
        mainPanel.add(idField, gbc);

        // Password Field
        gbc.gridx = 0; gbc.gridy = 2;
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(UIConstants.REGULAR_FONT);
        mainPanel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        passwordField = new JPasswordField(15);
        passwordField.setBorder(UIConstants.createRoundedBorder());
        mainPanel.add(passwordField, gbc);

        // Buttons Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setOpaque(false);

        JButton loginButton = new JButton("Login");
        styleButton(loginButton);
        loginButton.addActionListener(e -> handleLogin());

        JButton registerButton = new JButton("Register");
        styleButton(registerButton);
        registerButton.addActionListener(e -> {
            new RegistrationFrame().setVisible(true);
            this.dispose();
        });

        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        mainPanel.add(buttonPanel, gbc);

        add(mainPanel);
    }

    private void styleButton(JButton button) {
        button.setBackground(UIConstants.PRIMARY_COLOR);
        button.setForeground(Color.WHITE);
        button.setFont(UIConstants.REGULAR_FONT);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(100, 35));
    }

    private void handleLogin() {
        String id = idField.getText();
        String password = new String(passwordField.getPassword());

        Student student = students.get(id);
        if (student != null && student.getPassword().equals(password)) {
            new GradeInputFrame(student).setVisible(true);
            this.dispose();
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
