package Rydo.ride.controller;

import Rydo.ride.dto.RideRequest;
import Rydo.ride.dto.RideResponse;
import Rydo.ride.entity.RideStatus;
import Rydo.ride.service.RideService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rides")
public class RideController {

    private final RideService rideService;

    // Dependency Injection
    public RideController(RideService rideService) {
        this.rideService = rideService;
    }

    @PostMapping
    public ResponseEntity<RideResponse> bookRide(@RequestBody RideRequest request) {
        RideResponse response = rideService.bookRide(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<RideResponse>> getAllRides() {
        List<RideResponse> responses = rideService.getAllRides();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RideResponse> getRideById(@PathVariable Long id) {
        RideResponse response = rideService.getRideById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<RideResponse> updateRideStatus(
            @PathVariable Long id, 
            @RequestParam RideStatus status) {
        RideResponse response = rideService.updateRideStatus(id, status);
        return ResponseEntity.ok(response);
    }
}
