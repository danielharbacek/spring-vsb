package cz.vsb.vea.services;

import cz.vsb.vea.database.dto.CarDto;
import cz.vsb.vea.database.dto.InsuranceCompanyDto;
import cz.vsb.vea.database.dto.InsuranceContractDto;
import cz.vsb.vea.database.entities.*;
import cz.vsb.vea.database.repositories.IInsuranceContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InsuranceContractService {

    @Autowired
    private IInsuranceContractRepository insuranceContractRepository;

    @Autowired
    private CarService carService;

    @Autowired
    private InsuranceCompanyService insuranceCompanyService;

    public List<InsuranceContract> getAll(){
        List<InsuranceContract> insuranceContracts = insuranceContractRepository.getAll();
        for(InsuranceContract insuranceContract : insuranceContracts){
            if(insuranceContract.getCar() == null){
                Car car = carService.find(insuranceContract.getId());
                insuranceContract.setCar(car);
            }
            if(insuranceContract.getInsuranceCompany() == null){
                InsuranceCompany insuranceCompany = insuranceCompanyService.find(insuranceContract.getId());
                insuranceContract.setInsuranceCompany(insuranceCompany);
            }
        }
        return insuranceContracts;
    }

    public InsuranceContract find(long id){
        return insuranceContractRepository.find(id);
    }

    public void update(long id, InsuranceContract insuranceContract){
        insuranceContractRepository.update(id, insuranceContract);
    }

    public void insert(InsuranceContract insuranceContract){
        insuranceContractRepository.insert(insuranceContract);
    }

    public InsuranceContract insertWithRelationship(InsuranceContractDto insuranceContractDto){
        InsuranceContract insuranceContract = new InsuranceContract();
        insuranceContract.setStartTime(insuranceContractDto.getStartTime());
        insuranceContract.setEndTime(insuranceContractDto.getEndTime());
        insuranceContract.setPricePerYear(insuranceContractDto.getPricePerYear());
        insuranceContract.setCar(createRelatedCar(insuranceContractDto.getCar()));
        insuranceContract.setInsuranceCompany(createRelatedInsuranceCompany(insuranceContractDto.getInsuranceCompany()));

        insert(insuranceContract);
        return insuranceContract;
    }

    private InsuranceCompany createRelatedInsuranceCompany(InsuranceCompanyDto insuranceCompanyDto){
        if(insuranceCompanyDto == null){
            return null;
        }

        InsuranceCompany insuranceCompany = null;
        if(insuranceCompanyDto.getId() != null){
            insuranceCompany = insuranceCompanyService.find(insuranceCompanyDto.getId());
        }
        if(insuranceCompany == null){
            insuranceCompany = new InsuranceCompany(insuranceCompanyDto);
            insuranceCompanyService.insert(insuranceCompany);
        }

        return insuranceCompany;
    }

    private Car createRelatedCar(CarDto carDto){
        if(carDto == null){
            return null;
        }

        if(carDto.getEmissions() != null){
            ConventionalCar conventionalCar = null;
            if(carDto.getId() != null){
                conventionalCar = (ConventionalCar) carService.find(carDto.getId());
            }
            if(conventionalCar == null){
                conventionalCar = new ConventionalCar(carDto);
                carService.insert(conventionalCar);
            }
            return conventionalCar;
        } else if(carDto.getBatteryCapacity() != null){
            ElectricCar electricCar = null;
            if(carDto.getId() != null){
                electricCar = (ElectricCar) carService.find(carDto.getId());
            }
            if(electricCar == null) {
                electricCar = new ElectricCar(carDto);
                carService.insert(electricCar);
            }
            return electricCar;
        }

        return null;
    }

    public boolean delete(long id){
        return insuranceContractRepository.delete(id);
    }

    public boolean areAllPropertiesSet(InsuranceContract insuranceContract){
        return insuranceContract.getStartTime() != null && insuranceContract.getEndTime() != null &&
                insuranceContract.getPricePerYear() != null;
    }

    public boolean areAllPropertiesSet(InsuranceContractDto insuranceContractDto){
        return insuranceContractDto.getStartTime() != null && insuranceContractDto.getEndTime() != null &&
                insuranceContractDto.getPricePerYear() != null;
    }

    public boolean exists(long id){
        return insuranceContractRepository.exists(id);
    }
}
