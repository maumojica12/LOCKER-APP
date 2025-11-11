import java.util.ArrayList;
import java.util.List;
import javafx.scene.layout.BorderPane;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
                    btn.setOnAction(e -> handlePayments(primaryStage));
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
                    btn.setOnAction(e -> viewAllUser(stage));
                    break;
                case "SEARCH USER BY ID/NAME":
                    btn.setOnAction(e -> searchUser(stage));
                    break;
                case "UPDATE USER":
                    btn.setOnAction(e -> updateUser(stage));
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

private static void viewAllUser(Stage stage) {
    // --- Background setup ---
    Image backgroundImage = new Image(AppFX.class.getResourceAsStream("viewUsers.jpg"));
    ImageView backgroundView = new ImageView(backgroundImage);
    backgroundView.setPreserveRatio(false);

    StackPane root = new StackPane();
    double INITIAL_WIDTH = 1300;
    double INITIAL_HEIGHT = 700;
    Scene scene = new Scene(root, INITIAL_WIDTH, INITIAL_HEIGHT);

    backgroundView.fitWidthProperty().bind(scene.widthProperty());
    backgroundView.fitHeightProperty().bind(scene.heightProperty());
    root.getChildren().add(backgroundView);

    // --- Scrollable user list setup ---
    ScrollPane scrollPane = new ScrollPane();
    scrollPane.setFitToWidth(true);
    scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
    scrollPane.setPrefViewportHeight(500); // Adjust visible scroll area height

    VBox userList = new VBox(15);
    userList.setPadding(new Insets(10));
    userList.setAlignment(Pos.TOP_CENTER);

    // --- Load users from DB ---
    UserDAO userDAO = new UserDAO();
    List<User> users = new ArrayList<>();

    try {
        users = userDAO.searchUsersByName("");
    } catch (Exception e) {
        e.printStackTrace();
    }

    if (users.isEmpty()) {
        Label noUser = new Label("No users found in the database.");
        noUser.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        noUser.setTextFill(Color.WHITE);
        userList.getChildren().add(noUser);
    } else {
        for (User user : users) {
            VBox card = new VBox(5);
            card.setPadding(new Insets(15));
            card.setPrefWidth(900); // narrower than before for balance
            card.setStyle(
                "-fx-background-color: rgba(255,255,255,0.85); " +
                "-fx-background-radius: 12; " +
                "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.25), 10, 0, 0, 5);"
            );

            Label id = new Label("User ID: " + user.getUserID());
            Label name = new Label("Name: " + user.getFirstName() + " " + user.getLastName());
            Label contact = new Label("Contact: " + (user.getUserContact() != null ? user.getUserContact() : "N/A"));
            Label email = new Label("Email: " + (user.getUserEmail() != null ? user.getUserEmail() : "N/A"));

            id.setFont(Font.font("Arial", FontWeight.BOLD, 14));
            name.setFont(Font.font("Arial", 14));
            contact.setFont(Font.font("Arial", 14));
            email.setFont(Font.font("Arial", 14));

            id.setTextFill(Color.BLACK);
            name.setTextFill(Color.BLACK);
            contact.setTextFill(Color.BLACK);
            email.setTextFill(Color.BLACK);

            card.getChildren().addAll(id, name, contact, email);
            userList.getChildren().add(card);
        }
    }

    scrollPane.setContent(userList);

    // --- Back Button ---
    Button backBtn = new Button("Back to User Menu");
    backBtn.setFont(Font.font("Arial", FontWeight.BOLD, 16));
    backBtn.setPrefWidth(180); 
    backBtn.setPrefHeight(40);
    backBtn.setStyle("-fx-background-color: #003366; -fx-text-fill: white; -fx-background-radius: 8;");
    backBtn.setOnAction(e -> handleManageUsers(stage));

    // --- Layout positioning ---
    VBox content = new VBox(30, scrollPane, backBtn);
    content.setAlignment(Pos.TOP_CENTER);
    content.setPadding(new Insets(230, 20, 40, 20)); // scroll pane position lower

    root.getChildren().add(content);
    StackPane.setAlignment(content, Pos.TOP_CENTER);

    stage.setScene(scene);
    stage.setTitle("View All Users");
    stage.show();
}

