import java.time.LocalDateTime;

public class Booking {

    private String bookingReference;
    private int userID;
    private int lockerID;
    private double reservationFee;
    private String reservationDate;
    private String bookingStatus;
    private String checkInTime;
    private String checkOutTime;

    // --- Constructors ---
    public Booking() {}

    public Booking(String bookingReference, int userID, int lockerID, double reservationFee, 
                   String reservationDate, String bookingStatus, String checkInTime, String checkOutTime) {
        this.bookingReference = bookingReference;
        this.userID = userID;
        this.lockerID = lockerID;
        this.reservationFee = reservationFee;
        this.reservationDate = reservationDate;
        this.bookingStatus = bookingStatus;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
    }

    public Booking(int userID, int lockerID, double reservationFee, String bookingStatus) {
        this.userID = userID;
        this.lockerID = lockerID;
        this.reservationFee = reservationFee;
        this.bookingStatus = bookingStatus;
        this.reservationDate = LocalDateTime.now().toString();
    }

    public Booking(int userID, int lockerID, double reservationFee) {
        this.userID = userID;
        this.lockerID = lockerID;
        this.reservationFee = reservationFee;
        this.bookingStatus = "Pending Check-in";
        this.reservationDate = LocalDateTime.now().toString();
    }

    // --- Getters ---
    public String getBookingReference() { return bookingReference; }
    public int getUserID() { return userID; }
    public int getLockerID() { return lockerID; }
    public double getReservationFee() { return reservationFee; }
    public String getReservationDate() { return reservationDate; }
    public String getBookingStatus() { return bookingStatus; }
    public String getCheckInTime() { return checkInTime; }
    public String getCheckOutTime() { return checkOutTime; }

    // --- Setters ---
      public void setBookingReference(String bookingReference) {
        this.bookingReference = bookingReference;
    }
    public void setBookingStatus(String bookingStatus) { this.bookingStatus = bookingStatus; }
    public void setCheckInTime(String checkInTime) { this.checkInTime = checkInTime; }
    public void setCheckOutTime(String checkOutTime) { this.checkOutTime = checkOutTime; }

    @Override
    public String toString() {
        return "Booking Reference: " + bookingReference +
               ", User ID: " + userID +
               ", Locker ID: " + lockerID +
               ", Fee: " + reservationFee +
               ", Date: " + reservationDate +
               ", Status: " + bookingStatus +
               ", Check-In: " + (checkInTime != null ? checkInTime : "N/A") +
               ", Check-Out: " + (checkOutTime != null ? checkOutTime : "N/A");
    }
}