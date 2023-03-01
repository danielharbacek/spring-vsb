package cz.vsb.vea.database.repositories;

import cz.vsb.vea.database.entities.InsuranceCompany;

public interface IInsuranceCompanyRepository extends CrudRepository<InsuranceCompany> {
    boolean exists(long id);
}
