package cz.vsb.vea.controllers;

import cz.vsb.vea.database.entities.InsuranceCompany;
import cz.vsb.vea.services.InsuranceCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class InsuranceCompanyController {

    @Autowired
    InsuranceCompanyService insuranceCompanyService;

    @GetMapping(path = "/insurance-companies")
    public String getInsuranceCompanies(){
        return "insurance-companies/index";
    }

    @GetMapping(path = "/insurance-companies/create")
    public String createInsuranceCompany(Model model){
        model.addAttribute("insuranceCompany", new InsuranceCompany());
        return "insurance-companies/create";
    }

    @PostMapping(path = "/insurance-companies/create")
    public String createInsuranceCompany(@ModelAttribute InsuranceCompany insuranceCompany){
        insuranceCompanyService.insert(insuranceCompany);
        return "redirect:/insurance-companies";
    }

    @GetMapping(path = "/insurance-companies/{id}/edit")
    public String editInsuranceCompany(@PathVariable long id, Model model){
        model.addAttribute("insuranceCompany", insuranceCompanyService.find(id));
        return "insurance-companies/edit";
    }

    @PostMapping(path = "/insurance-companies/{id}/edit")
    public String editInsuranceCompany(@PathVariable long id, @ModelAttribute InsuranceCompany insuranceCompany){
        insuranceCompanyService.update(id, insuranceCompany);
        return "redirect:/insurance-companies";
    }

    @PostMapping(path = "/insurance-companies/{id}/delete")
    public String deleteInsuranceCompany(@PathVariable long id){
        insuranceCompanyService.delete(id);
        return "redirect:/insurance-companies";
    }

    @ModelAttribute(name = "insuranceCompanies")
    public List<InsuranceCompany> getInsuranceCompaniesList(){
        return insuranceCompanyService.getAll();
    }
}
