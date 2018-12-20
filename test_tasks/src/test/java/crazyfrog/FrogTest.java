package crazyfrog;
import org.junit.Test;
import ru.job4j.coffeemachine.CoffeeMachine;
import ru.job4j.crazyfrog.Frog;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
public class FrogTest {
    @Test
    public void whenStartIs18AndFinishIs43Then8Steps() {
        Frog frog = new Frog();
        frog.findAnyWays();


    }


}
