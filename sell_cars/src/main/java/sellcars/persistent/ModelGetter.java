package sellcars.persistent;


import sellcars.models.User;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ModelGetter<T> {
     List<T> getAll(String entityName);

     T getByName(String entityName, String fieldName, String fieldValue);

//     List<T> getListByFilter(String entityName, Map<String, List<String>> parameters);

}
