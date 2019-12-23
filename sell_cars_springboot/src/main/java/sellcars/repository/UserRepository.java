package sellcars.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sellcars.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    User findByLogin(String login);

}
