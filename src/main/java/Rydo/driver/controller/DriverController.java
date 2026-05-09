package Rydo.driver.controller;

import Rydo.driver.dto.DriverRequest;
import Rydo.driver.dto.DriverResponse;
import Rydo.driver.service.DriverService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/drivers")
public class DriverController {

    private final DriverService driverService;

    // Constructor based Dependency Injection
    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @PostMapping
    public ResponseEntity<DriverResponse> createDriver(@RequestBody DriverRequest request) {
        DriverResponse response = driverService.createDriver(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DriverResponse>> getAllDrivers() {
        List<DriverResponse> responses = driverService.getAllDrivers();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DriverResponse> getDriverById(@PathVariable Long id) {
        DriverResponse response = driverService.getDriverById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/availability")
    public ResponseEntity<DriverResponse> updateAvailability(
            @PathVariable Long id, 
            @RequestParam boolean available) {
        DriverResponse response = driverService.updateAvailability(id, available);
        return ResponseEntity.ok(response);
    }
}
