package sellcars.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import sellcars.models.Engine;
import sellcars.persistent.GetModel;
import sellcars.persistent.ModelGetter;

import java.util.Comparator;
import java.util.List;
@Component
public class ValidateEngine implements ModelValidator {

    private final static ModelGetter<Engine> MODEL_GETTER = new GetModel<>();

    private ValidateEngine() {
    }



    @Override
    public String getModels() {
        List<Engine> list = MODEL_GETTER.getAll("Engine");
        list.sort(new Comparator<Engine>() {
            @Override
            public int compare(Engine o1, Engine o2) {
                return o1.getEngineType().compareTo(o2.getEngineType());
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
