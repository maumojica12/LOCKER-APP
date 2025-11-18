import javafx.scene.control.*;
import javafx.scene.layout.*;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.scene.layout.Region;
import javafx.beans.property.SimpleStringProperty;
import java.util.Map;
import java.util.HashMap;
import java.time.Year;
import java.sql.Timestamp;
import javafx.stage.Modality;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.util.List;

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
                    btn.setOnAction(e -> handleManageLockers(primaryStage));
                    break;
                case "MANAGE LOCKER LOCATIONS":
                    btn.setOnAction(e -> handleManageLocations(primaryStage));
                    break;
                case "BOOK/MANAGE RESERVATIONS":
                    btn.setOnAction(e -> handleBooking(primaryStage));
                    break;
                case "MANAGE CANCELLATIONS":
                    btn.setOnAction(e -> handleCancellations(primaryStage));
                    break;
                case "MANAGE PAYMENTS":
                    btn.setOnAction(e -> handlePayments(primaryStage));
                    break;
                case "MANAGE TRANSFERS":
                    btn.setOnAction(e -> handleTransfers(primaryStage));
                    break;
                case "GENERATE REPORTS":
                    btn.setOnAction(e -> handleReports(primaryStage));
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
                    btn.setOnAction(e -> deleteUser(stage));
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
            errorMessageLabel.setText("First and Last Names are Required!");
            errorMessageLabel.setStyle("-fx-text-fill: yellow;-fx-font-size: 16px; -fx-font-weight: bold;");
            errorMessageLabel.setVisible(true);
            return;
        }

        if (!contact.isEmpty()) {
            // Starts with 0 must be all digits + length 11
            if (contact.startsWith("0")) {

                if (!contact.matches("[0-9]+") || contact.length() != 11) {
                    errorMessageLabel.setText("Invalid Contact Number Format");
                    errorMessageLabel.setStyle("-fx-text-fill: yellow; -fx-font-size: 16px; -fx-font-weight: bold;");
                    errorMessageLabel.setVisible(true);
                    return;
                }
            }

            // Starts with +63  must be +63 + 10 digits = length 13
            else if (contact.startsWith("+63")) {

                if (!contact.substring(3).matches("[0-9]+") || contact.length() != 13) {
                    errorMessageLabel.setText("Invalid Contact Number Format");
                    errorMessageLabel.setStyle("-fx-text-fill: yellow; -fx-font-size: 16px; -fx-font-weight: bold;");
                    errorMessageLabel.setVisible(true);
                    return;
                }
            }

            // Any other prefix is invalid
            else {
                errorMessageLabel.setText("Invalid Contact Number Format");
                errorMessageLabel.setStyle("-fx-text-fill: yellow; -fx-font-size: 16px; -fx-font-weight: bold;");
                errorMessageLabel.setVisible(true);
                return;
            }
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

    AnchorPane root = new AnchorPane();
    root.getChildren().add(backgroundView);

    double FIELD_WIDTH = 330;
    double FIELD_HEIGHT = 40;
    double FIELD_SPACING = 10;

    UserDAO userDAO = new UserDAO();
    User[] currentUser = new User[1];

    // --- LEFT SIDE: Fetch User ---
    TextField userIDField = new TextField();
    userIDField.setPromptText("Enter User ID");
    userIDField.setPrefSize(FIELD_WIDTH, FIELD_HEIGHT);

    Button fetchBtn = new Button("Fetch User");
    fetchBtn.setPrefSize(120, FIELD_HEIGHT);

    Label errorLabel = new Label();
    errorLabel.setStyle("-fx-text-fill: yellow; -fx-font-size: 16px; -fx-font-weight: bold;");
    errorLabel.setWrapText(true);
    errorLabel.setMaxWidth(FIELD_WIDTH);
    errorLabel.setVisible(false);

    Label userInfoLabel = new Label();
    userInfoLabel.setStyle("-fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;");
    userInfoLabel.setWrapText(true);

    VBox leftBox = new VBox(FIELD_SPACING, userIDField, fetchBtn, errorLabel, userInfoLabel);
    leftBox.setAlignment(Pos.TOP_LEFT);

    // Anchor leftBox top-left
    AnchorPane.setTopAnchor(leftBox, 100.0);
    AnchorPane.setLeftAnchor(leftBox, 35.0);

    // --- RIGHT SIDE: Update User ---
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

    VBox updateBox = new VBox(FIELD_SPACING, firstNameField, lastNameField, contactField, emailField, updateBtn);
    updateBox.setAlignment(Pos.TOP_LEFT);
    updateBox.setVisible(false);

    // Anchor updateBox top-right
    AnchorPane.setTopAnchor(updateBox, 250.0);
    AnchorPane.setRightAnchor(updateBox, 300.0);

    // --- Back button ---
    Button backBtn = new Button("Back to User Menu");
    backBtn.setPrefSize(200, 50);
    AnchorPane.setBottomAnchor(backBtn, 50.0);
    AnchorPane.setRightAnchor(backBtn, 50.0);

    // --- Add everything to root ---
    root.getChildren().addAll(leftBox, updateBox, backBtn);
    backgroundView.toBack();

    // --- Scene and Background Binding ---
    Scene scene = new Scene(root, 1300, 700);
    backgroundView.fitWidthProperty().bind(scene.widthProperty());
    backgroundView.fitHeightProperty().bind(scene.heightProperty());

    stage.setScene(scene);
    stage.setTitle("Update User");
    stage.show();

    // --- BUTTON LOGIC REMAINS UNCHANGED ---
    fetchBtn.setOnAction(e -> {
        errorLabel.setText("");
        userInfoLabel.setText("");

        String input = userIDField.getText() != null ? userIDField.getText().trim() : "";
        if (input.isEmpty()) {
            errorLabel.setText("Please enter a User ID!");
            errorLabel.setVisible(true);  // make sure it’s visible
            errorLabel.toFront();      
            updateBox.setVisible(false);
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

                firstNameField.setText(user.getFirstName() != null ? user.getFirstName() : "");
                lastNameField.setText(user.getLastName() != null ? user.getLastName() : "");
                contactField.setText(user.getUserContact() != null ? user.getUserContact() : "");
                emailField.setText(user.getUserEmail() != null ? user.getUserEmail() : "");

                updateBox.setVisible(true);

            } else {
                errorLabel.setText("No user found with ID " + id + ".");
                errorLabel.setVisible(true);  // make sure it’s visible
                errorLabel.toFront();      
                updateBox.setVisible(false);
            }

        } catch (NumberFormatException ex) {
            errorLabel.setText("Invalid User ID format!");
            errorLabel.setVisible(true);  // make sure it’s visible
            errorLabel.toFront();      
            updateBox.setVisible(false);
        }
    });

    updateBtn.setOnAction(e -> {
        errorLabel.setText("");

        if (currentUser[0] == null) {
            errorLabel.setText("Fetch a user first before updating.");
            return;
        }

        String newFirstName = firstNameField.getText() != null ? firstNameField.getText().trim() : "";
        String newLastName  = lastNameField.getText() != null ? lastNameField.getText().trim() : "";
        String newContact   = contactField.getText() != null ? contactField.getText().trim() : "";
        String newEmail     = emailField.getText() != null ? emailField.getText().trim() : "";

        if (newFirstName.isEmpty() || newLastName.isEmpty()) {
            errorLabel.setText("First Name and Last Name cannot be empty!");
            return;
        }

        if (newContact.isEmpty()) newContact = null;
        if (newEmail.isEmpty()) newEmail = null;

        if (newContact != null) {
            if (newContact.startsWith("0")) {
                if (!newContact.matches("[0-9]+") || newContact.length() != 11) {
                    errorLabel.setText("Invalid Contact Number Format\n(must be 11 digits starting with 0)");
                    errorLabel.setVisible(true);
                    errorLabel.toFront();
                    return;
                }
            } else if (newContact.startsWith("+63")) {
                if (!newContact.substring(3).matches("[0-9]+") || newContact.length() != 13) {
                    errorLabel.setText("Invalid Contact Number Format\n(must be +63 followed by 10 digits)");
                    errorLabel.setVisible(true);
                    errorLabel.toFront();
                    return;
                }
            } else {
                errorLabel.setText("Invalid Contact Number Format\n(must start with 0 or +63)");
                errorLabel.setVisible(true);
                errorLabel.toFront();
                return;
            }
        }

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
                    "Contact: " + (currentUser[0].getUserContact() != null ? currentUser[0].getUserContact() : "N/A") + "\n" +
                    "Email: " + (currentUser[0].getUserEmail() != null ? currentUser[0].getUserEmail() : "N/A")
            );
            errorLabel.setText("");
        } else {
            errorLabel.setText("Update failed. Try again.");
        }
    });

    backBtn.setOnAction(e -> handleManageUsers(stage));
}

