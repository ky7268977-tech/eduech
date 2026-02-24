package com.eduecho.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

//@EnableScheduling
@SpringBootApplication
public class EduEchoApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduEchoApplication.class, args);
        System.out.println("The server is running on port no 8080...");
    }
}
