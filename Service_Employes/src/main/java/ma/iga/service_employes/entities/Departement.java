package ma.iga.service_employes.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Departement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nomDepartement;
    private String description;
    private String adresse;
    private Date dateCreation;

    @OneToOne
    private Employe manager;

    @OneToMany(mappedBy = "departement", cascade = CascadeType.ALL)
    private List<Employe> employes;
}
