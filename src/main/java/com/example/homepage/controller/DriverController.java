package com.example.homepage.controller;

import com.example.homepage.entity.Driver;
import com.example.homepage.service.DriverService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/drivers")
public class DriverController {

    private final DriverService service;

    public DriverController(DriverService service) {
        this.service = service;
    }

    @GetMapping
    public List<Driver> getAll() { return service.getAll(); }

    @PostMapping
    public Driver create(@RequestBody Driver driver) { return service.save(driver); }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { service.delete(id); }
}