private static void searchUser(Stage stage) {
    // --- Background setup ---
    Image backgroundImage = new Image(AppFX.class.getResourceAsStream("searchUser.jpg"));
    ImageView backgroundView = new ImageView(backgroundImage);
    backgroundView.setPreserveRatio(false);

    StackPane root = new StackPane();
    double INITIAL_WIDTH = 1300;
    double INITIAL_HEIGHT = 700;
    Scene scene = new Scene(root, INITIAL_WIDTH, INITIAL_HEIGHT);

    backgroundView.fitWidthProperty().bind(scene.widthProperty());
    backgroundView.fitHeightProperty().bind(scene.heightProperty());
    root.getChildren().add(backgroundView);

    // --- Search Input Fields + Button ---
    Label nameLabel = new Label("Search by Name:");
    nameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
    nameLabel.setTextFill(Color.BLACK);

    TextField nameField = new TextField();
    nameField.setPromptText("Enter user name");
    nameField.setPrefWidth(300);
    nameField.setMaxWidth(300);
    nameField.setStyle("-fx-pref-height: 50px; -fx-font-size: 20px;");

    Label idLabel = new Label("Search by User ID:");
    idLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
    idLabel.setTextFill(Color.BLACK);

    TextField idField = new TextField();
    idField.setPromptText("Enter user ID");
    idField.setPrefWidth(300);
    idField.setMaxWidth(300);
    idField.setStyle("-fx-pref-height: 50px; -fx-font-size: 20px;");

    Button searchBtn = new Button("Search");
    searchBtn.setFont(Font.font("Arial", FontWeight.BOLD, 14));
    searchBtn.setStyle("-fx-background-color: #003366; -fx-text-fill: white; -fx-background-radius: 8;");
    searchBtn.setPrefWidth(150);
    searchBtn.setPrefHeight(40);

    // --- Search Input Fields + Button ---
VBox searchBox = new VBox(10, nameLabel, nameField, idLabel, idField, searchBtn);
searchBox.setAlignment(Pos.TOP_LEFT);
searchBox.setPadding(new Insets(160, 0, 0, 120)); // top, right, bottom, left

// --- Scrollable Results ---
VBox userList = new VBox(15);
userList.setPadding(new Insets(10));
userList.setAlignment(Pos.TOP_CENTER);

ScrollPane scrollPane = new ScrollPane(userList);
scrollPane.setFitToWidth(true);
scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
scrollPane.setPrefViewportWidth(600);
scrollPane.setPrefViewportHeight(500);

// Put scrollPane in its own container to adjust position independently
VBox scrollContainer = new VBox(scrollPane);
scrollContainer.setAlignment(Pos.TOP_RIGHT); // keep it on the right
scrollContainer.setPadding(new Insets(150, 0, 0, 100)); 
// top=50 (so it’s a little lower than the very top)
// right=80 (distance from right edge)
// bottom=0, left=0

// --- Back Button ---
Button backBtn = new Button("Back to User Menu");
backBtn.setFont(Font.font("Arial", FontWeight.BOLD, 16));
backBtn.setPrefWidth(180);
backBtn.setPrefHeight(40);
backBtn.setStyle("-fx-background-color: #003366; -fx-text-fill: white; -fx-background-radius: 8;");
backBtn.setOnAction(e -> handleManageUsers(stage));

HBox mainArea = new HBox(50, searchBox, scrollContainer); // 50px spacing
mainArea.setAlignment(Pos.TOP_LEFT);
mainArea.setPadding(new Insets(50, 20, 0, 80)); // adjust top/left as needed

// Put backBtn in its own container to adjust position independently
HBox backContainer = new HBox(backBtn);
backContainer.setAlignment(Pos.CENTER_RIGHT); // adjust as needed
backContainer.setPadding(new Insets(20, 50, 0, 0));

// --- Combine everything in rootContent ---
VBox rootContent = new VBox(30, mainArea, backContainer);
rootContent.setAlignment(Pos.TOP_LEFT);
rootContent.setPadding(new Insets(50, 20, 40, 20));

root.getChildren().add(rootContent);
StackPane.setAlignment(rootContent, Pos.TOP_CENTER);

    stage.setScene(scene);
    stage.setTitle("Search User");
    stage.show();

    // --- Search Button Action ---
    searchBtn.setOnAction(e -> {
        userList.getChildren().clear(); // clear previous results
        UserDAO userDAO = new UserDAO();
        List<User> results = new ArrayList<>();

        String nameQuery = nameField.getText().trim();
        String idQuery = idField.getText().trim();

        try {
            if (!idQuery.isEmpty()) {
                try {
                    int id = Integer.parseInt(idQuery);
                    User user = userDAO.getUserById(id);
                    if (user != null) results.add(user);
                } catch (NumberFormatException ex) {
                    Label invalid = new Label("Invalid User ID format. Please enter a number.");
                    invalid.setFont(Font.font("Arial", FontWeight.BOLD, 14));
                    invalid.setTextFill(Color.RED);
                    userList.getChildren().add(invalid);
                    return;
                }
            } else {
                results = userDAO.searchUsersByName(nameQuery);
            }

            if (results.isEmpty()) {
                Label noResult = new Label("No users found matching your search.");
                noResult.setFont(Font.font("Arial", FontWeight.BOLD, 16));
                noResult.setTextFill(Color.BLACK);
                noResult.setPadding(new Insets(220,0, 0, 220)); // top, right, bottom, left
                userList.getChildren().add(noResult);
            } else {
                for (User user : results) {
                    VBox card = new VBox(5);
                    card.setPadding(new Insets(15));
                    card.setPrefWidth(900);
                    card.setStyle(
                        "-fx-background-color: rgba(255,255,255,0.85); " +
                        "-fx-background-radius: 12; " +
                        "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.25), 10, 0, 0, 5);"
                    );

                    Label idLbl = new Label("User ID: " + user.getUserID());
                    Label nameLbl = new Label("Name: " + user.getFirstName() + " " + user.getLastName());
                    Label contactLbl = new Label("Contact: " +
                            (user.getUserContact() != null ? user.getUserContact() : "N/A"));
                    Label emailLbl = new Label("Email: " +
                            (user.getUserEmail() != null ? user.getUserEmail() : "N/A"));

                    idLbl.setFont(Font.font("Arial", FontWeight.BOLD, 14));
                    nameLbl.setFont(Font.font("Arial", 14));
                    contactLbl.setFont(Font.font("Arial", 14));
                    emailLbl.setFont(Font.font("Arial", 14));

                    idLbl.setTextFill(Color.BLACK);
                    nameLbl.setTextFill(Color.BLACK);
                    contactLbl.setTextFill(Color.BLACK);
                    emailLbl.setTextFill(Color.BLACK);

                    card.getChildren().addAll(idLbl, nameLbl, contactLbl, emailLbl);
                    userList.getChildren().add(card);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    });
}

private static void updateUser(Stage stage) {
    // --- Background setup ---
    Image backgroundImage = new Image(AppFX.class.getResourceAsStream("updateUser.jpg"));
    ImageView backgroundView = new ImageView(backgroundImage);
    backgroundView.setMouseTransparent(true);
    backgroundView.setPickOnBounds(false);
    backgroundView.setPreserveRatio(false);

    StackPane root = new StackPane();
    root.getChildren().add(backgroundView);

    // --- Common constants ---
    double FIELD_WIDTH = 330;
    double FIELD_HEIGHT = 40;

    // --- DAO + current user holder ---
    UserDAO userDAO = new UserDAO();
    User[] currentUser = new User[1];

    // --- LEFT SIDE: ID input + fetch + messages ---
    TextField userIDField = new TextField();
    userIDField.setPromptText("Enter User ID");  
    userIDField.setPrefSize(FIELD_WIDTH, FIELD_HEIGHT);

    Button fetchBtn = new Button("Fetch User");
    fetchBtn.setPrefSize(120, FIELD_HEIGHT);

    Label errorLabel = new Label();
    errorLabel.setStyle("-fx-text-fill: yellow; -fx-font-size: 16px; -fx-font-weight: bold;");
    errorLabel.setWrapText(true);

    Label userInfoLabel = new Label();
    userInfoLabel.setStyle("-fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;");
    userInfoLabel.setWrapText(true);

    VBox leftBox = new VBox(15, userIDField, fetchBtn, errorLabel, userInfoLabel);
    leftBox.setPadding(new Insets(100, 400, 0, 0)); // top, right, bottom, left
    leftBox.setAlignment(Pos.TOP_LEFT);

    // --- RIGHT SIDE: update fields + back button ---
    TextField firstNameField = new TextField();
    firstNameField.setPromptText("Update First Name");
    firstNameField.setPrefSize(400, FIELD_HEIGHT);
  
    TextField lastNameField = new TextField();
    lastNameField.setPromptText("Update Last Name");
    lastNameField.setPrefSize(400, FIELD_HEIGHT);

    TextField contactField = new TextField();
    contactField.setPromptText("Update Contact");
    contactField.setPrefSize(400, FIELD_HEIGHT);

    TextField emailField = new TextField();
    emailField.setPromptText("Update Email");
    emailField.setPrefSize(400, FIELD_HEIGHT);

    Button updateBtn = new Button("Update User");
    updateBtn.setPrefSize(200, FIELD_HEIGHT);

    Button backBtn = new Button("Back to User Menu");
    backBtn.setPrefSize(200, 50);

    // --- Right Pane ---
    BorderPane rightPane = new BorderPane();
    rightPane.setPadding(new Insets(50, 30, 50, 20)); // overall rightPane padding

    // Update fields + button
    VBox updateBox = new VBox(15, firstNameField, lastNameField, contactField, emailField, updateBtn);
    updateBox.setVisible(false);
    updateBox.setAlignment(Pos.TOP_LEFT);

    // Move slightly down and left using translate
    updateBox.setTranslateX(-250);  // moves left 20px
    updateBox.setTranslateY(170);   // moves down 50px

    rightPane.setTop(updateBox);

    // Back button at bottom
    VBox backBox = new VBox(backBtn);
    backBox.setAlignment(Pos.CENTER);
    rightPane.setBottom(backBox);

    // --- Main container ---
    HBox mainBox = new HBox(50, leftBox,rightPane); // 50px spacing between left/right
    mainBox.setAlignment(Pos.CENTER);
    root.getChildren().add(mainBox);

    // --- Scene setup ---
    Scene scene = new Scene(root, 1300, 700);
    backgroundView.fitWidthProperty().bind(scene.widthProperty());
    backgroundView.fitHeightProperty().bind(scene.heightProperty());

    stage.setScene(scene);
    stage.setTitle("Update User");
    stage.show();

    // --- Fetch Button Logic ---
    fetchBtn.setOnAction(e -> {
        errorLabel.setText("");
        userInfoLabel.setText("");

        String input = userIDField.getText().trim();
        if (input.isEmpty()) {
            errorLabel.setText("Please enter a User ID!");
            return;
        }

        try {
            int id = Integer.parseInt(input);
            User user = userDAO.getUserById(id);

            if (user != null) {
                currentUser[0] = user;

                userInfoLabel.setText(
                        "User ID: " + user.getUserID() + "\n" +
                        "Username: " + user.getFirstName() + " " + user.getLastName() + "\n" +
                        "Contact: " + (user.getUserContact() != null ? user.getUserContact() : "N/A") + "\n" +
                        "Email: " + (user.getUserEmail() != null ? user.getUserEmail() : "N/A")
                );

                // Prefill & show update fields
                firstNameField.setText(user.getFirstName());
                lastNameField.setText(user.getLastName());
                contactField.setText(user.getUserContact());
                emailField.setText(user.getUserEmail());

                updateBox.setVisible(true);

            } else {
                errorLabel.setText("No user found with ID " + id + ".");
                updateBox.setVisible(false);
            }

        } catch (NumberFormatException ex) {
            errorLabel.setText("Invalid User ID format!");
            updateBox.setVisible(false);
        }
    });

    // --- Update Button Logic ---
updateBtn.setOnAction(e -> {
    if (currentUser[0] == null) {
        errorLabel.setText("Fetch a user first before updating.");
        return;
    }

    String newFirstName = firstNameField.getText().trim();
    String newLastName = lastNameField.getText().trim();
    String newContact = contactField.getText().trim();
    String newEmail = emailField.getText().trim();

    // --- Validation: first and last name cannot be empty ---
    if (newFirstName.isEmpty() || newLastName.isEmpty()) {
        errorLabel.setText("First Name and Last Name cannot be empty!");
        return;
    }

    // Update user object
    currentUser[0].setFirstName(newFirstName);
    currentUser[0].setLastName(newLastName);
    currentUser[0].setUserContact(newContact);
    currentUser[0].setUserEmail(newEmail);

    boolean success = userDAO.updateUser(currentUser[0]);
    if (success) {
        userInfoLabel.setText(
                "User updated successfully!\n" +
                "User ID: " + currentUser[0].getUserID() + "\n" +
                "Username: " + currentUser[0].getFirstName() + " " + currentUser[0].getLastName() + "\n" +
                "Contact: " + currentUser[0].getUserContact() + "\n" +
                "Email: " + currentUser[0].getUserEmail()
        );
        errorLabel.setText("");
    } else {
        errorLabel.setText("Update failed. Try again.");
    }
});

    // --- Back Button ---
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
                    btn.setOnAction(e -> viewAllLocations(stage));
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

    private static void viewAllLocations(Stage stage) {
        Image backgroundImage = new Image(AppFX.class.getResourceAsStream("viewLocations.png"));
        ImageView backgroundView = new ImageView(backgroundImage);
        backgroundView.setPreserveRatio(false);

        StackPane root = new StackPane();
        double INITIAL_WIDTH = 1300;
        double INITIAL_HEIGHT = 700;
        Scene scene = new Scene(root, INITIAL_WIDTH, INITIAL_HEIGHT);

        backgroundView.fitWidthProperty().bind(scene.widthProperty());
        backgroundView.fitHeightProperty().bind(scene.heightProperty());
        root.getChildren().add(backgroundView);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        scrollPane.setPrefViewportHeight(500);

        VBox locationList = new VBox(15);
        locationList.setPadding(new Insets(10));
        locationList.setAlignment(Pos.TOP_CENTER);

        LocationDAO locationDAO = new LocationDAO();
        List<Location> locations = new ArrayList<>();

        try {
            locations = locationDAO.getAllLocations();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (locations.isEmpty()) {
            Label noLocation = new Label("No locations found in the database.");
            noLocation.setFont(Font.font("Arial", FontWeight.BOLD, 16));
            noLocation.setTextFill(Color.WHITE);
            locationList.getChildren().add(noLocation);
        } else {
            for (Location loc : locations) {
                VBox card = new VBox(5);
                card.setPadding(new Insets(15));
                card.setPrefWidth(900);
                card.setStyle(
                        "-fx-background-color: rgba(255,255,255,0.85); " +
                                "-fx-background-radius: 12; " +
                                "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.25), 10, 0, 0, 5);"
                );

                Label id = new Label("Location ID: " + loc.getLocationID());
                Label name = new Label("Name: " + loc.getLocationName());
                Label city = new Label("City: " + loc.getLocationCity());
                Label postal = new Label("Postal Code: " + loc.getLocationPostalCode());
                Label contact = new Label("Contact: " + loc.getContact());

                id.setFont(Font.font("Arial", FontWeight.BOLD, 14));
                name.setFont(Font.font("Arial", 14));
                city.setFont(Font.font("Arial", 14));
                postal.setFont(Font.font("Arial", 14));
                contact.setFont(Font.font("Arial", 14));

                id.setTextFill(Color.BLACK);
                name.setTextFill(Color.BLACK);
                city.setTextFill(Color.BLACK);
                postal.setTextFill(Color.BLACK);
                contact.setTextFill(Color.BLACK);

                card.getChildren().addAll(id, name, city, postal, contact);
                locationList.getChildren().add(card);
            }
        }

        scrollPane.setContent(locationList);

        Button backBtn = new Button("Back to Location Menu");
        backBtn.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        backBtn.setPrefWidth(180);
        backBtn.setPrefHeight(40);
        backBtn.setStyle("-fx-background-color: #003366; -fx-text-fill: white; -fx-background-radius: 8;");
        backBtn.setOnAction(e -> handleManageLocations(stage));

        VBox content = new VBox(30, scrollPane, backBtn);
        content.setAlignment(Pos.TOP_CENTER);
        content.setPadding(new Insets(230, 20, 40, 20));

        root.getChildren().add(content);
        StackPane.setAlignment(content, Pos.TOP_CENTER);

        stage.setScene(scene);
        stage.setTitle("View All Locker Locations");
        stage.show();
    }


    private static void handleManageLockerTypes(Stage stage) {
        stage.setTitle("Luggage Locker Booking System - Locker Type Management");
        Image bg = new Image(AppFX.class.getResourceAsStream("lockerTypeMenu.jpg"));
        ImageView backgroundView = new ImageView(bg);
        backgroundView.setPreserveRatio(false);

        String[] menuLabels = { "VIEW ALL LOCKER TYPES", "SEARCH LOCKER TYPE", "RETURN TO MAIN MENU" };

        VBox leftMenu = lockerTypeMenuButton(menuLabels, 0, 2, stage);
        VBox rightMenu = lockerTypeMenuButton(menuLabels, 2, 3, stage);

        StackPane root = new StackPane();
        root.getChildren().addAll(backgroundView, leftMenu, rightMenu);
        StackPane.setAlignment(leftMenu, Pos.CENTER_LEFT);
        StackPane.setAlignment(rightMenu, Pos.CENTER_RIGHT);

        Scene scene = new Scene(root, INITIAL_WIDTH, INITIAL_HEIGHT);
        backgroundView.fitWidthProperty().bind(scene.widthProperty());
        backgroundView.fitHeightProperty().bind(scene.heightProperty());

        stage.setScene(scene);
        stage.show();
    }

    private static VBox lockerTypeMenuButton(String[] labels, int start, int end, Stage stage) {
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
                case "VIEW ALL LOCKER TYPES":
                    btn.setOnAction(e -> viewAllLockerTypes(stage));
                    break;
                case "SEARCH LOCKER TYPE":
                    btn.setOnAction(e -> searchLockerType(stage));
                    break;
                case "RETURN TO MAIN MENU":
                    btn.setOnAction(e -> new AppFX().start(stage));
                    break;
            }
            menu.getChildren().add(btn);
        }
        return menu;
    }

    private static void viewAllLockerTypes(Stage stage) {
        stage.setTitle("View All Locker Types");

        Image bg = new Image(AppFX.class.getResourceAsStream("lockerTypeMenu.jpg"));
        ImageView backgroundView = new ImageView(bg);
        backgroundView.setPreserveRatio(false);

        StackPane root = new StackPane();
        root.getChildren().add(backgroundView);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        VBox lockerList = new VBox(15);
        lockerList.setPadding(new Insets(10));
        lockerList.setAlignment(Pos.TOP_CENTER);

        LockerTypeDAO dao = new LockerTypeDAO(); // create instance
        List<LockerType> types = dao.getAllLockerTypes(); // call non-static method

        if (types.isEmpty()) {
            Label noLocker = new Label("No locker types found.");
            lockerList.getChildren().add(noLocker);
        } else {
            for (LockerType type : types) {
                VBox card = new VBox(5);
                card.setPadding(new Insets(15));
                card.setPrefWidth(900);
                card.setStyle(
                        "-fx-background-color: rgba(255,255,255,0.85); " +
                                "-fx-background-radius: 12; " +
                                "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.25), 10, 0, 0, 5);"
                );

                Label idLbl = new Label("Locker Type ID: " + type.getLockerTypeID());
                Label sizeLbl = new Label("Size: " + type.getLockerTypeSize());
                Label maxWeightLbl = new Label("Max Weight: " + type.getLockerMaxWeight());
                Label rateLbl = new Label("Rate: ₱" + type.getLockerRate());

                idLbl.setFont(Font.font("Arial", FontWeight.BOLD, 14));
                sizeLbl.setFont(Font.font("Arial", 14));
                maxWeightLbl.setFont(Font.font("Arial", 14));
                rateLbl.setFont(Font.font("Arial", 14));

                card.getChildren().addAll(idLbl, sizeLbl, maxWeightLbl, rateLbl);
                lockerList.getChildren().add(card);
            }
        }

        scrollPane.setContent(lockerList);

        Button backBtn = new Button("Back to Locker Type Menu");
        backBtn.setPrefWidth(220);
        backBtn.setPrefHeight(40);
        backBtn.setStyle("-fx-font-weight: bold; -fx-background-color: #003366; -fx-text-fill: white;");
        backBtn.setOnAction(e -> handleManageLockerTypes(stage));

        VBox content = new VBox(20, scrollPane, backBtn);
        content.setAlignment(Pos.TOP_CENTER);
        content.setPadding(new Insets(150, 20, 40, 20));

        root.getChildren().add(content);
        StackPane.setAlignment(content, Pos.TOP_CENTER);

        Scene scene = new Scene(root, INITIAL_WIDTH, INITIAL_HEIGHT);
        backgroundView.fitWidthProperty().bind(scene.widthProperty());
        backgroundView.fitHeightProperty().bind(scene.heightProperty());

        stage.setScene(scene);
        stage.show();
    }

    private static void searchLockerType(Stage stage) {
        stage.setTitle("Search Locker Type");

        Image bg = new Image(AppFX.class.getResourceAsStream("lockerTypeMenu.jpg"));
        ImageView backgroundView = new ImageView(bg);
        backgroundView.setPreserveRatio(false);

        StackPane root = new StackPane();
        root.getChildren().add(backgroundView);

        Label idLabel = new Label("Locker Type ID:");
        idLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        TextField idField = new TextField();
        idField.setPrefWidth(200);

        Label sizeLabel = new Label("Locker Size:");
        sizeLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        TextField sizeField = new TextField();
        sizeField.setPrefWidth(200);

        Button searchBtn = new Button("Search");
        searchBtn.setStyle("-fx-font-weight: bold; -fx-background-color: #003366; -fx-text-fill: white;");

        VBox searchBox = new VBox(15, idLabel, idField, sizeLabel, sizeField, searchBtn);
        searchBox.setAlignment(Pos.TOP_LEFT);
        searchBox.setPadding(new Insets(150, 0, 0, 120));

        VBox resultList = new VBox(15);
        resultList.setPadding(new Insets(10));

        ScrollPane scrollPane = new ScrollPane(resultList);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefViewportHeight(500);

        Button backBtn = new Button("Back to Locker Type Menu");
        backBtn.setPrefWidth(220);
        backBtn.setPrefHeight(40);
        backBtn.setStyle("-fx-font-weight: bold; -fx-background-color: #003366; -fx-text-fill: white;");
        backBtn.setOnAction(e -> handleManageLockerTypes(stage));

        HBox mainArea = new HBox(50, searchBox, scrollPane);
        mainArea.setPadding(new Insets(50, 20, 0, 80));

        VBox content = new VBox(20, mainArea, backBtn);
        root.getChildren().add(content);

        Scene scene = new Scene(root, INITIAL_WIDTH, INITIAL_HEIGHT);
        backgroundView.fitWidthProperty().bind(scene.widthProperty());
        backgroundView.fitHeightProperty().bind(scene.heightProperty());

        stage.setScene(scene);
        stage.show();

        searchBtn.setOnAction(e -> {
            resultList.getChildren().clear();
            LockerTypeDAO dao = new LockerTypeDAO(); // create instance
            List<LockerType> results = dao.searchLockerTypes(idField.getText().trim(), sizeField.getText().trim());

            if (results.isEmpty()) {
                Label noResult = new Label("No locker types found.");
                resultList.getChildren().add(noResult);
            } else {
                for (LockerType type : results) {
                    VBox card = new VBox(5);
                    card.setPadding(new Insets(15));
                    card.setPrefWidth(400);
                    card.setStyle(
                            "-fx-background-color: rgba(255,255,255,0.85); " +
                                    "-fx-background-radius: 12; " +
                                    "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.25), 10, 0, 0, 5);"
                    );

                    Label idLbl = new Label("Locker Type ID: " + type.getLockerTypeID());
                    Label sizeLbl = new Label("Size: " + type.getLockerTypeSize());
                    Label maxWeightLbl = new Label("Max Weight: " + type.getLockerMaxWeight());
                    Label rateLbl = new Label("Rate: ₱" + type.getLockerRate());

                    card.getChildren().addAll(idLbl, sizeLbl, maxWeightLbl, rateLbl);
                    resultList.getChildren().add(card);
                }
            }
        });
    }

private static void handleManageLockers(){

}

private static void handleManageLockerLocations(){

    }

private static void handleBooking(){

}

private static void handleCancellations(){

}


     private static void handlePayments(Stage stage) {
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
                    btn.setOnAction(e -> viewAllPayments(stage));
                    break;
                case "RELEASE LOCKER (PROCESS PAYMENT)":
                    btn.setOnAction(e -> {
                        // TODO: open your payment processing / locker release form
                        System.out.println("-> Action: Process Payment and Release Locker");
                    });
                    break;
                case "SEARCH PAYMENT BY ID":
                    btn.setOnAction(e ->   searchPayment(stage));
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

    private static void viewAllPayments(Stage stage) {
        // --- Background setup ---
        Image backgroundImage = new Image(AppFX.class.getResourceAsStream("viewPayments.jpg"));
        ImageView backgroundView = new ImageView(backgroundImage);
        backgroundView.setPreserveRatio(false);

        StackPane root = new StackPane();
        double INITIAL_WIDTH = 1300;
        double INITIAL_HEIGHT = 700;
        Scene scene = new Scene(root, INITIAL_WIDTH, INITIAL_HEIGHT);

        backgroundView.fitWidthProperty().bind(scene.widthProperty());
        backgroundView.fitHeightProperty().bind(scene.heightProperty());
        root.getChildren().add(backgroundView);

        // --- Scrollable payment list setup ---
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        scrollPane.setPrefViewportHeight(500);

        VBox paymentList = new VBox(15);
        paymentList.setPadding(new Insets(10));
        paymentList.setAlignment(Pos.TOP_CENTER);

        // --- Load payments from DB ---
        PaymentDAO paymentDAO = new PaymentDAO();
        List<Payment> payments = new ArrayList<>();

        try {
            payments = paymentDAO.getAllPayments();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (payments.isEmpty()) {
            Label noPayment = new Label("No payments found in the database.");
            noPayment.setFont(Font.font("Arial", FontWeight.BOLD, 16));
            noPayment.setTextFill(Color.WHITE);
            paymentList.getChildren().add(noPayment);
        } else {
            for (Payment payment : payments) {
                VBox card = new VBox(5);
                card.setPadding(new Insets(15));
                card.setPrefWidth(900);
                card.setStyle(
                        "-fx-background-color: rgba(255,255,255,0.85); " +
                                "-fx-background-radius: 12; " +
                                "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.25), 10, 0, 0, 5);"
                );

                Label id = new Label("Payment ID: " + payment.getPaymentID());
                Label bookingRef = new Label("Booking Reference: " + payment.getBookingReference());
                Label userId = new Label("User ID: " + payment.getUserID());
                Label amount = new Label("Amount: $" + payment.getPaymentAmount());
                Label method = new Label("Method: " + payment.getPaymentMethod());
                Label status = new Label("Status: " + payment.getPaymentStatus());
                Label date = new Label("Date: " + payment.getPaymentDate());

                id.setFont(Font.font("Arial", FontWeight.BOLD, 14));
                bookingRef.setFont(Font.font("Arial", 14));
                userId.setFont(Font.font("Arial", 14));
                amount.setFont(Font.font("Arial", 14));
                method.setFont(Font.font("Arial", 14));
                status.setFont(Font.font("Arial", 14));
                date.setFont(Font.font("Arial", 14));

                id.setTextFill(Color.BLACK);
                bookingRef.setTextFill(Color.BLACK);
                userId.setTextFill(Color.BLACK);
                amount.setTextFill(Color.BLACK);
                method.setTextFill(Color.BLACK);
                status.setTextFill(Color.BLACK);
                date.setTextFill(Color.BLACK);

                card.getChildren().addAll(id, bookingRef, userId, amount, method, status, date);
                paymentList.getChildren().add(card);
            }
        }

        scrollPane.setContent(paymentList);

        // --- Back Button ---
        Button backBtn = new Button("Back to Payment Menu");
        backBtn.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        backBtn.setPrefWidth(180);
        backBtn.setPrefHeight(40);
        backBtn.setStyle("-fx-background-color: #003366; -fx-text-fill: white; -fx-background-radius: 8;");
        backBtn.setOnAction(e -> handlePayments(stage));

        // --- Layout positioning ---
        VBox content = new VBox(30, scrollPane, backBtn);
        content.setAlignment(Pos.TOP_CENTER);
        content.setPadding(new Insets(230, 20, 40, 20));

        root.getChildren().add(content);
        StackPane.setAlignment(content, Pos.TOP_CENTER);

        stage.setScene(scene);
        stage.setTitle("View All Payments");
        stage.show();
    }

    // Payment and Release of Locker
    private void releaseLockerAndProcessPayment(String bookingReference){
    }

    // Search Payments
    private static void searchPayment(Stage stage) {
        Image backgroundImage = new Image(AppFX.class.getResourceAsStream("searchPayment.jpg"));
        ImageView backgroundView = new ImageView(backgroundImage);
        backgroundView.setPreserveRatio(false);

        StackPane root = new StackPane();
        double INITIAL_WIDTH = 1300;
        double INITIAL_HEIGHT = 700;
        Scene scene = new Scene(root, INITIAL_WIDTH, INITIAL_HEIGHT);

        backgroundView.fitWidthProperty().bind(scene.widthProperty());
        backgroundView.fitHeightProperty().bind(scene.heightProperty());
        root.getChildren().add(backgroundView);

        Label idLabel = new Label("Search by Payment ID:");
        idLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        idLabel.setTextFill(Color.WHITE);

        TextField idField = new TextField();
        idField.setPromptText("Enter payment ID");
        idField.setPrefWidth(300);
        idField.setMaxWidth(300);
        idField.setStyle("-fx-pref-height: 50px; -fx-font-size: 20px;");

        Button searchBtn = new Button("Search");
        searchBtn.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        searchBtn.setStyle("-fx-background-color: #003366; -fx-text-fill: white; -fx-background-radius: 8;");
        searchBtn.setPrefWidth(150);
        searchBtn.setPrefHeight(40);

        VBox searchBox = new VBox(10, idLabel, idField, searchBtn);
        searchBox.setAlignment(Pos.TOP_LEFT);
        searchBox.setPadding(new Insets(200, 0, 0, 120));

        VBox paymentList = new VBox(15);
        paymentList.setPadding(new Insets(10));
        paymentList.setAlignment(Pos.TOP_CENTER);

        ScrollPane scrollPane = new ScrollPane(paymentList);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        scrollPane.setPrefViewportWidth(600);
        scrollPane.setPrefViewportHeight(500);

        VBox scrollContainer = new VBox(scrollPane);
        scrollContainer.setAlignment(Pos.TOP_RIGHT);
        scrollContainer.setPadding(new Insets(150, 0, 0, 100));

        Button backBtn = new Button("Back to Payment Menu");
        backBtn.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        backBtn.setPrefWidth(200);
        backBtn.setPrefHeight(40);
        backBtn.setStyle("-fx-background-color: #003366; -fx-text-fill: white; -fx-background-radius: 8;");
        backBtn.setOnAction(e -> handlePayments(stage));

        HBox mainArea = new HBox(50, searchBox, scrollContainer);
        mainArea.setAlignment(Pos.TOP_LEFT);
        mainArea.setPadding(new Insets(50, 20, 0, 80));

        HBox backContainer = new HBox(backBtn);
        backContainer.setAlignment(Pos.CENTER_RIGHT);
        backContainer.setPadding(new Insets(20, 50, 0, 0));

        VBox rootContent = new VBox(30, mainArea, backContainer);
        rootContent.setAlignment(Pos.TOP_LEFT);
        rootContent.setPadding(new Insets(50, 20, 40, 20));

        root.getChildren().add(rootContent);
        StackPane.setAlignment(rootContent, Pos.TOP_CENTER);

        stage.setScene(scene);
        stage.setTitle("Search Payment");
        stage.show();

        // --- Search Button Action ---
        searchBtn.setOnAction(e -> {
            paymentList.getChildren().clear(); // clear previous results
            PaymentDAO paymentDAO = new PaymentDAO();
            List<Payment> results = new ArrayList<>();

            String idQuery = idField.getText().trim();

            try {
                if (!idQuery.isEmpty()) {
                    try {
                        int paymentID = Integer.parseInt(idQuery);
                        Payment payment = paymentDAO.getPaymentById(paymentID);
                        if (payment != null) results.add(payment);
                    } catch (NumberFormatException ex) {
                        Label invalid = new Label("Invalid Payment ID format. Please enter a number.");
                        invalid.setFont(Font.font("Arial", FontWeight.BOLD, 14));
                        invalid.setTextFill(Color.RED);
                        paymentList.getChildren().add(invalid);
                        return;
                    }
                }

                if (results.isEmpty()) {
                    Label noResult = new Label("No payment found with the given ID.");
                    noResult.setFont(Font.font("Arial", FontWeight.BOLD, 16));
                    noResult.setTextFill(Color.BLACK);
                    noResult.setPadding(new Insets(220, 0, 0, 220));
                    paymentList.getChildren().add(noResult);
                } else {
                    for (Payment payment : results) {
                        VBox card = new VBox(5);
                        card.setPadding(new Insets(15));
                        card.setPrefWidth(900);
                        card.setStyle(
                                "-fx-background-color: rgba(255,255,255,0.85); " +
                                        "-fx-background-radius: 12; " +
                                        "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.25), 10, 0, 0, 5);"
                        );

                        // Convert Timestamp to LocalDateTime for formatting
                        LocalDateTime dateTime = payment.getPaymentDate().toLocalDateTime();

                        Label idLbl = new Label("Payment ID: " + payment.getPaymentID());
                        Label bookingRefLbl = new Label("Booking Reference: " + payment.getBookingReference());
                        Label userIDLbl = new Label("User ID: " + payment.getUserID());
                        Label amountLbl = new Label("Amount: $" + String.format("%.2f", payment.getPaymentAmount()));
                        Label methodLbl = new Label("Payment Method: " + payment.getPaymentMethod());
                        Label statusLbl = new Label("Status: " + payment.getPaymentStatus());
                        Label dateLbl = new Label("Date: " +
                                dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

                        idLbl.setFont(Font.font("Arial", FontWeight.BOLD, 14));
                        bookingRefLbl.setFont(Font.font("Arial", 14));
                        userIDLbl.setFont(Font.font("Arial", 14));
                        amountLbl.setFont(Font.font("Arial", 14));
                        methodLbl.setFont(Font.font("Arial", 14));
                        statusLbl.setFont(Font.font("Arial", 14));
                        dateLbl.setFont(Font.font("Arial", 14));

                        idLbl.setTextFill(Color.BLACK);
                        bookingRefLbl.setTextFill(Color.BLACK);
                        userIDLbl.setTextFill(Color.BLACK);
                        amountLbl.setTextFill(Color.BLACK);
                        methodLbl.setTextFill(Color.BLACK);
                        statusLbl.setTextFill(Color.BLACK);
                        dateLbl.setTextFill(Color.BLACK);

                        card.getChildren().addAll(idLbl, bookingRefLbl, userIDLbl, amountLbl, methodLbl, statusLbl, dateLbl);
                        paymentList.getChildren().add(card);
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    private static void handleTransfers(Stage stage) {
        stage.setTitle("Luggage Locker Booking System - Locker Transfer Management");
        Image bg = new Image(AppFX.class.getResourceAsStream("lockerTransferMenu.jpg"));
        ImageView backgroundView = new ImageView(bg);
        backgroundView.setPreserveRatio(false);

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

        stage.setScene(scene);
        stage.show();
    }

    private static VBox lockerTransferMenuButton(String[] labels, int start, int end, Stage stage) {
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
                case "LOCKER TRANSFER":
                    btn.setOnAction(e -> performLockerTransfer(stage));
                    break;
                case "VIEW ALL TRANSFERS":
                    btn.setOnAction(e -> viewAllTransfers(stage));
                    break;
                case "SEARCH TRANSFER BY ID":
                    btn.setOnAction(e -> searchTransferByID(stage));
                    break;
                case "RETURN TO MAIN MENU":
                    btn.setOnAction(e -> new AppFX().start(stage));
                    break;
            }
            menu.getChildren().add(btn);
        }
        return menu;
    }

    private static void performLockerTransfer(Stage stage) {
        stage.setTitle("Perform Locker Transfer");
        Image bg = new Image(AppFX.class.getResourceAsStream("lockerTransferMenu.jpg"));
        ImageView backgroundView = new ImageView(bg);
        backgroundView.setPreserveRatio(false);

        Label bookingLabel = new Label("Booking Reference:");
        TextField bookingField = new TextField();
        Label oldLockerLabel = new Label("Old Locker ID:");
        TextField oldLockerField = new TextField();
        Label newLockerLabel = new Label("New Locker ID:");
        TextField newLockerField = new TextField();
        Label amountLabel = new Label("Adjustment Amount:");
        TextField amountField = new TextField();

        Button submitBtn = new Button("Submit Transfer");
        submitBtn.setStyle("-fx-font-weight: bold; -fx-background-color: #003366; -fx-text-fill: white;");
        Button backBtn = new Button("Back to Transfer Menu");
        backBtn.setStyle("-fx-font-weight: bold; -fx-background-color: #003366; -fx-text-fill: white;");
        backBtn.setOnAction(e -> handleTransfers(stage));

        VBox form = new VBox(15, bookingLabel, bookingField,
                oldLockerLabel, oldLockerField,
                newLockerLabel, newLockerField,
                amountLabel, amountField,
                submitBtn, backBtn);
        form.setPadding(new Insets(100, 50, 50, 100));

        StackPane root = new StackPane();
        root.getChildren().addAll(backgroundView, form);

        Scene scene = new Scene(root, INITIAL_WIDTH, INITIAL_HEIGHT);
        backgroundView.fitWidthProperty().bind(scene.widthProperty());
        backgroundView.fitHeightProperty().bind(scene.heightProperty());
        stage.setScene(scene);
        stage.show();

        submitBtn.setOnAction(e -> {
            try {
                String bookingRef = bookingField.getText().trim();
                int oldLockerID = Integer.parseInt(oldLockerField.getText().trim());
                int newLockerID = Integer.parseInt(newLockerField.getText().trim());
                double adjAmount = Double.parseDouble(amountField.getText().trim());

                LockerTransfer transfer = new LockerTransfer(bookingRef, LocalDateTime.now(), adjAmount, oldLockerID, newLockerID);
                int id = LockerTransferDAO.addTransfer(transfer);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Transfer Successful");
                alert.setHeaderText(null);
                alert.setContentText("Transfer ID: " + id + " created successfully.");
                alert.showAndWait();

                handleTransfers(stage);

            } catch (NumberFormatException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Input Error");
                alert.setHeaderText(null);
                alert.setContentText("Please enter valid numbers for Locker IDs and Adjustment Amount.");
                alert.showAndWait();
            }
        });
    }

    private static void viewAllTransfers(Stage stage) {
        stage.setTitle("View All Locker Transfers");
        Image bg = new Image(AppFX.class.getResourceAsStream("lockerTransferMenu.jpg"));
        ImageView backgroundView = new ImageView(bg);
        backgroundView.setPreserveRatio(false);

        VBox transferList = new VBox(15);
        transferList.setPadding(new Insets(10));

        List<LockerTransfer> transfers = LockerTransferDAO.getAllTransfers();
        if (transfers.isEmpty()) {
            transferList.getChildren().add(new Label("No transfers found."));
        } else {
            for (LockerTransfer t : transfers) {
                VBox card = new VBox(5);
                card.setPadding(new Insets(10));
                card.setStyle("-fx-background-color: rgba(255,255,255,0.85); -fx-background-radius: 10;");
                Label info = new Label("ID: " + t.getTransferID() +
                        ", Booking: " + t.getBookingReference() +
                        ", Date: " + t.getTransferDate() +
                        ", Old Locker: " + t.getOldLockerID() +
                        ", New Locker: " + t.getNewLockerID() +
                        ", Adjustment: ₱" + t.getAdjustmentAmount());
                card.getChildren().add(info);
                transferList.getChildren().add(card);
            }
        }

        ScrollPane scrollPane = new ScrollPane(transferList);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefViewportHeight(500);

        Button backBtn = new Button("Back to Transfer Menu");
        backBtn.setStyle("-fx-font-weight: bold; -fx-background-color: #003366; -fx-text-fill: white;");
        backBtn.setOnAction(e -> handleTransfers(stage));

        VBox content = new VBox(20, scrollPane, backBtn);
        content.setPadding(new Insets(150, 20, 20, 20));
        content.setAlignment(Pos.TOP_CENTER);

        StackPane root = new StackPane(backgroundView, content);

        Scene scene = new Scene(root, INITIAL_WIDTH, INITIAL_HEIGHT);
        backgroundView.fitWidthProperty().bind(scene.widthProperty());
        backgroundView.fitHeightProperty().bind(scene.heightProperty());
        stage.setScene(scene);
        stage.show();
    }

    private static void searchTransferByID(Stage stage) {
        stage.setTitle("Search Transfer by ID");
        Image bg = new Image(AppFX.class.getResourceAsStream("lockerTransferMenu.jpg"));
        ImageView backgroundView = new ImageView(bg);
        backgroundView.setPreserveRatio(false);

        Label idLabel = new Label("Transfer ID:");
        TextField idField = new TextField();
        Button searchBtn = new Button("Search");
        searchBtn.setStyle("-fx-font-weight: bold; -fx-background-color: #003366; -fx-text-fill: white;");
        Button backBtn = new Button("Back to Transfer Menu");
        backBtn.setStyle("-fx-font-weight: bold; -fx-background-color: #003366; -fx-text-fill: white;");
        backBtn.setOnAction(e -> handleTransfers(stage));

        VBox inputBox = new VBox(15, idLabel, idField, searchBtn, backBtn);
        inputBox.setPadding(new Insets(150, 50, 50, 150));

        VBox resultBox = new VBox(15);
        ScrollPane scrollPane = new ScrollPane(resultBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefViewportHeight(500);

        HBox mainArea = new HBox(50, inputBox, scrollPane);
        mainArea.setPadding(new Insets(50, 20, 0, 50));

        StackPane root = new StackPane(backgroundView, mainArea);
        Scene scene = new Scene(root, INITIAL_WIDTH, INITIAL_HEIGHT);
        backgroundView.fitWidthProperty().bind(scene.widthProperty());
        backgroundView.fitHeightProperty().bind(scene.heightProperty());

        stage.setScene(scene);
        stage.show();

        searchBtn.setOnAction(e -> {
            resultBox.getChildren().clear();
            try {
                int id = Integer.parseInt(idField.getText().trim());
                LockerTransfer transfer = LockerTransferDAO.getTransferByID(id);
                if (transfer == null) {
                    resultBox.getChildren().add(new Label("No transfer found for ID " + id));
                } else {
                    VBox card = new VBox(5);
                    card.setPadding(new Insets(10));
                    card.setStyle("-fx-background-color: rgba(255,255,255,0.85); -fx-background-radius: 10;");
                    Label info = new Label("ID: " + transfer.getTransferID() +
                            ", Booking: " + transfer.getBookingReference() +
                            ", Date: " + transfer.getTransferDate() +
                            ", Old Locker: " + transfer.getOldLockerID() +
                            ", New Locker: " + transfer.getNewLockerID() +
                            ", Adjustment: ₱" + transfer.getAdjustmentAmount());
                    card.getChildren().add(info);
                    resultBox.getChildren().add(card);
                }
            } catch (NumberFormatException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Input Error");
                alert.setHeaderText(null);
                alert.setContentText("Please enter a valid numeric Transfer ID.");
                alert.showAndWait();
            }
        });
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