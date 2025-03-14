package ma.iga.service_conge.restClient;

import ma.iga.service_conge.modelClients.Employe;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(url = "http://localhost:8081",name = "service-employes")
public interface EmployeRestClient {

    @GetMapping("/employes")
    List<Employe> getAllEmployes();

    @GetMapping("/employes/{id}")
    Employe getEmployeById(@PathVariable int id);


    @GetMapping("/employes/actifs/{idEmploye}")
    List<Integer> getEmployesIdActifsByDepartement(@PathVariable int idEmploye);

    @PutMapping("/employes/update-en-conge/{employeId}")
    Employe updateEmployesEnConge(@PathVariable int employeId,@RequestBody boolean enConge);

    @PutMapping("/employes/updateSolde/{employeId}")
    Employe updateEmployeSoldeConge(@PathVariable int employeId, @RequestBody int newSoldeConge);
}
