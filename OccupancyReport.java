public class OccupancyReport {
    private int lockerID;
    private String lockerSize;
    private String lockerLocation;
    private int totalBookings;

    // --- Constructor ---
    public OccupancyReport(int lockerID, String lockerSize, String lockerLocation, int totalBookings) {
        this.lockerID = lockerID;
        this.lockerSize = lockerSize;
        this.lockerLocation = lockerLocation;
        this.totalBookings = totalBookings;
    }

    // --- Getters ---
    public int getLockerID() { 
        return lockerID; 
    }

    public String getLockerSize() { 
        return lockerSize; 
    }

    public String getLockerLocation() { 
        return lockerLocation; 
    }

    public int getTotalBookings() { 
        return totalBookings; 
    }

    @Override
    public String toString() {
        return lockerID + " | " + lockerSize + " | " + lockerLocation + " | " + totalBookings;
    }
}
