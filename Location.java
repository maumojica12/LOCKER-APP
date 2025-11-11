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

    // Getters and setters
    public int getLocationID() { return locationID; }
    public void setLocationID(int locationID) { this.locationID = locationID; }

    public String getLocationName() { return locationName; }
    public void setLocationName(String locationName) { this.locationName = locationName; }

    public String getLocationCity() { return locationCity; }
    public void setLocationCity(String locationCity) { this.locationCity = locationCity; }

    public String getLocationPostalCode() { return locationPostalCode; }
    public void setLocationPostalCode(String locationPostalCode) { this.locationPostalCode = locationPostalCode; }

    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }

    @Override
    public String toString() {
        return locationID + " | " + locationName + " | " + locationCity + " | " + locationPostalCode + " | " + contact;
    }
}