private static void deleteUser(Stage stage) {
    // --- Background setup ---
    Image backgroundImage = new Image(AppFX.class.getResourceAsStream("deleteUser.jpg"));
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
    scrollPane.setPrefViewportHeight(500);

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

    Label messageLabel = new Label("");
    messageLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
    messageLabel.setTextFill(Color.BLACK);

    if (users.isEmpty()) {
        Label noUser = new Label("No users found in the database.");
        noUser.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        noUser.setTextFill(Color.WHITE);
        userList.getChildren().add(noUser);
    } else {
        for (User user : users) {
            HBox card = new HBox(15);
            card.setPadding(new Insets(15));
            card.setPrefWidth(900);
            card.setAlignment(Pos.CENTER_LEFT);
            card.setStyle(
                "-fx-background-color: rgba(255,255,255,0.85); " +
                "-fx-background-radius: 12; " +
                "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.25), 10, 0, 0, 5);"
            );

            // --- User Info ---
            VBox info = new VBox(5);
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

            info.getChildren().addAll(id, name, contact, email);

            // DELETE X ICON
            Button deleteBtn = new Button("✖");
            deleteBtn.setFont(Font.font("Arial", FontWeight.BOLD, 16));
            deleteBtn.setStyle("-fx-background-color: transparent; -fx-text-fill: black;");
            deleteBtn.setCursor(Cursor.HAND);

            deleteBtn.setOnAction(e -> {
                Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
                confirm.setTitle("Confirm Deletion");
                confirm.setHeaderText(null);
                confirm.setContentText("Are you sure you want to delete " + user.getFirstName() + " " + user.getLastName() + "?");

                confirm.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        boolean success = userDAO.deleteUserByID(user.getUserID());
                        if (success) {
                            userList.getChildren().remove(card);
                            messageLabel.setText("User ID: " + user.getUserID() + "  [ " + user.getFirstName() + " " + user.getLastName() + " ]  Deleted successfully!");
                        } else {
                            messageLabel.setText("Failed to delete user.");
                        }
                    }
                });
            });

            Region spacer = new Region();
            HBox.setHgrow(spacer, Priority.ALWAYS);

            card.getChildren().addAll(info, spacer, deleteBtn);
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
    VBox content = new VBox(20, scrollPane, messageLabel, backBtn);
    content.setAlignment(Pos.TOP_CENTER);
    content.setPadding(new Insets(230, 20, 40, 20));

    root.getChildren().add(content);
    StackPane.setAlignment(content, Pos.TOP_CENTER);

    stage.setScene(scene);
    stage.setTitle("Delete Users");
    stage.show();
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
                    btn.setOnAction(e -> viewAvailableLockersInLocation(stage));
                    break;
                case "VIEW LOCKERS IN LOCATION":
                    btn.setOnAction(e -> viewLockersInLocation(stage));
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

    private static void viewAvailableLockersInLocation(Stage stage) {

        LocationDAO locationDAO = new LocationDAO();
        LockerDAO lockerDAO = new LockerDAO();
        LockerTypeDAO lockerTypeDAO = new LockerTypeDAO();

        Image backgroundImage = new Image(AppFX.class.getResourceAsStream("viewAvailableLockersInLocation.jpg"));
        ImageView backgroundView = new ImageView(backgroundImage);
        backgroundView.setPreserveRatio(false);
        backgroundView.setMouseTransparent(true);
        backgroundView.setFitWidth(1300);
        backgroundView.setFitHeight(700);

        AnchorPane root = new AnchorPane();
        root.getChildren().add(backgroundView);

        double FIELD_WIDTH = 330;
        double FIELD_HEIGHT = 40;

        TextField locationIDField = new TextField();
        locationIDField.setPromptText("Enter Location ID");
        locationIDField.setPrefSize(FIELD_WIDTH, FIELD_HEIGHT);

        Button fetchBtn = new Button("Fetch Location");
        fetchBtn.setPrefSize(140, FIELD_HEIGHT);
        fetchBtn.setStyle("-fx-background-color: #003366; -fx-text-fill: white; -fx-background-radius: 8;");

        Label errorLabel = new Label();
        errorLabel.setStyle("-fx-text-fill: yellow; -fx-font-size: 16; -fx-font-weight: bold;");

        Label locationInfoLabel = new Label();
        locationInfoLabel.setStyle("-fx-text-fill: white; -fx-font-size: 16; -fx-font-weight: bold;");
        locationInfoLabel.setWrapText(true);

        AnchorPane leftPane = new AnchorPane();
        leftPane.setPrefSize(400, 600);
        leftPane.getChildren().addAll(locationIDField, fetchBtn, errorLabel, locationInfoLabel);

        AnchorPane.setTopAnchor(locationIDField, 20.0);
        AnchorPane.setLeftAnchor(locationIDField, 20.0);
        AnchorPane.setTopAnchor(fetchBtn, 70.0);
        AnchorPane.setLeftAnchor(fetchBtn, 20.0);
        AnchorPane.setTopAnchor(errorLabel, 130.0);
        AnchorPane.setLeftAnchor(errorLabel, 20.0);
        AnchorPane.setTopAnchor(locationInfoLabel, 180.0);
        AnchorPane.setLeftAnchor(locationInfoLabel, 20.0);

        leftPane.setLayoutX(10);
        leftPane.setLayoutY(100);

        ScrollPane lockerScroll = new ScrollPane();
        lockerScroll.setPrefSize(700, 400);
        lockerScroll.setLayoutX(550);
        lockerScroll.setLayoutY(230);
        lockerScroll.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        lockerScroll.setVisible(false);

        Label lockerTitle = new Label("Available Lockers in Location");
        lockerTitle.setStyle("-fx-text-fill: white; -fx-font-size: 24px; -fx-font-weight: bold;");
        lockerTitle.setLayoutX(560);
        lockerTitle.setLayoutY(185);
        lockerTitle.setVisible(false);

        Button backBtn = new Button("Back to Location Menu");
        backBtn.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        backBtn.setPrefSize(200, 40);
        backBtn.setStyle("-fx-background-color: #003366; -fx-text-fill: white; -fx-background-radius: 8;");
        backBtn.setLayoutX(1050);
        backBtn.setLayoutY(630);

        root.getChildren().addAll(leftPane, lockerScroll, lockerTitle, backBtn);

        Scene scene = new Scene(root, 1300, 700);
        stage.setScene(scene);
        stage.setTitle("View Available Lockers");
        stage.show();

        fetchBtn.setOnAction(e -> {

            errorLabel.setText("");
            locationInfoLabel.setText("");
            lockerTitle.setVisible(false);
            lockerScroll.setVisible(false);

            String input = locationIDField.getText().trim();

            if (input.isEmpty()) {
                errorLabel.setText("Please enter a Location ID!");
                return;
            }

            try {
                int locID = Integer.parseInt(input);

                List<Location> allLocations = locationDAO.getAllLocations();

                Location loc = allLocations.stream()
                        .filter(l -> l.getLocationID() == locID)
                        .findFirst()
                        .orElse(null);

                if (loc == null) {
                    errorLabel.setText("No location found with ID " + locID);
                    return;
                }

                locationInfoLabel.setText(
                        "Location ID: " + loc.getLocationID() + "\n" +
                                "Name: " + loc.getLocationName() + "\n" +
                                "City: " + loc.getLocationCity() + "\n" +
                                "Postal Code: " + loc.getLocationPostalCode() + "\n" +
                                "Contact: " + loc.getContact()
                );

                lockerTitle.setVisible(true);

                VBox lockerList = new VBox(15);
                lockerList.setPadding(new Insets(10));
                lockerList.setAlignment(Pos.TOP_CENTER);

                List<Locker> availableLockers = lockerDAO.getAvailableLockersByLocation(locID);

                if (availableLockers.isEmpty()) {
                    Label noLocker = new Label("No available lockers in this location.");
                    noLocker.setFont(Font.font("Arial", FontWeight.BOLD, 16));
                    noLocker.setTextFill(Color.WHITE);
                    lockerList.getChildren().add(noLocker);
                } else {
                    for (Locker locker : availableLockers) {

                        LockerType type = lockerTypeDAO.getLockerTypeByID(locker.getLockerTypeID());
                        String size = (type != null ? type.getLockerTypeSize() : "N/A");

                        HBox card = new HBox(15);
                        card.setPadding(new Insets(15));
                        card.setPrefWidth(600);
                        card.setAlignment(Pos.CENTER_LEFT);
                        card.setStyle(
                                "-fx-background-color: rgba(255,255,255,0.85); -fx-background-radius: 12;" +
                                        "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.25), 10,0,0,5);"
                        );

                        VBox info = new VBox(5);
                        Label idLabel = new Label("Locker ID: " + locker.getLockerID());
                        Label typeLabel = new Label("Size: " + size);
                        Label postal = new Label("Postal Code: " + locker.getLocationPostalCode());

                        idLabel.setTextFill(Color.BLACK);
                        typeLabel.setTextFill(Color.BLACK);
                        postal.setTextFill(Color.BLACK);

                        info.getChildren().addAll(idLabel, typeLabel, postal);

                        Region spacer = new Region();
                        HBox.setHgrow(spacer, Priority.ALWAYS);

                        card.getChildren().addAll(info, spacer);
                        lockerList.getChildren().add(card);
                    }
                }

                lockerScroll.setContent(lockerList);
                lockerScroll.setVisible(true);

            } catch (NumberFormatException ex) {
                errorLabel.setText("Invalid Location ID format!");
            }
        });

        backBtn.setOnAction(e -> AppFX.handleManageLocations(stage));
    }

    private static void viewLockersInLocation(Stage stage) {

        LocationDAO locationDAO = new LocationDAO();
        LockerDAO lockerDAO = new LockerDAO();
        LockerTypeDAO lockerTypeDAO = new LockerTypeDAO();

        Image backgroundImage = new Image(AppFX.class.getResourceAsStream("viewLockersInLocation.jpg"));
        ImageView backgroundView = new ImageView(backgroundImage);
        backgroundView.setPreserveRatio(false);
        backgroundView.setMouseTransparent(true);
        backgroundView.setFitWidth(1300);
        backgroundView.setFitHeight(700);

        AnchorPane root = new AnchorPane();
        root.getChildren().add(backgroundView);

        double FIELD_WIDTH = 330;
        double FIELD_HEIGHT = 40;

        TextField locationIDField = new TextField();
        locationIDField.setPromptText("Enter Location ID");
        locationIDField.setPrefSize(FIELD_WIDTH, FIELD_HEIGHT);

        Button fetchBtn = new Button("Fetch Location");
        fetchBtn.setPrefSize(140, FIELD_HEIGHT);
        fetchBtn.setStyle("-fx-background-color: #003366; -fx-text-fill: white; -fx-background-radius: 8;");

        Label errorLabel = new Label();
        errorLabel.setStyle("-fx-text-fill: yellow; -fx-font-size: 16; -fx-font-weight: bold;");

        Label locationInfoLabel = new Label();
        locationInfoLabel.setStyle("-fx-text-fill: white; -fx-font-size: 16; -fx-font-weight: bold;");
        locationInfoLabel.setWrapText(true);

        AnchorPane leftPane = new AnchorPane();
        leftPane.setPrefSize(400, 600);
        leftPane.getChildren().addAll(locationIDField, fetchBtn, errorLabel, locationInfoLabel);

        AnchorPane.setTopAnchor(locationIDField, 20.0);
        AnchorPane.setLeftAnchor(locationIDField, 20.0);
        AnchorPane.setTopAnchor(fetchBtn, 70.0);
        AnchorPane.setLeftAnchor(fetchBtn, 20.0);
        AnchorPane.setTopAnchor(errorLabel, 130.0);
        AnchorPane.setLeftAnchor(errorLabel, 20.0);
        AnchorPane.setTopAnchor(locationInfoLabel, 180.0);
        AnchorPane.setLeftAnchor(locationInfoLabel, 20.0);

        leftPane.setLayoutX(10);
        leftPane.setLayoutY(100);

        ScrollPane lockerScroll = new ScrollPane();
        lockerScroll.setPrefSize(700, 400);
        lockerScroll.setLayoutX(550);
        lockerScroll.setLayoutY(230);
        lockerScroll.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        lockerScroll.setVisible(false);

        Label lockerTitle = new Label("All Lockers in Location");
        lockerTitle.setStyle("-fx-text-fill: white; -fx-font-size: 24px; -fx-font-weight: bold;");
        lockerTitle.setLayoutX(560);
        lockerTitle.setLayoutY(185);
        lockerTitle.setVisible(false);

        Button backBtn = new Button("Back to Location Menu");
        backBtn.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        backBtn.setPrefSize(200, 40);
        backBtn.setStyle("-fx-background-color: #003366; -fx-text-fill: white; -fx-background-radius: 8;");
        backBtn.setLayoutX(1050);
        backBtn.setLayoutY(630);

        root.getChildren().addAll(leftPane, lockerScroll, lockerTitle, backBtn);

        Scene scene = new Scene(root, 1300, 700);
        stage.setScene(scene);
        stage.setTitle("View Lockers in Location");
        stage.show();

        fetchBtn.setOnAction(e -> {

            errorLabel.setText("");
            locationInfoLabel.setText("");
            lockerScroll.setVisible(false);
            lockerTitle.setVisible(false);

            String input = locationIDField.getText().trim();

            if (input.isEmpty()) {
                errorLabel.setText("Please enter a Location ID!");
                return;
            }

            try {
                int locID = Integer.parseInt(input);

                List<Location> allLocations = locationDAO.getAllLocations();
                Location loc = allLocations.stream()
                        .filter(l -> l.getLocationID() == locID)
                        .findFirst()
                        .orElse(null);

                if (loc == null) {
                    errorLabel.setText("No location found with ID " + locID);
                    return;
                }

                locationInfoLabel.setText(
                        "Location ID: " + loc.getLocationID() + "\n" +
                                "Name: " + loc.getLocationName() + "\n" +
                                "City: " + loc.getLocationCity() + "\n" +
                                "Postal Code: " + loc.getLocationPostalCode() + "\n" +
                                "Contact: " + loc.getContact()
                );

                lockerTitle.setVisible(true);

                List<Locker> lockerListData = lockerDAO.getAllLockers();
                List<Locker> filtered = lockerListData.stream()
                        .filter(l -> l.getLocationID() == locID)
                        .toList();


                VBox lockerList = new VBox(15);
                lockerList.setPadding(new Insets(10));
                lockerList.setAlignment(Pos.TOP_CENTER);


                if (filtered.isEmpty()) {
                    Label noLocker = new Label("This location has no lockers.");
                    noLocker.setFont(Font.font("Arial", FontWeight.BOLD, 16));
                    noLocker.setTextFill(Color.WHITE);
                    lockerList.getChildren().add(noLocker);

                } else {

                    for (Locker locker : filtered) {

                        LockerType type = lockerTypeDAO.getLockerTypeByID(locker.getLockerTypeID());
                        String size = type != null ? type.getLockerTypeSize() : "N/A";

                        VBox card = new VBox(5);
                        card.setPadding(new Insets(15));
                        card.setPrefWidth(600);
                        card.setStyle("""
                            -fx-background-color: rgba(255,255,255,0.85);
                            -fx-background-radius: 12;
                            -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.25), 10,0,0,5);
                        """);

                        Label idLabel = new Label("Locker ID: " + locker.getLockerID());
                        Label sizeLabel = new Label("Size: " + size);
                        Label postal = new Label("Postal Code: " + locker.getLocationPostalCode());
                        Label statusLabel = new Label("Status: " + locker.getLockerStatus());

                        statusLabel.setTextFill(
                                switch (locker.getLockerStatus().toLowerCase()) {
                                    case "available" -> Color.GREEN;
                                    case "occupied" -> Color.RED;
                                    case "reserved" -> Color.BLUE;
                                    default -> Color.ORANGE;
                                }
                        );

                        idLabel.setTextFill(Color.BLACK);
                        sizeLabel.setTextFill(Color.BLACK);
                        postal.setTextFill(Color.BLACK);
                        statusLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));

                        card.getChildren().addAll(idLabel, sizeLabel, postal, statusLabel);
                        lockerList.getChildren().add(card);
                    }

                }

                lockerScroll.setContent(lockerList);
                lockerScroll.setVisible(true);


            } catch (NumberFormatException ex) {
                errorLabel.setText("Invalid Location ID format!");
            }
        });


        backBtn.setOnAction(e -> AppFX.handleManageLocations(stage));
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
        // --- Background setup ---
        Image backgroundImage = new Image(AppFX.class.getResourceAsStream("viewAllLockerTypes.jpg"));
        ImageView backgroundView = new ImageView(backgroundImage);
        backgroundView.setPreserveRatio(false);

        StackPane root = new StackPane();
        double INITIAL_WIDTH = 1300;
        double INITIAL_HEIGHT = 700;
        Scene scene = new Scene(root, INITIAL_WIDTH, INITIAL_HEIGHT);

        backgroundView.fitWidthProperty().bind(scene.widthProperty());
        backgroundView.fitHeightProperty().bind(scene.heightProperty());
        root.getChildren().add(backgroundView);

        // --- Scrollable locker list setup ---
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        scrollPane.setPrefViewportHeight(500); // same visible scroll area height as viewAllUser()

        VBox lockerList = new VBox(15);
        lockerList.setPadding(new Insets(10));
        lockerList.setAlignment(Pos.TOP_CENTER);

        LockerTypeDAO dao = new LockerTypeDAO();
        List<LockerType> types = dao.getAllLockerTypes();

        if (types.isEmpty()) {
            Label noLocker = new Label("No locker types found in the database.");
            noLocker.setFont(Font.font("Arial", FontWeight.BOLD, 16));
            noLocker.setTextFill(Color.WHITE);
            lockerList.getChildren().add(noLocker);
        } else {
            for (LockerType type : types) {
                // --- Card container ---
                VBox card = new VBox(5);
                card.setPadding(new Insets(15));
                card.setPrefWidth(900); // same width as viewAllUser()
                card.setStyle(
                        "-fx-background-color: rgba(255,255,255,0.85); " +
                                "-fx-background-radius: 12; " +
                                "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.25), 10, 0, 0, 5);"
                );
                card.setAlignment(Pos.CENTER_LEFT); // consistent left alignment

                // --- Labels ---
                Label idLbl = new Label("Locker Type ID: " + type.getLockerTypeID());
                Label sizeLbl = new Label("Size: " + type.getLockerTypeSize());
                Label maxWeightLbl = new Label("Max Weight: " + type.getLockerMaxWeight() + " kg");
                Label rateLbl = new Label("Rate: ₱" + type.getLockerRate());

                idLbl.setFont(Font.font("Arial", FontWeight.BOLD, 14));
                sizeLbl.setFont(Font.font("Arial", 14));
                maxWeightLbl.setFont(Font.font("Arial", 14));
                rateLbl.setFont(Font.font("Arial", 14));

                idLbl.setTextFill(Color.BLACK);
                sizeLbl.setTextFill(Color.BLACK);
                maxWeightLbl.setTextFill(Color.BLACK);
                rateLbl.setTextFill(Color.BLACK);

                // --- Consistent label spacing ---
                VBox.setMargin(idLbl, new Insets(0, 0, 0, 10));
                VBox.setMargin(sizeLbl, new Insets(0, 0, 0, 10));
                VBox.setMargin(maxWeightLbl, new Insets(0, 0, 0, 10));
                VBox.setMargin(rateLbl, new Insets(0, 0, 0, 10));

                card.getChildren().addAll(idLbl, sizeLbl, maxWeightLbl, rateLbl);
                lockerList.getChildren().add(card);
            }
        }

        scrollPane.setContent(lockerList);

        // --- Back Button ---
        Button backBtn = new Button("Back to Locker Type Menu");
        backBtn.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        backBtn.setPrefWidth(220);
        backBtn.setPrefHeight(40);
        backBtn.setStyle("-fx-background-color: #003366; -fx-text-fill: white; -fx-background-radius: 8;");
        backBtn.setOnAction(e -> handleManageLockerTypes(stage));

        // --- Layout positioning ---
        VBox content = new VBox(30, scrollPane, backBtn);
        content.setAlignment(Pos.TOP_CENTER);
        content.setPadding(new Insets(230, 20, 40, 20)); // same vertical offset as viewAllUser()

        root.getChildren().add(content);
        StackPane.setAlignment(content, Pos.TOP_CENTER);

        stage.setScene(scene);
        stage.setTitle("View All Locker Types");
        stage.show();
    }

    private static void searchLockerType(Stage stage) {
        // --- Background setup ---
        Image backgroundImage = new Image(AppFX.class.getResourceAsStream("searchLockerTypes.jpg"));
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
        Label idLabel = new Label("Locker Type ID:");
        idLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        idLabel.setTextFill(Color.BLACK);

        TextField idField = new TextField();
        idField.setPromptText("Enter Locker Type ID");
        idField.setPrefWidth(300);
        idField.setMaxWidth(300);
        idField.setStyle("-fx-pref-height: 50px; -fx-font-size: 20px;");

        Label sizeLabel = new Label("Locker Size:");
        sizeLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        sizeLabel.setTextFill(Color.BLACK);

        TextField sizeField = new TextField();
        sizeField.setPromptText("Enter Locker Size");
        sizeField.setPrefWidth(300);
        sizeField.setMaxWidth(300);
        sizeField.setStyle("-fx-pref-height: 50px; -fx-font-size: 20px;");

        Button searchBtn = new Button("Search");
        searchBtn.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        searchBtn.setStyle("-fx-background-color: #003366; -fx-text-fill: white; -fx-background-radius: 8;");
        searchBtn.setPrefWidth(150);
        searchBtn.setPrefHeight(40);

        VBox searchBox = new VBox(10, idLabel, idField, sizeLabel, sizeField, searchBtn);
        searchBox.setAlignment(Pos.TOP_LEFT);
        searchBox.setPadding(new Insets(160, 0, 0, 120));

        // --- Scrollable Results ---
        VBox resultList = new VBox(15);
        resultList.setPadding(new Insets(10));
        resultList.setAlignment(Pos.TOP_CENTER);

        ScrollPane scrollPane = new ScrollPane(resultList);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        scrollPane.setPrefViewportWidth(600);
        scrollPane.setPrefViewportHeight(500);

        VBox scrollContainer = new VBox(scrollPane);
        scrollContainer.setAlignment(Pos.TOP_RIGHT);
        scrollContainer.setPadding(new Insets(150, 0, 0, 100));

        // --- Back Button ---
        Button backBtn = new Button("Back to Locker Type Menu");
        backBtn.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        backBtn.setPrefWidth(220);
        backBtn.setPrefHeight(40);
        backBtn.setStyle("-fx-background-color: #003366; -fx-text-fill: white; -fx-background-radius: 8;");
        backBtn.setOnAction(e -> handleManageLockerTypes(stage));

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
        stage.setTitle("Search Locker Type");
        stage.show();

        // --- Search Button Action ---
        searchBtn.setOnAction(e -> {
            resultList.getChildren().clear();
            LockerTypeDAO dao = new LockerTypeDAO();
            List<LockerType> results = dao.searchLockerTypes(idField.getText().trim(), sizeField.getText().trim());

            if (results.isEmpty()) {
                Label noResult = new Label("No locker types found matching your search.");
                noResult.setFont(Font.font("Arial", FontWeight.BOLD, 16));
                noResult.setTextFill(Color.BLACK);
                noResult.setPadding(new Insets(220, 0, 0, 220));
                resultList.getChildren().add(noResult);
            } else {
                for (LockerType type : results) {
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

                    idLbl.setTextFill(Color.BLACK);
                    sizeLbl.setTextFill(Color.BLACK);
                    maxWeightLbl.setTextFill(Color.BLACK);
                    rateLbl.setTextFill(Color.BLACK);

                    card.getChildren().addAll(idLbl, sizeLbl, maxWeightLbl, rateLbl);
                    resultList.getChildren().add(card);
                }
            }
        });
    }

