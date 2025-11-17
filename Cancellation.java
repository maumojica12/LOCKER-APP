import java.time.LocalDateTime;

public class Cancellation {
    private int cancellationID;
    private String bookingReference;
    private LocalDateTime cancelDate;
    private String reason;
    private double refundFee;

    public Cancellation(int cancellationID, String bookingReference,
                        LocalDateTime cancelDate, String reason, double refundFee) {
        this.cancellationID = cancellationID;
        this.bookingReference = bookingReference;
        this.cancelDate = cancelDate;
        this.reason = reason;
        this.refundFee = refundFee;
    }

    public int getCancellationID() {
        return cancellationID;
    }

    public String getBookingReference() {
        return bookingReference;
    }
    public LocalDateTime getCancelDate() {
        return cancelDate;
    }
    public String getReason() {
        return reason;
    }
    public double getRefundFee() {
        return refundFee;
    }
}
