package dev.collegues.utils;

import dev.collegues.entite.Collegue;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class DataUtils {

    /**
     * Map sauvegardant les collegues, leur clé est leur matricule
     */
    private Map<String, Collegue> data = new HashMap<>();

    /**
     * Constructeur par défaut
     * créer automatiquement 4 collegues
     */
    public DataUtils() {

        Collegue c = new Collegue(UUID.randomUUID().toString(), "bob", "bobby", "a@a.fr", LocalDate.now(),
                "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e0/SNice.svg/180px-SNice.svg.png");
        data.put(c.getMatricule(), c);
        c = new Collegue(UUID.randomUUID().toString(), "tom", "tommy", "b@b.fr", LocalDate.now(),
                "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e0/SNice.svg/180px-SNice.svg.png");
        data.put(c.getMatricule(), c);
        c = new Collegue(UUID.randomUUID().toString(), "math", "mathy", "c@c.fr", LocalDate.now(),
                "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e0/SNice.svg/180px-SNice.svg.png");
        data.put(c.getMatricule(), c);
        c = new Collegue(UUID.randomUUID().toString(), "tim", "timmy", "d@d.fr", LocalDate.now(),
                "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e0/SNice.svg/180px-SNice.svg.png");
        data.put(c.getMatricule(), c);

    }

    public List<Collegue> rechercheParNom(String nomRecherche){
        List<Collegue> list = new ArrayList<>(data.values());
        return list.stream().filter(c -> c.getNom().equals(nomRecherche)).collect(Collectors.toList());
    }

    public Collegue rechercheParMatricule(String matricule){
        return data.get(matricule);
    }

    public void ajouterCollegue(Collegue collegue){
        data.put(collegue.getMatricule(),collegue);
    }

}