private static void handleManageLockers(Stage stage) {
        stage.setTitle("Luggage Locker Booking System - Locker Management");

        Image lockerMenu = new Image(AppFX.class.getResourceAsStream("lockerMenu.jpg"));
        ImageView backgroundView = new ImageView(lockerMenu);

        String[] menuLabels = {
                "VIEW ALL LOCKERS",
                "VIEW AVAILABLE LOCKERS",
                "VIEW OCCUPIED LOCKERS",
                "RETURN TO MAIN MENU"
        };

        VBox leftMenu = lockerMenuButton(menuLabels, 0, 2, stage);
        VBox rightMenu = lockerMenuButton(menuLabels, 2, 4, stage);

        StackPane root = new StackPane(backgroundView, leftMenu, rightMenu);
        StackPane.setAlignment(leftMenu, Pos.CENTER_LEFT);
        StackPane.setAlignment(rightMenu, Pos.CENTER_RIGHT);

        Scene scene = new Scene(root, INITIAL_WIDTH, INITIAL_HEIGHT);

        backgroundView.fitWidthProperty().bind(scene.widthProperty());
        backgroundView.fitHeightProperty().bind(scene.heightProperty());
        backgroundView.setPreserveRatio(false);

        stage.setScene(scene);
        stage.show();
    }

private static VBox lockerMenuButton(String[] labels, int start, int end, Stage stage) {

    VBox menu = new VBox(20);
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
        btn.setStyle("-fx-font-weight: bold; -fx-font-size: 16px;");

        switch (label) {

            case "VIEW ALL LOCKERS" ->
                    btn.setOnAction(e -> viewLockers(stage, "all"));

            case "VIEW AVAILABLE LOCKERS" ->
                    btn.setOnAction(e -> viewLockers(stage, "available"));

            case "VIEW OCCUPIED LOCKERS" ->
                    btn.setOnAction(e -> viewLockers(stage, "occupied"));

            case "RETURN TO MAIN MENU" ->
                    btn.setOnAction(e -> new AppFX().start(stage));
        }

        menu.getChildren().add(btn);
    }

    return menu;
}


