package generic;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class RoleStoreTest {

    RoleStore roleStore;

    @Before
    public void init() {
        roleStore = new RoleStore(3);

    }

    @Test
    public void shouldReturnOneWhenAddOne() {
        Role role1 = new Role("One");
        roleStore.add(role1);
        Role actual = roleStore.findById("0");
        Role expected = role1;

        assertThat(actual, is(expected));
    }


    @Test
    public void shouldReturnTwoWhenReplaceOneWithTwo() {
        Role role1 = new Role("One");
        Role role2 = new Role("Two");
        roleStore.add(role1);
        roleStore.replace("0", role2);
        Role actual = roleStore.findById("0");
        Role expected = role2;

        assertThat(actual, is(expected));
    }

    @Test
    public void shouldReturnTwoWhenFindElementWithId0() {
        Role role1 = new Role("One");
        Role role2 = new Role("Two");
        roleStore.add(role1);
        roleStore.add(role2);
        roleStore.delete("0");
        Role actual = roleStore.findById("0");
        Role expected = role2;

        assertThat(actual, is(expected));
    }


    @Test
    public void shouldReturnTwoWhenFind1() {
        Role role1 = new Role("One");
        Role role2 = new Role("Two");
        Role role3 = new Role("Three");

        roleStore.add(role1);
        roleStore.add(role2);
        roleStore.add(role3);

        Role actual = roleStore.findById("1");
        Role expected = role2;

        assertThat(actual, is(expected));
    }

}