package sellcars.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sellcars.models.CarBrand;
import sellcars.models.Photo;

@Repository
public interface CarBrandRepository extends CrudRepository<CarBrand, Integer> {

    CarBrand findByCarBrand(String carBrand);

}
