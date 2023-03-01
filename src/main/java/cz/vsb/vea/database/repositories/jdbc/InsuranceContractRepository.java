package cz.vsb.vea.database.repositories.jdbc;

import cz.vsb.vea.database.entities.InsuranceContract;
import cz.vsb.vea.database.repositories.IInsuranceContractRepository;
import cz.vsb.vea.database.repositories.jdbc.mapper.InsuranceContractMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

//@Repository
public class InsuranceContractRepository implements IInsuranceContractRepository {

    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert jdbcInsert;

    @PostConstruct
    public void init(){
        jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("insurance_contract")
                .usingGeneratedKeyColumns("id")
                .usingColumns("car_id", "insurance_company_id", "start_time", "end_time", "price_per_year");

        try{
            Connection connection = dataSource.getConnection();
            Statement stmtDrop = connection.createStatement();
            String sqlDrop = "drop table if exists insurance_contract cascade";
            stmtDrop.execute(sqlDrop);
            Statement stmt = connection.createStatement();
            String sql = "create table insurance_contract (id integer not null auto_increment, car_id int, insurance_company_id int, start_time date, end_time date, price_per_year float, primary key (id));";
            stmt.execute(sql);
        } catch (SQLException exception){
            exception.printStackTrace();
        }
    }

    @Override
    public List<InsuranceContract> getAll() {
        return jdbcTemplate.query("select * from insurance_contract", new InsuranceContractMapper());
    }

    @Override
    public InsuranceContract find(long id) {
        return jdbcTemplate.queryForObject("select * from insurance_contract where id = ?", new InsuranceContractMapper(), id);
    }

    @Override
    public void update(long id, InsuranceContract entity) {
        jdbcTemplate.update("update insurance_contract set car_id = ?, insurance_company_id = ?, start_time = ?, end_time = ?, price_per_year = ? where id = ?",
                entity.getCar() == null ? null : entity.getCar().getId(),
                entity.getInsuranceCompany() == null ? null : entity.getInsuranceCompany().getId(),
                entity.getStartTime(), entity.getCar(), entity.getPricePerYear(), entity.getId());
    }

    @Override
    public void insert(InsuranceContract entity) {
        BeanPropertySqlParameterSource bean = new BeanPropertySqlParameterSource(entity);
        jdbcInsert.execute(bean);
    }

    @Override
    public boolean delete(long id) {
        return jdbcTemplate.update("delete from insurance_contract WHERE id = ?", id) > 0;
    }

    @Override
    public boolean exists(long id) {
        return !jdbcTemplate.query("select * from insurance_contract where id = ?", new InsuranceContractMapper(), id).isEmpty();
    }
}
