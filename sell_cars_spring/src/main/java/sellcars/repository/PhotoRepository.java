package sellcars.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sellcars.models.Engine;
import sellcars.models.Photo;

@Repository
public interface PhotoRepository extends CrudRepository<Photo, Integer> {

}
