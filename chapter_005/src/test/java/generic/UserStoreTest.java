package generic;

import generic.exceptions.ElementNotFoundException;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        User actual = userStore.findById("One");
        User expected = user1;

        assertThat(actual, is(expected));
    }

    @Test
    public void shouldReturnTwoWhenReplaceOneWithTwo() {
        User user1 = new User("One");
        User user2 = new User("Two");
        userStore.add(user1);
        userStore.replace("One", user2);
        User actual = userStore.findById("Two");
        User expected = user2;

        assertThat(actual, is(expected));
    }

    @Test
    public void shouldThrowElementNotFoundExceptionWhenElementIsNotFound() {
        User user1 = new User("One");
        User user2 = new User("Two");
        userStore.add(user1);
        userStore.add(user2);
        userStore.delete("One");

        Throwable exception = assertThrows(ElementNotFoundException.class, () -> {
            userStore.findById("One");
        });
        assertEquals("Element not found", exception.getMessage());


    }


    @Test
    public void shouldReturnTwoWhenFind1() {
        User user1 = new User("One");
        User user2 = new User("Two");
        User user3 = new User("Three");

        userStore.add(user1);
        userStore.add(user2);
        userStore.add(user3);

        User actual = userStore.findById("Two");
        User expected = user2;

        assertThat(actual, is(expected));
    }
}