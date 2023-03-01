package cz.vsb.vea.database.repositories.jdbc.mapper;

import cz.vsb.vea.database.entities.ElectricCar;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ElectricCarMapper implements RowMapper<ElectricCar> {

    @Override
    public ElectricCar mapRow(ResultSet rs, int rowNum) throws SQLException {
        ElectricCar electricCar = new ElectricCar();
        electricCar.setId(rs.getLong("id"));
        electricCar.setName(rs.getString("name"));
        electricCar.setSeats(rs.getInt("seats"));
        electricCar.setEnginePower(rs.getFloat("engine_power"));
        electricCar.setBatteryCapacity(rs.getFloat("battery_capacity"));
        return electricCar;
    }
}
