package dev.collegues;

import dev.collegues.entite.Collegue;
import dev.collegues.repository.CollegueRepository;
import dev.collegues.utils.DataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.UUID;

@Component
public class StartupDataInit {

    @Autowired
    private CollegueRepository collegueRepo;
    private DataUtils dataUtils;

    public StartupDataInit(DataUtils dataUtils) {
        this.dataUtils = dataUtils;
    }

    // La méthode init va être invoquée au démarrage de l'application.
    @EventListener(ContextRefreshedEvent.class)
    public void init() {
        dataUtils.getData().values().forEach(c->collegueRepo.save(c));
    }

}
