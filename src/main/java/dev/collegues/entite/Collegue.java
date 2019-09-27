package dev.collegues.entite;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Clase représentant un collegue
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Collegue {

	/**
	 * matricule du collegue
	 */
	@Id
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
	@Column(unique = true)
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
