package sellcars.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sellcars.models.CarBody;
import sellcars.models.CarBrand;

@Repository
public interface CarBodyRepository extends CrudRepository<CarBody, Integer> {

    CarBody findByBodyType(String bodyType);

}
