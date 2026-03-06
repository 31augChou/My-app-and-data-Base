package com.example.homepage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class HomePageApplication {

    public static void main(String[] args) {
        SpringApplication.run(HomePageApplication.class, args);
    }
}
