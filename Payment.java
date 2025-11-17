import java.sql.Timestamp;

public class Payment {
    private int paymentID;
    private String bookingReference;
    private int userID;
    private double paymentAmount;
    private String paymentMethod;
    private String paymentStatus;
    private Timestamp paymentDate;


    public Payment(int paymentID, String bookingReference, int userID, double paymentAmount,
                   String paymentMethod, String paymentStatus, Timestamp paymentDate) {
        this.paymentID = paymentID;
        this.bookingReference = bookingReference;
        this.userID = userID;
        this.paymentAmount = paymentAmount;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
        this.paymentDate = paymentDate;
    }

    public int getPaymentID() { return paymentID; }
    public void setPaymentID(int paymentID) { this.paymentID = paymentID; }

    public String getBookingReference() { return bookingReference; }
    public void setBookingReference(String bookingReference) { this.bookingReference = bookingReference; }

    public int getUserID() { return userID; }
    public void setUserID(int userID) { this.userID = userID; }

    public double getPaymentAmount() { return paymentAmount; }
    public void setPaymentAmount(double paymentAmount) { this.paymentAmount = paymentAmount; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    public String getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }

    public Timestamp getPaymentDate() { return paymentDate; }
    public void setPaymentDate(Timestamp paymentDate) { this.paymentDate = paymentDate; }

    @Override
    public String toString() {
        return "PaymentID: " + paymentID +
                ", BookingRef: " + bookingReference +
                ", UserID: " + userID +
                ", Amount: ₱" + paymentAmount +
                ", Method: " + paymentMethod +
                ", Status: " + paymentStatus +
                ", Date: " + paymentDate;
    }
}
