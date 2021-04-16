package com.example.find_dog;


import lombok.RequiredArgsConstructor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.io.IOException;

@RequiredArgsConstructor
@EnableJpaAuditing
@SpringBootApplication
public class FindDogApplication {
    public static void main(String[] args)  {
        SpringApplication.run(FindDogApplication.class, args);
    }
}
