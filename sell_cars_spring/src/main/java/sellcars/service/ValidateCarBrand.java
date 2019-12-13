package sellcars.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sellcars.models.CarBrand;
import sellcars.repository.CarBrandRepository;

import java.util.Comparator;
import java.util.List;
@Service
public class ValidateCarBrand implements ModelValidator {

    private CarBrandRepository carBrandRepository;

    private ValidateCarBrand() {
    }


    @Override
    public String getModels() {
        List<CarBrand> list = (List<CarBrand>) carBrandRepository.findAll();
        list.sort(new Comparator<CarBrand>() {
            @Override
            public int compare(CarBrand o1, CarBrand o2) {
                return o1.getCarBrand().compareTo(o2.getCarBrand());
            }
        });
        String result = "";
        try {
            result = new ObjectMapper().writeValueAsString(list);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Autowired
    public void setCarBrandRepository(CarBrandRepository carBrandRepository) {
        this.carBrandRepository = carBrandRepository;
    }
}
