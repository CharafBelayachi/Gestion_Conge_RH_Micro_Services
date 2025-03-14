package ma.iga.service_conge.restClient;

import jakarta.persistence.ForeignKey;
import ma.iga.service_conge.modelClients.Employe;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(url = "http://localhost:8081",name = "service-employes")
public interface EmployeRestClient {

    @GetMapping("/employes")
    List<Employe> getAllEmployes();

    @GetMapping("/employes/{id}")
    Employe getEmployeById(@PathVariable int id);

//    @GetMapping("/employes/pourcentage/{id}")
//    Double getPourcentageEmployesEnConge(@PathVariable int idDepartement);

    @GetMapping("/employes/actifs/{idEmploye}")
    List<Integer> getEmployesIdActifsByDepartement(@PathVariable int idEmploye);//changer idDepartement par idEmploye

    @PostMapping("/employes/update-en-conge/{employeId}")
    void updateEmployesEnConge(@PathVariable int employeId,@RequestBody boolean enConge);
}
