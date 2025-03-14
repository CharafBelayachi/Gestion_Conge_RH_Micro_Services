package ma.iga.service_employes.mappers;

import ma.iga.service_employes.DTO.EmployeDTO;
import ma.iga.service_employes.entities.Employe;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmployeMapper {
    @Mapping(target = "roleEmploye", source = "roleEmploye")
    EmployeDTO toDto(Employe emp);
    @Mapping(target = "roleEmploye", source = "roleEmploye")
    Employe toEntity(EmployeDTO emp);
}
