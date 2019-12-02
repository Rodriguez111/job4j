package sellcars.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import sellcars.models.CarBody;
import sellcars.persistent.GetModel;
import sellcars.persistent.ModelGetter;

import java.util.Comparator;
import java.util.List;
@Component
public class ValidateCarBody implements ModelValidator {

    private final static ModelGetter<CarBody> MODEL_GETTER = new GetModel<>();

    private ValidateCarBody() {
    }



    @Override
    public String getModels() {
        List<CarBody> list = MODEL_GETTER.getAll("CarBody");
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

}
