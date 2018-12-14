package coffeemachine;
import org.junit.Test;
import ru.job4j.coffeemachine.CoffeeMachine;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
public class CoffeeMachineTest {

    @Test
    public void when50And35change15() {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        int[] actual = coffeeMachine.changes(50, 35);
        int[] expected = {10, 5};
        assertThat(actual, is(expected));
    }

    @Test
    public void when100And37change63() {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        int[] actual = coffeeMachine.changes(100, 37);
        int[] expected = {10, 10, 10, 10, 10, 10, 2, 1};
        assertThat(actual, is(expected));
    }

    @Test
    public void when25And25change0() {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        int[] actual = coffeeMachine.changes(25, 25);
        int[] expected = {};
        assertThat(actual, is(expected));
    }





}
