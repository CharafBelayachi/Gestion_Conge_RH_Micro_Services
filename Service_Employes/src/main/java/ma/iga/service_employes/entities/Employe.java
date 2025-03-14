package ma.iga.service_employes.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Employe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Enumerated(EnumType.STRING)
    private RoleEmploye roleEmploye;

//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    private Departement departement;



}
