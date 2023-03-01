package cz.vsb.vea.database.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class InsuranceContract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    protected InsuranceCompany insuranceCompany;
    @ManyToOne(cascade = CascadeType.ALL)
    protected Car car;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    protected Date startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    protected Date endTime;
    protected Float pricePerYear;

    public InsuranceContract(){

    }

    public InsuranceContract(Date startTime, Date endTime, Float pricePerYear) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.pricePerYear = pricePerYear;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public InsuranceCompany getInsuranceCompany() {
        return insuranceCompany;
    }

    public void setInsuranceCompany(InsuranceCompany insuranceCompany) {
        this.insuranceCompany = insuranceCompany;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Date getStartTime() {
        return startTime;
    }

    @JsonIgnore
    public String getStartTimeStr() {
        return new SimpleDateFormat("dd. MM. yyyy").format(startTime);
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    @JsonIgnore
    public String getEndTimeStr() {
        return new SimpleDateFormat("dd. MM. yyyy").format(endTime);
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Float getPricePerYear() {
        return pricePerYear;
    }

    public void setPricePerYear(Float pricePerYear) {
        this.pricePerYear = pricePerYear;
    }

    @Override
    public String toString() {
        return "InsuranceContract{" +
                "id=" + id +
                ", insuranceCompany=" + insuranceCompany +
                ", car=" + car +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", pricePerYear=" + pricePerYear +
                '}';
    }
}