private static void viewLockers(Stage stage, String filter) {
    StackPane root = new StackPane();
    Scene scene = new Scene(root, INITIAL_WIDTH, INITIAL_HEIGHT);

    // --- Choose background image ---
    String bgImageName = switch (filter.toLowerCase()) {
        case "available" -> "viewAvailableLockers.jpg";
        case "occupied"  -> "viewOccupiedLockers.jpg";
        default -> "viewAllLockers.jpg";
    };

    ImageView bg = new ImageView(new Image(AppFX.class.getResourceAsStream(bgImageName)));
    bg.fitWidthProperty().bind(scene.widthProperty());
    bg.fitHeightProperty().bind(scene.heightProperty());
    bg.setPreserveRatio(false);
    root.getChildren().add(bg);

    // --- Scrollable List ---
    ScrollPane scrollPane = new ScrollPane();
    scrollPane.setFitToWidth(true);
    scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
    scrollPane.setPrefViewportHeight(500);

    VBox lockerList = new VBox(15);
    lockerList.setPadding(new Insets(10));
    lockerList.setAlignment(Pos.TOP_CENTER);

    // --- Load lockers based on filter ---
    LockerDAO lockerDAO = new LockerDAO();
    List<Locker> lockers;

    try {
        lockers = switch (filter.toLowerCase()) {
            case "available" -> lockerDAO.getAvailableLocker();
            case "occupied" -> lockerDAO.getOccupiedLocker();
            default -> lockerDAO.getAllLockers();
        };

        if (lockers.isEmpty()) {
            Label noLocker = new Label("No lockers found.");
            noLocker.setFont(Font.font("Arial", FontWeight.BOLD, 16));
            noLocker.setTextFill(Color.WHITE);
            lockerList.getChildren().add(noLocker);
        }
        else {
            for (Locker locker : lockers) {
                VBox card = new VBox(5);
                card.setPadding(new Insets(15));
                card.setPrefWidth(900);
                card.setStyle("""
                -fx-background-color: rgba(255,255,255,0.85);
                -fx-background-radius: 12;
                -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.25), 10, 0, 0, 5);
            """);

                Label id = new Label("Locker ID: " + locker.getLockerID());
                Label type = new Label("Locker Type ID: " + locker.getLockerTypeID());
                Label loc = new Label("Location ID: " + locker.getLocationID());
                Label postal = new Label("Postal Code: " + locker.getLocationPostalCode());
                Label status = new Label("Status: " + locker.getLockerStatus());

                id.setFont(Font.font("Arial", FontWeight.BOLD, 14));
                status.setFont(Font.font("Arial", FontWeight.BOLD, 14));

                status.setTextFill(
                        switch (locker.getLockerStatus().toLowerCase()) {
                            case "available" -> Color.GREEN;
                            case "occupied" -> Color.RED;
                            case "reserved" -> Color.BLUE;
                            default -> Color.ORANGE;
                        }
                );

                for (Label l : List.of(id, type, loc, postal)) {
                    l.setTextFill(Color.BLACK);
                }

                card.getChildren().addAll(id, type, loc, postal, status);
                lockerList.getChildren().add(card);
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    scrollPane.setContent(lockerList);

    // --- Back Button ---
    Button backBtn = new Button("Return to Locker Menu");
    backBtn.setFont(Font.font("Arial", FontWeight.BOLD, 16));
    backBtn.setStyle("-fx-background-color:#003366; -fx-text-fill:white; -fx-background-radius:8;");
    backBtn.setPrefSize(220, 40);
    backBtn.setOnAction(e -> handleManageLockers(stage));

    // --- Layout ---
    VBox content = new VBox(30, scrollPane, backBtn);
    content.setAlignment(Pos.TOP_CENTER);
    content.setPadding(new Insets(230, 20, 40, 20));

    root.getChildren().add(content);
    StackPane.setAlignment(content, Pos.TOP_CENTER);

    stage.setScene(scene);
    stage.setTitle("Locker Management - " + filter.substring(0, 1).toUpperCase() + filter.substring(1));
    stage.show();
}


private static void handleManageLockerLocations(){

    }

private static void handleBooking(Stage stage) {
    stage.setTitle("Luggage Locker Booking System - Reservation/Booking Management");

    // --- Background ---
    Image userMenuBG = new Image(AppFX.class.getResourceAsStream("bookingMenu.jpg"));
    ImageView backgroundView = new ImageView(userMenuBG);
    backgroundView.setPreserveRatio(false);

    // --- Menu Labels ---
    String[] menuLabels = {
        "MAKE A RESERVATION",
        "CHECK-IN CUSTOMER",
        "VIEW ALL ACTIVE BOOKINGS",
        "RETURN TO MAIN MENU"
    };

    // --- Single vertical menu ---
    VBox centerMenu = bookingMenuButton(menuLabels, 0, menuLabels.length, stage);
    centerMenu.setAlignment(Pos.CENTER); // vertically centered
    centerMenu.setSpacing(20); // spacing between buttons

    // --- Root layout ---
    StackPane root = new StackPane();
    root.getChildren().addAll(backgroundView, centerMenu);
    StackPane.setAlignment(centerMenu, Pos.CENTER); // center alignment

    // --- Scene setup ---
    Scene scene = new Scene(root, INITIAL_WIDTH, INITIAL_HEIGHT);
    backgroundView.fitWidthProperty().bind(scene.widthProperty());
    backgroundView.fitHeightProperty().bind(scene.heightProperty());

    stage.setScene(scene);
    stage.show();
}

private static VBox bookingMenuButton(String[] labels, int start, int end, Stage stage) {
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
                case "MAKE A RESERVATION":
                    btn.setOnAction(e -> reservation(stage));
                    break;
                case "CHECK-IN CUSTOMER":
                    btn.setOnAction(e -> checkIn(stage));
                    break;
                case "VIEW ALL ACTIVE BOOKINGS":
                    btn.setOnAction(e -> viewAllActiveBookings(stage));
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

private static void reservation(Stage stage) {
    // --- DAO instances ---
    UserDAO userDAO = new UserDAO();
    LockerDAO lockerDAO = new LockerDAO();
    LockerTypeDAO lockerTypeDAO = new LockerTypeDAO();
    BookingDAO bookingDAO = new BookingDAO();
    BookingService bookingService = new BookingService(userDAO, bookingDAO, lockerDAO);

    // --- Background ---
    Image backgroundImage = new Image(AppFX.class.getResourceAsStream("reservation.jpg"));
    ImageView backgroundView = new ImageView(backgroundImage);
    backgroundView.setPreserveRatio(false);
    backgroundView.setMouseTransparent(true);
    backgroundView.setFitWidth(1300);
    backgroundView.setFitHeight(700);

    AnchorPane root = new AnchorPane();
    root.getChildren().add(backgroundView);

    double FIELD_WIDTH = 330;
    double FIELD_HEIGHT = 40;
    User[] currentUser = new User[1];

    // --- Input fields ---
    TextField userIDField = new TextField();
    userIDField.setPromptText("Enter User ID");
    userIDField.setPrefSize(FIELD_WIDTH, FIELD_HEIGHT);

    Button fetchBtn = new Button("Fetch User");
    fetchBtn.setPrefSize(120, FIELD_HEIGHT);
    fetchBtn.setStyle("-fx-background-color: #003366; -fx-text-fill: white; -fx-background-radius: 8;");

    Label errorLabel = new Label();
    errorLabel.setStyle("-fx-text-fill: yellow; -fx-font-size: 16px; -fx-font-weight: bold;");
    errorLabel.setWrapText(true);

    Label userInfoLabel = new Label();
    userInfoLabel.setStyle("-fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;");
    userInfoLabel.setWrapText(true);

    Label successInfoLabel = new Label();
    successInfoLabel.setStyle("-fx-text-fill: #00FF00; -fx-font-size: 16px; -fx-font-weight: bold;");
    successInfoLabel.setWrapText(true);
    successInfoLabel.setVisible(false);

    // --- Left Pane ---
    AnchorPane leftPane = new AnchorPane();
    leftPane.setPrefSize(400, 600);
    leftPane.getChildren().addAll(userIDField, fetchBtn, errorLabel, userInfoLabel, successInfoLabel);
    AnchorPane.setTopAnchor(userIDField, 20.0);
    AnchorPane.setLeftAnchor(userIDField, 20.0);
    AnchorPane.setTopAnchor(fetchBtn, 70.0);
    AnchorPane.setLeftAnchor(fetchBtn, 20.0);
    AnchorPane.setTopAnchor(errorLabel, 120.0);
    AnchorPane.setLeftAnchor(errorLabel, 20.0);
    AnchorPane.setTopAnchor(userInfoLabel, 160.0);
    AnchorPane.setLeftAnchor(userInfoLabel, 20.0);
    AnchorPane.setTopAnchor(successInfoLabel, 300.0);
    AnchorPane.setLeftAnchor(successInfoLabel, 20.0);
    leftPane.setLayoutX(10);
    leftPane.setLayoutY(100);

    // --- Locker Scroll ---
    ScrollPane lockerScroll = new ScrollPane();
    lockerScroll.setPrefSize(700, 400);
    lockerScroll.setLayoutX(550);
    lockerScroll.setLayoutY(230);
    lockerScroll.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
    lockerScroll.setVisible(false);

    // --- Locker Title ---
    Label lockerTitle = new Label("Select Available Lockers");
    lockerTitle.setStyle("-fx-text-fill: white; -fx-font-size: 24px; -fx-font-weight: bold;");
    lockerTitle.setLayoutX(560);
    lockerTitle.setLayoutY(185);
    lockerTitle.setVisible(false);

    // --- Back Button ---
    Button backBtn = new Button("Back to Booking Menu");
    backBtn.setFont(Font.font("Arial", FontWeight.BOLD, 16));
    backBtn.setPrefSize(200, 40);
    backBtn.setStyle("-fx-background-color: #003366; -fx-text-fill: white; -fx-background-radius: 8;");
    backBtn.setLayoutX(1050);
    backBtn.setLayoutY(630);

    root.getChildren().addAll(leftPane, lockerScroll, lockerTitle, backBtn);

    Scene scene = new Scene(root, 1300, 700);
    stage.setScene(scene);
    stage.setTitle("Make a Reservation");
    stage.show();

    // --- Fetch Logic ---
    fetchBtn.setOnAction(e -> {
        errorLabel.setText("");
        userInfoLabel.setText("");
        lockerTitle.setVisible(false);
        lockerScroll.setVisible(false);

        String input = userIDField.getText().trim();
        if (input.isEmpty()) {
            errorLabel.setText("Please enter a User ID!");
            successInfoLabel.setVisible(false);
            return;
        }

        try {
            int id = Integer.parseInt(input);
            User user = userDAO.getUserById(id);

            if (user != null) {
                currentUser[0] = user;
                userInfoLabel.setText(
                    "User ID: " + user.getUserID() + "\n" +
                    "Name: " + user.getFirstName() + " " + user.getLastName() + "\n" +
                    "Contact: " + (user.getUserContact() != null ? user.getUserContact() : "N/A") + "\n" +
                    "Email: " + (user.getUserEmail() != null ? user.getUserEmail() : "N/A")
                );

                lockerTitle.setVisible(true);
                successInfoLabel.setVisible(false);

                VBox lockerList = new VBox(15);
                lockerList.setPadding(new Insets(10));
                lockerList.setAlignment(Pos.TOP_CENTER);

                List<Locker> availableLockers = lockerDAO.getAvailableLocker();
                if (availableLockers.isEmpty()) {
                    Label noLocker = new Label("No available lockers at the moment.");
                    noLocker.setFont(Font.font("Arial", FontWeight.BOLD, 16));
                    noLocker.setTextFill(Color.WHITE);
                    noLocker.setLayoutX(800);
                    noLocker.setLayoutY(350);
                    root.getChildren().add(noLocker);

                    lockerTitle.setVisible(false);
                } else {
                    for (Locker locker : availableLockers) {
                        HBox card = new HBox(15);
                        card.setPadding(new Insets(15));
                        card.setPrefWidth(600);
                        card.setAlignment(Pos.CENTER_LEFT);
                        card.setStyle(
                            "-fx-background-color: rgba(255,255,255,0.85); -fx-background-radius: 12;" +
                            "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.25), 10,0,0,5);"
                        );

                        LockerType type = lockerTypeDAO.getLockerTypeByID(locker.getLockerTypeID());
                        String lockerSize = type != null ? type.getLockerTypeSize() : "N/A";

                        VBox info = new VBox(5);
                        Label idLabel = new Label("Locker ID: " + locker.getLockerID());
                        Label typeLabel = new Label("Locker Size: " + lockerSize);
                        Label location = new Label("Location ID: " + locker.getLocationID());
                        Label postal = new Label("Postal Code: " + locker.getLocationPostalCode());
                        idLabel.setTextFill(Color.BLACK);
                        typeLabel.setTextFill(Color.BLACK);
                        location.setTextFill(Color.BLACK);
                        postal.setTextFill(Color.BLACK);
                        info.getChildren().addAll(idLabel, typeLabel, location, postal);

                        Button checkBtn = new Button("✓");
                        checkBtn.setFont(Font.font("Arial", FontWeight.BOLD, 16));
                        checkBtn.setStyle("-fx-background-color: transparent; -fx-text-fill: green;");
                        checkBtn.setCursor(Cursor.HAND);

                        checkBtn.setOnAction(ev -> {
                            double fee = 0;
                            switch (lockerSize.toLowerCase()) {
                                case "small": fee = 80; break;
                                case "medium": fee = 120; break;
                                case "large": fee = 180; break;
                            }

                            Booking booking = bookingService.makeReservation(
                                currentUser[0].getUserID(),
                                locker.getLockerID(),
                                fee
                            );

                            if (booking != null) {
                                card.setStyle("-fx-background-color: rgba(200,255,200,0.85); -fx-background-radius: 12;");
                                checkBtn.setDisable(true);
                                String reservationDate = booking.getReservationDate(); 
                                String formattedDate = reservationDate; 
                                LocalDateTime dt = LocalDateTime.parse(reservationDate); 
                                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm"); 
                                formattedDate = dt.format(formatter);

                                successInfoLabel.setText(
                                    "Reserved Successfully!\n" +
                                    "Locker ID: " + locker.getLockerID() + "\n" +
                                    "Locker Size: " + lockerSize + "\n" +
                                    "Reservation Fee: ₱" + fee + "\n" +
                                    "Reservation Date: " + formattedDate + "\n" +
                                    "Booking Status: " + booking.getBookingStatus() + "\n" +
                                    "Booking Reference: " + booking.getBookingReference()
                                );
                                successInfoLabel.setVisible(true);
                            } else {
                                successInfoLabel.setVisible(false);
                            }
                        });

                        Region spacer = new Region();
                        HBox.setHgrow(spacer, Priority.ALWAYS);
                        card.getChildren().addAll(info, spacer, checkBtn);
                        lockerList.getChildren().add(card);
                    }
                }

                lockerScroll.setContent(lockerList);
                lockerScroll.setVisible(true);

            } else {
                errorLabel.setText("No user found with ID " + id + ".");
                successInfoLabel.setVisible(false);
            }

        } catch (NumberFormatException ex) {
            errorLabel.setText("Invalid User ID format!");
            successInfoLabel.setVisible(false);
        }
    });

    backBtn.setOnAction(e -> AppFX.handleBooking(stage));
}

private static void checkIn(Stage stage) {
    // --- Background setup ---
    Image backgroundImage = new Image(AppFX.class.getResourceAsStream("checkIn.jpg"));
    ImageView backgroundView = new ImageView(backgroundImage);
    backgroundView.setPreserveRatio(false);

    StackPane root = new StackPane();
    double INITIAL_WIDTH = 1300;
    double INITIAL_HEIGHT = 700;
    Scene scene = new Scene(root, INITIAL_WIDTH, INITIAL_HEIGHT);

    backgroundView.fitWidthProperty().bind(scene.widthProperty());
    backgroundView.fitHeightProperty().bind(scene.heightProperty());
    root.getChildren().add(backgroundView);

    // --- Scrollable booking list setup ---
    ScrollPane scrollPane = new ScrollPane();
    scrollPane.setFitToWidth(true);
    scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
    scrollPane.setPrefViewportHeight(500);

    VBox bookingList = new VBox(15);
    bookingList.setPadding(new Insets(10));
    bookingList.setAlignment(Pos.TOP_CENTER);

    // --- DAOs and service ---
    BookingDAO bookingDAO = new BookingDAO();
    LockerDAO lockerDAO = new LockerDAO();
    LockerTypeDAO lockerTypeDAO = new LockerTypeDAO();
    UserDAO userDAO = new UserDAO();
    BookingService bookingService = new BookingService(userDAO, bookingDAO, lockerDAO);

    // --- Load all pending bookings ---
    List<Booking> pendingBookings = bookingDAO.getPendingCheckInBookings();

    if (pendingBookings.isEmpty()) {
        Label noBooking = new Label("No pending Check-In Reservations.");
        noBooking.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        noBooking.setTextFill(Color.WHITE);

        StackPane wrapper = new StackPane(noBooking);
        wrapper.setPrefWidth(900); 
        StackPane.setAlignment(noBooking, Pos.TOP_CENTER); 
        StackPane.setMargin(noBooking, new Insets(120, 0, 0, 0)); 

        bookingList.getChildren().add(wrapper);
    } else {
        for (Booking booking : pendingBookings) {
            HBox card = new HBox(15);
            card.setPadding(new Insets(15));
            card.setPrefWidth(900);
            card.setAlignment(Pos.CENTER_LEFT);
            card.setStyle(
                "-fx-background-color: rgba(255,255,255,0.85); " +
                "-fx-background-radius: 12; " +
                "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.25), 10, 0, 0, 5);"
            );

            // --- Fetch user and locker info ---
            User user = userDAO.getUserById(booking.getUserID());
            Locker locker = lockerDAO.getLockerByID(booking.getLockerID());
            LockerType lockerType = lockerTypeDAO.getLockerTypeByID(locker.getLockerTypeID());
            String lockerSize = lockerType != null ? lockerType.getLockerTypeSize() : "Unknown";

           // --- Build info label using TextFlow ---
            Text bookingRefText = new Text("Booking Reference: " + booking.getBookingReference() + "\n");
            bookingRefText.setFont(Font.font("Arial", FontWeight.BOLD, 14));
            bookingRefText.setFill(Color.BLACK);

            Text statusText = new Text(
                    "Booking Status: " + booking.getBookingStatus() + "\n" +
                    "Reservation Date: " + booking.getReservationDate() + "\n" +
                    "User ID: " + user.getUserID() + "\n" +
                    "User Name: " + user.getFirstName() + " " + user.getLastName() + "\n" +
                    "Locker ID: " + locker.getLockerID() + " [" + lockerSize + "]"
            );
            statusText.setFont(Font.font("Arial", 14));
            statusText.setFill(Color.BLACK);

            TextFlow infoFlow = new TextFlow(bookingRefText, statusText);

            // --- Check-In button ---
            Button checkInBtn = new Button("✔ Check-In");
            checkInBtn.setFont(Font.font("Arial", FontWeight.BOLD, 16));
            checkInBtn.setStyle("-fx-background-color: #28a745; -fx-text-fill: white; -fx-background-radius: 8;");
            checkInBtn.setCursor(Cursor.HAND);

            checkInBtn.setOnAction(e -> {
                boolean success = bookingService.checkIn(booking.getBookingReference());
                if (success) {
                     Booking updatedBooking = bookingDAO.getBookingByReference(booking.getBookingReference());
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Check-In Success");
                    alert.setHeaderText("Checked-In Successfully!");
                    alert.setContentText(
                            "User ID: " + user.getUserID() + "\n" +
                            "User Name: " + user.getFirstName() + " " + user.getLastName() + "\n" +
                            "Locker ID: " + locker.getLockerID() + " [" + lockerSize + "]\n" +
                            "Check-In Date: " + updatedBooking.getCheckInTime()  + "\n" +
                            "Booking Status: Checked-In\n" +
                            "Booking Reference: " + booking.getBookingReference()
                    );
                    alert.showAndWait();
                    bookingList.getChildren().remove(card);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Failed to check-in.");
                    alert.showAndWait();
                }
            });

            Region spacer = new Region();
            HBox.setHgrow(spacer, Priority.ALWAYS);

            card.getChildren().addAll(infoFlow, spacer, checkInBtn);
            bookingList.getChildren().add(card);
        }
    }

    scrollPane.setContent(bookingList);

    // --- Back Button ---
    Button backBtn = new Button("Back to Booking Menu");
    backBtn.setFont(Font.font("Arial", FontWeight.BOLD, 16));
    backBtn.setPrefWidth(200);
    backBtn.setPrefHeight(40);
    backBtn.setStyle("-fx-background-color: #003366; -fx-text-fill: white; -fx-background-radius: 8;");
    backBtn.setOnAction(e -> handleBooking(stage));

    // --- Layout positioning ---
    VBox content = new VBox(20, scrollPane, backBtn);
    content.setAlignment(Pos.TOP_CENTER);
    content.setPadding(new Insets(200, 20, 40, 20));

    root.getChildren().add(content);
    StackPane.setAlignment(content, Pos.TOP_CENTER);

    stage.setScene(scene);
    stage.setTitle("Check-In Customer");
    stage.show();
}

private static void viewAllActiveBookings(Stage stage) {
    // --- Background setup ---
    Image backgroundImage = new Image(AppFX.class.getResourceAsStream("viewBookings.jpg"));
    ImageView backgroundView = new ImageView(backgroundImage);
    backgroundView.setPreserveRatio(false);

    StackPane root = new StackPane();
    double INITIAL_WIDTH = 1300;
    double INITIAL_HEIGHT = 700;
    Scene scene = new Scene(root, INITIAL_WIDTH, INITIAL_HEIGHT);

    backgroundView.fitWidthProperty().bind(scene.widthProperty());
    backgroundView.fitHeightProperty().bind(scene.heightProperty());
    root.getChildren().add(backgroundView);

    // --- Scrollable booking list setup ---
    ScrollPane scrollPane = new ScrollPane();
    scrollPane.setFitToWidth(true);
    scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
    scrollPane.setPrefViewportHeight(500);

    VBox bookingList = new VBox(15);
    bookingList.setPadding(new Insets(10));
    bookingList.setAlignment(Pos.TOP_CENTER);

    // --- DAOs and service ---
    BookingDAO bookingDAO = new BookingDAO();
    LockerDAO lockerDAO = new LockerDAO();
    LockerTypeDAO lockerTypeDAO = new LockerTypeDAO();
    UserDAO userDAO = new UserDAO();

    // --- Load all active bookings ---
    List<Booking> activeBookings = bookingDAO.getCheckedInBookings();

    if (activeBookings.isEmpty()) {
        Label noBooking = new Label("No Active Bookings.");
        noBooking.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        noBooking.setTextFill(Color.WHITE);

        StackPane wrapper = new StackPane(noBooking);
        wrapper.setPrefWidth(900);
        StackPane.setAlignment(noBooking, Pos.TOP_CENTER);
        StackPane.setMargin(noBooking, new Insets(120, 0, 0, 0));

        bookingList.getChildren().add(wrapper);
    } else {
        for (Booking booking : activeBookings) {
            HBox card = new HBox(15);
            card.setPadding(new Insets(15));
            card.setPrefWidth(900);
            card.setAlignment(Pos.CENTER_LEFT);
            card.setStyle(
                    "-fx-background-color: rgba(255,255,255,0.85); " +
                    "-fx-background-radius: 12; " +
                    "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.25), 10, 0, 0, 5);"
            );

            // --- Fetch user info ---
            User user = userDAO.getUserById(booking.getUserID());

            // --- Determine latest locker (consider transfers) ---
            LockerTransfer latestTransfer = LockerTransferDAO.getLatestTransferForBooking(booking.getBookingReference());
            int displayLockerID = (latestTransfer != null) ? latestTransfer.getNewLockerID() : booking.getLockerID();
            Locker locker = lockerDAO.getLockerByID(displayLockerID);

            LockerType lockerType = lockerTypeDAO.getLockerTypeByID(locker.getLockerTypeID());
            String lockerSize = lockerType != null ? lockerType.getLockerTypeSize() : "Unknown";

            // --- Build info label ---
            Text bookingRefText = new Text("Booking Reference: " + booking.getBookingReference() + "\n");
            bookingRefText.setFont(Font.font("Arial", FontWeight.BOLD, 14));
            bookingRefText.setFill(Color.BLACK);

            Text statusText = new Text(
                    "Booking Status: " + booking.getBookingStatus() + "\n" +
                    "Check-In Date: " + booking.getCheckInTime() + "\n" +
                    "User ID: " + user.getUserID() + "\n" +
                    "User Name: " + user.getFirstName() + " " + user.getLastName() + "\n" +
                    "Locker ID: " + locker.getLockerID() + " [" + lockerSize + "]"
            );
            statusText.setFont(Font.font("Arial", 14));
            statusText.setFill(Color.BLACK);

            TextFlow infoFlow = new TextFlow(bookingRefText, statusText);
            Region spacer = new Region();
            HBox.setHgrow(spacer, Priority.ALWAYS);

            card.getChildren().addAll(infoFlow, spacer);
            bookingList.getChildren().add(card);
        }
    }

    scrollPane.setContent(bookingList);

    // --- Back Button ---
    Button backBtn = new Button("Back to Booking Menu");
    backBtn.setFont(Font.font("Arial", FontWeight.BOLD, 16));
    backBtn.setPrefWidth(200);
    backBtn.setPrefHeight(40);
    backBtn.setStyle("-fx-background-color: #003366; -fx-text-fill: white; -fx-background-radius: 8;");
    backBtn.setOnAction(e -> handleBooking(stage));

    // --- Layout positioning ---
    VBox content = new VBox(20, scrollPane, backBtn);
    content.setAlignment(Pos.TOP_CENTER);
    content.setPadding(new Insets(200, 20, 40, 20));

    root.getChildren().add(content);
    StackPane.setAlignment(content, Pos.TOP_CENTER);

    stage.setScene(scene);
    stage.setTitle("Check-In Customer");
    stage.show();
}

private static void handleCancellations(Stage stage){
    stage.setTitle("Luggage Locker Booking System - Cancellation Management");

    Image lockerMenu = new Image(AppFX.class.getResourceAsStream("manageCancellations.jpg"));
    ImageView backgroundView = new ImageView(lockerMenu);

    String[] menuLabels = {
            "VIEW ALL CANCELLATIONS",
            "CANCEL RESERVATION",
            "RETURN TO MAIN MENU"
    };

    VBox leftMenu = cancellationMenuButton(menuLabels, 0, 2, stage);
    VBox rightMenu = cancellationMenuButton(menuLabels, 2, 4, stage);

    StackPane root = new StackPane(backgroundView, leftMenu, rightMenu);
    StackPane.setAlignment(leftMenu, Pos.CENTER_LEFT);
    StackPane.setAlignment(rightMenu, Pos.CENTER_RIGHT);

    Scene scene = new Scene(root, INITIAL_WIDTH, INITIAL_HEIGHT);

    backgroundView.fitWidthProperty().bind(scene.widthProperty());
    backgroundView.fitHeightProperty().bind(scene.heightProperty());
    backgroundView.setPreserveRatio(false);

    stage.setScene(scene);
    stage.show();
}

private static VBox cancellationMenuButton(String[] labels, int start, int end, Stage stage){
    VBox menu = new VBox(20);
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
        btn.setStyle("-fx-font-weight: bold; -fx-font-size: 16px;");

        switch (label) {
            case "VIEW ALL CANCELLATIONS" ->
                    btn.setOnAction(e -> viewAllCancellations(stage));

            case "CANCEL RESERVATION" ->
                    btn.setOnAction(e -> cancelAReservation(stage));

            case "RETURN TO MAIN MENU" ->
                    btn.setOnAction(e -> new AppFX().start(stage));
        }

        menu.getChildren().add(btn);
    }

    return menu;

}

private static void viewAllCancellations(Stage stage) {
    StackPane root = new StackPane();
    Scene scene = new Scene(root, INITIAL_WIDTH, INITIAL_HEIGHT);

    Image bg = new Image(AppFX.class.getResourceAsStream("viewCancellation.jpg"));
    ImageView bgView = new ImageView(bg);
    bgView.fitWidthProperty().bind(scene.widthProperty());
    bgView.fitHeightProperty().bind(scene.heightProperty());
    bgView.setPreserveRatio(false);
    root.getChildren().add(bgView);

    ScrollPane scrollPane = new ScrollPane();
    scrollPane.setFitToWidth(true);
    scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
    scrollPane.setPrefViewportHeight(500);

    VBox list = new VBox(15);
    list.setPadding(new Insets(10));
    list.setAlignment(Pos.TOP_CENTER);

    CancellationDAO dao = new CancellationDAO();
    List<Cancellation> cancellations = dao.getAllCancellations();

    if (cancellations.isEmpty()) {
        Label none = new Label("No cancellation records found.");
        none.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        none.setTextFill(Color.WHITE);
        list.getChildren().add(none);
    }
    else {
        for (Cancellation c : cancellations) {

            VBox card = new VBox(6);
            card.setPadding(new Insets(15));
            card.setPrefWidth(900);
            card.setStyle("""
                -fx-background-color: rgba(255,255,255,0.85);
                -fx-background-radius: 12;
                -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.25), 10, 0, 0, 5);
            """);

            Label id = new Label("Cancellation ID: " + c.getCancellationID());
            Label ref = new Label("Booking Reference: " + c.getBookingReference());
            Label date = new Label("Cancelled On: " + c.getCancelDate());
            Label reason = new Label("Reason: " + c.getReason());
            Label refund = new Label("Refund Fee: ₱" + c.getRefundFee());

            id.setFont(Font.font("Arial", FontWeight.BOLD, 14));

            for (Label l : List.of(id, ref, date, reason, refund)) {
                l.setFont(Font.font("Arial", 14));
                l.setTextFill(Color.BLACK);
            }

            card.getChildren().addAll(id, ref, date, reason, refund);
            list.getChildren().add(card);
        }
    }

    scrollPane.setContent(list);

    Button backBtn = new Button("Return to Cancellation Menu");
    backBtn.setFont(Font.font("Arial", FontWeight.BOLD, 16));
    backBtn.setStyle("-fx-background-color:#003366; -fx-text-fill:white; -fx-background-radius:8;");
    backBtn.setPrefSize(250, 40);
    backBtn.setOnAction(e -> handleCancellations(stage));

    VBox content = new VBox(30, scrollPane, backBtn);
    content.setAlignment(Pos.TOP_CENTER);
    content.setPadding(new Insets(230, 20, 40, 20));

    root.getChildren().add(content);
    StackPane.setAlignment(content, Pos.TOP_CENTER);

    stage.setScene(scene);
    stage.setTitle("Cancellation Records");
    stage.show();
}

    private static void cancelAReservation(Stage stage) {

        StackPane root = new StackPane();
        Scene scene = new Scene(root, INITIAL_WIDTH, INITIAL_HEIGHT);

        Image bg = new Image(AppFX.class.getResourceAsStream("cancelReservation.jpg"));
        ImageView bgView = new ImageView(bg);
        bgView.fitWidthProperty().bind(scene.widthProperty());
        bgView.fitHeightProperty().bind(scene.heightProperty());
        bgView.setPreserveRatio(false);
        root.getChildren().add(bgView);

        HBox mainLayout = new HBox(50);
        mainLayout.setPadding(new Insets(50, 0, 0, 0));
        mainLayout.setAlignment(Pos.TOP_CENTER);

        //LEFT PANEL
        VBox leftPanel = new VBox(20);
        leftPanel.setAlignment(Pos.TOP_CENTER);

        Label reasonTitle = new Label("Enter Cancellation Reason:");
        reasonTitle.setFont(Font.font("Arial", FontWeight.BOLD, 22));
        reasonTitle.setTextFill(Color.BLACK);

        TextField reasonField = new TextField();
        reasonField.setPromptText("Type cancellation reason...");
        reasonField.setPrefWidth(350);

        Label errorLabel = new Label("");
        errorLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        errorLabel.setTextFill(Color.RED);

        leftPanel.getChildren().addAll(reasonTitle, reasonField, errorLabel);


        //RIGHT PANEL
        VBox rightPanel = new VBox(20);
        rightPanel.setAlignment(Pos.TOP_CENTER);

        Label rightTitle = new Label("Select a Booking to Cancel");
        rightTitle.setFont(Font.font("Arial", FontWeight.BOLD, 22));
        rightTitle.setTextFill(Color.BLACK);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        scrollPane.setPrefViewportHeight(500);

        VBox bookingList = new VBox(15);
        bookingList.setAlignment(Pos.TOP_CENTER);

        scrollPane.setContent(bookingList);

        rightPanel.getChildren().addAll(rightTitle, scrollPane);

        // FETCH BOOKINGS
        BookingDAO bookingDAO = new BookingDAO();
        LockerDAO lockerDAO = new LockerDAO();
        UserDAO userDAO = new UserDAO();
        CancellationDAO cancellationDAO = new CancellationDAO();

        List<Booking> pendingBookings = bookingDAO.getPendingCheckInBookings();

        if (pendingBookings.isEmpty()) {
            Label none = new Label("No reservations available for cancellation.");
            none.setFont(Font.font("Arial", FontWeight.BOLD, 18));
            none.setTextFill(Color.BLACK);
            bookingList.getChildren().add(none);
        } else {
            for (Booking booking : pendingBookings) {

                //CARD CONTAINER
                HBox card = new HBox(15);
                card.setPadding(new Insets(15));
                card.setPrefWidth(600);
                card.setAlignment(Pos.CENTER_LEFT);
                card.setStyle("""
                -fx-background-color: rgba(255,255,255,0.90);
                -fx-background-radius: 12;
                -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.25), 10, 0, 0, 5);
            """);

                //FETCH USER + LOCKER INFO
                User user = userDAO.getUserById(booking.getUserID());
                Locker locker = lockerDAO.getLockerByID(booking.getLockerID());
                LockerTypeDAO lockerTypeDAO = new LockerTypeDAO();
                String size = lockerTypeDAO.getLockerTypeByID(locker.getLockerTypeID()).getLockerTypeSize();

                //LABELS
                Label info = new Label(
                        "Booking Ref: " + booking.getBookingReference() + "\n" +
                                "User: " + user.getFirstName() + " " + user.getLastName() + "\n" +
                                "Locker: " + locker.getLockerID() + " (" + size + ")\n" +
                                "Reservation Date: " + booking.getReservationDate()
                );
                info.setTextFill(Color.BLACK);
                info.setFont(Font.font("Arial", 14));

                //CANCEL BUTTON
                Button cancelBtn = new Button("✖ Cancel");
                cancelBtn.setFont(Font.font("Arial", FontWeight.BOLD, 14));
                cancelBtn.setStyle("-fx-background-color: #c62828; -fx-text-fill: white; -fx-background-radius: 8;");
                cancelBtn.setCursor(Cursor.HAND);

                cancelBtn.setOnAction(e -> {

                    // Validate reason
                    String reason = reasonField.getText().trim();
                    if (reason.isEmpty()) {
                        errorLabel.setText("Please enter a cancellation reason.");
                        return;
                    }

                    // Check if too late to cancel
                    try {
                        if (booking.getCheckInTime() != null) {
                            LocalDateTime checkIn = LocalDateTime.parse(booking.getCheckInTime());
                            if (LocalDateTime.now().isAfter(checkIn.minusHours(1))) {
                                errorLabel.setText("Cancellation allowed only 1 hour before check-in.");
                                return;
                            }
                        }
                    } catch (Exception ex) {
                        errorLabel.setText("Stored date format invalid.");
                        return;
                    }

                    // Perform cancellation
                    booking.setBookingStatus("Cancelled");
                    bookingDAO.updateBooking(booking);

                    lockerDAO.updateLockerStatus(booking.getLockerID(), "Available");

                    cancellationDAO.addCancellation(
                            booking.getBookingReference(),
                            LocalDateTime.now().toString(),
                            reason,
                            booking.getReservationFee()
                    );

                    errorLabel.setTextFill(Color.GREEN);
                    errorLabel.setText("Booking cancelled successfully!");
                    bookingList.getChildren().remove(card);
                });

                Region spacer = new Region();
                HBox.setHgrow(spacer, Priority.ALWAYS);

                card.getChildren().addAll(info, spacer, cancelBtn);
                bookingList.getChildren().add(card);
            }
        }

        //BACK BUTTON
        Button backBtn = new Button("Back to Cancellation Menu");
        backBtn.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        backBtn.setPrefWidth(300);
        backBtn.setStyle("-fx-background-color:#003366; -fx-text-fill:white; -fx-background-radius:8;");
        backBtn.setOnAction(e -> handleCancellations(stage));


        VBox finalLayout = new VBox(20, mainLayout, backBtn);
        finalLayout.setAlignment(Pos.TOP_CENTER);
        finalLayout.setPadding(new Insets(240, 0, 40, 0));
        root.getChildren().add(finalLayout);

        mainLayout.getChildren().addAll(leftPanel, rightPanel);

        stage.setScene(scene);
        stage.setTitle("Cancel Reservation");
        stage.show();
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
                    btn.setOnAction(e -> releaseLockerAndProcessPayment(stage));
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

    // RELEASE LOCKER (PROCESS PAYMENT)
    private static void releaseLockerAndProcessPayment(Stage stage) {
        Image backgroundImage = new Image(AppFX.class.getResourceAsStream("selectBooking.jpg"));
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

        VBox bookingList = new VBox(15);
        bookingList.setPadding(new Insets(10));
        bookingList.setAlignment(Pos.TOP_CENTER);

        BookingDAO bookingDAO = new BookingDAO();
        LockerDAO lockerDAO = new LockerDAO();
        LockerTypeDAO lockerTypeDAO = new LockerTypeDAO();
        UserDAO userDAO = new UserDAO();
        PaymentDAO paymentDAO = new PaymentDAO();

        List<Booking> checkedInBookings = bookingDAO.getCheckedInBookings();

        if (checkedInBookings.isEmpty()) {
            Label noBooking = new Label("No Checked-In Bookings.");
            noBooking.setFont(Font.font("Arial", FontWeight.BOLD, 16));
            noBooking.setTextFill(Color.WHITE);

            StackPane wrapper = new StackPane(noBooking);
            wrapper.setPrefWidth(900);
            StackPane.setAlignment(noBooking, Pos.TOP_CENTER);
            StackPane.setMargin(noBooking, new Insets(120, 0, 0, 0));

            bookingList.getChildren().add(wrapper);
        } else {
            for (Booking booking : checkedInBookings) {
                HBox card = new HBox(15);
                card.setPadding(new Insets(15));
                card.setPrefWidth(900);
                card.setAlignment(Pos.CENTER_LEFT);
                card.setStyle(
                        "-fx-background-color: rgba(255,255,255,0.85); " +
                                "-fx-background-radius: 12; " +
                                "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.25), 10, 0, 0, 5);"
                );

                // --- Fetch user and locker info ---
                User user = userDAO.getUserById(booking.getUserID());
                Locker locker = lockerDAO.getLockerByID(booking.getLockerID());
                LockerType lockerType = lockerTypeDAO.getLockerTypeByID(locker.getLockerTypeID());
                String lockerSize = lockerType != null ? lockerType.getLockerTypeSize() : "Unknown";

                // --- Build info label using TextFlow ---
                Text bookingRefText = new Text("Booking Reference: " + booking.getBookingReference() + "\n");
                bookingRefText.setFont(Font.font("Arial", FontWeight.BOLD, 14));
                bookingRefText.setFill(Color.BLACK);

                Text statusText = new Text(
                        "Booking Status: " + booking.getBookingStatus() + "\n" +
                                "Check-In Date: " + booking.getCheckInTime() + "\n" +
                                "User ID: " + user.getUserID() + "\n" +
                                "User Name: " + user.getFirstName() + " " + user.getLastName() + "\n" +
                                "Locker ID: " + locker.getLockerID() + " [" + lockerSize + "]"
                );
                statusText.setFont(Font.font("Arial", 14));
                statusText.setFill(Color.BLACK);

                TextFlow infoFlow = new TextFlow(bookingRefText, statusText);

                // --- Payment Method Dropdown ---
                ComboBox<String> paymentMethodCombo = new ComboBox<>();
                paymentMethodCombo.getItems().addAll("Credit Card", "E-wallet");
                paymentMethodCombo.setValue("Credit Card");
                paymentMethodCombo.setPrefWidth(120);

                VBox paymentBox = new VBox(5, paymentMethodCombo);
                paymentBox.setAlignment(Pos.CENTER);

                // --- Process Payment Button ---
                Button processBtn = new Button("Process Payment");
                processBtn.setFont(Font.font("Arial", FontWeight.BOLD, 14));
                processBtn.setStyle("-fx-background-color: #28a745; -fx-text-fill: white; -fx-background-radius: 8;");
                processBtn.setCursor(Cursor.HAND);

                processBtn.setOnAction(e -> {
                    String selectedMethod = paymentMethodCombo.getValue();
                    LocalDateTime paymentTime = LocalDateTime.now(); // this is both checkout & payment time

                    // --- Update booking checkout time and status ---
                    booking.setCheckOutTime(paymentTime.format(Booking.FORMATTER));
                    booking.setBookingStatus("Checked-out");
                    bookingDAO.updateBooking(booking);

                    // --- Update locker status ---
                    locker.setLockerStatus("Available");
                    lockerDAO.updateLockerStatus(locker.getLockerID(), "Available");
                    /*
                    // --- Calculate total fee using paymentTime as checkout ---
                    double hours = booking.calculateDurationHours(); // rounded up
                    double rate = lockerType != null ? lockerType.getLockerRate() : 0;
                    double totalFee = (hours * rate) - booking.getReservationFee();
                    if (totalFee < 0) totalFee = 0;
                    */
                    // --- Calculate total fee using paymentTime as checkout ---
                    double hours = booking.calculateDurationHours(); // already rounded up
                    double rate = lockerType != null ? lockerType.getLockerRate() : 0;
                    double totalUsageFee = hours * rate; // no extra ceil
                    double remainingFee = totalUsageFee - booking.getReservationFee();
                    if (remainingFee < 0) remainingFee = 0;
                    double totalFee = booking.getReservationFee() + remainingFee;

                    // --- Insert payment ---
                    // Create Payment object (paymentID can be 0 as placeholder)
                    Payment payment = new Payment(
                            0,
                            booking.getBookingReference(),
                            booking.getUserID(),
                            totalFee,
                            paymentMethodCombo.getValue(),
                            "Paid",
                            Timestamp.valueOf(paymentTime)
                    );

                    payment = paymentDAO.addPayment(payment); // paymentID is now set

                    // Generate receipt
                    releaseLockerReceiptPage(payment);
                    bookingList.getChildren().remove(card);
                });

                Region spacer = new Region();
                HBox.setHgrow(spacer, Priority.ALWAYS);

                card.getChildren().addAll(infoFlow, spacer, paymentBox, processBtn);
                bookingList.getChildren().add(card);
            }
        }

        scrollPane.setContent(bookingList);

        // --- Back Button ---
        Button backBtn = new Button("Back to Booking Menu");
        backBtn.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        backBtn.setPrefWidth(200);
        backBtn.setPrefHeight(40);
        backBtn.setStyle("-fx-background-color: #003366; -fx-text-fill: white; -fx-background-radius: 8;");
        backBtn.setOnAction(e -> handlePayments(stage));

        // --- Layout positioning ---
        VBox content = new VBox(20, scrollPane, backBtn);
        content.setAlignment(Pos.TOP_CENTER);
        content.setPadding(new Insets(200, 20, 40, 20));

        root.getChildren().add(content);
        StackPane.setAlignment(content, Pos.TOP_CENTER);

        stage.setScene(scene);
        stage.setTitle("Release Locker & Process Payment");
        stage.show();
    }

    private static void releaseLockerReceiptPage(Payment payment) {
        Stage receiptStage = new Stage();
        receiptStage.setTitle("Payment Receipt");
        receiptStage.initModality(Modality.APPLICATION_MODAL);

        StackPane root = new StackPane();
        root.setPadding(new Insets(20));
        Scene scene = new Scene(root, 500, 450);

        VBox content = new VBox(15);
        content.setPadding(new Insets(20));
        content.setAlignment(Pos.TOP_LEFT);

        BookingDAO bookingDAO = new BookingDAO();
        LockerDAO lockerDAO = new LockerDAO();
        LockerTypeDAO lockerTypeDAO = new LockerTypeDAO();
        UserDAO userDAO = new UserDAO();

        Booking booking = bookingDAO.getBookingByReference(payment.getBookingReference());
        User user = userDAO.getUserById(payment.getUserID());
        Locker locker = lockerDAO.getLockerByID(booking.getLockerID());
        LockerType lockerType = lockerTypeDAO.getLockerTypeByID(locker.getLockerTypeID());

        String lockerSize = lockerType != null ? lockerType.getLockerTypeSize() : "Unknown";

        double reservationFee = booking.getReservationFee();
        double remainingFee = payment.getPaymentAmount() - reservationFee; // compute remaining
        double totalFee = payment.getPaymentAmount();

        Label title = new Label("Payment Receipt");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        title.setTextFill(Color.DEEPPINK);

// --- Add Payment ID ---
        Label paymentIDLabel = new Label("Payment ID: " + payment.getPaymentID());
        paymentIDLabel.setFont(Font.font("Arial", 16));
        paymentIDLabel.setTextFill(Color.BLACK);

        Label bookingRef = new Label("Booking Reference: " + payment.getBookingReference());
        Label userInfo = new Label("User: " + user.getFirstName() + " " + user.getLastName() + " (ID: " + user.getUserID() + ")");
        Label lockerInfo = new Label("Locker: " + locker.getLockerID() + " [" + lockerSize + "]");
        Label duration = new Label("Check-In: " + booking.getCheckInTime() + "\nCheck-Out: " + booking.getCheckOutTime());

        Label feeBreakdown = new Label(
                String.format(
                        "Reservation Fee: ₱%.2f\nRemaining Amount: ₱%.2f\nTotal Amount Paid: ₱%.2f",
                        reservationFee,
                        remainingFee,
                        totalFee
                )
        );

        Label paymentInfo = new Label(
                "Payment Method: " + payment.getPaymentMethod() + "\n" +
                        "Payment Status: " + payment.getPaymentStatus() + "\n" +
                        "Payment Date: " + payment.getPaymentDate()
        );

        for (Label l : new Label[]{paymentIDLabel, bookingRef, userInfo, lockerInfo, duration, feeBreakdown, paymentInfo}) {
            l.setFont(Font.font("Arial", 16));
            l.setTextFill(Color.BLACK);
        }

        Button closeBtn = new Button("Close");
        closeBtn.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        closeBtn.setStyle("-fx-background-color: #003366; -fx-text-fill: white; -fx-background-radius: 8;");
        closeBtn.setPrefWidth(100);
        closeBtn.setOnAction(e -> receiptStage.close());

        content.getChildren().addAll(title, paymentIDLabel, bookingRef, userInfo, lockerInfo, duration, feeBreakdown, paymentInfo, closeBtn);

        root.getChildren().add(content);

        receiptStage.setScene(scene);
        receiptStage.showAndWait(); // modal pop-up
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
        // --- Background setup ---
        Image backgroundImage = new Image(AppFX.class.getResourceAsStream("lockerTransferBG.jpg"));
        ImageView backgroundView = new ImageView(backgroundImage);
        backgroundView.setPreserveRatio(false);

        StackPane root = new StackPane();
        double INITIAL_WIDTH = 1300;
        double INITIAL_HEIGHT = 700;
        Scene scene = new Scene(root, INITIAL_WIDTH, INITIAL_HEIGHT);

        backgroundView.fitWidthProperty().bind(scene.widthProperty());
        backgroundView.fitHeightProperty().bind(scene.heightProperty());
        root.getChildren().add(backgroundView);

        // --- Form Labels and Fields ---
        Label bookingLabel = new Label("Booking Reference:");
        bookingLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        bookingLabel.setTextFill(Color.BLACK);

        TextField bookingField = new TextField();
        bookingField.setPromptText("Enter Booking Reference");
        bookingField.setPrefWidth(300);
        bookingField.setMaxWidth(300);
        bookingField.setStyle("-fx-pref-height: 50px; -fx-font-size: 20px;");

        Label oldLockerLabel = new Label("Old Locker ID:");
        oldLockerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        oldLockerLabel.setTextFill(Color.BLACK);

        TextField oldLockerField = new TextField();
        oldLockerField.setPromptText("Enter Old Locker ID");
        oldLockerField.setPrefWidth(300);
        oldLockerField.setMaxWidth(300);
        oldLockerField.setStyle("-fx-pref-height: 50px; -fx-font-size: 20px;");

        Label newLockerLabel = new Label("Select New Locker:");
        newLockerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        newLockerLabel.setTextFill(Color.BLACK);

        // --- ComboBox for available lockers ---
        ComboBox<Locker> lockerCombo = new ComboBox<>();
        lockerCombo.setPrefWidth(300);
        lockerCombo.setMaxWidth(300);
        lockerCombo.setStyle("-fx-pref-height: 50px; -fx-font-size: 20px;");

        LockerDAO lockerDAO = new LockerDAO();
        LockerTypeDAO lockerTypeDAO = new LockerTypeDAO();
        List<Locker> availableLockers = lockerDAO.getAvailableLocker();
        lockerCombo.getItems().addAll(availableLockers);

        lockerCombo.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Locker locker, boolean empty) {
                super.updateItem(locker, empty);
                if (empty || locker == null) {
                    setText(null);
                } else {
                    LockerType lt = lockerTypeDAO.getLockerTypeByID(locker.getLockerTypeID());
                    setText("ID " + locker.getLockerID() + " [" + (lt != null ? lt.getLockerTypeSize() : "Unknown") + "]");
                }
            }
        });
        lockerCombo.setButtonCell(lockerCombo.getCellFactory().call(null));

        // --- Adjustment Fee ---
        Label amountLabel = new Label("Adjustment Amount:");
        amountLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        amountLabel.setTextFill(Color.BLACK);

        TextField amountField = new TextField();
        amountField.setPrefWidth(300);
        amountField.setMaxWidth(300);
        amountField.setStyle("-fx-pref-height: 50px; -fx-font-size: 20px;");
        amountField.setEditable(false);

        // Auto-update fee
        lockerCombo.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                LockerType lt = lockerTypeDAO.getLockerTypeByID(newVal.getLockerTypeID());
                double fee = 0;

                if (lt != null) {
                    switch (lt.getLockerTypeSize()) {
                        case "Small":  fee = 40; break;
                        case "Medium": fee = 60; break;
                        case "Large":  fee = 80; break;
                    }
                }
                amountField.setText(String.valueOf(fee));
            } else {
                amountField.setText("");
            }
        });

        // --- Buttons ---
        Button submitBtn = new Button("Submit Transfer");
        submitBtn.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        submitBtn.setStyle("-fx-background-color: #003366; -fx-text-fill: white; -fx-background-radius: 8;");
        submitBtn.setPrefWidth(180);
        submitBtn.setPrefHeight(40);

        Button backBtn = new Button("Back to Transfer Menu");
        backBtn.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        backBtn.setStyle("-fx-background-color: #003366; -fx-text-fill: white; -fx-background-radius: 8;");
        backBtn.setPrefWidth(180);
        backBtn.setPrefHeight(40);
        backBtn.setOnAction(e -> handleTransfers(stage));

        // --- Form Layout ---
        VBox fieldsBox = new VBox(15,
                bookingLabel, bookingField,
                oldLockerLabel, oldLockerField,
                newLockerLabel, lockerCombo,
                amountLabel, amountField
        );
        fieldsBox.setAlignment(Pos.TOP_LEFT);
        fieldsBox.setPadding(new Insets(200, 0, 0, 120));

        HBox buttonsBox = new HBox(20, submitBtn, backBtn);
        buttonsBox.setAlignment(Pos.CENTER_LEFT);
        buttonsBox.setPadding(new Insets(30, 50, 0, 120));

        VBox formContainer = new VBox(fieldsBox, buttonsBox);
        formContainer.setAlignment(Pos.TOP_LEFT);

        VBox rootContent = new VBox(formContainer);
        rootContent.setAlignment(Pos.TOP_LEFT);
        rootContent.setPadding(new Insets(50, 20, 40, 20));

        root.getChildren().add(rootContent);
        StackPane.setAlignment(rootContent, Pos.TOP_CENTER);

        stage.setScene(scene);
        stage.setTitle("Perform Locker Transfer");
        stage.show();

        // --- Submit Button Action ---
        submitBtn.setOnAction(e -> {
            try {
                String bookingRef = bookingField.getText().trim();
                int oldLockerID = Integer.parseInt(oldLockerField.getText().trim());
                Locker selectedLocker = lockerCombo.getValue();

                if (selectedLocker == null) {
                    new Alert(Alert.AlertType.WARNING, "Please select a new locker.").showAndWait();
                    return;
                }

                int newLockerID = selectedLocker.getLockerID();

                // --- #2 Prevent overlapping locker IDs ---
                if (oldLockerID == newLockerID) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Invalid Transfer");
                    alert.setHeaderText(null);
                    alert.setContentText("Old Locker ID and New Locker ID cannot be the same.");
                    alert.showAndWait();
                    return;
                }

                double adjAmount = Double.parseDouble(amountField.getText());

                LockerTransfer transfer = new LockerTransfer(
                        bookingRef,
                        LocalDateTime.now(),
                        adjAmount,
                        oldLockerID,
                        newLockerID
                );

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
                alert.setContentText("Please enter valid numbers.");
                alert.showAndWait();
            }
        });
    }

    private static void viewAllTransfers(Stage stage) {
        // --- Background setup ---
        Image backgroundImage = new Image(AppFX.class.getResourceAsStream("viewAllTransfers.jpg"));
        ImageView backgroundView = new ImageView(backgroundImage);
        backgroundView.setPreserveRatio(false);

        StackPane root = new StackPane();
        double INITIAL_WIDTH = 1300;
        double INITIAL_HEIGHT = 700;
        Scene scene = new Scene(root, INITIAL_WIDTH, INITIAL_HEIGHT);

        backgroundView.fitWidthProperty().bind(scene.widthProperty());
        backgroundView.fitHeightProperty().bind(scene.heightProperty());
        root.getChildren().add(backgroundView);

        // --- Scrollable transfer list setup ---
        VBox transferList = new VBox(15);
        transferList.setPadding(new Insets(10));
        transferList.setAlignment(Pos.TOP_CENTER);

        List<LockerTransfer> transfers = LockerTransferDAO.getAllTransfers();

        if (transfers.isEmpty()) {
            Label noTransfers = new Label("No transfers found in the database.");
            noTransfers.setFont(Font.font("Arial", FontWeight.BOLD, 16));
            noTransfers.setTextFill(Color.WHITE);
            transferList.getChildren().add(noTransfers);
        } else {
            for (LockerTransfer t : transfers) {
                // --- Card container ---
                VBox card = new VBox(5);
                card.setPadding(new Insets(15));
                card.setPrefWidth(900);
                card.setStyle(
                    "-fx-background-color: rgba(255,255,255,0.85); " +
                    "-fx-background-radius: 12; " +
                    "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.25), 10, 0, 0, 5);"
                );
                card.setAlignment(Pos.CENTER_LEFT);

                // --- Labels ---
                Label idLbl = new Label("Transfer ID: " + t.getTransferID());
                Label bookingLbl = new Label("Booking Reference: " + t.getBookingReference());
                Label dateLbl = new Label("Date: " + t.getTransferDate());
                Label oldLockerLbl = new Label("Old Locker ID: " + t.getOldLockerID());
                Label newLockerLbl = new Label("New Locker ID: " + t.getNewLockerID());
                Label adjLbl = new Label("Adjustment Amount: ₱" + t.getAdjustmentAmount());

                idLbl.setFont(Font.font("Arial", FontWeight.BOLD, 14));
                bookingLbl.setFont(Font.font("Arial", 14));
                dateLbl.setFont(Font.font("Arial", 14));
                oldLockerLbl.setFont(Font.font("Arial", 14));
                newLockerLbl.setFont(Font.font("Arial", 14));
                adjLbl.setFont(Font.font("Arial", 14));

                idLbl.setTextFill(Color.BLACK);
                bookingLbl.setTextFill(Color.BLACK);
                dateLbl.setTextFill(Color.BLACK);
                oldLockerLbl.setTextFill(Color.BLACK);
                newLockerLbl.setTextFill(Color.BLACK);
                adjLbl.setTextFill(Color.BLACK);

                // --- Consistent label spacing ---
                VBox.setMargin(idLbl, new Insets(0, 0, 0, 10));
                VBox.setMargin(bookingLbl, new Insets(0, 0, 0, 10));
                VBox.setMargin(dateLbl, new Insets(0, 0, 0, 10));
                VBox.setMargin(oldLockerLbl, new Insets(0, 0, 0, 10));
                VBox.setMargin(newLockerLbl, new Insets(0, 0, 0, 10));
                VBox.setMargin(adjLbl, new Insets(0, 0, 0, 10));

                card.getChildren().addAll(idLbl, bookingLbl, dateLbl, oldLockerLbl, newLockerLbl, adjLbl);
                transferList.getChildren().add(card);
            }
        }

        ScrollPane scrollPane = new ScrollPane(transferList);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        scrollPane.setPrefViewportHeight(500);

        // --- Back Button ---
        Button backBtn = new Button("Back to Transfer Menu");
        backBtn.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        backBtn.setPrefWidth(220);
        backBtn.setPrefHeight(40);
        backBtn.setStyle("-fx-background-color: #003366; -fx-text-fill: white; -fx-background-radius: 8;");
        backBtn.setOnAction(e -> handleTransfers(stage));

        // --- Layout positioning ---
        VBox content = new VBox(30, scrollPane, backBtn);
        content.setAlignment(Pos.TOP_CENTER);
        content.setPadding(new Insets(230, 20, 40, 20)); // same vertical offset as viewAllLockerTypes()

        root.getChildren().add(content);
        StackPane.setAlignment(content, Pos.TOP_CENTER);

        stage.setScene(scene);
        stage.setTitle("View All Locker Transfers");
        stage.show();
    }


    private static void searchTransferByID(Stage stage) {
        // --- Background setup ---
        Image backgroundImage = new Image(AppFX.class.getResourceAsStream("searchLockerTransfer.jpg"));
        ImageView backgroundView = new ImageView(backgroundImage);
        backgroundView.setPreserveRatio(false);

        StackPane root = new StackPane();
        double INITIAL_WIDTH = 1300;
        double INITIAL_HEIGHT = 700;
        Scene scene = new Scene(root, INITIAL_WIDTH, INITIAL_HEIGHT);

        backgroundView.fitWidthProperty().bind(scene.widthProperty());
        backgroundView.fitHeightProperty().bind(scene.heightProperty());
        root.getChildren().add(backgroundView);

        // --- Search Input Fields ---
        Label idLabel = new Label("Transfer ID:");
        idLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        idLabel.setTextFill(Color.BLACK);

        TextField idField = new TextField();
        idField.setPromptText("Enter Transfer ID");
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
        searchBox.setPadding(new Insets(160, 0, 0, 120));

        // --- Scrollable Results ---
        VBox resultList = new VBox(15);
        resultList.setPadding(new Insets(10));
        resultList.setAlignment(Pos.TOP_CENTER);

        ScrollPane scrollPane = new ScrollPane(resultList);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        scrollPane.setPrefViewportWidth(600);
        scrollPane.setPrefViewportHeight(500);

        VBox scrollContainer = new VBox(scrollPane);
        scrollContainer.setAlignment(Pos.TOP_RIGHT);
        scrollContainer.setPadding(new Insets(150, 0, 0, 100));

        // --- Back Button ---
        Button backBtn = new Button("Back to Transfer Menu");
        backBtn.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        backBtn.setPrefWidth(220);
        backBtn.setPrefHeight(40);
        backBtn.setStyle("-fx-background-color: #003366; -fx-text-fill: white; -fx-background-radius: 8;");
        backBtn.setOnAction(e -> handleTransfers(stage));

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
        stage.setTitle("Search Transfer by ID");
        stage.show();

        // --- Search Button Action ---
        searchBtn.setOnAction(e -> {
            resultList.getChildren().clear();
            try {
                int id = Integer.parseInt(idField.getText().trim());
                LockerTransfer transfer = LockerTransferDAO.getTransferByID(id);

                if (transfer == null) {
                    Label noResult = new Label("No transfer found for ID " + id);
                    noResult.setFont(Font.font("Arial", FontWeight.BOLD, 16));
                    noResult.setTextFill(Color.BLACK);
                    noResult.setPadding(new Insets(220, 0, 0, 220));
                    resultList.getChildren().add(noResult);
                } else {
                    VBox card = new VBox(5);
                    card.setPadding(new Insets(15));
                    card.setPrefWidth(900);
                    card.setStyle(
                            "-fx-background-color: rgba(255,255,255,0.85); " +
                            "-fx-background-radius: 12; " +
                            "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.25), 10, 0, 0, 5);"
                    );
                    card.setAlignment(Pos.CENTER_LEFT);

                    Label idLbl = new Label("Transfer ID: " + transfer.getTransferID());
                    Label bookingLbl = new Label("Booking Reference: " + transfer.getBookingReference());
                    Label dateLbl = new Label("Date: " + transfer.getTransferDate());
                    Label oldLockerLbl = new Label("Old Locker ID: " + transfer.getOldLockerID());
                    Label newLockerLbl = new Label("New Locker ID: " + transfer.getNewLockerID());
                    Label adjLbl = new Label("Adjustment Amount: ₱" + transfer.getAdjustmentAmount());

                    idLbl.setFont(Font.font("Arial", FontWeight.BOLD, 14));
                    bookingLbl.setFont(Font.font("Arial", 14));
                    dateLbl.setFont(Font.font("Arial", 14));
                    oldLockerLbl.setFont(Font.font("Arial", 14));
                    newLockerLbl.setFont(Font.font("Arial", 14));
                    adjLbl.setFont(Font.font("Arial", 14));

                    idLbl.setTextFill(Color.BLACK);
                    bookingLbl.setTextFill(Color.BLACK);
                    dateLbl.setTextFill(Color.BLACK);
                    oldLockerLbl.setTextFill(Color.BLACK);
                    newLockerLbl.setTextFill(Color.BLACK);
                    adjLbl.setTextFill(Color.BLACK);

                    VBox.setMargin(idLbl, new Insets(0, 0, 0, 10));
                    VBox.setMargin(bookingLbl, new Insets(0, 0, 0, 10));
                    VBox.setMargin(dateLbl, new Insets(0, 0, 0, 10));
                    VBox.setMargin(oldLockerLbl, new Insets(0, 0, 0, 10));
                    VBox.setMargin(newLockerLbl, new Insets(0, 0, 0, 10));
                    VBox.setMargin(adjLbl, new Insets(0, 0, 0, 10));

                    card.getChildren().addAll(idLbl, bookingLbl, dateLbl, oldLockerLbl, newLockerLbl, adjLbl);
                    resultList.getChildren().add(card);
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

    private static void handleReports(Stage stage){
        stage.setTitle("Luggage Locker Booking System - Generate Reports");

        // --- Background ---
        Image userMenuBG = new Image(AppFX.class.getResourceAsStream("reportMenu.jpg"));
        ImageView backgroundView = new ImageView(userMenuBG);
        backgroundView.setPreserveRatio(false);

        // --- Menu Labels ---
        String[] menuLabels = {
            "LOCKER OCCUPANCY REPORT",
            "CANCELED BOOKINGS REPORT",
            "PAYMENT TRANSACTIONS REPORT",
            "REVENUE REPORT",
            "RETURN TO MAIN MENU"
        };

        // --- Single vertical menu ---
        VBox centerMenu = reportMenuButton(menuLabels, 0, menuLabels.length, stage);
        centerMenu.setAlignment(Pos.CENTER); // vertically centered
        centerMenu.setSpacing(20); // spacing between buttons

        // --- Root layout ---
        StackPane root = new StackPane();
        root.getChildren().addAll(backgroundView, centerMenu);
        StackPane.setAlignment(centerMenu, Pos.CENTER); // center alignment

        // --- Scene setup ---
        Scene scene = new Scene(root, INITIAL_WIDTH, INITIAL_HEIGHT);
        backgroundView.fitWidthProperty().bind(scene.widthProperty());
        backgroundView.fitHeightProperty().bind(scene.heightProperty());

        stage.setScene(scene);
        stage.show();
    }

    private static VBox reportMenuButton(String[] labels, int start, int end, Stage stage) {
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
                case "LOCKER OCCUPANCY REPORT":
                    btn.setOnAction(e -> occupancyReports(stage));
                    break;
                case "CANCELED BOOKINGS REPORT":
                    btn.setOnAction(e -> canceledBookingsReport(stage));
                    break;
                case "PAYMENT TRANSACTIONS REPORT":
                    btn.setOnAction(e -> paymentTransactionReport(stage));
                    break;
                case "REVENUE REPORT":
                    btn.setOnAction(e -> revenueReports(stage));
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

private static void occupancyReports(Stage stage) {
    stage.setTitle("Luggage Locker Booking System - Occupancy Reports");

    Image userMenuBG = new Image(AppFX.class.getResourceAsStream("occupancyReport.jpg"));
    ImageView backgroundView = new ImageView(userMenuBG);
    backgroundView.setPreserveRatio(false);

    String[] menuLabels = {
            "GROUPED BY LOCKER SIZE",
            "GROUPED BY LOCKER LOCATION",
            "RETURN TO REPORTS MENU"
    };

    VBox centerMenu = occupancyMenuButton(menuLabels, 0, menuLabels.length, stage);
    centerMenu.setAlignment(Pos.CENTER);
    centerMenu.setSpacing(20);

    StackPane root = new StackPane();
    root.getChildren().addAll(backgroundView, centerMenu);
    StackPane.setAlignment(centerMenu, Pos.CENTER);

    Scene scene = new Scene(root, INITIAL_WIDTH, INITIAL_HEIGHT);
    backgroundView.fitWidthProperty().bind(scene.widthProperty());
    backgroundView.fitHeightProperty().bind(scene.heightProperty());

    stage.setScene(scene);
    stage.show();
}

private static VBox occupancyMenuButton(String[] labels, int start, int end, Stage stage) {
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
            case "GROUPED BY LOCKER SIZE":
                btn.setOnAction(e -> occReportSize(stage));
                break;
            case "GROUPED BY LOCKER LOCATION":
                btn.setOnAction(e -> occReportLocation(stage));
                break;
            case "RETURN TO REPORTS MENU":
                btn.setOnAction(e -> handleReports(stage));
                break;
        }
        menu.getChildren().add(btn);
    }
    return menu;
}

private static void occReportSize(Stage stage) {
    stage.setTitle("Occupancy Reports (Locker Size)");

    Image bg = new Image(AppFX.class.getResourceAsStream("occupancySizeReport.jpg"));
    ImageView backgroundView = new ImageView(bg);
    backgroundView.setPreserveRatio(false);

    StackPane root = new StackPane(backgroundView);
    backgroundView.fitWidthProperty().bind(root.widthProperty());
    backgroundView.fitHeightProperty().bind(root.heightProperty());

    BorderPane mainPane = new BorderPane();
    root.getChildren().add(mainPane);

    HBox topBox = new HBox(10);
    topBox.setPadding(new Insets(200, 20, 20, 100));
    topBox.setAlignment(Pos.CENTER_LEFT);

    Label yearLabel = new Label("Select Year:");
    yearLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: white;");

    ComboBox<Integer> yearCombo = new ComboBox<>();
    for (int y = 2000; y <= Year.now().getValue(); y++) yearCombo.getItems().add(y);
    yearCombo.setValue(Year.now().getValue());

    topBox.getChildren().addAll(yearLabel, yearCombo);
    mainPane.setTop(topBox);

    VBox centerBox = new VBox(15);
    centerBox.setAlignment(Pos.TOP_CENTER);
    centerBox.setPadding(new Insets(20));
    mainPane.setCenter(centerBox);

    HBox bottomBox = new HBox();
    bottomBox.setPadding(new Insets(20));
    bottomBox.setAlignment(Pos.BOTTOM_RIGHT);
    Button backBtn = new Button("Back");
    backBtn.setOnAction(e -> occupancyReports(stage));
    bottomBox.getChildren().add(backBtn);
    mainPane.setBottom(bottomBox);

    OccupancyReportDAO dao = new OccupancyReportDAO();

    yearCombo.setOnAction(e -> {
        int selectedYear = yearCombo.getValue();
        centerBox.getChildren().clear();

        List<OccupancyReport> list = dao.getOccupancyBySize(selectedYear);

        if (list.isEmpty()) {
            Label noReport = new Label("No Occupancy Report for Year " + selectedYear);
            noReport.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: white;");
            VBox.setMargin(noReport, new Insets(90, 0, 0, 0));
            centerBox.getChildren().add(noReport);
        } else {
            Label title = new Label(selectedYear + " Occupancy Report (By Locker Size)");
            title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: black;");
            centerBox.getChildren().add(title);

            TableView<OccupancyReport> table = new TableView<>();
            table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

            TableColumn<OccupancyReport, String> colSize = new TableColumn<>("Locker Size");
            colSize.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getLockerSize()));

            TableColumn<OccupancyReport, String> colTotal = new TableColumn<>("Total Bookings");
            colTotal.setCellValueFactory(data -> new SimpleStringProperty(
                    String.valueOf(data.getValue().getTotalBookings())
            ));

            table.getColumns().addAll(colSize, colTotal);
            colSize.prefWidthProperty().bind(table.widthProperty().multiply(0.5));
            colTotal.prefWidthProperty().bind(table.widthProperty().multiply(0.5));

            table.setItems(FXCollections.observableArrayList(list));

            StackPane tableWrapper = new StackPane(table);
            tableWrapper.setPrefWidth(700);
            tableWrapper.setMinWidth(700);
            tableWrapper.setMaxWidth(700);
            tableWrapper.setAlignment(Pos.CENTER);

            centerBox.setPadding(new Insets(70, 20, 20, 20));
            centerBox.getChildren().add(tableWrapper);
        }
    });

    yearCombo.getOnAction().handle(null);

    Scene scene = new Scene(root, INITIAL_WIDTH, INITIAL_HEIGHT);
    stage.setScene(scene);
    stage.show();
}

private static void occReportLocation(Stage stage) {
    stage.setTitle("Occupancy Reports (Locker Location)");

    Image bg = new Image(AppFX.class.getResourceAsStream("occupancyLocationReport.jpg"));
    ImageView backgroundView = new ImageView(bg);
    backgroundView.setPreserveRatio(false);

    StackPane root = new StackPane(backgroundView);
    backgroundView.fitWidthProperty().bind(root.widthProperty());
    backgroundView.fitHeightProperty().bind(root.heightProperty());

    BorderPane mainPane = new BorderPane();
    root.getChildren().add(mainPane);

    HBox topBox = new HBox(10);
    topBox.setPadding(new Insets(200, 20, 20, 100));
    topBox.setAlignment(Pos.CENTER_LEFT);

    Label yearLabel = new Label("Select Year:");
    yearLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: white;");

    ComboBox<Integer> yearCombo = new ComboBox<>();
    for (int y = 2000; y <= Year.now().getValue(); y++) yearCombo.getItems().add(y);
    yearCombo.setValue(Year.now().getValue());

    topBox.getChildren().addAll(yearLabel, yearCombo);
    mainPane.setTop(topBox);

    VBox centerBox = new VBox(15);
    centerBox.setAlignment(Pos.TOP_CENTER);
    centerBox.setPadding(new Insets(20));
    mainPane.setCenter(centerBox);

    HBox bottomBox = new HBox();
    bottomBox.setPadding(new Insets(20));
    bottomBox.setAlignment(Pos.BOTTOM_RIGHT);
    Button backBtn = new Button("Back");
    backBtn.setOnAction(e -> occupancyReports(stage));
    bottomBox.getChildren().add(backBtn);
    mainPane.setBottom(bottomBox);

    OccupancyReportDAO dao = new OccupancyReportDAO();

    yearCombo.setOnAction(e -> {
        int selectedYear = yearCombo.getValue();
        centerBox.getChildren().clear();

        List<OccupancyReport> list = dao.getOccupancyByLocation(selectedYear);

        if (list.isEmpty()) {
            Label noReport = new Label("No Occupancy Report for Year " + selectedYear);
            noReport.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: white;");
            VBox.setMargin(noReport, new Insets(90, 0, 0, 0));
            centerBox.getChildren().add(noReport);
        } else {
            Label title = new Label(selectedYear + " Occupancy Report (By Location)");
            title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: white;");
            centerBox.getChildren().add(title);

            TableView<OccupancyReport> table = new TableView<>();
            table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

            TableColumn<OccupancyReport, String> colLoc = new TableColumn<>("Location");
            colLoc.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getLockerLocation()));

            TableColumn<OccupancyReport, String> colTotal = new TableColumn<>("Total Bookings");
            colTotal.setCellValueFactory(data -> new SimpleStringProperty(
                    String.valueOf(data.getValue().getTotalBookings())
            ));

            table.getColumns().addAll(colLoc, colTotal);
            colLoc.prefWidthProperty().bind(table.widthProperty().multiply(0.5));
            colTotal.prefWidthProperty().bind(table.widthProperty().multiply(0.5));

            table.setItems(FXCollections.observableArrayList(list));

            StackPane tableWrapper = new StackPane(table);
            tableWrapper.setPrefWidth(700);
            tableWrapper.setMinWidth(700);
            tableWrapper.setMaxWidth(700);
            tableWrapper.setAlignment(Pos.CENTER);

            centerBox.setPadding(new Insets(30, 20, 20, 20));
            centerBox.getChildren().add(tableWrapper);
        }
    });

    yearCombo.getOnAction().handle(null);

    Scene scene = new Scene(root, INITIAL_WIDTH, INITIAL_HEIGHT);
    stage.setScene(scene);
    stage.show();
}

    private static void paymentTransactionReport(Stage stage) {
        stage.setTitle("Payment Transaction Report");

        Image bg = new Image(AppFX.class.getResourceAsStream("paymentReportBG.jpg"));
        ImageView bgView = new ImageView(bg);
        bgView.setPreserveRatio(false);

        StackPane root = new StackPane();
        root.getChildren().add(bgView);
        bgView.fitWidthProperty().bind(root.widthProperty());
        bgView.fitHeightProperty().bind(root.heightProperty());

        BorderPane mainPane = new BorderPane();
        root.getChildren().add(mainPane);

        HBox topBox = new HBox(15);
        topBox.setAlignment(Pos.CENTER_LEFT);
        topBox.setPadding(new Insets(200, 20, 20, 100)); 
        Label startLabel = new Label("Start Date:");
        startLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: white;");

        DatePicker startPicker = new DatePicker();
        startPicker.setPromptText("Start");

        Label endLabel = new Label("End Date:");
        endLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: white;");

        DatePicker endPicker = new DatePicker();
        endPicker.setPromptText("End");

        Button generateBtn = new Button("Generate");
        generateBtn.setStyle("-fx-background-color: #FF69B4; -fx-text-fill: white;");

        topBox.getChildren().addAll(startLabel, startPicker, endLabel, endPicker, generateBtn);
        mainPane.setTop(topBox);

        VBox centerBox = new VBox(20);
        centerBox.setAlignment(Pos.TOP_CENTER);
        centerBox.setPadding(new Insets(50, 20, 20, 20));
        mainPane.setCenter(centerBox);

        HBox bottomBox = new HBox();
        bottomBox.setAlignment(Pos.BOTTOM_RIGHT);
        bottomBox.setPadding(new Insets(20));

        Button backBtn = new Button("Back");
        backBtn.setOnAction(e -> handleReports(stage)); 
        bottomBox.getChildren().add(backBtn);

        mainPane.setBottom(bottomBox);

        PaymentReportDAO dao = new PaymentReportDAO();

        generateBtn.setOnAction(e -> {
            centerBox.getChildren().clear();

            if (startPicker.getValue() == null || endPicker.getValue() == null) {
                Label warn = new Label("Please select BOTH start and end dates.");
                warn.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: white;");
                centerBox.getChildren().add(warn);
                return;
            }

            Timestamp startTS = Timestamp.valueOf(startPicker.getValue().atStartOfDay());
            Timestamp endTS = Timestamp.valueOf(endPicker.getValue().atTime(23, 59, 59));

            List<PaymentReportEntry> result = dao.getPaymentsByDateRange(startTS, endTS);

            if (result.isEmpty()) {
                Label noData = new Label("No Payment Transactions Found");
                noData.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: white;");
                centerBox.getChildren().add(noData);
                return;
            }

            TableView<PaymentReportEntry> table = new TableView<>();
            table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

            TableColumn<PaymentReportEntry, Integer> colID = new TableColumn<>("Payment ID");
            colID.setCellValueFactory(new PropertyValueFactory<>("paymentID"));

            TableColumn<PaymentReportEntry, String> colBR = new TableColumn<>("Booking Ref");
            colBR.setCellValueFactory(new PropertyValueFactory<>("bookingReference"));

            TableColumn<PaymentReportEntry, Integer> colUID = new TableColumn<>("User ID");
            colUID.setCellValueFactory(new PropertyValueFactory<>("userID"));

            TableColumn<PaymentReportEntry, String> colName = new TableColumn<>("User Name");
            colName.setCellValueFactory(new PropertyValueFactory<>("userName"));

            TableColumn<PaymentReportEntry, Double> colAmt = new TableColumn<>("Amount Paid");
            colAmt.setCellValueFactory(new PropertyValueFactory<>("paymentAmount"));

            TableColumn<PaymentReportEntry, String> colMeth = new TableColumn<>("Method");
            colMeth.setCellValueFactory(new PropertyValueFactory<>("paymentMethod"));

            TableColumn<PaymentReportEntry, String> colStat = new TableColumn<>("Status");
            colStat.setCellValueFactory(new PropertyValueFactory<>("paymentStatus"));

            TableColumn<PaymentReportEntry, Timestamp> colDate = new TableColumn<>("Payment Date");
            colDate.setCellValueFactory(new PropertyValueFactory<>("paymentDate"));

            table.getColumns().addAll(colID, colBR, colUID, colName, colAmt, colMeth, colStat, colDate);

            table.setItems(FXCollections.observableArrayList(result));

            StackPane tableWrapper = new StackPane();
            tableWrapper.setPrefWidth(900);
            tableWrapper.setMaxWidth(900);
            tableWrapper.getChildren().add(table);

            centerBox.getChildren().add(tableWrapper);
        });

        Scene scene = new Scene(root, INITIAL_WIDTH, INITIAL_HEIGHT);
        stage.setScene(scene);
        stage.show();
    }

    private static void revenueReports(Stage stage){
        stage.setTitle("Luggage Locker Booking System - Revenue Reports");

        // --- Background ---
        Image userMenuBG = new Image(AppFX.class.getResourceAsStream("revenueMenu.jpg"));
        ImageView backgroundView = new ImageView(userMenuBG);
        backgroundView.setPreserveRatio(false);

        // --- Menu Labels ---
        String[] menuLabels = {
            "GROUPED BY LOCKER SIZE",
            "GROUPED BY LOCKER LOCATION",
            "RETURN TO REPORTS MENU"
        };

        // --- Single vertical menu ---
        VBox centerMenu = revenueMenuButton(menuLabels, 0, menuLabels.length, stage);
        centerMenu.setAlignment(Pos.CENTER); // vertically centered
        centerMenu.setSpacing(20); // spacing between buttons

        // --- Root layout ---
        StackPane root = new StackPane();
        root.getChildren().addAll(backgroundView, centerMenu);
        StackPane.setAlignment(centerMenu, Pos.CENTER); // center alignment

        // --- Scene setup ---
        Scene scene = new Scene(root, INITIAL_WIDTH, INITIAL_HEIGHT);
        backgroundView.fitWidthProperty().bind(scene.widthProperty());
        backgroundView.fitHeightProperty().bind(scene.heightProperty());

        stage.setScene(scene);
        stage.show();
    }

     private static VBox revenueMenuButton(String[] labels, int start, int end, Stage stage) {
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
                case "GROUPED BY LOCKER SIZE":
                    btn.setOnAction(e -> revReportSize(stage));
                    break;
                case "GROUPED BY LOCKER LOCATION":
                    btn.setOnAction(e -> revReportLocation(stage));
                    break;
                case "RETURN TO REPORTS MENU":
                    btn.setOnAction(e -> handleReports(stage));
                    break;
            }
            menu.getChildren().add(btn);
        }
    return menu;
}

