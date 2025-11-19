public class User {
    private int userID;
    private String firstName;
    private String lastName;
    private String userContact;
    private String userEmail;

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

    public void setFirstName(String firstName) {
    this.firstName = firstName;
}

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUserContact(String userContact) {
        this.userContact = userContact;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Override
    public String toString() {
        return "User ID: " + userID +
               ", Name: " + firstName + " " + lastName +
               ", Contact: " + userContact +
               ", Email: " + userEmail;
    }
}