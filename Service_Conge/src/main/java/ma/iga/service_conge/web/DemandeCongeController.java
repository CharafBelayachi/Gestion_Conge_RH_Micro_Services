package ma.iga.service_conge.web;

import ma.iga.service_conge.dto.DemandeCongeDTO;
import ma.iga.service_conge.dto.RefusDemandeRequest;
import ma.iga.service_conge.entities.DemandeConge;
import ma.iga.service_conge.entities.StatutDemande;
import ma.iga.service_conge.services.DemandeCongeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/demandes")
public class DemandeCongeController {

    @Autowired
    private DemandeCongeService service;

    @GetMapping
    public List<DemandeCongeDTO> getAllDemandeConge(){
    return service.getAllDemandeConge();
    }

    @GetMapping("/{id}")
    public DemandeCongeDTO getDemandeCongeById(@PathVariable int id){
        return service.getDemandeCongeById(id);
    }

    @GetMapping("/ByEmploye/{id}")
    public List<DemandeCongeDTO> getDemandesByEmployeId(@PathVariable int id){
        return service.getDemandeCongeByEmployeId(id);
    }

    @PostMapping
    public Optional<DemandeCongeDTO> addDemandeConge(@RequestBody DemandeCongeDTO demandeCongeDTO) {
        return service.CreateDemandeConge(demandeCongeDTO);
    }

    @PutMapping("/valide")
    public DemandeCongeDTO validerDemandeConge(@RequestBody DemandeCongeDTO demandeCongeDTO) {
        return service.validerDemandeConge(demandeCongeDTO);
    }

    @PutMapping("/refus")
    public DemandeCongeDTO refuseDemandeConge(@RequestBody RefusDemandeRequest refusDemandeRequest) {
        return service.refuseDemandeConge(refusDemandeRequest.getDemandeCongeDTO(),refusDemandeRequest.getMotifRefus());
    }


    @DeleteMapping
    public void deleteDemandeConge(@RequestParam int id) {
        service.deleteDemandeConge(id);
    }








}
