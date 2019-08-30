package dev.collegues.utils;

import static org.assertj.core.api.Assertions.assertThat;

import dev.collegues.entite.Collegue;
import org.junit.Test;

import java.time.LocalDate;
import java.util.UUID;

public class CollegueValidatorTest {

    @Test
    public void test_ValiderCollegue_Valide(){
    CollegueValidator collegueValidator = new CollegueValidator();
    Collegue c = new Collegue(UUID.randomUUID().toString(), "bob", "bobby", "a@a.fr", LocalDate.of(1990,1,1),
            "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e0/SNice.svg/180px-SNice.svg.png");
    assertThat(collegueValidator.validerCollegue(c)).isTrue();
}

    @Test
    public void test_ValiderCollegue_Invalide(){
        CollegueValidator collegueValidator = new CollegueValidator();
        Collegue c = new Collegue(UUID.randomUUID().toString(), "bob", "bobby", "a@a.fr", LocalDate.now(),
                "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e0/SNice.svg/180px-SNice.svg.png");
        assertThat(collegueValidator.validerCollegue(c)).isFalse();
    }

    @Test
    public void test_AgeValide_Valide(){
        CollegueValidator collegueValidator = new CollegueValidator();
        Collegue c = new Collegue(UUID.randomUUID().toString(), "bob", "bobby", "a@a.fr", LocalDate.of(1990,1,1),
                "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e0/SNice.svg/180px-SNice.svg.png");
        assertThat(collegueValidator.ageValide(c)).isTrue();
    }

    @Test
    public void test_AgeValide_Invalide(){
        CollegueValidator collegueValidator = new CollegueValidator();
        Collegue c = new Collegue(UUID.randomUUID().toString(), "bob", "bobby", "a@a.fr", LocalDate.now(),
                "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e0/SNice.svg/180px-SNice.svg.png");
        assertThat(collegueValidator.ageValide(c)).isFalse();
    }

    @Test
    public void test_StartWithHttp_Valide(){
        CollegueValidator collegueValidator = new CollegueValidator();
        Collegue c = new Collegue(UUID.randomUUID().toString(), "bob", "bobby", "a@a.fr", LocalDate.now(),
                "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e0/SNice.svg/180px-SNice.svg.png");
        assertThat(collegueValidator.startWithhttp(c)).isTrue();
    }

    @Test
    public void test_StartWithHttp_Invalide(){
        CollegueValidator collegueValidator = new CollegueValidator();
        Collegue c = new Collegue(UUID.randomUUID().toString(), "bob", "bobby", "a@a.fr", LocalDate.now(),
                "/upload.wikimedia.org/wikipedia/commons/thumb/e/e0/SNice.svg/180px-SNice.svg.png");
        assertThat(collegueValidator.startWithhttp(c)).isFalse();
    }

    @Test
    public void test_IsContains_Valide(){
        CollegueValidator collegueValidator = new CollegueValidator();
        Collegue c = new Collegue(UUID.randomUUID().toString(), "bob", "bobby", "a@a.fr", LocalDate.now(),
                "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e0/SNice.svg/180px-SNice.svg.png");
        assertThat(collegueValidator.isContains(c)).isTrue();
    }

    @Test
    public void test_IsContains_Invalide(){
        CollegueValidator collegueValidator = new CollegueValidator();
        Collegue c = new Collegue(UUID.randomUUID().toString(), "bob", "bobby", "aa.fr", LocalDate.now(),
                "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e0/SNice.svg/180px-SNice.svg.png");
        assertThat(collegueValidator.isContains(c)).isFalse();
    }

    @Test
    public void test_LongueurValide_Valide(){
        CollegueValidator collegueValidator = new CollegueValidator();
        Collegue c = new Collegue(UUID.randomUUID().toString(), "bob", "bobby", "a@a.fr", LocalDate.now(),
                "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e0/SNice.svg/180px-SNice.svg.png");
        assertThat(collegueValidator.longueurValide(c.getNom(),2)).isTrue();
    }

    @Test
    public void test_LongueurValide_Invalide(){
        CollegueValidator collegueValidator = new CollegueValidator();
        Collegue c = new Collegue(UUID.randomUUID().toString(), "b", "bobby", "a@a.fr", LocalDate.now(),
                "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e0/SNice.svg/180px-SNice.svg.png");
        assertThat(collegueValidator.longueurValide(c.getNom(),2)).isFalse();
    }




}
