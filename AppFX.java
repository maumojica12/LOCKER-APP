import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.Label;

public class AppFX extends Application {

    // Window Size
    private static final double INITIAL_WIDTH = 1300;
    private static final double INITIAL_HEIGHT = 700;
    
    // Define a padding value to move the menus down from the top.
    private static final double VERTICAL_OFFSET = 550; 
    
    // Define button size constants for easy adjustment
    private static final double BUTTON_WIDTH = 300; // Increased width slightly
    private static final double BUTTON_HEIGHT = 50; // New: Added height

    //Main Menu
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
        VBox leftMenu = mainMenuButton(menuLabels, 0, 5,primaryStage); 
        
        // --- 2. Create Right Menu (VBox) ---
        VBox rightMenu = mainMenuButton(menuLabels, 5, 10,primaryStage); 
        
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
     * FOR MAIN MENU ACTION
     */
    private static VBox mainMenuButton(String[] labels, int start, int end,Stage primaryStage) {
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

            switch (labels[i]) {
                case "MANAGE USERS":
                    btn.setOnAction(e -> handleManageUsers(primaryStage));
                    break;
                case "MANAGE LOCKER TYPES":
                    btn.setOnAction(e -> handleManageLockerTypes(primaryStage));
                    break;
                case "MANAGE LOCKERS":
                    btn.setOnAction(e -> handleManageLockers());
                    break;
                case "MANAGE LOCKER LOCATIONS":
                    btn.setOnAction(e -> handleManageLocations(primaryStage));
                    break;
                case "BOOK/MANAGE RESERVATIONS":
                    btn.setOnAction(e -> handleBooking());
                    break;
                case "MANAGE CANCELLATIONS":
                    btn.setOnAction(e -> handleCancellations());
                    break;
                case "MANAGE PAYMENTS":
                    btn.setOnAction(e -> handlePaymentReport(primaryStage));
                    break;
                case "MANAGE TRANSFERS":
                    btn.setOnAction(e -> handleTransfers(primaryStage));
                    break;
                case "GENERATE REPORTS":
                    btn.setOnAction(e -> handleReports());
                    break;
                case "EXIT":
                    btn.setOnAction(e -> handleExit(primaryStage));
                    break;
            }
            
            menu.getChildren().add(btn);
        }
        return menu;
    }

   // Updated handleManageUsers function:
private static void handleManageUsers(Stage stage) {
    stage.setTitle("Luggage Locker Booking System - User Management");
    Image userMenuBG = new Image(AppFX.class.getResourceAsStream("userMenu.jpg"));
    ImageView backgroundView = new ImageView(userMenuBG);
    
    String[] menuLabels = {
        "ADD USER", "VIEW ALL USERS", "SEARCH USER BY ID/NAME", 
        "UPDATE USER", "DELETE USER", "RETURN TO MAIN MENU"
    };
    VBox leftMenu = userMenuButton(menuLabels, 0, 3, stage); //left menu
    VBox rightMenu = userMenuButton(menuLabels, 3, 6, stage);  // right menu
    StackPane root = new StackPane();
    root.getChildren().addAll(backgroundView, leftMenu, rightMenu);
    StackPane.setAlignment(leftMenu, Pos.CENTER_LEFT);
    StackPane.setAlignment(rightMenu, Pos.CENTER_RIGHT);
    Scene scene = new Scene(root, INITIAL_WIDTH, INITIAL_HEIGHT); 

    backgroundView.fitWidthProperty().bind(scene.widthProperty());
    backgroundView.fitHeightProperty().bind(scene.heightProperty());
    backgroundView.setPreserveRatio(false);
    stage.setScene(scene);
    stage.show();
}

