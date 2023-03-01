package cz.vsb.vea.database.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class InsuranceContractDto {

    protected Long id;
    protected InsuranceCompanyDto insuranceCompany;
    protected CarDto car;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    protected Date startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    protected Date endTime;
    protected Float pricePerYear;

    public InsuranceContractDto(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public InsuranceCompanyDto getInsuranceCompany() {
        return insuranceCompany;
    }

    public void setInsuranceCompany(InsuranceCompanyDto insuranceCompany) {
        this.insuranceCompany = insuranceCompany;
    }

    public CarDto getCar() {
        return car;
    }

    public void setCar(CarDto car) {
        this.car = car;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
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
        return "InsuranceContractDto{" +
                "id=" + id +
                ", insuranceCompany=" + insuranceCompany +
                ", car=" + car +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", pricePerYear=" + pricePerYear +
                '}';
    }
}
