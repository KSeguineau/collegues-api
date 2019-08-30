package dev.collegues.utils;


import dev.collegues.entite.Collegue;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;

/**
 * Classe contenant des méthodes de validation de collegue
 */
@Component
public class CollegueValidator {


    /**
     * valide les informations d’un collegue
     * @param collegue collegue dont les informations doivent etre validé
     * @return un boolean
     */
    public boolean validerCollegue(Collegue collegue){
    boolean nom, prenom, email, photo, ddn;

    nom = longueurValide(collegue.getNom(), 1);
    prenom = longueurValide(collegue.getPrenom(), 1);
    email = longueurValide(collegue.getEmail(), 2) && isContains(collegue);
    photo = startWithhttp(collegue);
    ddn = ageValide(collegue);

    return nom && prenom && email && photo && ddn;


}

    /**
     * valide l’age d’un collegue
     * @param collegue un collegue
     * @return un boolean, vrai s’il est majeur
     */
    public boolean ageValide(Collegue collegue) {
        return Period.between(collegue.getDdn(), LocalDate.now()).getYears() >= 18;
    }

    /**
     * valide si l’url de la photo commence par http
     * @param collegue un collegue
     * @return un boolean
     */
    public boolean startWithhttp(Collegue collegue) {
        return collegue.getPhotoUrl().startsWith("http");
    }

    /**
     * valide si l’email contient @
     * @param collegue un collegue
     * @return un boolean
     */
    public boolean isContains(Collegue collegue) {
        return collegue.getEmail().contains("@");
    }

    /**
     * valide si la longueur d’une chaine de caractere est plus grande que la longueur indiqué en parametre
     * @param chaine chaine de caractere
     * @param i longueur minimal
     * @return un boolean
     */
    public boolean longueurValide(String chaine, int i) {
        return chaine.length() > i;
    }

}
