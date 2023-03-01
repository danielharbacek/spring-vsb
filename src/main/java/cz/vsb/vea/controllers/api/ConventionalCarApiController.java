package cz.vsb.vea.controllers.api;

import cz.vsb.vea.database.entities.ConventionalCar;
import cz.vsb.vea.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ConventionalCarApiController {

    @Autowired
    CarService carService;

    @PostMapping(path = "/conventional-cars")
    public ResponseEntity<?> createConventionalCar(@RequestBody ConventionalCar conventionalCar){
        if(conventionalCar.getId() != null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        carService.insert(conventionalCar);
        return new ResponseEntity<>(conventionalCar, HttpStatus.CREATED);
    }

    @GetMapping(path = "/conventional-cars")
    public List<ConventionalCar> getConventionalCars(){
        return carService.getConventionalCars();
    }

    @GetMapping(path = "/conventional-cars/{id}")
    public Object getConventionalCar(@PathVariable long id){
        ConventionalCar conventionalCar = (ConventionalCar) carService.find(id);
        if(conventionalCar == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return conventionalCar;
    }

    @PutMapping(path = "/conventional-cars/{id}")
    public ResponseEntity<ConventionalCar> updateConventionalCar(@RequestBody ConventionalCar conventionalCar, @PathVariable long id){
        if(conventionalCar.getId() == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if(carService.exists(id)){
            carService.update(id, conventionalCar);
            return new ResponseEntity<>(conventionalCar, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(path = "/conventional-cars/{id}")
    public ResponseEntity<?> deleteConventionalCar(@PathVariable long id){
        boolean deleted = carService.delete(id);
        return new ResponseEntity<>(deleted ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }
}
