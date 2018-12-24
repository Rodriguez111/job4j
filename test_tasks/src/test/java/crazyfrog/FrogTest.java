package crazyfrog;
import org.junit.Test;
import ru.job4j.crazyfrog.Frog;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
public class FrogTest {
    @Test
    public void whenStartIs18AndFinishIs43Then8Steps() {
        Frog frog = new Frog();
        List<Integer> barriers = new ArrayList<>();
        barriers.add(116);
        barriers.add(145);
        int[] actual =  frog.findAnyWays(18, 43, barriers);
        int[] expected = {18, 48, 78, 108, 138, 157, 27, 35, 43};
       assertThat(actual, is(expected));
    }

}
