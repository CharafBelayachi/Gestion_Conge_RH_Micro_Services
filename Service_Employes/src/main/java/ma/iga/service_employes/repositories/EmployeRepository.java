package ma.iga.service_employes.repositories;

import ma.iga.service_employes.entities.Departement;
import ma.iga.service_employes.entities.Employe;
import ma.iga.service_employes.entities.RoleEmploye;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeRepository extends JpaRepository<Employe, Integer> {
    List<Employe> findAllByDepartementId(int id);
    Boolean existsByEmail(String email);
//    int countEmployeByEnCongeByDepartement(Departement departement);
    List<Employe> findAllByRoleEmploye(RoleEmploye roleEmploye);

    @Query("SELECT e.id FROM Employe e WHERE e.departement.id = :departementId AND e.actif = true")
    List<Integer> findByDepartementIdAndActifTrue(int departementId);

    @Query("UPDATE Employe e SET e.soldeConge = 18 ")
    void initializeSoldeConge();

}
