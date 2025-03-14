package ma.iga.service_employes.mappers;

import ma.iga.service_employes.DTO.EmployeDTO;
import ma.iga.service_employes.entities.Employe;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmployeMapper {
    @Mapping(target = "roleEmploye", source = "roleEmploye")
    @Mapping(target = "soldeConge", source = "soldeConge")
    EmployeDTO toDto(Employe emp);
    @Mapping(target = "roleEmploye", source = "roleEmploye")
    @Mapping(target = "soldeConge", source = "soldeConge")
    Employe toEntity(EmployeDTO emp);
}
