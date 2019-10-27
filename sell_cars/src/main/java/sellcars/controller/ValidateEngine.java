package sellcars.controller;

import org.json.JSONObject;
import sellcars.models.Engine;
import sellcars.persistent.GetModel;
import sellcars.persistent.ModelGetter;

import java.util.Comparator;
import java.util.List;

public class ValidateEngine implements ModelValidator {
    private final static ModelValidator INSTANCE = new ValidateEngine();
    private final static ModelGetter<Engine> MODEL_GETTER = new GetModel<>();

    private ValidateEngine() {
    }

    public static ModelValidator getINSTANCE() {
        return INSTANCE;
    }

    @Override
    public JSONObject getModels() {
        List<Engine> list = MODEL_GETTER.getAll("Engine");
        list.sort(new Comparator<Engine>() {
            @Override
            public int compare(Engine o1, Engine o2) {
                return o1.getEngineType().compareTo(o2.getEngineType());
            }
        });
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("listOfEngines", list);
        return jsonObject;
    }

}
