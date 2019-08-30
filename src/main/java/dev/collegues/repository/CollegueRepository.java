package dev.collegues.repository;

import dev.collegues.entite.Collegue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CollegueRepository extends JpaRepository<Collegue,String> {

    List<Collegue> findByNom(String nom);
    Collegue findByMatricule(String nom);


}
