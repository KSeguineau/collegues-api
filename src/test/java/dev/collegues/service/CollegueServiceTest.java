package dev.collegues.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.Test;

import dev.collegues.entite.Collegue;
import dev.collegues.exception.CollegueInvalideException;

public class CollegueServiceTest {

	@Test
	public void testAjoutCollegueValide() {
		Collegue collegue = new Collegue(null, "Kong", "Diddy", "diddy.kong@bannane.fr",
				LocalDate.of(1990, 1, 1),
				"http://www.nioutaik.fr/index.php/gallery/General/fourre-tout/fail-02#gallery");
		CollegueService cs = new CollegueService();
		collegue = cs.ajouterUnCollegue(collegue);

		assertThat(cs.rechercherParMatricule(collegue.getMatricule())).isNotNull();
	}

	@Test(expected = CollegueInvalideException.class)
	public void testAjoutCollegueInvalideNom() {
		Collegue collegue = new Collegue(null, "K", "Diddy", "diddy.kong@bannane.fr",
				LocalDate.of(1990, 1, 1),
				"http://www.nioutaik.fr/index.php/gallery/General/fourre-tout/fail-02#gallery");
		CollegueService cs = new CollegueService();
		collegue = cs.ajouterUnCollegue(collegue);
	}

	@Test(expected = CollegueInvalideException.class)
	public void testAjoutCollegueInvalidePrenom() {
		Collegue collegue = new Collegue(null, "Kong", "D", "diddy.kong@bannane.fr",
				LocalDate.of(1990, 1, 1),
				"http://www.nioutaik.fr/index.php/gallery/General/fourre-tout/fail-02#gallery");
		CollegueService cs = new CollegueService();
		collegue = cs.ajouterUnCollegue(collegue);

	}

	@Test(expected = CollegueInvalideException.class)
	public void testAjoutCollegueInvalideEmail1() {
		Collegue collegue = new Collegue(null, "Kong", "Diddy", "@",
				LocalDate.of(1990, 1, 1),
				"http://www.nioutaik.fr/index.php/gallery/General/fourre-tout/fail-02#gallery");
		CollegueService cs = new CollegueService();
		collegue = cs.ajouterUnCollegue(collegue);
	}

	@Test(expected = CollegueInvalideException.class)
	public void testAjoutCollegueInvalideEmail2() {
		Collegue collegue = new Collegue(null, "Kong", "Diddy", "abc",
				LocalDate.of(1990, 1, 1),
				"http://www.nioutaik.fr/index.php/gallery/General/fourre-tout/fail-02#gallery");
		CollegueService cs = new CollegueService();
		collegue = cs.ajouterUnCollegue(collegue);
	}

	@Test(expected = CollegueInvalideException.class)
	public void testAjoutCollegueInvalideAge() {
		Collegue collegue = new Collegue(null, "Kong", "Diddy", "diddy.kong@bannane.fr",
				LocalDate.of(2010, 1, 1),
				"http://www.nioutaik.fr/index.php/gallery/General/fourre-tout/fail-02#gallery");
		CollegueService cs = new CollegueService();
		collegue = cs.ajouterUnCollegue(collegue);
	}

	@Test(expected = CollegueInvalideException.class)
	public void testAjoutCollegueinvalidePhotoUrl() {
		Collegue collegue = new Collegue(null, "Kong", "Diddy", "diddy.kong@bannane.fr",
				LocalDate.of(1990, 1, 1),
				"www.nioutaik.fr/index.php/gallery/General/fourre-tout/fail-02#gallery");
		CollegueService cs = new CollegueService();
		collegue = cs.ajouterUnCollegue(collegue);
	}
}
