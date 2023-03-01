package cz.vsb.vea.controllers;

import cz.vsb.vea.database.entities.ConventionalCar;
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
public class ConventionalCarController {

    @Autowired
    CarService carService;

    @GetMapping(path = "/conventional-cars")
    public String getConventionalCars(){
        return "conventional-cars/index";
    }

    @GetMapping(path = "/conventional-cars/create")
    public String createConventionalCar(Model model){
        model.addAttribute("conventionalCar", new ConventionalCar());
        return "conventional-cars/create";
    }

    @PostMapping(path = "/conventional-cars/create")
    public String createConventionalCar(@ModelAttribute ConventionalCar conventionalCar){
        carService.insert(conventionalCar);
        return "redirect:/conventional-cars";
    }

    @GetMapping(path = "/conventional-cars/{id}/edit")
    public String editConventionalCar(@PathVariable long id, Model model){
        model.addAttribute("conventionalCar", carService.find(id));
        return "conventional-cars/edit";
    }

    @PostMapping(path = "/conventional-cars/{id}/edit")
    public String editConventionalCar(@PathVariable long id, @ModelAttribute ConventionalCar conventionalCar){
        carService.update(id, conventionalCar);
        return "redirect:/conventional-cars";
    }

    @PostMapping(path = "/conventional-cars/{id}/delete")
    public String deleteConventionalCar(@PathVariable long id){
        carService.delete(id);
        return "redirect:/conventional-cars";
    }

    @ModelAttribute(name = "conventionalCars")
    public List<ConventionalCar> getConventionalCarsList(){
        return carService.getConventionalCars();
    }
}
