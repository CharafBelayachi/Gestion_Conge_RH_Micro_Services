package ma.iga.service_employes.web;

import ma.iga.service_employes.DTO.DepartementDTO;
import ma.iga.service_employes.DTO.EmployeDTO;
import ma.iga.service_employes.entities.Departement;
import ma.iga.service_employes.entities.Employe;
import ma.iga.service_employes.services.EmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employes")
public class EmployeController {
    @Autowired
    EmployeService empService;

    @GetMapping
    public List<EmployeDTO> getAllEmployees() {
        return empService.ListerEmployer();
    }

    @GetMapping("/ByDepartement/{departementId}")
    public List<EmployeDTO> getAllEmployeesByDepartment(@PathVariable int departementId) {
        return empService.getAllEmployeesByDepartement(departementId);
    }

    @GetMapping("/{id}")
    public EmployeDTO GetEmploye(@PathVariable int id) {
        return empService.getEmployeById(id);
    }

    @PostMapping("/{departementId}")
    public Optional<EmployeDTO> addEmploye(@RequestBody EmployeDTO employeDTO, @PathVariable int departementId) {
        return empService.ajouterEmploye(employeDTO, departementId) ;
    }

    @PutMapping("/{departementId}")
    public EmployeDTO UpdateEmploye(@RequestBody EmployeDTO employeDTO,@PathVariable int departementId){
        return empService.modifierEmploye(employeDTO,departementId) ;
    }

    @DeleteMapping
    public void DeleteEmploye(@RequestParam(name = "id") int id) {
        empService.supprimerEmploye(id);
    }

    @GetMapping("/managers")
    public List<EmployeDTO> GetEmployeesManagers() {
        return empService.getManagers();
    }

    @GetMapping("/actifs/{idEmploye}")
    public List<Integer> getEmployesIdActifsByDepartement(@PathVariable int idEmploye) {
        return empService.getEmployesIdActifs(idEmploye);
    }

    @PutMapping("/update-en-conge/{employeId}")
    public EmployeDTO updateEmployesEnConge(@PathVariable int employeId,@RequestBody boolean enConge){
        return empService.updateEmployesEnConge(employeId, enConge);
    }

    @PutMapping("/employes/updateSolde/{employeId}")
    public EmployeDTO updateEmployeSoldeConge(@PathVariable int employeId, @RequestBody int newSoldeConge){
        return empService.updateEmployeSoldeConge(employeId, newSoldeConge);
    }


}
