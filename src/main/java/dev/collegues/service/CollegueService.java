package dev.collegues.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import dev.collegues.entite.Collegue;
import lombok.Data;

@Data
public class CollegueService {

	private Map<String, Collegue> data = new HashMap<>();

	public CollegueService() {

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

	public List<Collegue> rechercherParNom(String nomRecherche) {
		List<Collegue> list = new ArrayList<Collegue>(data.values());
		return list.stream().filter(c -> c.getNom().equals(nomRecherche)).collect(Collectors.toList());
	}
}
