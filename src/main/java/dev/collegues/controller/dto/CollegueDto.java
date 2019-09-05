package dev.collegues.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CollegueDto {

    private String nom;
    private String prenom;
    private String matricule;
    private List<String> roles;
}
