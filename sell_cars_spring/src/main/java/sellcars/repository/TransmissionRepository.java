package sellcars.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sellcars.models.Transmission;
import sellcars.models.User;

@Repository
public interface TransmissionRepository extends CrudRepository<Transmission, Integer> {

    Transmission findByTransmissionType(String transmissionType);

}