//FOR USER MENU ACTION
private static VBox userMenuButton(String[] labels, int start, int end, Stage stage) {
    VBox menu = new VBox(25); 
    menu.setPadding(new Insets(450, 320, 320, 320)); 
    menu.setMaxWidth(VBox.USE_PREF_SIZE);

    for (int i = start; i < end; i++) {
        if (i >= labels.length) break; 
        
        String label = labels[i];
        Button btn = new Button(label);

        btn.setMinWidth(BUTTON_WIDTH); 
        btn.setMinHeight(BUTTON_HEIGHT); 
        btn.setMaxWidth(BUTTON_WIDTH); 
        btn.setAlignment(Pos.CENTER); 
        btn.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;"); 
        
        switch (label) {
                case "ADD USER":
                    btn.setOnAction(e -> registerUser(stage));
                    break;
                case "VIEW ALL USERS":
                    btn.setOnAction(e -> System.out.println("-> Action: Show All Users Table"));
                    break;
                case "SEARCH USER BY ID/NAME":
                    btn.setOnAction(e -> System.out.println("-> Action: Show Search Dialog"));
                    break;
                case "UPDATE USER":
                    btn.setOnAction(e -> System.out.println("-> Action: Show Update User Form"));
                    break;
                case "DELETE USER":
                    btn.setOnAction(e -> System.out.println("-> Action: Show Delete Confirmation"));
                    break;
                case "RETURN TO MAIN MENU":
                    btn.setOnAction(e -> {
                        new AppFX().start(stage); 
                    });
                    break;
            }
            menu.getChildren().add(btn);
        }
    return menu;
}

