package ma.iga.service_conge.modelClients;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Employe {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private Date dateEmbauche;
    private String poste;
    private Boolean enConge;
    private boolean actif;
    private int soldeConge;
}
