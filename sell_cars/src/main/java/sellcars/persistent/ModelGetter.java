package sellcars.persistent;


import org.json.JSONObject;
import sellcars.models.User;

import java.util.List;

public interface ModelGetter<T> {
     List<T> getAll(String entityName);

     T getByName(String entityName, String fieldName, String fieldValue);

}
