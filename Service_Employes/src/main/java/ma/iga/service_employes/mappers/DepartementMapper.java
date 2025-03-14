package ma.iga.service_employes.mappers;

import ma.iga.service_employes.DTO.DepartementDTO;
import ma.iga.service_employes.entities.Departement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",  uses = EmployeMapper.class)
public interface DepartementMapper {
    @Mapping(target = "manager", source = "manager")
    DepartementDTO toDto(Departement departement);
    @Mapping(target = "manager", source = "manager")
    Departement toEntity(DepartementDTO departementDTO);
}
