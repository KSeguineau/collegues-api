package dev.collegues;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * point d’entrée de l’application
 */
@SpringBootApplication
public class ColleguesApiApplication {


    public static void main(String[] args) {

        SpringApplication.run(ColleguesApiApplication.class, args);

    }
}


