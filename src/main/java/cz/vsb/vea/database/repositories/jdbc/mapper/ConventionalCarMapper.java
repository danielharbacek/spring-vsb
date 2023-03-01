package cz.vsb.vea.database.repositories.jdbc.mapper;

import cz.vsb.vea.database.entities.ConventionalCar;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ConventionalCarMapper implements RowMapper<ConventionalCar> {

    @Override
    public ConventionalCar mapRow(ResultSet rs, int rowNum) throws SQLException {
        ConventionalCar conventionalCar = new ConventionalCar();
        conventionalCar.setId(rs.getLong("id"));
        conventionalCar.setName(rs.getString("name"));
        conventionalCar.setSeats(rs.getInt("seats"));
        conventionalCar.setEnginePower(rs.getFloat("engine_power"));
        conventionalCar.setEmissions(rs.getFloat("emissions"));
        return conventionalCar;
    }
}
