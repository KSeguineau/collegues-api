package dev.collegues.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.UUID;

import org.junit.Test;

import dev.collegues.entite.Collegue;
import dev.collegues.exception.CollegueInvalideException;
import dev.collegues.exception.CollegueNonTrouveException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CollegueServiceIntegrationTest {

    @Autowired
    private CollegueService collegueService;



    @Test
    public void test_RechercheParNom_Valide(){
        Collegue c = new Collegue(UUID.randomUUID().toString(), "bob", "bobby", "a@a.fr", LocalDate.now(),
                "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e0/SNice.svg/180px-SNice.svg.png");
        collegueService.getCollegueRepository().save(c);
                assertThat(collegueService.rechercherParNom("bob")).isNotNull();
    }

    @Test
    public void test_RechercherParNom_Invalide(){
        assertThat(collegueService.rechercherParNom(UUID.randomUUID().toString())).isEmpty();
    }

    @Test
    public void test_RechercherParMatricule_Valide(){
        Collegue c = new Collegue(UUID.randomUUID().toString(), "bob", "bobby", "a@a.fr", LocalDate.now(),
                "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e0/SNice.svg/180px-SNice.svg.png");
        collegueService.getCollegueRepository().save(c);
        assertThat(collegueService.rechercherParMatricule(c.getMatricule())).isNotNull();
    }

    @Test(expected = CollegueNonTrouveException.class)
    public void test_RechercherParMatricule_Invalide(){
        collegueService.rechercherParMatricule(UUID.randomUUID().toString());
    }

    @Test
    public void test_AjouterUnCollegue_Valide(){
        Collegue c = new Collegue(UUID.randomUUID().toString(), "bob", "bobby", "a@a.fr", LocalDate.of(1990,1,1),
                "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e0/SNice.svg/180px-SNice.svg.png");
        collegueService.ajouterUnCollegue(c);
        assertThat(collegueService.getCollegueRepository().findByMatricule(c.getMatricule())).isNotNull();
    }

    @Test(expected = CollegueInvalideException.class)
    public void test_AjouterUnCollegue_InformationInvalide(){
        Collegue c = new Collegue(UUID.randomUUID().toString(), "b", "bobby", "a@a.fr", LocalDate.now(),
                "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e0/SNice.svg/180px-SNice.svg.png");
        collegueService.ajouterUnCollegue(c);
    }

    @Test
    public void test_ModifierEmail_Valide(){
        Collegue c = new Collegue(UUID.randomUUID().toString(), "bob", "bobby", "a@a.fr", LocalDate.now(),
                "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e0/SNice.svg/180px-SNice.svg.png");
        collegueService.getCollegueRepository().save(c);
        collegueService.modifierEmail(c.getMatricule(),"toto@toto.fr");
        assertThat(collegueService.getCollegueRepository().findByMatricule(c.getMatricule()).getEmail()).isNotEqualTo(c.getEmail());
    }

    @Test(expected = CollegueInvalideException.class)
    public void test_ModifierEmail_InformationInvalide(){
        Collegue c = new Collegue(UUID.randomUUID().toString(), "bob", "bobby", "a@a.fr", LocalDate.now(),
                "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e0/SNice.svg/180px-SNice.svg.png");
        collegueService.getCollegueRepository().save(c);
        collegueService.modifierEmail(c.getMatricule(),"tot.fr");

    }

    @Test
    public void test_ModifierPhotoUrl_Valide(){
        Collegue c = new Collegue(UUID.randomUUID().toString(), "bob", "bobby", "a@a.fr", LocalDate.now(),
                "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e0/SNice.svg/180px-SNice.svg.png");
        collegueService.getCollegueRepository().save(c);
        collegueService.modifierPhotoUrl(c.getMatricule(),"http://new.fr");
        assertThat(collegueService.getCollegueRepository().findByMatricule(c.getMatricule()).getPhotoUrl()).isNotEqualTo(c.getPhotoUrl());
    }

    @Test(expected = CollegueInvalideException.class)
    public void test_ModifierPhotoUrl_InformationInvalide(){
        Collegue c = new Collegue(UUID.randomUUID().toString(), "bob", "bobby", "a@a.fr", LocalDate.now(),
                "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e0/SNice.svg/180px-SNice.svg.png");
        collegueService.getCollegueRepository().save(c);
        collegueService.modifierPhotoUrl(c.getMatricule(),"/new.fr");
    }


}
