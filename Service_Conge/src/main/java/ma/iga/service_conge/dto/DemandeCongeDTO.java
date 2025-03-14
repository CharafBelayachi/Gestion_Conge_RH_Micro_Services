package ma.iga.service_conge.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.iga.service_conge.entities.StatutDemande;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class DemandeCongeDTO {

    private int id;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private String motif;
    private Date dateCreation;
    private Date dateModification;
    private String motifRefus;
    private StatutDemande statutDemande=StatutDemande.EN_ATTENTE;

    private int employeId;
}
