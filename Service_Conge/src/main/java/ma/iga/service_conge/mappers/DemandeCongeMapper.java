package ma.iga.service_conge.mappers;

import ma.iga.service_conge.dto.DemandeCongeDTO;
import ma.iga.service_conge.entities.DemandeConge;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DemandeCongeMapper {
//    DemandeCongeMapper INSTANCE = Mappers.getMapper(DemandeCongeMapper.class);

    //@Mapping(source = "statutDemande", target = "statutDemande", expression = "java(demande.getStatutDemande().name())")
    DemandeCongeDTO toDTO(DemandeConge demande);

    //@Mapping(target = "statutDemande", expression = "java(StatutDemande.valueOf(dto.getStatutDemande()))")
    DemandeConge toEntity(DemandeCongeDTO dto);
}
