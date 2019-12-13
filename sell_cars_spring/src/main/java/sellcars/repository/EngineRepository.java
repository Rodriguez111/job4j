package sellcars.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sellcars.models.Engine;
import sellcars.models.Transmission;

@Repository
public interface EngineRepository extends CrudRepository<Engine, Integer> {

    Engine findByEngineType(String engineType);

}
