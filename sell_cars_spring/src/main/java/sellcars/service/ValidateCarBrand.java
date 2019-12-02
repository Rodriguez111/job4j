package sellcars.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import sellcars.models.CarBrand;
import sellcars.persistent.GetModel;
import sellcars.persistent.ModelGetter;

import java.util.Comparator;
import java.util.List;
@Component
public class ValidateCarBrand implements ModelValidator {

    private final static ModelGetter<CarBrand> MODEL_GETTER = new GetModel<>();

    private ValidateCarBrand() {
    }


    @Override
    public String getModels() {
        List<CarBrand> list = MODEL_GETTER.getAll("CarBrand");
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

}
