package Rydo.ride.dto;

public class RideRequest {
    private String pickupLocation;
    private String dropLocation;
    private Long userId;
    private Long driverId;
    private Double fare;

    public String getPickupLocation() { return pickupLocation; }
    public void setPickupLocation(String pickupLocation) { this.pickupLocation = pickupLocation; }

    public String getDropLocation() { return dropLocation; }
    public void setDropLocation(String dropLocation) { this.dropLocation = dropLocation; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Long getDriverId() { return driverId; }
    public void setDriverId(Long driverId) { this.driverId = driverId; }

    public Double getFare() { return fare; }
    public void setFare(Double fare) { this.fare = fare; }
}
