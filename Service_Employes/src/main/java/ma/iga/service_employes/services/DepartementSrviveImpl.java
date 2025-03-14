package ma.iga.service_employes.services;

import jakarta.transaction.Transactional;
import ma.iga.service_employes.DTO.DepartementDTO;
import ma.iga.service_employes.aspects.Log;
import ma.iga.service_employes.entities.Departement;
import ma.iga.service_employes.entities.Employe;
import ma.iga.service_employes.mappers.DepartementMapper;
import ma.iga.service_employes.repositories.DepartementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class DepartementSrviveImpl implements DepartementService{
    @Autowired
    private DepartementRepository departementRepository;

    @Autowired
    private DepartementMapper departementMapper;

    @Override
    public DepartementDTO getDepartement(int id) {
        return departementMapper.toDto(departementRepository.findById(id).get());
    }

    @Log
    @Override
    public DepartementDTO AjouterDepartement(DepartementDTO departementDTO) {
        Departement departement = departementMapper.toEntity(departementDTO);
        Departement departement1 = departementRepository.save(departement);
        return departementMapper.toDto(departement1);
    }

    @Log
    @Override
    public List<DepartementDTO> getAllDepartements() {
        List<DepartementDTO> departementDTOS=new ArrayList<>();
        List<Departement> departements=departementRepository.findAll();
        departements.forEach(departement->{
            departementDTOS.add(departementMapper.toDto(departement));
        });
        return departementDTOS;
    }

    @Log
    @Override
    public DepartementDTO updateDepartement(DepartementDTO departementDTO) {
        Departement departement=departementMapper.toEntity(departementDTO);
        Departement departement1=departementRepository.save(departement);
        return departementMapper.toDto(departement1);
    }

    @Log
    @Override
    public void deleteDepartement(int id) {
        departementRepository.deleteById(id);
    }

}
