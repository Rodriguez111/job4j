package sellcars.persistent;


import sellcars.models.User;

public interface UserStorage {

    String add(User user);

    void update(User user);

    void delete(User user);

    User findUserByLogin(String login);

    User findById(int id);



}
