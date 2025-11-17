import java.time.LocalDateTime;

public class CanceledBookingsReport {

    private int cancellationID;
    private String bookingReference;
    private String userName;
    private String lockerSize;
    private String lockerLocation;
    private LocalDateTime cancelDate;
    private String reason;
    private double refundAmount;

    public CanceledBookingsReport(int cancellationID, String bookingReference, String userName, String lockerSize,
                            String lockerLocation, LocalDateTime cancelDate, String reason, double refundAmount){
        this.cancellationID = cancellationID;
        this.bookingReference = bookingReference;
        this.userName = userName;
        this.lockerSize = lockerSize;
        this.lockerLocation = lockerLocation;
        this.cancelDate = cancelDate;
        this.reason = reason;
        this.refundAmount = refundAmount;
    }

    public int getCancellationID(){
        return cancellationID;
    }

    public String getBookingReference(){
        return bookingReference;
    }

    public String getUserName(){
        return userName;
    }

    public String getLockerSize(){
        return lockerSize;
    }

    public String getLockerLocation(){
        return lockerLocation;
    }

    public LocalDateTime getCancelDate(){
        return cancelDate;
    }

    public String getReason(){
        return reason;
    }

    public double getRefundAmount(){
        return refundAmount;
    }

}