private static void registerUser(Stage stage) {
    // Background image
    Image backgroundImage = new Image(AppFX.class.getResourceAsStream("registerUser.jpg"));
    ImageView backgroundView = new ImageView(backgroundImage);

    // Root layout
    StackPane root = new StackPane();
    root.getChildren().add(backgroundView);

    // --- Form Elements ---
    VBox form = new VBox(15); // spacing between elements
    form.setAlignment(Pos.TOP_CENTER);
    
    // Left padding moves the entire form block to the right. (e.g., 400)
    final double FORM_LEFT_OFFSET = 400; 
    form.setPadding(new Insets(220, 0, 0, FORM_LEFT_OFFSET)); 
    
    // Field size constants
    double FIELD_WIDTH = 500; 
    double FIELD_HEIGHT = 50;
    double BUTTON_WIDTH = 240; 
    double BUTTON_HEIGHT = 50;
    
    // --- Text Fields ---
    TextField firstNameField = new TextField();
    firstNameField.setPromptText("First Name");
    firstNameField.setPrefSize(FIELD_WIDTH, FIELD_HEIGHT);
    firstNameField.setMaxWidth(FIELD_WIDTH); 

    TextField lastNameField = new TextField();
    lastNameField.setPromptText("Last Name");
    lastNameField.setPrefSize(FIELD_WIDTH, FIELD_HEIGHT);
    lastNameField.setMaxWidth(FIELD_WIDTH);

    TextField contactField = new TextField();
    contactField.setPromptText("Contact Number");
    contactField.setPrefSize(FIELD_WIDTH, FIELD_HEIGHT);
    contactField.setMaxWidth(FIELD_WIDTH);

    TextField emailField = new TextField();
    emailField.setPromptText("Email Address");
    emailField.setPrefSize(FIELD_WIDTH, FIELD_HEIGHT);
    emailField.setMaxWidth(FIELD_WIDTH);

    // --- Buttons ---
    Button registerBtn = new Button("REGISTER");
    registerBtn.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT); 
    registerBtn.setStyle("-fx-font-weight: bold; -fx-background-color: #2ecc71; -fx-text-fill: white;");

    Button backBtn = new Button("BACK TO USER MENU");
    backBtn.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT); 
    backBtn.setStyle("-fx-font-weight: bold; -fx-background-color: #e74c3c; -fx-text-fill: white;");

    // --- Button Container (HBox) ---
    HBox buttonContainer = new HBox(10); // spacing between buttons
    buttonContainer.setAlignment(Pos.CENTER);
    buttonContainer.getChildren().addAll(registerBtn, backBtn);
    HBox.setMargin(buttonContainer, new Insets(0, 0, 0, -120)); 

    // 1. ERROR/VALIDATION LABEL
    Label errorMessageLabel = new Label();
    errorMessageLabel.setMaxWidth(FIELD_WIDTH);
    errorMessageLabel.setAlignment(Pos.CENTER);
    VBox.setMargin(errorMessageLabel, new Insets(10, 0, 0, -220)); 
    errorMessageLabel.setVisible(false); // Start hidden
    
    // 2. SUCCESS MESSAGE LABEL
    Label successMessageLabel = new Label();
    successMessageLabel.setMaxWidth(FIELD_WIDTH); 
    successMessageLabel.setAlignment(Pos.CENTER);
    // Move lower (Top: 30) and more to the left (Left: -220). ADJUST -220 for position.
    VBox.setMargin(successMessageLabel, new Insets(-40, 0, 0, -290)); 
    successMessageLabel.setVisible(false); // Start hidden

    // Add both labels to the form
    form.getChildren().addAll(firstNameField, lastNameField, contactField, emailField, buttonContainer, errorMessageLabel, successMessageLabel);
    root.getChildren().add(form);

    double INITIAL_WIDTH = 1300;
    double INITIAL_HEIGHT = 700;
    Scene scene = new Scene(root, INITIAL_WIDTH, INITIAL_HEIGHT);

    // Bind background to fill window
    backgroundView.fitWidthProperty().bind(scene.widthProperty());
    backgroundView.fitHeightProperty().bind(scene.heightProperty());
    backgroundView.setPreserveRatio(false);

    stage.setScene(scene);
    stage.setTitle("Register User");
    stage.show();

    // --- Button Actions ---
    registerBtn.setOnAction(e -> {
        // Clear and hide both labels at the start
        errorMessageLabel.setVisible(false);
        successMessageLabel.setVisible(false);

        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String contact = contactField.getText().trim();
        String email = emailField.getText().trim();

        if (firstName.isEmpty() || lastName.isEmpty()) {
            // Display required fields error using the ERROR label
            errorMessageLabel.setText("First and Last Names are Required!");
            errorMessageLabel.setStyle("-fx-text-fill: yellow;-fx-font-size: 16px; -fx-font-weight: bold;");
            errorMessageLabel.setVisible(true);
            return;
        }

        User newUser = new User(firstName, lastName, contact, email);
        UserDAO userDAO = new UserDAO();
        int generatedId = userDAO.addUser(newUser); 

        if (generatedId > 0) {
            // Display success message using the SUCCESS label
            successMessageLabel.setText("User: "+ firstName + " " + lastName + "\nUser ID: " + generatedId + "\nRegistered Successfully!");
            successMessageLabel.setStyle("-fx-text-fill:yellow; -fx-font-size: 16px; -fx-font-weight: bold;"); // Changed color for clear differentiation
            successMessageLabel.setVisible(true);
            
            firstNameField.clear();
            lastNameField.clear();
            contactField.clear();
            emailField.clear();
        } else {
            // Display database failure error using the ERROR label
            errorMessageLabel.setText("Registration failed. Check database connection.");
            errorMessageLabel.setStyle("-fx-text-fill: red; -fx-font-size: 16px; -fx-font-weight: bold;");
            errorMessageLabel.setVisible(true);
        }
    });
    backBtn.setOnAction(e -> handleManageUsers(stage));
}

