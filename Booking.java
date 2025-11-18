import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Booking {

    private String bookingReference;
    private int userID;
    private int lockerID;
    private double reservationFee;
    private String reservationDate;
    private String selectedReservationDate;
    private String bookingStatus;
    private String checkInTime;
    private String checkOutTime;

    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public Booking(String bookingReference, int userID, int lockerID, double reservationFee,
                   String reservationDate,String selectedReservationDate, String bookingStatus, String checkInTime, String checkOutTime) {
        this.bookingReference = bookingReference;
        this.userID = userID;
        this.lockerID = lockerID;
        this.reservationFee = reservationFee;
        this.reservationDate = reservationDate;
        this.selectedReservationDate = selectedReservationDate;
        this.bookingStatus = bookingStatus;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
    }

    public Booking(int userID, int lockerID, double reservationFee, String bookingStatus, String selectedReservationDate) {
        this.userID = userID;
        this.lockerID = lockerID;
        this.reservationFee = reservationFee;
        this.bookingStatus = bookingStatus;
        this.reservationDate = LocalDateTime.now().format(FORMATTER);
        this.selectedReservationDate = selectedReservationDate;
    }

    public Booking(int userID, int lockerID, double reservationFee, String selectedReservationDate) {
        this.userID = userID;
        this.lockerID = lockerID;
        this.reservationFee = reservationFee;
        this.bookingStatus = "Pending Check-in";
        this.reservationDate = LocalDateTime.now().format(FORMATTER);
        this.selectedReservationDate = selectedReservationDate;
    }

    // --- Getters ---
    public String getBookingReference() { return bookingReference; }
    public int getUserID() { return userID; }
    public int getLockerID() { return lockerID; }
    public double getReservationFee() { return reservationFee; }
    public String getReservationDate() { return reservationDate; }
    public String getSelectedReservationDate() { return selectedReservationDate; }
    public String getBookingStatus() { return bookingStatus; }
    public String getCheckInTime() { return checkInTime; }
    public String getCheckOutTime() { return checkOutTime; }

    // --- Setters ---
    public void setBookingReference(String bookingReference) { this.bookingReference = bookingReference; }
    public void setBookingStatus(String bookingStatus) { this.bookingStatus = bookingStatus; }
    public void setCheckInTime(String checkInTime) { this.checkInTime = checkInTime; }
    public void setCheckOutTime(String checkOutTime) { this.checkOutTime = checkOutTime; }
    public void setSelectedReservationDate(String selectedReservationDate) { this.selectedReservationDate = selectedReservationDate; } 

    // --- Convert string to LocalDateTime ---
    public LocalDateTime getCheckInDateTime() {
        if (checkInTime == null || checkInTime.isEmpty()) return null;
        return LocalDateTime.parse(checkInTime, FORMATTER);
    }

    public LocalDateTime getCheckOutDateTime() {
        if (checkOutTime == null || checkOutTime.isEmpty()) return null;
        return LocalDateTime.parse(checkOutTime, FORMATTER);
    }

    public LocalDateTime getSelectedReservationDateTime() { 
        if (selectedReservationDate == null || selectedReservationDate.isEmpty()) return null;
        return LocalDateTime.parse(selectedReservationDate, FORMATTER);
    }

    // --- In Booking class ---
    public double calculateDurationHours() {
        LocalDateTime in = getCheckInDateTime();
        LocalDateTime out = getCheckOutDateTime();
        if (in == null || out == null) return 0;

        long seconds = ChronoUnit.SECONDS.between(in, out);
        double hours = seconds / 3600.0;

        return Math.ceil(hours); // round up to nearest full hour
    }

    @Override
    public String toString() {
        return "Booking Reference: " + bookingReference +
                ", User ID: " + userID +
                ", Locker ID: " + lockerID +
                ", Fee: " + reservationFee +
                ", Reservation Date: " + reservationDate +
                ", Selected Reservation: " + selectedReservationDate +
                ", Status: " + bookingStatus +
                ", Check-In: " + (checkInTime != null ? checkInTime : "N/A") +
                ", Check-Out: " + (checkOutTime != null ? checkOutTime : "N/A");
    }
}
