public class LockerType{
    private int lockerTypeID;
    private String lockerTypeSize; 
    private double lockerMaxWeight;
    private double lockerRate;

    public LockerType(int lockerTypeID, String lockerTypeSize, double lockerMaxWeight, double lockerRate){
        this.lockerTypeID = lockerTypeID;
        this.lockerTypeSize = lockerTypeSize;
        this.lockerMaxWeight = lockerMaxWeight;
        this.lockerRate = lockerRate;
    }

    public int getLockerTypeID(){ 
        return lockerTypeID; 
    }

    public String getLockerTypeSize(){ 
        return lockerTypeSize; 
    }

    public double getLockerMaxWeight(){ 
        return lockerMaxWeight; 
    }

    public double getLockerRate(){ 
        return lockerRate; 
    }

    public String toString() {
        return "LockerTypeID: " + lockerTypeID + ", Size: " + lockerTypeSize + 
               ", MaxWeight: " + lockerMaxWeight + ", Rate: ₱" + lockerRate;
    }
}
