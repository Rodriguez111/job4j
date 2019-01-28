package generic;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserStoreTest {

    UserStore userStore;

    @Before
    public void init() {
        userStore = new UserStore(3);
        userStore.add(new Role(""));
    }

    @Test
    public void add() {

        User user1 = new User("One");
        User user2 = new User("Two");
        User user3 = new User("Three");
        userStore.add(user1);
        userStore.add(user2);
        userStore.add(user3);

      // User actual = userStore.findById("One");


    }

    @Test
    public void replace() {
    }
}