package crazyfrogtwo;
import org.junit.Test;
import ru.job4j.crazyfrogtwo.Cell;
import ru.job4j.crazyfrogtwo.Logic;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class LogicTest {
    @Test
    public void whenStartIs18AndFinishIs43Then8Steps() {
        Logic logic = new Logic();
        List<Integer> barriers = new ArrayList<>();
        barriers.add(116);
        barriers.add(145);
        Cell[] actual =  logic.findWay(18, 43, barriers);
        Cell[] expected = {logic.getField().get(18), logic.getField().get(26),
                logic.getField().get(45), logic.getField().get(75), logic.getField().get(105),
                logic.getField().get(135), logic.getField().get(5), logic.getField().get(13),
                logic.getField().get(43)};
       assertThat(actual, is(expected));
    }

}
