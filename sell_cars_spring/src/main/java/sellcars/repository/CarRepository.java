package sellcars.repository;


import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sellcars.models.Car;
import sellcars.models.CarBody;

import java.util.List;

@Repository
public interface CarRepository extends CrudRepository<Car, Integer>, JpaSpecificationExecutor<Car> {

}
