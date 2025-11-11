import java.time.LocalDateTime;

public class LockerTransfer {
    private int transferID;
    private String bookingReference;
    private LocalDateTime transferDate;
    private double adjustmentAmount;
    private int oldLockerID;
    private int newLockerID;

    public LockerTransfer(int transferID, String bookingReference, LocalDateTime transferDate,
                          double adjustmentAmount, int oldLockerID, int newLockerID) {
        this.transferID = transferID;
        this.bookingReference = bookingReference;
        this.transferDate = transferDate;
        this.adjustmentAmount = adjustmentAmount;
        this.oldLockerID = oldLockerID;
        this.newLockerID = newLockerID;
    }

    public LockerTransfer(String bookingReference, LocalDateTime transferDate,
                          double adjustmentAmount, int oldLockerID, int newLockerID) {
        this.bookingReference = bookingReference;
        this.transferDate = transferDate;
        this.adjustmentAmount = adjustmentAmount;
        this.oldLockerID = oldLockerID;
        this.newLockerID = newLockerID;
    }

    public int getTransferID(){ 
        return transferID; 
    }

    public void setTransferID(int transferID){ 
        this.transferID = transferID; 
    }

    public String getBookingReference(){ 
        return bookingReference; 
    }
    
    public void setBookingReference(String bookingReference){ 
        this.bookingReference = bookingReference; 
    }

    public LocalDateTime getTransferDate(){ 
        return transferDate;
    }

    public void setTransferDate(LocalDateTime transferDate){ 
        this.transferDate = transferDate; 
    }

    public double getAdjustmentAmount(){ 
        return adjustmentAmount;
    }

    public void setAdjustmentAmount(double adjustmentAmount){ 
        this.adjustmentAmount = adjustmentAmount;
    }

    public int getOldLockerID(){ 
        return oldLockerID; 
    }

    public void setOldLockerID(int oldLockerID){
        this.oldLockerID = oldLockerID;
    }

    public int getNewLockerID(){ 
        return newLockerID; 
    }

    public void setNewLockerID(int newLockerID){ 
        this.newLockerID = newLockerID;
    }
}
