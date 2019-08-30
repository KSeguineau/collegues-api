package dev.collegues.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import dev.collegues.entite.Collegue;
import dev.collegues.exception.CollegueInvalideException;
import dev.collegues.exception.CollegueNonTrouveException;
import dev.collegues.utils.CollegueValidator;
import dev.collegues.utils.DataUtils;
import lombok.Data;
import org.springframework.stereotype.Service;

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
	 * Classe gérant la generation de collegue et leur stockage
	 */
	private DataUtils dataUtils;

	/**
	 * Constructeur
	 */
	public CollegueService(CollegueValidator collegueValidator,DataUtils dataUtils) {

		this.collegueValidator = collegueValidator;
		this.dataUtils = dataUtils;
	}

	/**
	 * Permet de rechercher les collegues par leur nom
	 * @param nomRecherche nom du collegue à rechercher
	 * @return Une List de collegue */
	public List<Collegue> rechercherParNom(String nomRecherche) {
		return dataUtils.rechercheParNom(nomRecherche);
	}

	/**
	 * Permet de rechercher un collegue par son matricule
	 * @param matricule matricule du collegue
	 * @return un Collegue
	 */
	public Collegue rechercherParMatricule(String matricule) {
		Collegue c = dataUtils.rechercheParMatricule(matricule);
		if (c != null) {
			return c;
		} else {
			throw new CollegueNonTrouveException("aucun collegue avec ce matricule n’a été trouvé");
		}
	}

	/**
	 * Permet d’ajouter un collegue:
	 * longueur du nom et prénom supérieur à 2
	 * @param collegue collegue à ajouter
	 * @return un collegue
	 */
	public Collegue ajouterUnCollegue(Collegue collegue) {
		collegue.setMatricule(UUID.randomUUID().toString());

		if (collegueValidator.validerCollegue(collegue)) {
			dataUtils.ajouterCollegue(collegue);
			return collegue;
		} else {
			throw new CollegueInvalideException();
		}

	}

	/**
	 *Permet de modifier l’email d’un Collegue
	 * @param matricule matricule du Collegue
	 * @param email nouvelle valeur de l’email
	 * @return un collegue
	 */
	public Collegue modifierEmail(String matricule, String email) {
		Collegue collegue = this.rechercherParMatricule(matricule);
		if (collegue == null) {
			throw new CollegueNonTrouveException();
		}

		if (collegueValidator.longueurValide(email,2) && collegueValidator.isContains(collegue)) {
			collegue.setEmail(email);
			return collegue;
		} else {
			throw new CollegueInvalideException();
		}
	}

	/**
	 * Permet de modifier l’url de la photo d’un collegue
	 * @param matricule matricule du collegue
	 * @param photoUrl nouvelle valeur de l’url de la photo
	 * @return un collegue
	 */
	public Collegue modifierPhotoUrl(String matricule, String photoUrl) {
		Collegue collegue = this.rechercherParMatricule(matricule);
		if (collegue == null) {
			throw new CollegueNonTrouveException();
		}

		if (collegueValidator.startWithhttp(collegue)) {
			collegue.setPhotoUrl(photoUrl);
			return collegue;
		} else {
			throw new CollegueInvalideException();
		}

	}

}
