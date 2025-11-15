public class OccupancyReport {
    private int lockerID;
    private String lockerSize;
    private String lockerLocation;
    private int totalBookings;
    private double occupancyPercentage;

    public OccupancyReport(int lockerID, String lockerSize, String lockerLocation, int totalBookings, double occupancyPercentage) {
        this.lockerID = lockerID;
        this.lockerSize = lockerSize;
        this.lockerLocation = lockerLocation;
        this.totalBookings = totalBookings;
        this.occupancyPercentage = occupancyPercentage;
    }

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

    public double getOccupancyPercentage() { 
        return occupancyPercentage; 
    }

    @Override
    public String toString() {
        return lockerID + " | " + lockerSize + " | " + lockerLocation + " | " + totalBookings + " | " + String.format("%.2f", occupancyPercentage) + "%";
    }
}