private static void revReportSize(Stage stage) {
    stage.setTitle("Revenue Reports (Locker Size)");

    // --- Background ---
    Image userMenuBG = new Image(AppFX.class.getResourceAsStream("revRepSize.jpg"));
    ImageView backgroundView = new ImageView(userMenuBG);
    backgroundView.setPreserveRatio(false);

    // --- Root StackPane ---
    StackPane root = new StackPane();
    root.getChildren().add(backgroundView);
    backgroundView.fitWidthProperty().bind(root.widthProperty());
    backgroundView.fitHeightProperty().bind(root.heightProperty());

    // --- Main BorderPane ---
    BorderPane mainPane = new BorderPane();
    root.getChildren().add(mainPane);

    // --- Top: Year selection ---
    HBox topBox = new HBox(10);
    topBox.setPadding(new Insets(20));
    topBox.setAlignment(Pos.CENTER_LEFT);

    Label yearLabel = new Label("Select Year:");
    yearLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: white;");
    ComboBox<Integer> yearCombo = new ComboBox<>();
    for (int y = 2000; y <= 2025; y++) yearCombo.getItems().add(y);
    yearCombo.setValue(2025);
    topBox.setPadding(new Insets(200, 20, 20, 100));
    topBox.getChildren().addAll(yearLabel, yearCombo);
    mainPane.setTop(topBox);

    // --- Center VBox ---
    VBox centerBox = new VBox(15);
    centerBox.setAlignment(Pos.TOP_CENTER);
    centerBox.setPadding(new Insets(20));
    mainPane.setCenter(centerBox);

    // --- Bottom: Back Button ---
    HBox bottomBox = new HBox();
    bottomBox.setPadding(new Insets(20));
    bottomBox.setAlignment(Pos.BOTTOM_RIGHT);
    Button backBtn = new Button("Back");
    backBtn.setOnAction(e -> revenueReports(stage));
    bottomBox.getChildren().add(backBtn);
    mainPane.setBottom(bottomBox);

    RevenueReportDAO dao = new RevenueReportDAO();

    // --- Update report when year changes ---
yearCombo.setOnAction(e -> {
    int selectedYear = yearCombo.getValue();
    centerBox.getChildren().clear();

    List<RevenueReport> revenueList = dao.getRevenueByLockerSize(selectedYear);

    // If no data at all for the year
    if (revenueList.isEmpty()) {
        Label noReport = new Label("No Revenue Report for Year " + selectedYear);
        noReport.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: white;");
        VBox.setMargin(noReport, new Insets(90, 0, 0, 0)); // 50px from top
        centerBox.getChildren().add(noReport);
    } else {
        Label reportTitle = new Label(selectedYear + " Revenue Report");
        reportTitle.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: black;");
        centerBox.getChildren().add(reportTitle);

        TableView<RevenueReport> table = new TableView<>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<RevenueReport, String> colCategory = new TableColumn<>("Locker Size");
        colCategory.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getCategory()));

        TableColumn<RevenueReport, String> colRevenue = new TableColumn<>("Total Revenue");
        colRevenue.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(
                data.getValue().getTotalRevenue() >= 0 ?
                        String.format("%.2f", data.getValue().getTotalRevenue()) : "N/A"));

        table.getColumns().addAll(colCategory, colRevenue);

        colCategory.prefWidthProperty().bind(table.widthProperty().multiply(0.50));
        colRevenue.prefWidthProperty().bind(table.widthProperty().multiply(0.50));

        // Fixed 3 rows
        final double ROW_HEIGHT = 30.0;
        final int NUM_DATA_ROWS = 3;
        table.setFixedCellSize(ROW_HEIGHT);
        double tableHeight = (NUM_DATA_ROWS * ROW_HEIGHT) + ROW_HEIGHT + 2;
        table.setPrefHeight(tableHeight);
        table.setMaxHeight(tableHeight);

        ObservableList<RevenueReport> tableItems = FXCollections.observableArrayList();
        String[] sizes = {"Small", "Medium", "Large"};
        for (String size : sizes) {
            double revenue = -1; // N/A default
            for (RevenueReport r : revenueList) {
                if (r.getCategory().equalsIgnoreCase(size)) {
                    revenue = r.getTotalRevenue();
                    break;
                }
            }
            tableItems.add(new RevenueReport(size, revenue));
        }
        table.setItems(tableItems);

        // control table width ---
        StackPane tableWrapper = new StackPane();
        tableWrapper.setPrefWidth(700);  // Desired table width
        tableWrapper.setMinWidth(700);
        tableWrapper.setMaxWidth(700);
        tableWrapper.getChildren().add(table);
        tableWrapper.setAlignment(Pos.CENTER);
        centerBox.setPadding(new Insets(70, 20, 20, 20)); // top padding pushes content down

        centerBox.getChildren().add(tableWrapper);
    }
});
    // --- Trigger default selection ---
    yearCombo.getOnAction().handle(null);

    Scene scene = new Scene(root, INITIAL_WIDTH, INITIAL_HEIGHT);
    stage.setScene(scene);
    stage.show();
}

