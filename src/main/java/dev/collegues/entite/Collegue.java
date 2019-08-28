package dev.collegues.entite;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Collegue {

	private String matricule;
	private String nom;
	private String prenom;
	private String email;
	private LocalDate ddn;
	private String photoUrl;

}
