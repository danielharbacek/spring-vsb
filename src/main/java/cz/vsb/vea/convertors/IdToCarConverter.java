package cz.vsb.vea.convertors;

import cz.vsb.vea.database.entities.Car;
import cz.vsb.vea.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IdToCarConverter implements Converter<String, Car> {

    @Autowired
    private CarService carService;


    @Override
    public Car convert(String source) {
        if(source == null || "".equals(source) || "null".equals(source)){
            return null;
        }

        return carService.find(Long.parseLong(source));
    }
}
