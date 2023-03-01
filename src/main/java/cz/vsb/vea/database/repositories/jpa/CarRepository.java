package cz.vsb.vea.database.repositories.jpa;

import cz.vsb.vea.database.entities.Car;
import cz.vsb.vea.database.entities.ConventionalCar;
import cz.vsb.vea.database.entities.ElectricCar;
import cz.vsb.vea.database.repositories.ICarRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class CarRepository implements ICarRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Car> getAll() {
        return em.createQuery("select c from Car c", Car.class).getResultList();
    }

    @Override
    public Car find(long id) {
        return em.find(Car.class, id);
    }

    @Transactional
    @Override
    public void update(long id, Car entity) {
        entity.setId(id);
        em.merge(entity);
    }

    @Transactional
    @Override
    public void insert(Car entity) {
        em.persist(entity);
    }

    @Transactional
    @Override
    public boolean delete(long id) {
        int deleted = em.createQuery("delete from Car where id = :id").setParameter("id", id).executeUpdate();
        return deleted == 1;
    }

    @Transactional
    @Override
    public boolean exists(long id) {
        Long count = (Long) em.createQuery("select count(c) from Car c where id = :id").setParameter("id", id).getSingleResult();
        return !count.equals(0L);
    }

    @Override
    public List<ConventionalCar> getConventionalCars() {
        return em.createQuery("select c from ConventionalCar c", ConventionalCar.class).getResultList();
    }

    @Override
    public List<ElectricCar> getElectricCars() {
        return em.createQuery("select e from ElectricCar e", ElectricCar.class).getResultList();
    }
}
