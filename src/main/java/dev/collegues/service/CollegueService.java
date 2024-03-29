package dev.collegues.service;


import java.util.List;
import java.util.UUID;

import dev.collegues.controller.dto.PhotoDto;
import dev.collegues.entite.Collegue;
import dev.collegues.exception.CollegueInvalideException;
import dev.collegues.exception.CollegueNonTrouveException;
import dev.collegues.repository.CollegueRepository;
import dev.collegues.utils.CollegueValidator;
import lombok.Data;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static java.util.stream.Collectors.toList;

/**
 * Service gérant les collegues
 */
@Data
@Service
public class CollegueService {


    /**
     * Classe contenant des methodes de validation de collegue
     */
    private CollegueValidator collegueValidator;

    /**
     * interface gérant les actions avec la base de donnees
     */
    private CollegueRepository collegueRepository;


    /**
     * Constructeur
     */
    public CollegueService(CollegueValidator collegueValidator, CollegueRepository collegueRepository) {

        this.collegueValidator = collegueValidator;
        this.collegueRepository = collegueRepository;
    }

    /**
     * Permet de rechercher les collegues par leur nom
     *
     * @param nomRecherche nom du collegue à rechercher
     * @return Une List de collegue
     */
    public List<String> rechercherParNom(String nomRecherche) {
        return collegueRepository.findByNom(nomRecherche).stream().map(Collegue::getMatricule).collect(toList());
    }

    /**
     * Permet de rechercher un collegue par son matricule
     *
     * @param matricule matricule du collegue
     * @return un Collegue
     */
    public Collegue rechercherParMatricule(String matricule) {
        Collegue c = collegueRepository.findByMatricule(matricule);
        if (c != null) {
            return c;
        } else {
            throw new CollegueNonTrouveException("aucun collegue avec ce matricule n’a été trouvé");
        }
    }

    /**
     * Permet d’ajouter un collegue:
     * longueur du nom et prénom supérieur à 2
     *
     * @param collegue collegue à ajouter
     * @return un collegue
     */
    public Collegue ajouterUnCollegue(Collegue collegue) {
        collegue.setMatricule(UUID.randomUUID().toString());

        if (collegueValidator.validerCollegue(collegue)) {
            collegueRepository.save(collegue);
            return collegue;
        } else {
            throw new CollegueInvalideException();
        }

    }

    /**
     * Permet de modifier l’email d’un Collegue
     *
     * @param matricule matricule du Collegue
     * @param email     nouvelle valeur de l’email
     * @return un collegue
     */
    @Transactional
    public Collegue modifierEmail(String matricule, String email) {
        Collegue collegue = this.rechercherParMatricule(matricule);
        if (collegue == null) {
            throw new CollegueNonTrouveException();
        }

        if (collegueValidator.longueurValide(email, 2) && collegueValidator.isContains(email)) {
            collegue.setEmail(email);
            collegueRepository.save(collegue);

            return collegue;
        } else {
            throw new CollegueInvalideException();
        }
    }

    /**
     * Permet de modifier l’url de la photo d’un collegue
     *
     * @param matricule matricule du collegue
     * @param photoUrl  nouvelle valeur de l’url de la photo
     * @return un collegue
     */
    @Transactional
    public Collegue modifierPhotoUrl(String matricule, String photoUrl) {
        Collegue collegue = this.rechercherParMatricule(matricule);
        if (collegue == null) {
            throw new CollegueNonTrouveException();
        }

        if (collegueValidator.startWithhttp(photoUrl)) {
            collegue.setPhotoUrl(photoUrl);
            collegueRepository.save(collegue);
            return collegue;
        } else {
            throw new CollegueInvalideException();
        }

    }

	/**
	 * retourne la liste des matricules grace à l’email
	 * @param email email d’un collegue
	 * @return une liste de matricule
	 */
	public List<String> rechercherParEmail(String email) {
        return collegueRepository.findByEmail(email).stream().map(Collegue::getMatricule).collect(toList());
    }

	/**
	 * retourne la liste des photosDto
	 * @return une liste de photosDto
	 */
	public List<PhotoDto> rechercherPhotosCollegues() {
    	return collegueRepository.findPhotos();
	}
}
