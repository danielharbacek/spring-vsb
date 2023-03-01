package cz.vsb.vea.convertors;

import cz.vsb.vea.database.entities.InsuranceCompany;
import cz.vsb.vea.services.InsuranceCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IdToInsuranceCompanyConverter implements Converter<String, InsuranceCompany> {

    @Autowired
    private InsuranceCompanyService insuranceCompanyService;

    @Override
    public InsuranceCompany convert(String source) {
        if(source == null || "".equals(source) || "null".equals(source)){
            return null;
        }

        return insuranceCompanyService.find(Long.parseLong(source));
    }
}
