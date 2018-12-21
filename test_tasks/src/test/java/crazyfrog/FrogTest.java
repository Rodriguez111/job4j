package crazyfrog;
import org.junit.Test;
import ru.job4j.crazyfrog.Frog;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
public class FrogTest {
    @Test
    public void whenStartIs18AndFinishIs43Then8Steps() {
        Frog frog = new Frog(18, 116, 145, 43);
        frog.getField()[18] = -2; //start position
        frog.getField()[43] = -3; //end position
        frog.getField()[116] = -1; //barrier
        frog.getField()[145] = -1; //barrier
        int[] actual =  frog.findAnyWays();
        int[] expected = {18, 48, 78, 108, 138, 157, 27, 35, 43};
       assertThat(actual, is(expected));
    }


}
