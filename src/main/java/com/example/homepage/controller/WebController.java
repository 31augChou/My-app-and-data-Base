package com.example.homepage.controller;

import com.example.homepage.entity.Driver;
import com.example.homepage.entity.RegisterForm;
import com.example.homepage.entity.User;
import com.example.homepage.service.DriverService;
import com.example.homepage.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
public class WebController {

    private final DriverService driverService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public WebController(DriverService driverService,
                         UserService userService,
                         PasswordEncoder passwordEncoder) {
        this.driverService = driverService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/")
    public String index() { return "ndex"; }

    @GetMapping("/login")
    public String login() { return "login"; }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("registerForm", new RegisterForm());
        return "Register";
    }

    @PostMapping("/register")
    public String registerSubmit(@ModelAttribute("registerForm") RegisterForm form,
                                 RedirectAttributes ra, Model model) {
        if (!form.getPassword().equals(form.getConfirmPassword())) {
            model.addAttribute("registerForm", form);
            model.addAttribute("errorMsg", "Passwords do not match!");
            return "Register";
        }

        if (userService.existsByUsername(form.getUsername())) {
            model.addAttribute("registerForm", form);
            model.addAttribute("errorMsg", "Username already taken.");
            return "Register";
        }

        User user = new User();
        user.setUsername(form.getUsername());
        user.setPassword(passwordEncoder.encode(form.getPassword()));
        user.setRole("USER");
        userService.save(user);

        ra.addFlashAttribute("successMsg", "Account created! Please login.");
        return "redirect:/login";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, Principal principal) {
        List<Driver> drivers = driverService.getAll();

        model.addAttribute("drivers", drivers);
        model.addAttribute("newDriver", new Driver());
        model.addAttribute("username", principal.getName());
        model.addAttribute("totalDrivers", drivers.size());
        model.addAttribute("activeDrivers", drivers.size());
        model.addAttribute("licensedDrivers", drivers.stream()
                .filter(d -> d.getLicenseNumber() != null && !d.getLicenseNumber().isBlank())
                .count());
        model.addAttribute("driversWithEmail", drivers.stream()
                .filter(d -> d.getEmail() != null && !d.getEmail().isBlank())
                .count());

        return "dashboard";
    }

    @PostMapping("/drivers/add")
    public String addDriver(@ModelAttribute Driver driver, RedirectAttributes ra) {
        driverService.save(driver);
        ra.addFlashAttribute("successMsg", "Driver added successfully!");
        return "redirect:/dashboard";
    }

    @PostMapping("/drivers/edit/{id}")
    public String editDriver(@PathVariable Long id, @ModelAttribute Driver driver, RedirectAttributes ra) {
        driver.setId(id);
        driverService.save(driver);
        ra.addFlashAttribute("successMsg", "Driver updated successfully!");
        return "redirect:/dashboard";
    }

    @PostMapping("/drivers/delete/{id}")
    public String deleteDriver(@PathVariable Long id, RedirectAttributes ra) {
        driverService.delete(id);
        ra.addFlashAttribute("successMsg", "Driver deleted.");
        return "redirect:/dashboard";
    }
}