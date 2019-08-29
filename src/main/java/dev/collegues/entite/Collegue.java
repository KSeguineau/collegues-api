package dev.collegues.entite;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Clase représentant un collegue
 */
@Data
@AllArgsConstructor
public class Collegue {

	/**
	 * matricule du collegue
	 */
	private String matricule;
	/**
	 * nom du collegue
	 */
	private String nom;
	/**
	 * prenom du collegue
	 */
	private String prenom;
	/**
	 * email du collegue
	 */
	private String email;
	/**
	 * date de naissance du collegue
	 */
	private LocalDate ddn;
	/**
	 * url de la photo du collegue
	 */
	private String photoUrl;

}
