package sellcars.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import sellcars.models.Transmission;
import sellcars.persistent.GetModel;
import sellcars.persistent.ModelGetter;

import java.util.Comparator;
import java.util.List;

public class ValidateTransmission implements ModelValidator {
    private final static ModelValidator INSTANCE = new ValidateTransmission();
    private final static ModelGetter<Transmission> MODEL_GETTER = new GetModel<>();

    private ValidateTransmission() {
    }

    public static ModelValidator getINSTANCE() {
        return INSTANCE;
    }

    @Override
    public String getModels() {
        List<Transmission> list = MODEL_GETTER.getAll("Transmission");
        list.sort(new Comparator<Transmission>() {
            @Override
            public int compare(Transmission o1, Transmission o2) {
                return o1.getTransmissionType().compareTo(o2.getTransmissionType());
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
