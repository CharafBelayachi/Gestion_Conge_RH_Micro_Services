package ma.iga.service_employes.services;

import ma.iga.service_employes.DTO.DepartementDTO;
import ma.iga.service_employes.entities.Departement;
import ma.iga.service_employes.entities.Employe;

import java.util.List;

public interface DepartementService {
    DepartementDTO getDepartement(int id);
    DepartementDTO AjouterDepartement(DepartementDTO departementDTO);
    List<DepartementDTO> getAllDepartements();
    DepartementDTO updateDepartement(DepartementDTO departementDTO);
    void deleteDepartement(int id);

}
