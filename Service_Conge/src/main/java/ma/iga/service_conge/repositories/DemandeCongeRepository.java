package ma.iga.service_conge.repositories;

import feign.Param;
import ma.iga.service_conge.dto.DemandeCongeDTO;
import ma.iga.service_conge.entities.DemandeConge;
import ma.iga.service_conge.entities.StatutDemande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DemandeCongeRepository extends JpaRepository<DemandeConge, Integer> {
    List<DemandeConge> findByEmployeId(int idEmployee);

    DemandeConge findTopByEmployeIdAndStatutDemandeOrderByDateCreationDesc(int employeId, StatutDemande statutDemande);

    @Query("SELECT d FROM DemandeConge d WHERE d.statutDemande = :statutDemande " +
            "ORDER BY d.employeId, d.dateCreation DESC")
    List<DemandeConge> findTopGroupByIdEmployeStatutDemandeOrderByDateCreationDesc(@Param("statutDemande") StatutDemande statutDemande);
}