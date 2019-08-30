package dev.collegues.repository;

import dev.collegues.entite.Collegue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Interface permettant de travailler avec la bdd
 */
public interface CollegueRepository extends JpaRepository<Collegue,String> {

    /**
     * méthode retournant la liste des collegues ayant le nom recherché
     * @param nom nom à rechercher
     * @return une liste de collegues
     */
    List<Collegue> findByNom(String nom);

    /**
     * méthode retournant un collegue selon son matricule
     * @param matricule matricule à rechercher
     * @return un collegue
     */
    Collegue findByMatricule(String matricule);


}
