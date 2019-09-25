package dev.collegues.controller;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import dev.collegues.entite.Collegue;
import dev.collegues.exception.CollegueInvalideException;
import dev.collegues.exception.CollegueNonTrouveException;
import dev.collegues.service.CollegueService;

/**
 * Classe representant le controller de Collegue
 */
@RestController
public class CollegueController {

	/**
	 * Classe service des collegues
	 */
	private CollegueService collegueService;

	@Autowired
	public CollegueController(CollegueService collegueService) {
		this.collegueService = collegueService;
	}

	/**
	 * traite la requete de recherche par nom
	 * @param nom nom du Collegue
	 * @return la liste des matricules des collegues trouvé
	 */
	@GetMapping("/collegues")
	public List<String> rechercherParNom(@RequestParam String nom) {
		return collegueService.rechercherParNom(nom).stream().map(c -> c.getMatricule()).collect(toList());
	}

	/**
	 * traite la requete de recherche par matricule
	 * @param matricule matricule d’un collegue
	 * @return un collegue
	 */
	@GetMapping("/collegues/{matricule}")
	public Collegue rechercherParMatricule(@PathVariable String matricule) {
		return collegueService.rechercherParMatricule(matricule);
	}

	/**
	 * Traite la requete d’ajout de collegue
	 * @param collegue le collegue à ajouter
	 * @return un collegue
	 */
	@PostMapping("/collegues")
	public Collegue ajouterCollegue(@RequestBody Collegue collegue) {
		return collegueService.ajouterUnCollegue(collegue);
	}

	/**
	 * Traite la requete de modification d’un collegue
	 * @param collegue collegue portant la modification
	 * @param matricule matricule du collegue à modifier
	 * @return le collegue modifié
	 */
	@PatchMapping("/collegues/{matricule}")
	public Collegue modifierCollegue(@RequestBody Collegue collegue, @PathVariable String matricule) {
		Collegue colleguemodifie = null;
		if (collegue.getEmail() != null) {
			colleguemodifie=collegueService.modifierEmail(matricule, collegue.getEmail());
		}
		if (collegue.getPhotoUrl() != null) {
			colleguemodifie=collegueService.modifierPhotoUrl(matricule, collegue.getPhotoUrl());
		}
		return colleguemodifie;
	}

	/**
	 * Catch les exception CollegueNonTrouve
	 * @return une ResponseEntity
	 */
	@ExceptionHandler({ CollegueNonTrouveException.class })
	public ResponseEntity<String> errorNonTrouve() {
		return ResponseEntity.status(404)
				.body("Collegue non trouvé");

	}

	/**
	 * Catch les exception CollegueInvalide
	 * @return une ResponseEntity
	 */
	@ExceptionHandler({ CollegueInvalideException.class })
	public ResponseEntity<String> errorInvalide() {
		return ResponseEntity.status(400)
				.body("Informations du collegue incorrect");

	}
}
