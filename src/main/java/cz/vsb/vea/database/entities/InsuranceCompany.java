package cz.vsb.vea.database.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import cz.vsb.vea.database.dto.InsuranceCompanyDto;

import javax.persistence.*;
import java.util.List;

@Entity
public class InsuranceCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @OneToMany(mappedBy = "insuranceCompany")
    protected List<InsuranceContract> insuranceContracts;
    protected String name;

    public InsuranceCompany() {
    }

    public InsuranceCompany(InsuranceCompanyDto insuranceCompanyDto) {
        this.name = insuranceCompanyDto.getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonIgnore
    public List<InsuranceContract> getInsuranceContracts() {
        return insuranceContracts;
    }

    public void setInsuranceContracts(List<InsuranceContract> insuranceContracts) {
        this.insuranceContracts = insuranceContracts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "InsuranceCompany{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
