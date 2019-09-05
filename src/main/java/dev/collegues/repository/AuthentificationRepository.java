package dev.collegues.repository;

import dev.collegues.controller.dto.CollegueDto;
import dev.collegues.entite.Authentification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface AuthentificationRepository extends JpaRepository<Authentification,Integer> {


    Optional<Authentification> findByLogin(String login);

}
