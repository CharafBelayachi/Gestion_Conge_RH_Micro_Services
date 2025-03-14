package ma.iga.service_employes.services;

import jakarta.transaction.Transactional;
import ma.iga.service_employes.DTO.DepartementDTO;
import ma.iga.service_employes.DTO.EmployeDTO;
import ma.iga.service_employes.aspects.Log;
import ma.iga.service_employes.entities.Departement;
import ma.iga.service_employes.entities.Employe;
import ma.iga.service_employes.entities.RoleEmploye;
import ma.iga.service_employes.mappers.DepartementMapper;
import ma.iga.service_employes.mappers.EmployeMapper;
import ma.iga.service_employes.repositories.DepartementRepository;
import ma.iga.service_employes.repositories.EmployeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
public class EmployeServiceImpl implements EmployeService {
    @Autowired
    private EmployeRepository employeRepository;

    @Autowired
    private DepartementRepository departementRepository;

    @Autowired
    private EmployeMapper employeMapper;

    @Autowired
    private DepartementMapper departementMapper;

    /*public EmployeServiceImpl(EmployeRepository employeRepository){
        this.employeRepository = employeRepository;
    }*/

    @Log
    @Override
    public Optional<EmployeDTO> ajouterEmploye(EmployeDTO employeDTO, int departementId) {
        Employe employe = employeMapper.toEntity(employeDTO);
//        employe.setRole(employeDTO.getRole());

        // Vérifier si l'email existe déjà
        if (!employeRepository.existsByEmail(employe.getEmail())) {
            Departement departement = departementRepository.findById(departementId)
                    .orElseThrow(() -> new RuntimeException("Département non trouvé"));

            employe.setDepartement(departement);
            employe.setSoldeConge(18); // initialiser solde conger par 18
            employe.setActif(true);
            employe.setEnConge(false);
            Employe savedEmploye = employeRepository.save(employe);

            if(employe.getRoleEmploye() == RoleEmploye.MANAGER){
                departement.setManager(savedEmploye);
                departementRepository.save(departement);
            }

            return Optional.of(employeMapper.toDto(savedEmploye));
        }
        return Optional.empty(); // Retourne un Optional vide si l'email existe déjà
    }


    @Log
    @Override
    public EmployeDTO modifierEmploye(EmployeDTO employeDTO,int departementId) {

        Employe employe=employeMapper.toEntity(employeDTO);
        Departement departement = departementRepository.findById(departementId).orElseThrow(() -> new RuntimeException("Département non trouvé"));
        employe.setDepartement(departement);
        Employe employe1=employeRepository.save(employe);

        if(employe.getRoleEmploye() == RoleEmploye.MANAGER){
            departement.setManager(employe1);
            departementRepository.save(departement);
        }

        return employeMapper.toDto(employe1);
    }

    @Log
    @Override
    public List<EmployeDTO> ListerEmployer() {
        List<Employe> employes = employeRepository.findAll();
        List<EmployeDTO> employeDTOs = new ArrayList<>();
        employes.forEach(e->{
            employeDTOs.add(employeMapper.toDto(e));
        });
        return employeDTOs;
    }

    @Log
    @Override
    public void supprimerEmploye(int id) {
        employeRepository.deleteById(id);
    }

    @Log
    @Override
    public EmployeDTO getEmployeById(int id) {
        return employeMapper.toDto(employeRepository.findById(id).get());
    }

    @Log
    @Override
    public List<EmployeDTO> getManagers() {
        List<EmployeDTO> employeDTOS=new ArrayList<>();
        List<Employe> employes = employeRepository.findAllByRoleEmploye(RoleEmploye.MANAGER);

        employes.forEach(e->{
            employeDTOS.add(employeMapper.toDto(e));
        });

        return employeDTOS;
    }

    @Log
    @Override
    public List<EmployeDTO> getAllEmployeesByDepartement(int departementId) {
        List<EmployeDTO> employeDTOS=new ArrayList<>();
//        Departement departement=departementMapper.toEntity(departementDTO);
        List<Employe> employes=employeRepository.findAllByDepartementId(departementId);
        employes.forEach(e->{
            employeDTOS.add(employeMapper.toDto(e));
        });
        return employeDTOS;
    }

    @Override
    public List<Integer> getEmployesIdActifs(int idEmploye) {
        Employe employe=employeRepository.findById(idEmploye).get();
        List<Integer> employesIdActifs  = employeRepository.findByDepartementIdAndActifTrue(employe.getDepartement().getId());
        return employesIdActifs;
    }

    @Override
    public EmployeDTO updateEmployesEnConge(int employeId, boolean enConge) {
        Employe employe = employeRepository.findById(employeId).get();
        employe.setEnConge(enConge);
        return employeMapper.toDto(employeRepository.save(employe));
    }

    @Override
    public EmployeDTO updateEmployeSoldeConge(int employeId, int newSoldeConge) {
        Employe employe = employeRepository.findById(employeId).get();
        employe.setSoldeConge(newSoldeConge);
        return employeMapper.toDto(employeRepository.save(employe));
    }


    @Scheduled(cron = "0 0 0 1 1 ?") // Exécution chaque année à minuit
    public void initializeEmployesSoldes() {
        employeRepository.initializeSoldeConge();
    }

}



