import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class UIConstants {
    // Colors
    public static final Color PRIMARY_COLOR = new Color(51, 102, 204);
    public static final Color SECONDARY_COLOR = new Color(240, 240, 240);
    public static final Color SUCCESS_COLOR = new Color(46, 125, 50);
    public static final Color ERROR_COLOR = new Color(211, 47, 47);
    public static final Color TEXT_COLOR = new Color(33, 33, 33);
    public static final Color LIGHT_TEXT_COLOR = new Color(117, 117, 117);
    
    // Fonts
    public static final Font HEADER_FONT = new Font("Arial", Font.BOLD, 24);
    public static final Font TITLE_FONT = new Font("Arial", Font.BOLD, 18);
    public static final Font REGULAR_FONT = new Font("Arial", Font.PLAIN, 14);
    public static final Font SMALL_FONT = new Font("Arial", Font.PLAIN, 12);
    
    // Spacing
    public static final int PADDING = 10;
    public static final int LARGE_PADDING = 20;
    
    // Borders
    public static final Border PANEL_BORDER = BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(new Color(200, 200, 200)),
        BorderFactory.createEmptyBorder(PADDING, PADDING, PADDING, PADDING)
    );
    
    // Styling Methods
    public static Border createRoundedBorder() {
        return BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(PRIMARY_COLOR),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        );
    }
}
