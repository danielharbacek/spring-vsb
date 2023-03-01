package cz.vsb.vea.database.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @OneToMany(mappedBy = "car")
    protected List<InsuranceContract> insuranceContracts;
    protected String name;
    protected Integer seats;
    protected Float enginePower;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonIgnore
    public List<InsuranceContract> getInsuranceContracts() {
        return insuranceContracts;
    }

    public void setInsuranceContracts(List<InsuranceContract> insuranceContracts) {
        this.insuranceContracts = insuranceContracts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    public Float getEnginePower() {
        return enginePower;
    }

    public void setEnginePower(Float enginePower) {
        this.enginePower = enginePower;
    }

    @JsonIgnore
    public InsuranceContract getCurrentInsuranceContract(){
        if(insuranceContracts == null){
            return null;
        }

        Date now = new Date();
        for(InsuranceContract insuranceContract : insuranceContracts){
            if(insuranceContract.getStartTime().before(now) && insuranceContract.getEndTime().after(now)){
                return insuranceContract;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", seats=" + seats +
                ", enginePower=" + enginePower +
                '}';
    }
}
