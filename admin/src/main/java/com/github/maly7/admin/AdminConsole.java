package com.github.maly7.admin;

import de.codecentric.boot.admin.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAdminServer
@SpringBootApplication
public class AdminConsole {
    public static void main(String[] args) {
        SpringApplication.run(AdminConsole.class, args);
    }
}
