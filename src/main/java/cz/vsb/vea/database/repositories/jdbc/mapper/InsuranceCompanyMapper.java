package cz.vsb.vea.database.repositories.jdbc.mapper;

import cz.vsb.vea.database.entities.InsuranceCompany;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InsuranceCompanyMapper implements RowMapper<InsuranceCompany> {

    @Override
    public InsuranceCompany mapRow(ResultSet rs, int rowNum) throws SQLException {
        InsuranceCompany insuranceCompany = new InsuranceCompany();
        insuranceCompany.setId(rs.getLong("id"));
        insuranceCompany.setName(rs.getString("name"));
        return insuranceCompany;
    }
}
