package cz.vsb.vea.services;

import cz.vsb.vea.database.entities.InsuranceCompany;
import cz.vsb.vea.database.repositories.IInsuranceCompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InsuranceCompanyService {

    @Autowired
    private IInsuranceCompanyRepository insuranceCompanyRepository;

    public List<InsuranceCompany> getAll(){
        return insuranceCompanyRepository.getAll();
    }

    public InsuranceCompany find(long id){
        return insuranceCompanyRepository.find(id);
    }

    public void update(long id, InsuranceCompany insuranceCompany){
        insuranceCompanyRepository.update(id, insuranceCompany);
    }

    public void insert(InsuranceCompany insuranceCompany){
        insuranceCompanyRepository.insert(insuranceCompany);
    }

    public boolean delete(long id){
        return insuranceCompanyRepository.delete(id);
    }

    public boolean areAllPropertiesSet(InsuranceCompany insuranceCompany){
        return insuranceCompany.getName() != null;
    }

    public boolean exists(long id){
        return insuranceCompanyRepository.exists(id);
    }
}
