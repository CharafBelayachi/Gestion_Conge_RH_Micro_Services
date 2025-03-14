package ma.iga.service_conge.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.iga.service_conge.modelClients.Employe;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class DemandeConge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
//    @Temporal(TemporalType.TIMESTAMP)
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private String motif;
    private Date dateCreation;
    private Date dateModification;
    private String motifRefus;
    @Enumerated(EnumType.STRING)
    private StatutDemande statutDemande=StatutDemande.EN_ATTENTE;

    private int employeId;

    //Pour fiare la recherche de l'employe dans autre microservice
    @Transient
    private Employe employe;



    public int calculerJoursDemande(){
        if (dateDebut == null || dateFin == null) {
            throw new IllegalArgumentException("Les dates ne doivent pas être nulles");
        }
        return (int)ChronoUnit.DAYS.between(dateDebut, dateFin);
    }

}
