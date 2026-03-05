package com.example.homepage.service;

import com.example.homepage.entity.Driver;
import com.example.homepage.repository.DriverRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverService {

    private final DriverRepository driverRepository;

    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    // Used by DriverController (REST)
    public List<Driver> getAll() {
        return driverRepository.findAll();
    }

    public Driver save(Driver driver) {
        return driverRepository.save(driver);
    }

    public void delete(Long id) {
        driverRepository.deleteById(id);
    }

    // Used by WebController (Thymeleaf)
    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }

    public Driver saveDriver(Driver driver) {
        return driverRepository.save(driver);
    }

    public void deleteDriver(Long id) {
        driverRepository.deleteById(id);
    }

    public Driver getDriverById(Long id) {
        return driverRepository.findById(id).orElse(null);
    }
}