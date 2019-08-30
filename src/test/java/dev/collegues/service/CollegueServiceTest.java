package dev.collegues.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.Test;

import dev.collegues.entite.Collegue;
import dev.collegues.exception.CollegueInvalideException;
import dev.collegues.exception.CollegueNonTrouveException;

public class CollegueServiceTest {
 //TODO faire classe test validator
	/*@Test
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

	@Test
	public void testModificationEmailValide() {
		CollegueService cs = new CollegueService();
		Collegue collegue = new Collegue(null, "Kong", "Diddy", "diddy.kong@bannane.fr",
				LocalDate.of(1990, 1, 1),
				"http://www.nioutaik.fr/index.php/gallery/General/fourre-tout/fail-02#gallery");
		collegue = cs.ajouterUnCollegue(collegue);
		String email = "dd@b.fr";
		cs.modifierEmail(collegue.getMatricule(), email);
		assertThat(cs.rechercherParMatricule(collegue.getMatricule()).getEmail()).isEqualTo(email);
	}

	@Test(expected = CollegueNonTrouveException.class)
	public void testModificationEmailInvalideMatriculeNonTrouve() {
		CollegueService cs = new CollegueService();
		String email = "@";
		cs.modifierEmail("123", email);
	}

	@Test(expected = CollegueInvalideException.class)
	public void testModificationEmailInvalide1() {
		CollegueService cs = new CollegueService();
		Collegue collegue = new Collegue(null, "Kong", "Diddy", "diddy.kong@bannane.fr",
				LocalDate.of(1990, 1, 1),
				"http://www.nioutaik.fr/index.php/gallery/General/fourre-tout/fail-02#gallery");
		collegue = cs.ajouterUnCollegue(collegue);
		String email = "@";
		cs.modifierEmail(collegue.getMatricule(), email);

	}

	@Test(expected = CollegueInvalideException.class)
	public void testModificationEmailInvalide2() {
		CollegueService cs = new CollegueService();
		Collegue collegue = new Collegue(null, "Kong", "Diddy", "diddy.kong@bannane.fr",
				LocalDate.of(1990, 1, 1),
				"http://www.nioutaik.fr/index.php/gallery/General/fourre-tout/fail-02#gallery");
		collegue = cs.ajouterUnCollegue(collegue);
		String email = "abc";
		cs.modifierEmail(collegue.getMatricule(), email);

	}

	@Test
	public void testModificationPhotoUrlValide() {
		CollegueService cs = new CollegueService();
		Collegue collegue = new Collegue(null, "Kong", "Diddy", "diddy.kong@bannane.fr",
				LocalDate.of(1990, 1, 1),
				"http://www.nioutaik.fr/index.php/gallery/General/fourre-tout/fail-02#gallery");
		collegue = cs.ajouterUnCollegue(collegue);
		String photo = "http://new.fr";
		cs.modifierPhotoUrl(collegue.getMatricule(), photo);
		assertThat(cs.rechercherParMatricule(collegue.getMatricule()).getPhotoUrl()).isEqualTo(photo);
	}

	@Test(expected = CollegueNonTrouveException.class)
	public void testModificationPhotoUrlInvalideMatriculeNonTrouve() {
		CollegueService cs = new CollegueService();
		String photo = "http://new.fr";
		cs.modifierEmail("123", photo);
	}

	@Test(expected = CollegueInvalideException.class)
	public void testModificationPhotoUrlInvalide() {
		CollegueService cs = new CollegueService();
		Collegue collegue = new Collegue(null, "Kong", "Diddy", "diddy.kong@bannane.fr",
				LocalDate.of(1990, 1, 1),
				"http://www.nioutaik.fr/index.php/gallery/General/fourre-tout/fail-02#gallery");
		collegue = cs.ajouterUnCollegue(collegue);
		String photo = "/new.fr";
		cs.modifierPhotoUrl(collegue.getMatricule(), photo);

	}*/

}
