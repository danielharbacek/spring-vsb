package cz.vsb.vea.database.repositories.jdbc;

import cz.vsb.vea.database.entities.InsuranceCompany;
import cz.vsb.vea.database.repositories.IInsuranceCompanyRepository;
import cz.vsb.vea.database.repositories.jdbc.mapper.InsuranceCompanyMapper;
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
public class InsuranceCompanyRepository implements IInsuranceCompanyRepository {

    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert jdbcInsert;

    @PostConstruct
    public void init(){
        jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("insurance_company")
                .usingGeneratedKeyColumns("id")
                .usingColumns("name");

        try{
            Connection connection = dataSource.getConnection();
            Statement stmtDrop = connection.createStatement();
            String sqlDrop = "drop table if exists insurance_company cascade";
            stmtDrop.execute(sqlDrop);
            Statement stmt = connection.createStatement();
            String sql = "create table insurance_company (id integer not null auto_increment, name varchar(255), primary key (id));";
            stmt.execute(sql);
        } catch (SQLException exception){
            exception.printStackTrace();
        }
    }

    @Override
    public List<InsuranceCompany> getAll() {
        return jdbcTemplate.query("select * from insurance_company", new InsuranceCompanyMapper());
    }

    @Override
    public InsuranceCompany find(long id) {
        return jdbcTemplate.queryForObject("select * from insurance_company where id = ?", new InsuranceCompanyMapper(), id);
    }

    @Override
    public void update(long id, InsuranceCompany entity) {
        jdbcTemplate.update("update insurance_company set name = ? where id = ?",
                entity.getName(), entity.getId());
    }

    @Override
    public void insert(InsuranceCompany entity) {
        BeanPropertySqlParameterSource bean = new BeanPropertySqlParameterSource(entity);
        jdbcInsert.execute(bean);
    }

    @Override
    public boolean delete(long id) {
        return jdbcTemplate.update("delete from insurance_company WHERE id = ?", id) > 0;
    }

    @Override
    public boolean exists(long id) {
        return !jdbcTemplate.query("select * from insurance_company where id = ?", new InsuranceCompanyMapper(), id).isEmpty();
    }
}
