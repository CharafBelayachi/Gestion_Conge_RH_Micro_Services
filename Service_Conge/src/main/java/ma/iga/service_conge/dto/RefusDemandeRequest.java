package ma.iga.service_conge.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RefusDemandeRequest {
    private DemandeCongeDTO demandeCongeDTO;
    private String motifRefus;

}