private static void handleManageLocations(Stage stage) {
    stage.setTitle("Luggage Locker Booking System - Location Management");
    Image locationBG = new Image(AppFX.class.getResourceAsStream("locationMenu.jpg"));
    ImageView backgroundView = new ImageView(locationBG);

    String[] menuLabels = {
            "VIEW ALL LOCKER LOCATION",
            "VIEW ALL AVAILABLE LOCKER IN LOCATION",
            "VIEW LOCKERS IN LOCATION",
            "RETURN TO MAIN MENU"
    };

    VBox leftMenu = locationMenuButton(menuLabels, 0, 2, stage); // left side
    VBox rightMenu = locationMenuButton(menuLabels, 2, 4, stage); // right side

    StackPane root = new StackPane();
    root.getChildren().addAll(backgroundView, leftMenu, rightMenu);
    StackPane.setAlignment(leftMenu, Pos.CENTER_LEFT);
    StackPane.setAlignment(rightMenu, Pos.CENTER_RIGHT);

    Scene scene = new Scene(root, INITIAL_WIDTH, INITIAL_HEIGHT);
    backgroundView.fitWidthProperty().bind(scene.widthProperty());
    backgroundView.fitHeightProperty().bind(scene.heightProperty());
    backgroundView.setPreserveRatio(false);
    stage.setScene(scene);
    stage.show();

}

private static VBox locationMenuButton(String[] labels, int start, int end, Stage stage) {
    VBox menu = new VBox(25);
    menu.setPadding(new Insets(450, 320, 320, 320));
    menu.setMaxWidth(VBox.USE_PREF_SIZE);

    for (int i = start; i < end; i++) {
        if (i >= labels.length) break;
        Button btn = new Button(labels[i]);
        btn.setMinWidth(BUTTON_WIDTH);
        btn.setMinHeight(BUTTON_HEIGHT);
        btn.setMaxWidth(BUTTON_WIDTH);
        btn.setAlignment(Pos.CENTER);
        btn.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");

        switch (labels[i]) {
            case "VIEW ALL LOCKER LOCATION":
                btn.setOnAction(e -> System.out.println("-> Action: Show All Locker Locations Table"));
                break;
            case "VIEW ALL AVAILABLE LOCKER IN LOCATION":
                btn.setOnAction(e -> System.out.println("-> Action: Show Available Lockers in All Locations"));
                break;
            case "VIEW LOCKERS IN LOCATION":
                btn.setOnAction(e -> System.out.println("-> Action: Show Lockers in Specific Location"));
                break;
            case "RETURN TO MAIN MENU":
                btn.setOnAction(e -> {
                    new AppFX().start(stage);
                });
                break;
        }
        menu.getChildren().add(btn);
    }
    return menu;
}

private static void handleManageLockerTypes(Stage stage) {
    stage.setTitle("Luggage Locker Booking System - Locker Type Management");    
    Image bg = new Image(AppFX.class.getResourceAsStream("lockerTypeMenu.jpg"));
    ImageView backgroundView = new ImageView(bg);

    String[] menuLabels = {
        "VIEW ALL LOCKER TYPES",
        "SEARCH LOCKER TYPE",
        "RETURN TO MAIN MENU"
    };

    VBox leftMenu = lockerTypeMenuButton(menuLabels, 0, 2, stage);
    VBox rightMenu = lockerTypeMenuButton(menuLabels, 2, 3, stage);

    StackPane root = new StackPane();
    root.getChildren().addAll(backgroundView, leftMenu, rightMenu);
    StackPane.setAlignment(leftMenu, Pos.CENTER_LEFT);
    StackPane.setAlignment(rightMenu, Pos.CENTER_RIGHT);

    Scene scene = new Scene(root, INITIAL_WIDTH, INITIAL_HEIGHT);
    backgroundView.fitWidthProperty().bind(scene.widthProperty());
    backgroundView.fitHeightProperty().bind(scene.heightProperty());
    backgroundView.setPreserveRatio(false);

    stage.setScene(scene);
    stage.show();
}

