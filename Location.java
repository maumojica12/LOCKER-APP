public class Location {
    private int locationID;
    private String locationName;
    private String locationCity;
    private String locationPostalCode;
    private String contact;

    public Location(int locationID, String locationName, String locationCity, String locationPostalCode, String contact) {
        this.locationID = locationID;
        this.locationName = locationName;
        this.locationCity = locationCity;
        this.locationPostalCode = locationPostalCode;
        this.contact = contact;
    }

    // Getters
    public int getLocationID() { return locationID; }

    public String getLocationName() { return locationName; }

    public String getLocationCity() { return locationCity; }

    public String getLocationPostalCode() { return locationPostalCode; }

    public String getContact() { return contact; }

    @Override
    public String toString() {
        return locationID + " | " + locationName + " | " + locationCity + " | " + locationPostalCode + " | " + contact;
    }
}
