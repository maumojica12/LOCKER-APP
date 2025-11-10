public class User {

    private int userID;
    private String firstName;
    private String lastName;
    private String userContact;
    private String userEmail;

    // --- Constructors ---
    public User() {
        // default constructor
    }

    //for viewing existing user
    public User(int userID, String firstName, String lastName, String userContact, String userEmail) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userContact = userContact;
        this.userEmail = userEmail;
    }

    // for registering new user
    public User(String firstName, String lastName, String userContact, String userEmail) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userContact = userContact;
        this.userEmail = userEmail;
    }

    // --- Getters and Setters ---
    public int getUserID() {
        return userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserContact() {
        return userContact;
    }

    public String getUserEmail() {
        return userEmail;
    }

    @Override
    public String toString() {
        return "User ID: " + userID +
               ", Name: " + firstName + " " + lastName +
               ", Contact: " + userContact +
               ", Email: " + userEmail;
    }
}