private static VBox lockerTypeMenuButton(String[] labels, int start, int end, Stage stage){
    VBox menu = new VBox(25);
    menu.setPadding(new Insets(450, 320, 320, 320));
    menu.setMaxWidth(VBox.USE_PREF_SIZE);

    for(int i = start; i < end; i++) {
        if(i >= labels.length) break;
        Button btn = new Button(labels[i]);
        btn.setMinWidth(BUTTON_WIDTH);
        btn.setMinHeight(BUTTON_HEIGHT);
        btn.setMaxWidth(BUTTON_WIDTH);
        btn.setAlignment(Pos.CENTER);
        btn.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");

        switch(labels[i]){
            case "VIEW ALL LOCKER TYPES":
                btn.setOnAction(e -> System.out.println("-> Action: Display All Locker Types Table"));
                break;
            case "SEARCH LOCKER TYPE":
                btn.setOnAction(e -> System.out.println("-> Action: Search Locker Type by ID/Size"));
                break;
            case "RETURN TO MAIN MENU":
                btn.setOnAction(e -> new AppFX().start(stage));
                break;
        }
        menu.getChildren().add(btn);
    }
    return menu;
}

private static void handleManageLockers(){

}

private static void handleManageLockerLocations(){

    }

private static void handleBooking(){

}

