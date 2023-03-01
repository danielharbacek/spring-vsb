package cz.vsb.vea.database.repositories.jpa;

import cz.vsb.vea.database.entities.InsuranceCompany;
import cz.vsb.vea.database.repositories.IInsuranceCompanyRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class InsuranceCompanyRepository implements IInsuranceCompanyRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<InsuranceCompany> getAll() {
        return em.createQuery("select i from InsuranceCompany i", InsuranceCompany.class).getResultList();
    }

    @Override
    public InsuranceCompany find(long id) {
        return em.find(InsuranceCompany.class, id);
    }

    @Transactional
    @Override
    public void update(long id, InsuranceCompany entity) {
        entity.setId(id);
        em.merge(entity);
    }

    @Transactional
    @Override
    public void insert(InsuranceCompany entity) {
        em.persist(entity);
    }

    @Transactional
    @Override
    public boolean delete(long id) {
        int deleted = em.createQuery("delete from InsuranceCompany where id = :id").setParameter("id", id).executeUpdate();
        return deleted == 1;
    }

    @Transactional
    @Override
    public boolean exists(long id) {
        Long count = (Long) em.createQuery("select count(i) from InsuranceCompany i where id = :id").setParameter("id", id).getSingleResult();
        return !count.equals(0L);
    }
}
