package cz.vsb.vea.database.repositories.jpa;

import cz.vsb.vea.database.entities.InsuranceContract;
import cz.vsb.vea.database.repositories.IInsuranceContractRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class InsuranceContractRepository implements IInsuranceContractRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<InsuranceContract> getAll() {
        return em.createQuery("select i from InsuranceContract i", InsuranceContract.class).getResultList();
    }

    @Override
    public InsuranceContract find(long id) {
        return em.find(InsuranceContract.class, id);
    }

    @Transactional
    @Override
    public void update(long id, InsuranceContract entity) {
        entity.setId(id);
        em.merge(entity);
    }

    @Transactional
    @Override
    public void insert(InsuranceContract entity) {
        em.persist(entity);
    }

    @Transactional
    @Override
    public boolean delete(long id) {
        int deleted = em.createQuery("delete from InsuranceContract where id = :id").setParameter("id", id).executeUpdate();
        return deleted == 1;
    }

    @Transactional
    @Override
    public boolean exists(long id) {
        Long count = (Long) em.createQuery("select count(i) from InsuranceContract i where id = :id").setParameter("id", id).getSingleResult();
        return !count.equals(0L);
    }
}
