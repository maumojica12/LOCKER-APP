public class Locker {

    private int lockerID;
    private int lockerTypeID;
    private int locationID;
    private int locationPostalCode;
    private String lockerStatus;

    //Constructors
    public Locker(int lockerID, int lockerTypeID, int locationID, int locationPostalCode, String lockerStatus){
        this.lockerID = lockerID;
        this.lockerTypeID = lockerTypeID;
        this.locationID = locationID;
        this.locationPostalCode = locationPostalCode;
        this.lockerStatus = lockerStatus;
    }

    public Locker(int lockerTypeID, int locationID, int postal) {
        this.lockerTypeID = lockerTypeID;
        this.locationID = locationID;
        this.locationPostalCode = postal;
        this.lockerStatus = "Available";
    }

    //Getters
    public int getLockerID(){
        return lockerID;
    }

    public int getLockerTypeID(){
        return lockerTypeID;
    }

    public int getLocationID(){
        return locationID;
    }

    public int getLocationPostalCode(){
        return locationPostalCode;
    }

    public String getLockerStatus(){
        return lockerStatus;
    }

    public void setLockerID(int id) {
        lockerID = id;
    }
    public void setLockerTypeID(int id) {
        lockerTypeID = id;
    }
    public void setLocationID(int id) {
        locationID = id;
    }
    public void setLocationPostalCode(int p) {
        locationPostalCode = p;
    }
    public void setLockerStatus(String s) {
        lockerStatus = s;
    }

    @Override
    public String toString(){
        return "LockerID: " + lockerID +
                ", LockerTypeID: " + lockerTypeID +
                ", LocationID: " + locationID +
                ", LocationPostalCode: " + locationPostalCode +
                ", LockerStatus: " + lockerStatus;
    }
}