package dev.collegues.controller;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.collegues.entite.Collegue;
import dev.collegues.exception.CollegueNonTrouveException;
import dev.collegues.service.CollegueService;

@RestController
public class CollegueController {

	CollegueService collegueService = new CollegueService();

	@GetMapping("/collegues")
	public List<String> findByName(@RequestParam String nom) {
		return collegueService.rechercherParNom(nom).stream().map(c -> c.getMatricule()).collect(toList());
	}

	@GetMapping("/collegues/{matricule}")
	public Collegue findByMatricule(@PathVariable String matricule) {
		return collegueService.rechercherParMatricule(matricule);
	}

	@ExceptionHandler({ CollegueNonTrouveException.class })
	public ResponseEntity<String> error() {
		return ResponseEntity.status(404)
				.body("Collegue non trouvé");

	}
}
