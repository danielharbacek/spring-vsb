package cz.vsb.vea.database.entities;

import cz.vsb.vea.database.dto.CarDto;

import javax.persistence.*;

@Entity
public class ConventionalCar extends Car {

    private Float emissions;

    public ConventionalCar(){
    }

    public ConventionalCar(CarDto carDto) {
        this.name = carDto.getName();
        this.seats = carDto.getSeats();
        this.enginePower = carDto.getEnginePower();
        this.emissions = carDto.getEmissions();
    }

    public Float getEmissions() {
        return emissions;
    }

    public void setEmissions(Float emissions) {
        this.emissions = emissions;
    }

    @Override
    public String toString() {
        return "ConventionalCar{" +
                "emissions=" + emissions +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", seats=" + seats +
                ", enginePower=" + enginePower +
                '}';
    }
}
