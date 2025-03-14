package ma.iga.service_conge.services;

import ma.iga.service_conge.aspects.Log;
import ma.iga.service_conge.dto.SoldeCongeDTO;
import ma.iga.service_conge.entities.SoldeConge;
import ma.iga.service_conge.mappers.SoldeCongeMapper;
import ma.iga.service_conge.repositories.SoldeCongeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Transactional
@Service
public class SoldeCongeServiceImp implements SoldeCongeService {

    @Autowired
    private SoldeCongeRepository soldeCongeRepository;

    @Autowired
    private SoldeCongeMapper soldeCongeMapper;

    @Log
    @Override
    public List<SoldeCongeDTO> getAllSoldeConge() {
        List<SoldeConge> soldeConges=soldeCongeRepository.findAll();
        List<SoldeCongeDTO> soldeCongeDTOs=new ArrayList<>();
        soldeConges.forEach(solde ->{
            soldeCongeDTOs.add(soldeCongeMapper.toDTO(solde));

        });
        return soldeCongeDTOs;
    }

    @Log
    @Override
    public SoldeCongeDTO getSoldeCongeById(int id) {

        SoldeConge solde=soldeCongeRepository.findById(id).get();
        return soldeCongeMapper.toDTO(solde);
    }

    @Log
    @Override
    public SoldeCongeDTO addSoldeConge(SoldeCongeDTO soldeCongeDTO) {
        SoldeConge solde=soldeCongeMapper.toEntity(soldeCongeDTO);
        SoldeConge NewSolde =soldeCongeRepository.save(solde);
        return soldeCongeMapper.toDTO(NewSolde);
    }

    @Log
    @Override
    public void deleteSoldeConge(int id) {
        soldeCongeRepository.deleteById(id);

    }

    @Log
    @Override
    public SoldeCongeDTO updateSoldeConge(SoldeCongeDTO soldeCongeDTO, int calculeJoursARetirer) {
        SoldeConge soldeConge=soldeCongeMapper.toEntity(soldeCongeDTO);
        soldeConge.setSoldeCongePris(soldeConge.getSoldeCongePris() + calculeJoursARetirer);
        soldeConge.setDernierMiseAjours(new Date());
        SoldeConge soldeUpdate=soldeCongeRepository.save(soldeConge);

        return soldeCongeMapper.toDTO(soldeUpdate);
    }

    @Log
    @Override
    public SoldeCongeDTO getSoldeCongeByEmployeeId(int employeeId) {
        SoldeConge soldeConge = soldeCongeRepository.findByEmployeId(employeeId);
        SoldeCongeDTO soldeCongeDTO = soldeCongeMapper.toDTO(soldeConge);
        return soldeCongeDTO;
    }
}

