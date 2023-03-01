package cz.vsb.vea.controllers.api;

import cz.vsb.vea.database.entities.Car;
import cz.vsb.vea.database.entities.ElectricCar;
import cz.vsb.vea.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ElectricCarApiController {

    @Autowired
    CarService carService;

    @PostMapping(path = "/electric-cars")
    public ResponseEntity<?> createElectricCar(@RequestBody ElectricCar electricCar){
        if(electricCar.getId() != null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        carService.insert(electricCar);
        return new ResponseEntity<>(electricCar, HttpStatus.CREATED);
    }

    @GetMapping(path = "/electric-cars")
    public List<ElectricCar> getElectricCars(){
        return carService.getElectricCars();
    }

    @GetMapping(path = "/electric-cars/{id}")
    public Object getElectricCar(@PathVariable long id){
        Car car = carService.find(id);
        if(!(car instanceof ElectricCar)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return car;
    }

    @PutMapping(path = "/electric-cars/{id}")
    public ResponseEntity<ElectricCar> updateElectricCar(@RequestBody ElectricCar electricCar, @PathVariable long id){
        if(electricCar.getId() == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if(carService.exists(id)){
            carService.update(id, electricCar);
            return new ResponseEntity<>(electricCar, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(path = "/electric-cars/{id}")
    public ResponseEntity<?> deleteElectricCar(@PathVariable long id){
        boolean deleted = carService.delete(id);
        return new ResponseEntity<>(deleted ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }


}
