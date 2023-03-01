package cz.vsb.vea.controllers.api;

import cz.vsb.vea.database.entities.InsuranceCompany;
import cz.vsb.vea.services.InsuranceCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class InsuranceCompanyApiController {

    @Autowired
    InsuranceCompanyService insuranceCompanyService;

    @PostMapping(path = "/insurance-companies")
    public ResponseEntity<?> createInsuranceCompany(@RequestBody InsuranceCompany insuranceCompany){
        if(!insuranceCompanyService.areAllPropertiesSet(insuranceCompany) || insuranceCompany.getId() != null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        insuranceCompanyService.insert(insuranceCompany);
        return new ResponseEntity<>(insuranceCompany, HttpStatus.CREATED);
    }

    @GetMapping(path = "/insurance-companies")
    public List<InsuranceCompany> getInsuranceCompanies(){
        return insuranceCompanyService.getAll();
    }

    @GetMapping(path = "/insurance-companies/{id}")
    public Object getInsuranceCompany(@PathVariable long id){
        InsuranceCompany insuranceCompany = insuranceCompanyService.find(id);
        if(insuranceCompany == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return insuranceCompany;
    }

    @PutMapping(path = "/insurance-companies/{id}")
    public ResponseEntity<InsuranceCompany> updateInsuranceCompany(@RequestBody InsuranceCompany insuranceCompany, @PathVariable long id){
        if(!insuranceCompanyService.areAllPropertiesSet(insuranceCompany)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if(insuranceCompanyService.exists(id)){
            insuranceCompanyService.update(id, insuranceCompany);
            return new ResponseEntity<>(insuranceCompany, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(path = "/insurance-companies/{id}")
    public ResponseEntity<?> deleteInsuranceCompany(@PathVariable long id){
        if(!insuranceCompanyService.exists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        boolean deleted = insuranceCompanyService.delete(id);
        return new ResponseEntity<>(deleted ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }
}
