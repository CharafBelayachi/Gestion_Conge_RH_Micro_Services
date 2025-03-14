package ma.iga.service_employes.services;

import ma.iga.service_employes.DTO.DepartementDTO;
import ma.iga.service_employes.DTO.EmployeDTO;
import ma.iga.service_employes.entities.Departement;
import ma.iga.service_employes.entities.Employe;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface EmployeService {
    Optional<EmployeDTO> ajouterEmploye(EmployeDTO employeDTO, int departementId);
    EmployeDTO modifierEmploye(EmployeDTO employeDTO, int departementId);
    List<EmployeDTO> ListerEmployer();
    void supprimerEmploye(int id);
    EmployeDTO getEmployeById(int id);
    List<EmployeDTO> getManagers();
    List<EmployeDTO> getAllEmployeesByDepartement(int departementId);
    List<Integer> getEmployesIdActifs(int idEmploye);

    void updateEmployesEnConge(int employeId, boolean enConge);
}
