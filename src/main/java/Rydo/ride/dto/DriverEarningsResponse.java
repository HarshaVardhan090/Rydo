package Rydo.ride.dto;

public class DriverEarningsResponse {
    private Long driverId;
    private long totalCompletedRides;
    private Double totalEarnings;

    public Long getDriverId() { return driverId; }
    public void setDriverId(Long driverId) { this.driverId = driverId; }

    public long getTotalCompletedRides() { return totalCompletedRides; }
    public void setTotalCompletedRides(long totalCompletedRides) { this.totalCompletedRides = totalCompletedRides; }

    public Double getTotalEarnings() { return totalEarnings; }
    public void setTotalEarnings(Double totalEarnings) { this.totalEarnings = totalEarnings; }
}
