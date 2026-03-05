package com.example.homepage.controller;

import com.example.homepage.entity.Driver;
import com.example.homepage.entity.RegisterForm;
import com.example.homepage.service.DriverService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class WebController {

    private final DriverService driverService;

    public WebController(DriverService driverService) {
        this.driverService = driverService;
    }

    @GetMapping("/")
    public String index() { return "index"; }

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
        Driver driver = new Driver();
        driver.setFirstName(form.getFirstName());
        driver.setLastName(form.getLastName());
        driver.setEmail(form.getEmail());
        driverService.save(driver);
        ra.addFlashAttribute("successMsg", "Account created! Please login.");
        return "redirect:/login";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("drivers", driverService.getAll());
        model.addAttribute("newDriver", new Driver());
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