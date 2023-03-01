package cz.vsb.vea.database.entities;

import cz.vsb.vea.database.dto.CarDto;

import javax.persistence.*;

@Entity
public class ElectricCar extends Car {

    private Float batteryCapacity;

    public ElectricCar() {
    }

    public ElectricCar(CarDto carDto) {
        this.name = carDto.getName();
        this.seats = carDto.getSeats();
        this.enginePower = carDto.getEnginePower();
        this.batteryCapacity = carDto.getBatteryCapacity();
    }

    public Float getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(Float batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    @Override
    public String toString() {
        return "ElectricCar{" +
                "batteryCapacity=" + batteryCapacity +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", seats=" + seats +
                ", enginePower=" + enginePower +
                '}';
    }
}
