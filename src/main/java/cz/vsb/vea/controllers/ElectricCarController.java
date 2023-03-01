package cz.vsb.vea.controllers;

import cz.vsb.vea.database.entities.ElectricCar;
import cz.vsb.vea.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ElectricCarController {

    @Autowired
    CarService carService;

    @GetMapping(path = "/electric-cars")
    public String getElectricCars(){
        return "electric-cars/index";
    }

    @GetMapping(path = "/electric-cars/create")
    public String createElectricCar(Model model){
        model.addAttribute("electricCar", new ElectricCar());
        return "electric-cars/create";
    }

    @PostMapping(path = "/electric-cars/create")
    public String createElectricCar(@ModelAttribute ElectricCar electricCar){
        carService.insert(electricCar);
        return "redirect:/electric-cars";
    }

    @GetMapping(path = "/electric-cars/{id}/edit")
    public String editElectricCar(@PathVariable long id, Model model){
        model.addAttribute("electricCar", carService.find(id));
        return "electric-cars/edit";
    }

    @PostMapping(path = "/electric-cars/{id}/edit")
    public String editElectricCar(@PathVariable long id, @ModelAttribute ElectricCar electricCar){
        carService.update(id, electricCar);
        return "redirect:/electric-cars";
    }

    @PostMapping(path = "/electric-cars/{id}/delete")
    public String deleteElectricCar(@PathVariable long id){
        carService.delete(id);
        return "redirect:/electric-cars";
    }

    @ModelAttribute(name = "electricCars")
    public List<ElectricCar> getElectricCarsList(){
        return carService.getElectricCars();
    }
}
