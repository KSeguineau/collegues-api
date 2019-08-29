package dev.collegues.controller;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.collegues.entite.Collegue;
import dev.collegues.exception.CollegueInvalideException;
import dev.collegues.exception.CollegueNonTrouveException;
import dev.collegues.service.CollegueService;

@RestController
public class CollegueController {

	CollegueService collegueService = new CollegueService();

	@GetMapping("/collegues")
	public List<String> rechercherParNom(@RequestParam String nom) {
		return collegueService.rechercherParNom(nom).stream().map(c -> c.getMatricule()).collect(toList());
	}

	@GetMapping("/collegues/{matricule}")
	public Collegue rechercherParMatricule(@PathVariable String matricule) {
		return collegueService.rechercherParMatricule(matricule);
	}

	@PostMapping("/collegues")
	public Collegue ajouterCollegue(@RequestBody Collegue collegue) {
		return collegueService.ajouterUnCollegue(collegue);
	}

	@PatchMapping("/collegues/{matricule}")
	public Collegue modifierCollegue(@RequestBody Collegue collegue, @PathVariable String matricule) {
		if (collegue.getEmail() != null) {
			return collegueService.modifierEmail(matricule, collegue.getEmail());
		}
		if (collegue.getPhotoUrl() != null) {
			return collegueService.modifierPhotoUrl(matricule, collegue.getPhotoUrl());
		}
		throw new CollegueInvalideException();
	}

	@ExceptionHandler({ CollegueNonTrouveException.class })
	public ResponseEntity<String> errorNonTrouve() {
		return ResponseEntity.status(404)
				.body("Collegue non trouvé");

	}

	@ExceptionHandler({ CollegueInvalideException.class })
	public ResponseEntity<String> errorInvalide() {
		return ResponseEntity.status(500)
				.body("Informations du collegue incorrect");

	}
}
