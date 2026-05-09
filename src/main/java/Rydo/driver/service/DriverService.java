package Rydo.driver.service;

import Rydo.driver.dto.DriverRequest;
import Rydo.driver.dto.DriverResponse;
import Rydo.driver.entity.Driver;
import Rydo.driver.repository.DriverRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DriverService {
    
    private final DriverRepository driverRepository;

    // Constructor based Dependency Injection
    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    public DriverResponse createDriver(DriverRequest request) {
        Driver driver = new Driver();
        driver.setName(request.getName());
        driver.setEmail(request.getEmail());
        driver.setPhone(request.getPhone());
        driver.setVehicleNumber(request.getVehicleNumber());
        driver.setVehicleType(request.getVehicleType());
        driver.setAvailable(true); // Default to available

        Driver savedDriver = driverRepository.save(driver);
        return mapToResponse(savedDriver);
    }

    public List<DriverResponse> getAllDrivers() {
        return driverRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public DriverResponse getDriverById(Long id) {
        Driver driver = driverRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Driver not found with id: " + id));
        return mapToResponse(driver);
    }

    public DriverResponse updateAvailability(Long id, boolean available) {
        Driver driver = driverRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Driver not found with id: " + id));
        
        driver.setAvailable(available);
        Driver updatedDriver = driverRepository.save(driver);
        return mapToResponse(updatedDriver);
    }

    // Helper method to map Entity to DTO
    private DriverResponse mapToResponse(Driver driver) {
        DriverResponse response = new DriverResponse();
        response.setId(driver.getId());
        response.setName(driver.getName());
        response.setEmail(driver.getEmail());
        response.setPhone(driver.getPhone());
        response.setVehicleNumber(driver.getVehicleNumber());
        response.setVehicleType(driver.getVehicleType());
        response.setAvailable(driver.isAvailable());
        return response;
    }
}
