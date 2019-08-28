package dev.collegues.controller;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.collegues.service.CollegueService;

@RestController
public class CollegueController {

	@GetMapping("/collegues")
	public List<String> findByName(@RequestParam String nom) {
		CollegueService collegueService = new CollegueService();
		return collegueService.rechercherParNom(nom).stream().map(c -> c.getMatricule()).collect(toList());
	}
}