private static void revReportLocation(Stage stage) {
        stage.setTitle("Revenue Reports (Locker Location)");

    // --- Background ---
    Image userMenuBG = new Image(AppFX.class.getResourceAsStream("revRepLoc.jpg"));
    ImageView backgroundView = new ImageView(userMenuBG);
    backgroundView.setPreserveRatio(false);

    // --- Root StackPane ---
    StackPane root = new StackPane();
    root.getChildren().add(backgroundView);
    backgroundView.fitWidthProperty().bind(root.widthProperty());
    backgroundView.fitHeightProperty().bind(root.heightProperty());

    // --- Main BorderPane ---
    BorderPane mainPane = new BorderPane();
    root.getChildren().add(mainPane);

    // --- Top: Year selection ---
    HBox topBox = new HBox(10);
    topBox.setPadding(new Insets(20));
    topBox.setAlignment(Pos.CENTER_LEFT);

    Label yearLabel = new Label("Select Year:");
    yearLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: white;");
    ComboBox<Integer> yearCombo = new ComboBox<>();
    for (int y = 2000; y <= 2025; y++) yearCombo.getItems().add(y);
    yearCombo.setValue(2025);
    topBox.setPadding(new Insets(200, 20, 20, 100));
    topBox.getChildren().addAll(yearLabel, yearCombo);
    mainPane.setTop(topBox);

    // --- Center VBox ---
    VBox centerBox = new VBox(15);
    centerBox.setAlignment(Pos.TOP_CENTER);
    centerBox.setPadding(new Insets(20));
    mainPane.setCenter(centerBox);

    // --- Bottom: Back Button ---
    HBox bottomBox = new HBox();
    bottomBox.setPadding(new Insets(20));
    bottomBox.setAlignment(Pos.BOTTOM_RIGHT);
    Button backBtn = new Button("Back");
    backBtn.setOnAction(e -> revenueReports(stage));
    bottomBox.getChildren().add(backBtn);
    mainPane.setBottom(bottomBox);

    RevenueReportDAO dao = new RevenueReportDAO();

    // --- Update report when year changes ---
yearCombo.setOnAction(e -> {
    int selectedYear = yearCombo.getValue();
    centerBox.getChildren().clear();

    List<RevenueReport> revenueList = dao.getRevenueByLocation(selectedYear);

    if (revenueList.isEmpty()) {
        Label noReport = new Label("No Revenue Report for Year " + selectedYear);
        noReport.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: white;");
        VBox.setMargin(noReport, new Insets(90, 0, 0, 0));
        centerBox.getChildren().add(noReport);
    } else {
        // Title
        Label reportTitle = new Label(selectedYear + " Revenue Report");
        reportTitle.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: white;");
        centerBox.getChildren().add(reportTitle);

        TableView<RevenueReport> table = new TableView<>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Columns
        TableColumn<RevenueReport, String> colLocation = new TableColumn<>("Locker Location");
        colLocation.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getCategory()));

        TableColumn<RevenueReport, String> colRevenue = new TableColumn<>("Total Revenue");
        colRevenue.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(
                data.getValue().getTotalRevenue() >= 0 ?
                        String.format("%.2f", data.getValue().getTotalRevenue()) : "N/A"));

        table.getColumns().addAll(colLocation, colRevenue);

        // Equal column widths
        colLocation.prefWidthProperty().bind(table.widthProperty().multiply(0.50));
        colRevenue.prefWidthProperty().bind(table.widthProperty().multiply(0.50));

        // Populate rows dynamically
        ObservableList<RevenueReport> tableItems = FXCollections.observableArrayList();
        tableItems.addAll(revenueList); // only show actual locations from DB

        table.setItems(tableItems);

        // Wrap table for width control
        StackPane tableWrapper = new StackPane();
        tableWrapper.setPrefWidth(700);
        tableWrapper.setMinWidth(700);
        tableWrapper.setMaxWidth(700);
        tableWrapper.getChildren().add(table);
        tableWrapper.setAlignment(Pos.CENTER);

        centerBox.setPadding(new Insets(30, 20, 20, 20)); // top padding
        centerBox.getChildren().add(tableWrapper);
    }
});
    // --- Trigger default selection ---
    yearCombo.getOnAction().handle(null);

    Scene scene = new Scene(root, INITIAL_WIDTH, INITIAL_HEIGHT);
    stage.setScene(scene);
    stage.show();
}

