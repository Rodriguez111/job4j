package sellcars.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sellcars.models.Transmission;
import sellcars.repository.TransmissionRepository;

import java.util.Comparator;
import java.util.List;

@Service
public class ValidateTransmission implements ModelValidator {

    private TransmissionRepository transmissionRepository;


    @Override
    public String getModels() {
        List<Transmission> list = (List<Transmission>) transmissionRepository.findAll();
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

    @Autowired
    public void setTransmissionRepository(TransmissionRepository transmissionRepository) {
        this.transmissionRepository = transmissionRepository;
    }
}
