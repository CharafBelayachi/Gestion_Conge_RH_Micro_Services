package ma.iga.service_employes.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.iga.service_employes.entities.Employe;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class DepartementDTO {
    private int id;
    private String nomDepartement;
    private String description;
    private String adresse;
    private Date dateCreation;
    private EmployeDTO manager;
}
