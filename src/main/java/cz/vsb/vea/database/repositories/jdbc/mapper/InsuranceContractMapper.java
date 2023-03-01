package cz.vsb.vea.database.repositories.jdbc.mapper;

import cz.vsb.vea.database.entities.InsuranceContract;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InsuranceContractMapper implements RowMapper<InsuranceContract> {

    @Override
    public InsuranceContract mapRow(ResultSet rs, int rowNum) throws SQLException {
        InsuranceContract insuranceContract = new InsuranceContract();
        insuranceContract.setId(rs.getLong("id"));
        insuranceContract.setStartTime(rs.getDate("start_time"));
        insuranceContract.setEndTime(rs.getDate("end_time"));
        insuranceContract.setPricePerYear(rs.getFloat("price_per_year"));
        return insuranceContract;
    }
}
