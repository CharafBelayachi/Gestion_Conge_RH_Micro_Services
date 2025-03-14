package ma.iga.service_conge.services;

import ma.iga.service_conge.dto.DemandeCongeDTO;
import ma.iga.service_conge.entities.DemandeConge;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DemandeCongeService {

    List<DemandeCongeDTO> getAllDemandeConge();
    DemandeCongeDTO getDemandeCongeById(int id);
    Optional<DemandeCongeDTO> CreateDemandeConge(DemandeCongeDTO demandeCongeDTO);
//    DemandeCongeDTO updateDemandeConge(DemandeCongeDTO demandeCongeDTO);
    void deleteDemandeConge(int id);
    List<DemandeCongeDTO> getDemandeCongeByEmployeId(int idEmployee);
    DemandeCongeDTO validerDemandeConge(DemandeCongeDTO demandeCongeDTO);
    DemandeCongeDTO refuseDemandeConge(DemandeCongeDTO demandeCongeDTO, String motifRefuse);


}
