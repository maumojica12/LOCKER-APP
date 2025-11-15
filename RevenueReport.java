public class RevenueReport {
 private String category;     // locker size or location name
    private double totalRevenue;

    public RevenueReport(String category, double totalRevenue) {
        this.category = category;
        this.totalRevenue = totalRevenue;
    }

    public String getCategory() { 
        return category; 
    }

    public double getTotalRevenue() { 
        return totalRevenue; 
    }
}
