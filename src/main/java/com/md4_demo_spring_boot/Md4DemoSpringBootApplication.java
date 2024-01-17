package com.md4_demo_spring_boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Md4DemoSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(Md4DemoSpringBootApplication.class, args);
        System.out.println("http://localhost:8081/products");
    }
}
