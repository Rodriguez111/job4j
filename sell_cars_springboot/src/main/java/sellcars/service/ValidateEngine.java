package sellcars.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sellcars.models.Engine;
import sellcars.repository.EngineRepository;

import java.util.Comparator;
import java.util.List;
@Service
public class ValidateEngine implements ModelValidator {

    private EngineRepository engineRepository;


    @Override
    public String getModels() {
        List<Engine> list = (List<Engine>) engineRepository.findAll();
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

    @Autowired
    public void setEngineRepository(EngineRepository engineRepository) {
        this.engineRepository = engineRepository;
    }
}
