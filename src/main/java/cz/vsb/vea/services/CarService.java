package cz.vsb.vea.services;

import cz.vsb.vea.database.entities.Car;
import cz.vsb.vea.database.entities.ConventionalCar;
import cz.vsb.vea.database.entities.ElectricCar;
import cz.vsb.vea.database.repositories.ICarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    @Autowired
    private ICarRepository carRepository;

    public List<Car> getAll(){
        return carRepository.getAll();
    }

    public List<ConventionalCar> getConventionalCars(){
        return carRepository.getConventionalCars();
    }

    public List<ElectricCar> getElectricCars(){
        return carRepository.getElectricCars();
    }

    public Car find(long id){
        return carRepository.find(id);
    }

    public void update(long id, Car car){
        carRepository.update(id, car);
    }

    public void insert(Car car){
        carRepository.insert(car);
    }

    public boolean delete(long id){
        return carRepository.delete(id);
    }

    public boolean exists(long id){
        return carRepository.exists(id);
    }
}
