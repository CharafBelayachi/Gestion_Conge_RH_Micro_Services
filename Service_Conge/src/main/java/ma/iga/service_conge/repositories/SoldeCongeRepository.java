package ma.iga.service_conge.repositories;

import ma.iga.service_conge.entities.SoldeConge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoldeCongeRepository extends JpaRepository<SoldeConge, Integer> {
    SoldeConge findByEmployeId(int employeId);
}
