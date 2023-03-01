package cz.vsb.vea.database.dto;

public class CarDto {

    private Long id;
    private String name;
    private Integer seats;
    private Float enginePower;
    private Float batteryCapacity;
    private Float emissions;

    public CarDto(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Float getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(Float batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public Float getEmissions() {
        return emissions;
    }

    public void setEmissions(Float emissions) {
        this.emissions = emissions;
    }

    @Override
    public String toString() {
        return "CarDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", seats=" + seats +
                ", enginePower=" + enginePower +
                ", batteryCapacity=" + batteryCapacity +
                ", emissions=" + emissions +
                '}';
    }
}
