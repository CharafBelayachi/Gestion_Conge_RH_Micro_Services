package ma.iga.service_conge.services;

import ma.iga.service_conge.aspects.Log;
import ma.iga.service_conge.dto.DemandeCongeDTO;
import ma.iga.service_conge.dto.SoldeCongeDTO;
import ma.iga.service_conge.entities.DemandeConge;
import ma.iga.service_conge.entities.SoldeConge;
import ma.iga.service_conge.entities.StatutDemande;
import ma.iga.service_conge.mappers.DemandeCongeMapper;
import ma.iga.service_conge.modelClients.Employe;
import ma.iga.service_conge.repositories.DemandeCongeRepository;
import ma.iga.service_conge.restClient.EmployeRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
public class DemandeCongeServiceImp implements DemandeCongeService {
    @Autowired
    private DemandeCongeRepository repository;

    @Autowired
    private EmployeRestClient employeRestClient;

    @Autowired
    private DemandeCongeMapper demandeCongeMapper;

    @Autowired
    private SoldeCongeService soldeCongeService;

    @Autowired
    private ValidationPourcentage validationPourcentage;

    @Log
    @Override
    public List<DemandeCongeDTO> getAllDemandeConge() {
        List<DemandeConge> demandes = repository.findAll();
        List<DemandeCongeDTO> demandesDTOs = new ArrayList<>();
        demandes.forEach(d->{
            demandesDTOs.add(demandeCongeMapper.toDTO(d));
        });
        return demandesDTOs;
    }

    @Log
    @Override
    public DemandeCongeDTO getDemandeCongeById(int id) {

        DemandeConge demande = repository.findById(id).get();

        Employe employe = employeRestClient.getEmployeById(demande.getEmployeId());
        demande.setEmploye(employe);

        DemandeCongeDTO demandeCongeDTO = demandeCongeMapper.toDTO(demande);

        return demandeCongeDTO;
    }

    @Log
    @Override
    public List<DemandeCongeDTO> getDemandeCongeByEmployeId(int idEmployee) {
        List<DemandeConge> demandeConges=repository.findByEmployeId(idEmployee);
        List<DemandeCongeDTO> demandeCongesDTOs=new ArrayList<>();
        demandeConges.forEach(d->{
            demandeCongesDTOs.add(demandeCongeMapper.toDTO(d));
        });
        return demandeCongesDTOs;
    }

    @Log
    @Override
    public Optional<DemandeCongeDTO> CreateDemandeConge(DemandeCongeDTO demandeCongeDTO) {

        if(validationPourcentage.valider(demandeCongeDTO)) {
            Employe employe = employeRestClient.getEmployeById(demandeCongeDTO.getEmployeId());

            DemandeConge demandeConge = demandeCongeMapper.toEntity(demandeCongeDTO);
            demandeConge.setDateCreation(new Date());
            demandeConge.setEmploye(employe);

            DemandeConge NewDemande = repository.save(demandeConge);

            return Optional.of(demandeCongeMapper.toDTO(NewDemande));
        }
        return Optional.empty();

    }

    @Scheduled(cron = "0 0 0 * * ?") // Exécution quotidienne à minuit
    public void updateEmployesEnConge() {
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plusDays(1);

        List<DemandeConge> demandeCongesApproved = repository.findTopGroupByIdEmployeStatutDemandeOrderByDateCreationDesc(StatutDemande.APPROUVEE);

        demandeCongesApproved.forEach(
                d -> {
                    if (d.getDateDebut() == today){
                        employeRestClient.updateEmployesEnConge(d.getEmployeId(), true);
                    }
                    if (d.getDateFin() == tomorrow){
                        employeRestClient.updateEmployesEnConge(d.getEmployeId(), false);
                    }
                }
        );

    }


    @Log
    @Override
    public void deleteDemandeConge(int id){
        DemandeConge demande = repository.findById(id).get();
        if(estEnAttente(demande))
            repository.deleteById(id);
    }

    @Log
    @Override
    public DemandeCongeDTO validerDemandeConge(DemandeCongeDTO demandeCongeDTO) {

        DemandeConge demandeConge=demandeCongeMapper.toEntity(demandeCongeDTO);
        int joursRestant = calculeJoursRestant(demandeConge.getEmployeId());

        if(estEnAttente(demandeConge) && joursRestant >= demandeConge.calculerJoursDemande()){
            demandeConge.setStatutDemande(StatutDemande.APPROUVEE);
            repository.save(demandeConge);

            // mise a jour le solde au moment de validation de ddemande
            SoldeCongeDTO soldeCongeDTO = soldeCongeService.getSoldeCongeByEmployeeId(demandeConge.getEmployeId());
            soldeCongeService.updateSoldeConge(soldeCongeDTO, demandeConge.calculerJoursDemande());
            //
        }
        return demandeCongeMapper.toDTO(demandeConge);
    }

    @Log
    @Override
    public DemandeCongeDTO refuseDemandeConge(DemandeCongeDTO demandeCongeDTO, String motifRefuse) {
        DemandeConge demandeConge=demandeCongeMapper.toEntity(demandeCongeDTO);
        if(estEnAttente(demandeConge)){
            demandeConge.setStatutDemande(StatutDemande.REFUSEE);
            demandeConge.setMotifRefus(motifRefuse);
            repository.save(demandeConge);
        }
        return demandeCongeMapper.toDTO(demandeConge);
    }


    private Boolean estEnAttente(DemandeConge demandeConge) {
        return demandeConge.getStatutDemande()== StatutDemande.EN_ATTENTE;
    }

    private int calculeJoursRestant(int idEmploye){
        SoldeCongeDTO soldeCongeDTO = soldeCongeService.getSoldeCongeByEmployeeId(idEmploye);
        int joursRestant = soldeCongeDTO.getSoldeInitial() - soldeCongeDTO.getSoldeCongePris();
        return joursRestant;
    }






}
