import java.sql.Timestamp;

public class PaymentReportEntry {
    private int paymentID;
    private String bookingReference;
    private int userID;
    private String userName;
    private double paymentAmount;
    private String paymentMethod;
    private String paymentStatus;
    private Timestamp paymentDate;

    public PaymentReportEntry(int paymentID, String bookingReference, int userID, String userName,
                              double paymentAmount, String paymentMethod, String paymentStatus, Timestamp paymentDate) {
        this.paymentID = paymentID;
        this.bookingReference = bookingReference;
        this.userID = userID;
        this.userName = userName;
        this.paymentAmount = paymentAmount;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
        this.paymentDate = paymentDate;
    }

    public int getPaymentID() { return paymentID; }
    public String getBookingReference() { return bookingReference; }
    public int getUserID() { return userID; }
    public String getUserName() { return userName; }
    public double getPaymentAmount() { return paymentAmount; }
    public String getPaymentMethod() { return paymentMethod; }
    public String getPaymentStatus() { return paymentStatus; }
    public Timestamp getPaymentDate() { return paymentDate; }
}

