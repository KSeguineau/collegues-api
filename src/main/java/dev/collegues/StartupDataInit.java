package dev.collegues;

import dev.collegues.repository.AuthentificationRepository;
import dev.collegues.repository.CollegueRepository;
import dev.collegues.utils.DataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;



/**
 * Classe initialisant la bdd
 */
@Component
public class StartupDataInit {

    /**
     * CollegueRepository pour faire des traitements sur la bdd
     */
    @Autowired
    private CollegueRepository collegueRepo;
    @Autowired
    private AuthentificationRepository authentificationRepo;

    /**
     * DataUtils pour récuperer les collegues pour remplir la bdd
     */
    private DataUtils dataUtils;

    public StartupDataInit(DataUtils dataUtils) {
        this.dataUtils = dataUtils;
    }

    /**
     * initialise la bdd
     */
    // La méthode init va être invoquée au démarrage de l'application.
    @EventListener(ContextRefreshedEvent.class)
    public void init() {
        dataUtils.getDataCollegue().values().forEach(c->collegueRepo.save(c));
        dataUtils.getDataAuthentification().forEach(a -> authentificationRepo.save(a));
    }

}
