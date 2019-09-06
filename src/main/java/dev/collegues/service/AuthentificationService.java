package dev.collegues.service;

import dev.collegues.controller.dto.CollegueDto;
import dev.collegues.controller.dto.InfosAuthentificationPost;
import dev.collegues.entite.Authentification;
import dev.collegues.exception.AuthentificationIncorrect;
import dev.collegues.exception.CollegueNonTrouveException;
import dev.collegues.repository.AuthentificationRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthentificationService {

    private AuthentificationRepository authentificationRepository;
    private PasswordEncoder passwordEncoder;

    @Value("${jwt.expires_in}")
    private Integer EXPIRES_IN;

    @Value("${jwt.cookie}")
    private String TOKEN_COOKIE;

    @Value("${jwt.secret}")
    private String SECRET;

    public AuthentificationService(AuthentificationRepository authentificationRepository, PasswordEncoder passwordEncoder) {
        this.authentificationRepository = authentificationRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity<?> authenticate(InfosAuthentificationPost infos){
        return  this.authentificationRepository.findByLogin(infos.getLogin())
                .filter(utilisateur -> passwordEncoder.matches(infos.getMdp(),utilisateur.getMdp()))
                .map(utilisateur -> {
                    Map<String,Object> infosSupplementaireToken = new HashMap<>();
                    infosSupplementaireToken.put("roles",utilisateur.getRoles());
                    infosSupplementaireToken.put("matricule",authentificationRepository.findByLogin(utilisateur.getLogin()).orElseThrow(CollegueNonTrouveException::new).getCollegue().getMatricule());

                    String jetonJWT = Jwts.builder()
                            .setSubject(utilisateur.getLogin())
                            .addClaims(infosSupplementaireToken)
                            .setExpiration(new Date(System.currentTimeMillis()+EXPIRES_IN*1000))
                            .signWith(SignatureAlgorithm.HS512,SECRET)
                            .compact();

                    ResponseCookie tokenCookie = ResponseCookie.from(TOKEN_COOKIE,jetonJWT)
                            .httpOnly(true)
                            .maxAge(EXPIRES_IN)
                            .path("/")
                            .build();

                    return ResponseEntity.ok()
                            .header(HttpHeaders.SET_COOKIE, tokenCookie.toString())
                            .build();

                })
                .orElseGet(()->ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    public CollegueDto findCollegue(String login){
        Authentification authentification = authentificationRepository.findByLogin(login).orElseThrow(AuthentificationIncorrect::new);
        CollegueDto collegueDto = new CollegueDto(authentification.getCollegue().getNom(),authentification.getCollegue().getPrenom(),authentification.getCollegue().getMatricule(),authentification.getRoles());
        return collegueDto;
    }
}
