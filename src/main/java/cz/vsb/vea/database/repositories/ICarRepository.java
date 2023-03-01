package cz.vsb.vea.database.repositories;

import cz.vsb.vea.database.entities.Car;
import cz.vsb.vea.database.entities.ConventionalCar;
import cz.vsb.vea.database.entities.ElectricCar;

import java.util.List;

public interface ICarRepository extends CrudRepository<Car> {
    boolean exists(long id);
    List<ConventionalCar> getConventionalCars();
    List<ElectricCar> getElectricCars();
}
