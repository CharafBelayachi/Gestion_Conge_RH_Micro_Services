package ma.iga.service_conge.services;

import ma.iga.service_conge.dto.DemandeCongeDTO;
import ma.iga.service_conge.entities.DemandeConge;
import ma.iga.service_conge.entities.StatutDemande;
import ma.iga.service_conge.repositories.DemandeCongeRepository;
import ma.iga.service_conge.restClient.EmployeRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class ValidationPourcentage {

    @Autowired
    private EmployeRestClient client;

    @Autowired
    private DemandeCongeRepository demandeCongeRepository;

    public boolean valider(DemandeCongeDTO newDemandeConge) {
        if(checkLastDemandeIsEnAttente(newDemandeConge.getEmployeId())) return false;
        //l'employe ne peut pas creer un demande si il a deja un demande en attend


        List<Integer> employesId = client.getEmployesIdActifsByDepartement(newDemandeConge.getEmployeId());

        // Récupérer les dernières demandes approuvées pour chaque employé
        List<DemandeConge> demandeConges = employesId.stream()
                .map(employeId -> demandeCongeRepository.findTopByEmployeIdAndStatutDemandeOrderByDateCreationDesc(employeId, StatutDemande.APPROUVEE))
                .filter(Objects::nonNull) // Filtrer les résultats null pour éviter les erreurs
                .toList();

        // Compter les intersections
        long count = demandeConges.stream()
                .filter(d -> intersectionExiste(d.getDateDebut(), d.getDateFin(), newDemandeConge.getDateDebut(), newDemandeConge.getDateFin()))
                .count();

        // Calculer le ratio en évitant la division par zéro
        double result = employesId.isEmpty() ? 0.0 : (double) count / employesId.size();

        // Retourner true si le taux d'intersection est inférieur à 50%
        return result < 0.5;
    }

    private Boolean checkLastDemandeIsEnAttente(int idEmploye){
        if(demandeCongeRepository.findTopByEmployeIdAndStatutDemandeOrderByDateCreationDesc(idEmploye, StatutDemande.EN_ATTENTE) != null){
            return true;
        }
        return false;
    }


    private boolean intersectionExiste(LocalDate debut1, LocalDate fin1, LocalDate debut2, LocalDate fin2) {
        return !(fin1.isBefore(debut2) || fin2.isBefore(debut1));
    }
}
