package Rydo.ride.service;

import Rydo.ride.dto.RideRequest;
import Rydo.ride.dto.RideResponse;
import Rydo.ride.dto.DriverEarningsResponse;

import Rydo.ride.entity.Ride;
import Rydo.ride.entity.RideStatus;
import Rydo.ride.repository.RideRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RideService {

    private final RideRepository rideRepository;

    public RideService(RideRepository rideRepository) {
        this.rideRepository = rideRepository;
    }

    public RideResponse bookRide(RideRequest request) {
        Ride ride = new Ride();
        ride.setPickupLocation(request.getPickupLocation());
        ride.setDropLocation(request.getDropLocation());
        ride.setUserId(request.getUserId());
        ride.setDriverId(request.getDriverId());
        ride.setFare(request.getFare());
        
        // When a ride is booked, its initial status is BOOKED
        ride.setStatus(RideStatus.BOOKED);

        Ride savedRide = rideRepository.save(ride);
        return mapToResponse(savedRide);
    }

    public RideResponse getRideById(Long id) {
        Ride ride = rideRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ride not found with id: " + id));
        return mapToResponse(ride);
    }

    public List<RideResponse> getAllRides() {
        return rideRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public RideResponse updateRideStatus(Long id, RideStatus newStatus) {
        Ride ride = rideRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ride not found with id: " + id));

        ride.setStatus(newStatus);
        Ride updatedRide = rideRepository.save(ride);
        return mapToResponse(updatedRide);
    }

    public List<RideResponse> getRidesByUserId(Long userId) {
        return rideRepository.findByUserId(userId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public List<RideResponse> getRidesByDriverId(Long driverId) {
        return rideRepository.findByDriverId(driverId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public DriverEarningsResponse getTotalEarningsByDriverId(Long driverId) {
        List<Ride> driverRides = rideRepository.findByDriverId(driverId);
        
        long totalCompletedRides = 0;
        double totalEarnings = 0.0;

        for (Ride ride : driverRides) {
            if (ride.getStatus() == RideStatus.COMPLETED) {
                totalCompletedRides++;
                if (ride.getFare() != null) {
                    totalEarnings += ride.getFare();
                }
            }
        }

        DriverEarningsResponse response = new DriverEarningsResponse();
        response.setDriverId(driverId);
        response.setTotalCompletedRides(totalCompletedRides);
        response.setTotalEarnings(totalEarnings);

        return response;
    }

    // Helper method to convert an Entity to a DTO
    private RideResponse mapToResponse(Ride ride) {
        RideResponse response = new RideResponse();
        response.setId(ride.getId());
        response.setPickupLocation(ride.getPickupLocation());
        response.setDropLocation(ride.getDropLocation());
        response.setFare(ride.getFare());
        response.setStatus(ride.getStatus());
        response.setUserId(ride.getUserId());
        response.setDriverId(ride.getDriverId());
        return response;
    }
}