private static void handleCancellations(){

}


     private static void handlePaymentReport(Stage stage) {
        stage.setTitle("Luggage Locker Booking System - Payment and Release Management");
        Image locationBG = new Image(AppFX.class.getResourceAsStream("paymentMenu.jpg"));
        ImageView backgroundView = new ImageView(locationBG);

        String[] menuLabels = {
                "VIEW ALL PAYMENT",
                "RELEASE LOCKER (PROCESS PAYMENT)",
                "SEARCH PAYMENT BY ID",
                "RETURN TO MAIN MENU"
        };

        VBox leftMenu = paymentMenuButton(menuLabels, 0, 2, stage); // left side
        VBox rightMenu = paymentMenuButton(menuLabels, 2, 4, stage); // right side

        StackPane root = new StackPane();
        root.getChildren().addAll(backgroundView, leftMenu, rightMenu);
        StackPane.setAlignment(leftMenu, Pos.CENTER_LEFT);
        StackPane.setAlignment(rightMenu, Pos.CENTER_RIGHT);

        Scene scene = new Scene(root, INITIAL_WIDTH, INITIAL_HEIGHT);
        backgroundView.fitWidthProperty().bind(scene.widthProperty());
        backgroundView.fitHeightProperty().bind(scene.heightProperty());
        backgroundView.setPreserveRatio(false);
        stage.setScene(scene);
        stage.show();

    }

    private static VBox paymentMenuButton(String[] labels, int start, int end, Stage stage) {
        VBox menu = new VBox(25);
        menu.setPadding(new Insets(450, 320, 320, 320));
        menu.setMaxWidth(VBox.USE_PREF_SIZE);

        for (int i = start; i < end; i++) {
            if (i >= labels.length) break;
            Button btn = new Button(labels[i]);
            btn.setMinWidth(BUTTON_WIDTH);
            btn.setMinHeight(BUTTON_HEIGHT);
            btn.setMaxWidth(BUTTON_WIDTH);
            btn.setAlignment(Pos.CENTER);
            btn.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");

            switch (labels[i]) {
                case "VIEW ALL PAYMENT":
                    btn.setOnAction(e -> {
                        // Opens the table-based Payment Viewer window
                        PaymentViewer viewer = new PaymentViewer();
                        try {
                            viewer.start(new Stage());
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    });
                    break;

                case "RELEASE LOCKER (PROCESS PAYMENT)":
                    btn.setOnAction(e -> {
                        // TODO: open your payment processing / locker release form
                        System.out.println("-> Action: Process Payment and Release Locker");
                    });
                    break;

                case "SEARCH PAYMENT BY ID":
                    btn.setOnAction(e -> {
                        // TODO: open your Search Payment by ID window
                        System.out.println("-> Action: Search Payment by ID");
                    });
                    break;

                case "RETURN TO MAIN MENU":
                    btn.setOnAction(e -> {
                        new AppFX().start(stage);
                    });
                    break;
            }
            menu.getChildren().add(btn);
        }
        return menu;
    }

private static void handleTransfers(Stage stage) {
    stage.setTitle("Luggage Locker Booking System - Locker Transfer Management");
    Image bg = new Image(AppFX.class.getResourceAsStream("lockerTransferMenu.jpg"));
    ImageView backgroundView = new ImageView(bg);

    String[] menuLabels = {
        "LOCKER TRANSFER",
        "VIEW ALL TRANSFERS",
        "SEARCH TRANSFER BY ID",
        "RETURN TO MAIN MENU"
    };

    VBox leftMenu = lockerTransferMenuButton(menuLabels, 0, 2, stage);
    VBox rightMenu = lockerTransferMenuButton(menuLabels, 2, 4, stage);

    StackPane root = new StackPane();
    root.getChildren().addAll(backgroundView, leftMenu, rightMenu);
    StackPane.setAlignment(leftMenu, Pos.CENTER_LEFT);
    StackPane.setAlignment(rightMenu, Pos.CENTER_RIGHT);

    Scene scene = new Scene(root, INITIAL_WIDTH, INITIAL_HEIGHT);
    backgroundView.fitWidthProperty().bind(scene.widthProperty());
    backgroundView.fitHeightProperty().bind(scene.heightProperty());
    backgroundView.setPreserveRatio(false);

    stage.setScene(scene);
    stage.show();
}

private static VBox lockerTransferMenuButton(String[] labels, int start, int end, Stage stage) {
    VBox menu = new VBox(25);
    menu.setPadding(new Insets(450, 320, 320, 320));
    menu.setMaxWidth(VBox.USE_PREF_SIZE);

    for(int i = start; i < end; i++) {
        if(i >= labels.length) break;
        Button btn = new Button(labels[i]);
        btn.setMinWidth(BUTTON_WIDTH);
        btn.setMinHeight(BUTTON_HEIGHT);
        btn.setMaxWidth(BUTTON_WIDTH);
        btn.setAlignment(Pos.CENTER);
        btn.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");

        switch(labels[i]){
            case "LOCKER TRANSFER":
                btn.setOnAction(e -> System.out.println("-> Action: Perform Locker Transfer"));
                break;
            case "VIEW ALL TRANSFERS":
                btn.setOnAction(e -> System.out.println("-> Action: Display All Transfers Table"));
                break;
            case "SEARCH TRANSFER BY ID":
                btn.setOnAction(e -> System.out.println("-> Action: Search Transfer by ID"));
                break;
            case "RETURN TO MAIN MENU":
                btn.setOnAction(e -> new AppFX().start(stage));
                break;
        }
        menu.getChildren().add(btn);
    }
    return menu;
}
private static void handleReports(){

}

     /**
     * shows it for 5 seconds, and then terminates the application.
     * @param stage The primary stage of the application.
     */
    private static void handleExit(Stage stage) {        
        StackPane goodbyeRoot = new StackPane();
        Image goodbyeImage = new Image(AppFX.class.getResourceAsStream("goodbye.jpg"));
        ImageView goodbyeView = new ImageView(goodbyeImage);
        goodbyeView.fitWidthProperty().bind(stage.widthProperty());
        goodbyeView.fitHeightProperty().bind(stage.heightProperty());
        goodbyeView.setPreserveRatio(false);
        goodbyeRoot.getChildren().add(goodbyeView);
        Scene goodbyeScene = new Scene(goodbyeRoot, INITIAL_WIDTH, INITIAL_HEIGHT);
        stage.setScene(goodbyeScene);
        stage.centerOnScreen(); 

        // 2. Set 5-second delay before closing
        PauseTransition delay = new PauseTransition(Duration.seconds(5)); // 5-second delay
        
        // dispose after 5 sec delay
        delay.setOnFinished(event -> {
            stage.close();
        });

        delay.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}