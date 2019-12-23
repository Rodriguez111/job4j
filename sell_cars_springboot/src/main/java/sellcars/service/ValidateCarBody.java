package sellcars.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sellcars.models.CarBody;
import sellcars.repository.CarBodyRepository;

import java.util.Comparator;
import java.util.List;

@Service
public class ValidateCarBody implements ModelValidator {

    private CarBodyRepository carBodyRepository;



    @Override
    public String getModels() {
        List<CarBody> list = (List<CarBody>) carBodyRepository.findAll();
        list.sort(new Comparator<CarBody>() {
            @Override
            public int compare(CarBody o1, CarBody o2) {
                return o1.getBodyType().compareTo(o2.getBodyType());
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
    public void setCarBodyRepository(CarBodyRepository carBodyRepository) {
        this.carBodyRepository = carBodyRepository;
    }
}
