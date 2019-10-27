package sellcars.controller;

import org.json.JSONObject;
import sellcars.models.CarBrand;
import sellcars.persistent.GetModel;
import sellcars.persistent.ModelGetter;

import java.util.Comparator;
import java.util.List;

public class ValidateCarBrand implements ModelValidator {
    private final static ModelValidator INSTANCE = new ValidateCarBrand();
    private final static ModelGetter<CarBrand> MODEL_GETTER = new GetModel<>();

    private ValidateCarBrand() {
    }

    public static ModelValidator getINSTANCE() {
        return INSTANCE;
    }

    @Override
    public JSONObject getModels() {
        List<CarBrand> list = MODEL_GETTER.getAll("CarBrand");
        list.sort(new Comparator<CarBrand>() {
            @Override
            public int compare(CarBrand o1, CarBrand o2) {
                return o1.getCarBrand().compareTo(o2.getCarBrand());
            }
        });
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("listOfCarBrands", list);
        return jsonObject;
    }

}
