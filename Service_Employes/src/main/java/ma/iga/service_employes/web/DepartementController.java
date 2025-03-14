package ma.iga.service_employes.web;

import ma.iga.service_employes.DTO.DepartementDTO;
import ma.iga.service_employes.entities.Departement;
import ma.iga.service_employes.entities.Employe;
import ma.iga.service_employes.services.DepartementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departements")
public class DepartementController {
    @Autowired
    DepartementService departementService;

    @GetMapping
    public List<DepartementDTO> getAllDepartements() {
        return departementService.getAllDepartements();
    }

    @GetMapping("/{id}")
    public DepartementDTO getDepartementById(@PathVariable int id) {
        return departementService.getDepartement(id);
    }

    @PostMapping
    public DepartementDTO saveDepartement(@RequestBody DepartementDTO departementDTO) {
        return departementService.AjouterDepartement(departementDTO);
    }

    @PutMapping
    public DepartementDTO updateDepartement(@RequestBody DepartementDTO departementDTO) {
        return departementService.updateDepartement(departementDTO);
    }

    @DeleteMapping
    public void deleteDepartement(@RequestParam int id) {
        departementService.deleteDepartement(id);
    }



}
