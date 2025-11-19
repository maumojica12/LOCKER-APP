public class Locker {

    private int lockerID;
    private int lockerTypeID;
    private int locationID;
    private String lockerStatus;

    // Constructor for full record
    public Locker(int lockerID, int lockerTypeID, int locationID, String lockerStatus){
        this.lockerID = lockerID;
        this.lockerTypeID = lockerTypeID;
        this.locationID = locationID;
        this.lockerStatus = lockerStatus;
    }

    // Constructor for adding new locker
    public Locker(int lockerTypeID, int locationID) {
        this.lockerTypeID = lockerTypeID;
        this.locationID = locationID;
        this.lockerStatus = "Available";
    }

    public int getLockerID() { return lockerID; }
    public int getLockerTypeID() { return lockerTypeID; }
    public int getLocationID() { return locationID; }
    public String getLockerStatus() { return lockerStatus; }

    public void setLockerID(int id) { lockerID = id; }
    public void setLockerTypeID(int id) { lockerTypeID = id; }
    public void setLocationID(int id) { locationID = id; }
    public void setLockerStatus(String s) { lockerStatus = s; }

    @Override
    public String toString(){
        return "LockerID: " + lockerID +
                ", LockerTypeID: " + lockerTypeID +
                ", LocationID: " + locationID +
                ", LockerStatus: " + lockerStatus;
    }
}
