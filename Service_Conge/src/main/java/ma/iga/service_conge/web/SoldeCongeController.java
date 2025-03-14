//package ma.iga.service_conge.web;
//
//import ma.iga.service_conge.dto.SoldeCongeDTO;
//import ma.iga.service_conge.entities.SoldeConge;
//import ma.iga.service_conge.services.SoldeCongeService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/soldes")
//public class SoldeCongeController {
//
//    @Autowired
//    private SoldeCongeService soldeCongeService;
//
//    @GetMapping
//    public List<SoldeCongeDTO> getSoldes() {
//        return soldeCongeService.getAllSoldeConge();
//    }
//
//    @GetMapping("/{id}")
//    public SoldeCongeDTO getSoldeCongeById(@PathVariable int id){
//        return soldeCongeService.getSoldeCongeById(id);
//    }
//
//    @PostMapping
//    public SoldeCongeDTO addSoldeConge(SoldeCongeDTO soldeCongeDTO) {
//        return soldeCongeService.addSoldeConge(soldeCongeDTO);
//    }
//
////    @PutMapping
////    public SoldeCongeDTO updateSoldeConge(SoldeCongeDTO soldeCongeDTO) {
////        return soldeCongeService.updateSoldeConge(soldeCongeDTO);
////    }
//
//    @DeleteMapping
//    public void deleteSoldeConge(@RequestParam int id) {
//        soldeCongeService.deleteSoldeConge(id);
//    }
//
//
//}
