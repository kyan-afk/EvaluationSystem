

//import com.evaluation.models.CurriculumData;

public class Main {
    public static void main(String[] args) {
        // Initialize curriculum data
        CurriculumData.initializeCurriculum();

        // Start the application with the login frame
        javax.swing.SwingUtilities.invokeLater(() -> {
            LoginFrame loginFrame = new LoginFrame();
            loginFrame.setVisible(true);
        });
    }
}