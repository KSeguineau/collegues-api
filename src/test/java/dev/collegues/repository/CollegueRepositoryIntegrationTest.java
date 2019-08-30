package dev.collegues.repository;

import dev.collegues.entite.Collegue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CollegueRepositoryIntegrationTest {

    @Autowired
    private CollegueRepository collegueRepository;



    @Test
    public void test_findByNom_Valide(){
        Collegue c = new Collegue(UUID.randomUUID().toString(), "bob", "bobby", "a@a.fr", LocalDate.now(),
                "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e0/SNice.svg/180px-SNice.svg.png");
        collegueRepository.save(c);
        List<Collegue> list = collegueRepository.findByNom("bob");
        assertThat(list.size()>0).isTrue();
    }

    @Test
    public void test_findByNom_Invalide(){
        Collegue c = new Collegue(UUID.randomUUID().toString(), "tom", "bobby", "a@a.fr", LocalDate.now(),
                "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e0/SNice.svg/180px-SNice.svg.png");
        collegueRepository.save(c);
        List<Collegue> list = collegueRepository.findByNom("bob");
        assertThat(list.size()>0).isFalse();
    }



    @Test
    public void test_findByMatricule_Valide(){
        Collegue c = new Collegue(UUID.randomUUID().toString(), "bob", "bobby", "a@a.fr", LocalDate.now(),
                "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e0/SNice.svg/180px-SNice.svg.png");
        collegueRepository.save(c);
        Collegue collegue = collegueRepository.findByMatricule(c.getMatricule());
        assertThat(collegue).isNotNull();
    }

    @Test
    public void test_findByMatricule_Invalide(){
        Collegue c = new Collegue(UUID.randomUUID().toString(), "bob", "bobby", "a@a.fr", LocalDate.now(),
                "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e0/SNice.svg/180px-SNice.svg.png");
        collegueRepository.save(c);
        Collegue collegue = collegueRepository.findByMatricule("123");
        assertThat(collegue).isNull();
    }
}