private static void canceledBookingsReport(Stage stage) {

    StackPane root = new StackPane();
    Scene scene = new Scene(root, INITIAL_WIDTH, INITIAL_HEIGHT);

    // --- Background image ---
    ImageView bg = new ImageView(new Image(AppFX.class.getResourceAsStream("cancelReservation.jpg")));
    bg.fitWidthProperty().bind(scene.widthProperty());
    bg.fitHeightProperty().bind(scene.heightProperty());
    bg.setPreserveRatio(false);
    root.getChildren().add(bg);

    // DAO
    CancellationDAO dao = new CancellationDAO();

    // --- Table Definition ---
    TableView<CanceledBookingsReport> table = new TableView<>();
    table.setPrefWidth(1150);
    table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

    TableColumn<CanceledBookingsReport, Integer> colID =
            new TableColumn<>("Cancellation ID");
    colID.setCellValueFactory(new PropertyValueFactory<>("cancellationID"));

    TableColumn<CanceledBookingsReport, String> colRef =
            new TableColumn<>("Booking Reference");
    colRef.setCellValueFactory(new PropertyValueFactory<>("bookingReference"));

    TableColumn<CanceledBookingsReport, String> colUser =
            new TableColumn<>("User");
    colUser.setCellValueFactory(new PropertyValueFactory<>("userName"));

    TableColumn<CanceledBookingsReport, String> colSize =
            new TableColumn<>("Locker Size");
    colSize.setCellValueFactory(new PropertyValueFactory<>("lockerSize"));

    TableColumn<CanceledBookingsReport, String> colLoc =
            new TableColumn<>("Locker Location");
    colLoc.setCellValueFactory(new PropertyValueFactory<>("lockerLocation"));

    TableColumn<CanceledBookingsReport, LocalDateTime> colDate =
            new TableColumn<>("Cancelled Date");
    colDate.setCellValueFactory(new PropertyValueFactory<>("cancelDate"));
    colDate.setCellFactory(column -> new TableCell<>() {
        @Override
        protected void updateItem(LocalDateTime item, boolean empty) {
            super.updateItem(item, empty);
            if (empty || item == null) {
                setText(null);
            } else {
                setText(item.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            }
        }
    });

    TableColumn<CanceledBookingsReport, String> colReason =
            new TableColumn<>("Reason");
    colReason.setCellValueFactory(new PropertyValueFactory<>("reason"));

    TableColumn<CanceledBookingsReport, Double> colRefund =
            new TableColumn<>("Refund Amount");
    colRefund.setCellValueFactory(new PropertyValueFactory<>("refundAmount"));

    table.getColumns().addAll(colID, colRef, colUser, colSize, colLoc, colDate, colReason, colRefund);

    // --- Month Filter ---
    ComboBox<String> monthBox = new ComboBox<>();
    monthBox.getItems().addAll("January","February","March","April","May","June",
            "July","August","September","October","November","December");
    monthBox.setValue(LocalDate.now().getMonth().name().substring(0,1) +
            LocalDate.now().getMonth().name().substring(1).toLowerCase());

    // --- Year Filter ---
    ComboBox<Integer> yearBox = new ComboBox<>();
    for (int y = 2020; y <= LocalDate.now().getYear(); y++) yearBox.getItems().add(y);
    yearBox.setValue(LocalDate.now().getYear());

    Button filterBtn = new Button("Apply Filter");
    filterBtn.setStyle("-fx-background-color:#003366; -fx-text-fill:white; -fx-font-weight:bold;");

    filterBtn.setOnAction(e -> {
        int month = monthBox.getSelectionModel().getSelectedIndex() + 1;
        int year = yearBox.getValue();
        table.getItems().setAll(dao.getCancellationReport(month, year));
    });

    // Load initial month/year
    table.getItems().setAll(dao.getCancellationReport(
            LocalDate.now().getMonthValue(),
            LocalDate.now().getYear()
    ));

    // Layout filters horizontally
    HBox filters = new HBox(15,
            new Label("Month:"), monthBox,
            new Label("Year:"), yearBox,
            filterBtn
    );
    filters.setAlignment(Pos.CENTER);
    // Back button
    Button back = new Button("Back to Reports Menu");
    back.setFont(Font.font("Arial", FontWeight.BOLD, 16));
    back.setStyle("-fx-background-color:#003366; -fx-text-fill:white;");
    back.setOnAction(e -> handleReports(stage));

    VBox layout = new VBox(25, filters, table, back);
    layout.setAlignment(Pos.TOP_CENTER);
    layout.setPadding(new Insets(220, 20, 40, 20));  // adjusts vertical position

    root.getChildren().add(layout);

    stage.setScene(scene);
    stage.setTitle("Canceled Bookings Report");
    stage.show();
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