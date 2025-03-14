package ma.iga.service_conge.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.iga.service_conge.modelClients.Employe;

import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class SoldeConge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int annee;
    private int soldeInitial = 18;
    private int soldeCongePris;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dernierMiseAjours;
    private int employeId;

    @Transient
    private Employe employe;


}
