package generic;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class UserStoreTest {

    UserStore userStore;

    @Before
    public void init() {
        userStore = new UserStore(3);

    }

    @Test
    public void shouldReturnOneWhenAddOne() {
        User user1 = new User("One");
        userStore.add(user1);
        User actual = userStore.findById("0");
        User expected = user1;

        assertThat(actual, is(expected));
    }


    @Test
    public void shouldReturnTwoWhenReplaceOneWithTwo() {
        User user1 = new User("One");
        User user2 = new User("Two");
        userStore.add(user1);
        userStore.replace("0", user2);
        User actual = userStore.findById("0");
        User expected = user2;

        assertThat(actual, is(expected));
    }

    @Test
    public void shouldReturnTwoWhenFindElementWithId0() {
        User user1 = new User("One");
        User user2 = new User("Two");
        userStore.add(user1);
        userStore.add(user2);
        userStore.delete("0");
        User actual = userStore.findById("0");
        User expected = user2;

        assertThat(actual, is(expected));
    }


    @Test
    public void shouldReturnTwoWhenFind1() {
        User user1 = new User("One");
        User user2 = new User("Two");
        User user3 = new User("Three");

        userStore.add(user1);
        userStore.add(user2);
        userStore.add(user3);

        User actual = userStore.findById("1");
        User expected = user2;

        assertThat(actual, is(expected));
    }
}