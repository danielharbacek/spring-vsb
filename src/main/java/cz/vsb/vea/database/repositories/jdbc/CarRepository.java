package cz.vsb.vea.database.repositories.jdbc;

import cz.vsb.vea.database.entities.Car;
import cz.vsb.vea.database.entities.ConventionalCar;
import cz.vsb.vea.database.entities.ElectricCar;
import cz.vsb.vea.database.repositories.ICarRepository;
import cz.vsb.vea.database.repositories.jdbc.mapper.ConventionalCarMapper;
import cz.vsb.vea.database.repositories.jdbc.mapper.ElectricCarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//@Repository
public class CarRepository implements ICarRepository {

    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert conventionalCarInsert;
    private SimpleJdbcInsert electricCarInsert;

    @PostConstruct
    public void init(){
        jdbcTemplate = new JdbcTemplate(dataSource);
        conventionalCarInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("conventional_car")
                .usingGeneratedKeyColumns("id")
                .usingColumns("name", "seats", "engine_power", "emissions");
        electricCarInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("electric_car")
                .usingGeneratedKeyColumns("id")
                .usingColumns("name", "seats", "engine_power", "battery_capacity");

        try{
            Connection connection = dataSource.getConnection();
            Statement stmtDrop = connection.createStatement();
            String sqlDrop = "drop table if exists conventional_car cascade";
            stmtDrop.execute(sqlDrop);
            stmtDrop = connection.createStatement();
            sqlDrop = "drop table if exists electric_car cascade";
            stmtDrop.execute(sqlDrop);
            Statement stmt = connection.createStatement();
            String sql = "create table conventional_car (id integer not null auto_increment, name varchar(255), seats int, engine_power float, emissions float, primary key (id));";
            Statement statement = connection.createStatement();
            String query = "create table electric_car (id integer not null auto_increment, name varchar(255), seats int, engine_power float, battery_capacity float, primary key (id));";
            stmt.execute(sql);
            statement.execute(query);
        } catch (SQLException exception){
            exception.printStackTrace();
        }
    }

    @Override
    public List<Car> getAll() {
        List<Car> cars = new ArrayList<>();
        List<ElectricCar> electricCars = getElectricCars();
        List<ConventionalCar> conventionalCars = getConventionalCars();
        cars.addAll(electricCars);
        cars.addAll(conventionalCars);
        return cars;
    }

    @Override
    public Car find(long id) {
        try{
            Car car = jdbcTemplate.queryForObject("select * from conventional_car where id = ?", new ConventionalCarMapper(), id);
            if(car != null){
                return car;
            }
        } catch (EmptyResultDataAccessException e){}

        try{
            Car car = jdbcTemplate.queryForObject("select * from electric_car where id = ?", new ElectricCarMapper(), id);
            if(car != null){
                return car;
            }
        } catch (EmptyResultDataAccessException e){}

        return null;
    }

    @Override
    public void update(long id, Car entity) {
        if(entity instanceof ElectricCar){
            jdbcTemplate.update("update electric_car set name = ?, seats = ?, engine_power = ?, battery_capacity = ? where id = ?",
                    entity.getName(), entity.getSeats(), entity.getEnginePower(), ((ElectricCar) entity).getBatteryCapacity(), entity.getId());
        } else {
            jdbcTemplate.update("update conventional_car set name = ?, seats = ?, engine_power = ?, emissions = ? where id = ?",
                    entity.getName(), entity.getSeats(), entity.getEnginePower(), ((ConventionalCar) entity).getEmissions(), entity.getId());
        }
    }

    @Override
    public void insert(Car entity) {
        BeanPropertySqlParameterSource bean = new BeanPropertySqlParameterSource(entity);
        if(entity instanceof ElectricCar){
            electricCarInsert.execute(bean);
        } else {
            conventionalCarInsert.execute(bean);
        }
    }

    @Override
    public boolean delete(long id) {
        int count = jdbcTemplate.update("delete from electric_car WHERE id = ?", id);
        if(count == 0){
            count = jdbcTemplate.update("delete from conventional_car WHERE id = ?", id);
            return count > 0;
        }
        return true;
    }

    @Override
    public boolean exists(long id) {
        List<ConventionalCar> cars = jdbcTemplate.query("select * from conventional_car where id = ?", new ConventionalCarMapper(), id);
        if(cars.isEmpty()){
            List<ElectricCar> electricCars = jdbcTemplate.query("select * from electric_car where id = ?", new ElectricCarMapper(), id);
            return !electricCars.isEmpty();
        } else {
            return true;
        }
    }

    @Override
    public List<ConventionalCar> getConventionalCars() {
        return jdbcTemplate.query("select * from conventional_car", new ConventionalCarMapper());
    }

    @Override
    public List<ElectricCar> getElectricCars() {
        return jdbcTemplate.query("select * from electric_car", new ElectricCarMapper());
    }
}
