package ma.iga.service_employes.DTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.iga.service_employes.entities.RoleEmploye;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class EmployeDTO {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private Date dateEmbauche;
    private String poste;
    private Boolean enConge;
    private boolean actif;

    private RoleEmploye roleEmploye;

}

