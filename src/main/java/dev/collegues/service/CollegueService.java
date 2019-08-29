package dev.collegues.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import dev.collegues.entite.Collegue;
import dev.collegues.exception.CollegueInvalideException;
import dev.collegues.exception.CollegueNonTrouveException;
import lombok.Data;

/**
 * Service gérant les collegues
 */
@Data
public class CollegueService {

	/**
	 * Map sauvegardant les collegues, leur clé est leur matricule
	 */
	private Map<String, Collegue> data = new HashMap<>();

	/**
	 * Constructeur par défaut
	 * créer automatiquement 4 collegues
	 */
	public CollegueService() {

		Collegue c = new Collegue(UUID.randomUUID().toString(), "bob", "bobby", "a@a.fr", LocalDate.now(),
				"https://upload.wikimedia.org/wikipedia/commons/thumb/e/e0/SNice.svg/180px-SNice.svg.png");
		data.put(c.getMatricule(), c);
		c = new Collegue(UUID.randomUUID().toString(), "tom", "tommy", "b@b.fr", LocalDate.now(),
				"https://upload.wikimedia.org/wikipedia/commons/thumb/e/e0/SNice.svg/180px-SNice.svg.png");
		data.put(c.getMatricule(), c);
		c = new Collegue(UUID.randomUUID().toString(), "math", "mathy", "c@c.fr", LocalDate.now(),
				"https://upload.wikimedia.org/wikipedia/commons/thumb/e/e0/SNice.svg/180px-SNice.svg.png");
		data.put(c.getMatricule(), c);
		c = new Collegue(UUID.randomUUID().toString(), "tim", "timmy", "d@d.fr", LocalDate.now(),
				"https://upload.wikimedia.org/wikipedia/commons/thumb/e/e0/SNice.svg/180px-SNice.svg.png");
		data.put(c.getMatricule(), c);
	}

	/**
	 * Permet de rechercher les collegues par leur nom
	 * @param nomRecherche nom du collegue à rechercher
	 * @return Une List de collegue */
	public List<Collegue> rechercherParNom(String nomRecherche) {
		List<Collegue> list = new ArrayList<>(data.values());
		return list.stream().filter(c -> c.getNom().equals(nomRecherche)).collect(Collectors.toList());
	}

	/**
	 * Permet de rechercher un collegue par son matricule
	 * @param matricule matricule du collegue
	 * @return un Collegue
	 */
	public Collegue rechercherParMatricule(String matricule) {
		Collegue c = data.get(matricule);
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
		boolean nom, prenom, email, photo, ddn;

		nom = collegue.getNom().length() > 1;
		prenom = collegue.getPrenom().length() > 1;
		email = collegue.getEmail().length() > 2 && collegue.getEmail().contains("@");
		photo = collegue.getPhotoUrl().startsWith("http");
		ddn = Period.between(collegue.getDdn(), LocalDate.now()).getYears() >= 18;
		collegue.setMatricule(UUID.randomUUID().toString());

		if (nom && prenom && email && photo && ddn) {
			this.data.put(collegue.getMatricule(), collegue);
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

		if (email.length() > 2 && email.contains("@")) {
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

		if (photoUrl.startsWith("http")) {
			collegue.setPhotoUrl(photoUrl);
			return collegue;
		} else {
			throw new CollegueInvalideException();
		}

	}

}
