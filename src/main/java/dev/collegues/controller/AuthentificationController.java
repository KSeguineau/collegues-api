package dev.collegues.controller;

import dev.collegues.controller.dto.CollegueDto;
import dev.collegues.controller.dto.InfosAuthentificationPost;
import dev.collegues.repository.AuthentificationRepository;
import dev.collegues.service.AuthentificationService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@RestController
public class AuthentificationController {

    private AuthentificationService authentificationService;

    public AuthentificationController(AuthentificationService authentificationService) {
        this.authentificationService = authentificationService;
    }

    @PostMapping("/auth")
    public ResponseEntity<?> authenticate(@RequestBody InfosAuthentificationPost infos) {
        return authentificationService.authenticate(infos);
    }

    @GetMapping("/auth/user")
    public CollegueDto findCollegue() {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        return authentificationService.findCollegue(login);
    }
}


