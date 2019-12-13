package sellcars.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import sellcars.models.Advert;

import java.util.List;


public interface AdvertRepository extends CrudRepository<Advert, Integer>, JpaSpecificationExecutor<Advert> {

}
