package ma.iga.service_employes;

import ma.iga.service_employes.entities.Departement;
import ma.iga.service_employes.entities.Employe;
import ma.iga.service_employes.repositories.DepartementRepository;
import ma.iga.service_employes.repositories.EmployeRepository;
import ma.iga.service_employes.services.DepartementService;
import ma.iga.service_employes.services.EmployeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class ServiceEmployesApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceEmployesApplication.class, args);
    }

//    @Bean
    CommandLineRunner start(DepartementRepository departementRepository,
                            EmployeRepository employeRepository) {
        return args -> {
            List<Employe> employes = employeRepository.findAll();
            Departement departement1 = departementRepository.findById(9).get();

            employes.forEach(e->{
                e.setDepartement(departement1);
                employeRepository.save(e);
            });

            departement1.setEmployes(employes);

            departementRepository.save(departement1);
        };
    }

}
