package cz.vsb.vea.controllers;

import cz.vsb.vea.database.entities.Car;
import cz.vsb.vea.database.entities.InsuranceCompany;
import cz.vsb.vea.database.entities.InsuranceContract;
import cz.vsb.vea.services.CarService;
import cz.vsb.vea.services.InsuranceCompanyService;
import cz.vsb.vea.services.InsuranceContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@Secured("ROLE_ADMIN")
public class InsuranceContractController {

    @Autowired
    InsuranceContractService insuranceContractService;

    @Autowired
    CarService carService;

    @Autowired
    InsuranceCompanyService insuranceCompanyService;

    @GetMapping(path = "/insurance-contracts")
    public String getInsuranceContracts(){
        return "insurance-contracts/index";
    }

    @GetMapping(path = "/insurance-contracts/create")
    public String createInsuranceContract(Model model){
        model.addAttribute("insuranceContract", new InsuranceContract());
        return "insurance-contracts/create";
    }

    @PostMapping(path = "/insurance-contracts/create")
    public String createInsuranceContract(@ModelAttribute InsuranceContract insuranceContract){
        System.out.println(insuranceContract);
        insuranceContractService.insert(insuranceContract);
        return "redirect:/insurance-contracts";
    }

    @GetMapping(path = "/insurance-contracts/{id}/edit")
    public String editInsuranceContract(@PathVariable long id, Model model){
        model.addAttribute("insuranceContract", insuranceContractService.find(id));
        return "insurance-contracts/edit";
    }

    @PostMapping(path = "/insurance-contracts/{id}/edit")
    public String editInsuranceContract(@PathVariable long id, @ModelAttribute InsuranceContract insuranceContract){
        insuranceContractService.update(id, insuranceContract);
        return "redirect:/insurance-contracts";
    }

    @PostMapping(path = "/insurance-contracts/{id}/delete")
    public String deleteInsuranceContract(@PathVariable long id){
        insuranceContractService.delete(id);
        return "redirect:/insurance-contracts";
    }

    @ModelAttribute(name = "insuranceContracts")
    public List<InsuranceContract> getInsuranceContractsList(){
        return insuranceContractService.getAll();
    }

    @ModelAttribute(name = "cars")
    public List<Car> getCars(){
        return carService.getAll();
    }

    @ModelAttribute(name = "insuranceCompanies")
    public List<InsuranceCompany> getInsuranceCompaniesList(){
        return insuranceCompanyService.getAll();
    }
}
