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

    public List<Driver> getAll() {          // ← renamed from findAll()
        return driverRepository.findAll();
    }

    public Driver save(Driver driver) {
        return driverRepository.save(driver);
    }

    public void delete(Long id) {           // ← renamed from deleteById()
        driverRepository.deleteById(id);
    }
}