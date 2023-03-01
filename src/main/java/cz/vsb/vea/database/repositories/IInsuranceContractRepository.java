package cz.vsb.vea.database.repositories;

import cz.vsb.vea.database.entities.InsuranceContract;

public interface IInsuranceContractRepository extends CrudRepository<InsuranceContract> {
    boolean exists(long id);
}
