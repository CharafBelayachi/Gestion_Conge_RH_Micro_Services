package ma.iga.service_conge.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SoldeCongeDTO {
    private int id;
    private int annee;
    private int soldeInitial;
    private int soldeCongePris;
    private Date dernierMiseAjours;
    private int idEmploye;
}
