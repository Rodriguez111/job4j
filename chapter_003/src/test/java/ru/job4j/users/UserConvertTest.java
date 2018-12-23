package ru.job4j.users;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
public class UserConvertTest {

    @Test
    public void hashMapWith3Users() {
        User user1 = new User(1, "Имя1", "Город1");
        User user2 = new User(2, "Имя2", "Город2");
        User user3 = new User(3, "Имя3", "Город3");
        List<User> listOfUsers = new ArrayList<>();
        listOfUsers.add(user1);
        listOfUsers.add(user2);
        listOfUsers.add(user3);
       UserConvert userConvert = new UserConvert();
       Map<Integer, User> actual =  userConvert.process(listOfUsers);
        HashMap<Integer, User> expected =  new HashMap<>();
        expected.put(1, user1);
        expected.put(2, user2);
        expected.put(3, user3);
        assertThat(actual, is(expected));
    }

}
