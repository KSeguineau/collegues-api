package dev.collegues.utils;

import dev.collegues.entite.Authentification;
import dev.collegues.entite.Collegue;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.*;

/**
 * Classe fournissant des collegues
 */
@Data
@Component
public class DataUtils {

    /**
     * Map sauvegardant les collegues, leur clé est leur matricule
     */
    private Map<String, Collegue> dataCollegue = new HashMap<>();
    private List<Authentification> dataAuthentification = new ArrayList<>();


    private PasswordEncoder passwordEncoder;

    public DataUtils(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void createCollegueAuthentification(){

        Collegue c = new Collegue(UUID.randomUUID().toString(), "bob", "bobby", "a@a.fr", LocalDate.now(),
                "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e0/SNice.svg/180px-SNice.svg.png");
        dataCollegue.put(c.getMatricule(), c);
        c = new Collegue(UUID.randomUUID().toString(), "tom", "tommy", "b@b.fr", LocalDate.now(),
                "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e0/SNice.svg/180px-SNice.svg.png");
        dataCollegue.put(c.getMatricule(), c);
        c = new Collegue(UUID.randomUUID().toString(), "math", "mathy", "c@c.fr", LocalDate.now(),
                "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e0/SNice.svg/180px-SNice.svg.png");
        dataCollegue.put(c.getMatricule(), c);
        c = new Collegue(UUID.randomUUID().toString(), "tim", "timmy", "d@d.fr", LocalDate.now(),
                "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e0/SNice.svg/180px-SNice.svg.png");
        dataCollegue.put(c.getMatricule(), c);

        dataCollegue.values().forEach(v -> dataAuthentification.add(new Authentification(null,v.getNom(),passwordEncoder.encode(v.getPrenom()),v,Arrays.asList("ROLE_USER"))));
        dataAuthentification.get(1).setRoles(Arrays.asList("ROLE_ADMIN"));
        dataAuthentification.get(3).setRoles(Arrays.asList("ROLE_ADMIN"));

    }



}
