package cz.vsb.vea.controllers.api;

import cz.vsb.vea.database.dto.InsuranceContractDto;
import cz.vsb.vea.database.entities.InsuranceContract;
import cz.vsb.vea.services.InsuranceContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class InsuranceContractApiController {

    @Autowired
    InsuranceContractService insuranceContractService;

    @PostMapping(path = "/insurance-contracts")
    public ResponseEntity<?> createInsuranceContract(@RequestBody InsuranceContractDto insuranceContractDto){
        if(!insuranceContractService.areAllPropertiesSet(insuranceContractDto) || insuranceContractDto.getId() != null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        InsuranceContract insuranceContract = insuranceContractService.insertWithRelationship(insuranceContractDto);
        return new ResponseEntity<>(insuranceContract, HttpStatus.CREATED);
    }

    @GetMapping(path = "/insurance-contracts")
    public List<InsuranceContract> getInsuranceContracts(){
        return insuranceContractService.getAll();
    }

    @GetMapping(path = "/insurance-contracts/{id}")
    public Object getInsuranceContract(@PathVariable long id){
        InsuranceContract insuranceContract = insuranceContractService.find(id);
        if(insuranceContract == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return insuranceContract;
    }

    @PutMapping(path = "/insurance-contracts/{id}")
    public ResponseEntity<InsuranceContract> updateInsuranceContract(@RequestBody InsuranceContract insuranceContract, @PathVariable long id){
        if(!insuranceContractService.areAllPropertiesSet(insuranceContract)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if(insuranceContractService.exists(id)){
            insuranceContractService.update(id, insuranceContract);
            return new ResponseEntity<>(insuranceContract, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(path = "/insurance-contracts/{id}")
    public ResponseEntity<?> deleteInsuranceContract(@PathVariable long id){
        if(!insuranceContractService.exists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        boolean deleted = insuranceContractService.delete(id);
        return new ResponseEntity<>(deleted ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }
}
