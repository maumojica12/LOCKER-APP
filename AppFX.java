import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AppFX extends Application {

    // Window Size
    private static final double INITIAL_WIDTH = 1300;
    private static final double INITIAL_HEIGHT = 700;
    
    // Define a padding value to move the menus down from the top.
    private static final double VERTICAL_OFFSET = 550; 
    
    // Define button size constants for easy adjustment
    private static final double BUTTON_WIDTH = 300; // Increased width slightly
    private static final double BUTTON_HEIGHT = 50; // New: Added height

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Luggage Locker Booking System");

        // bbg image
        Image backgroundImage = new Image(getClass().getResourceAsStream("main.jpg"));
        ImageView backgroundView = new ImageView(backgroundImage);
        
        // Define button labels corresponding to your console menu
        String[] menuLabels = {
            "MANAGE USERS", "MANAGE LOCKER TYPES", "MANAGE LOCKERS", 
            "MANAGE LOCKER LOCATIONS", "BOOK/MANAGE RESERVATIONS", "MANAGE CANCELLATIONS", 
            "MANAGE PAYMENTS", "MANAGE TRANSFERS", "GENERATE REPORTS", 
            "EXIT"
        };
        
        // --- 1. Create Left Menu (VBox) ---
        VBox leftMenu = createButtonMenu(menuLabels, 0, 5); 
        
        // --- 2. Create Right Menu (VBox) ---
        VBox rightMenu = createButtonMenu(menuLabels, 5, 10); 
        
        // --- 3. Main Layout (StackPane) ---
        StackPane root = new StackPane();
        root.getChildren().addAll(backgroundView, leftMenu, rightMenu);
        
        // 4. Position the Menus
        StackPane.setAlignment(leftMenu, Pos.CENTER_LEFT);
        StackPane.setAlignment(rightMenu, Pos.CENTER_RIGHT);

        // 5. Create the Scene and Bind Image
        Scene scene = new Scene(root, INITIAL_WIDTH, INITIAL_HEIGHT); 

        backgroundView.fitWidthProperty().bind(scene.widthProperty());
        backgroundView.fitHeightProperty().bind(scene.heightProperty());
        backgroundView.setPreserveRatio(false);

        // 6. Set and show the Stage
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    /**
     * Helper method to create a VBox containing a subset of buttons.
     */
    private static VBox createButtonMenu(String[] labels, int start, int end) {
        // Removed 'Pos alignment' argument as it's not strictly necessary for button text alignment 
        // when using CENTER alignment for the text itself.
        
        VBox menu = new VBox(20); // Spacing between buttons
        
        // (Top, Right, Bottom, Left) - 50px padding from the sides
        menu.setPadding(new Insets(VERTICAL_OFFSET, 320, 320, 320)); 
        menu.setMaxWidth(VBox.USE_PREF_SIZE);

        for (int i = start; i < end; i++) {
            if (i >= labels.length) break; 
            
            String label = labels[i];
            Button btn = new Button(label);
            
            // --- NEW: Increase Button Size ---
            btn.setMinWidth(BUTTON_WIDTH); 
            btn.setMinHeight(BUTTON_HEIGHT); // Set a minimum height
            btn.setMaxWidth(BUTTON_WIDTH); // Ensure it doesn't stretch past this size
            
            // --- NEW: Center Text ---
            // Pos.CENTER ensures the text is centered both horizontally and vertically.
            btn.setAlignment(Pos.CENTER); 
            btn.setStyle("-fx-font-weight: bold;");
            btn.setOnAction(e -> System.out.println(label + " clicked!")); 
            
            menu.getChildren().add(btn);
        }
        return menu;
    }

    public static void main(String[] args) {
        launch(args);
    }
}