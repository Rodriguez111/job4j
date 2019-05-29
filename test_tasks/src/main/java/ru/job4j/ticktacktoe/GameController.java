package ru.job4j.ticktacktoe;

import ru.job4j.ticktacktoe.gamefield.Field;
import ru.job4j.ticktacktoe.gamefield.GameField;
import ru.job4j.ticktacktoe.input.ConsoleInput;
import ru.job4j.ticktacktoe.input.Input;
import ru.job4j.ticktacktoe.logic.*;
import ru.job4j.ticktacktoe.output.ConsoleOutput;

public class GameController {


    public static void main(String[] args) {
        Input input = new ConsoleInput();
        Field field = new GameField(3, new ConsoleOutput());
        BasicLogic userComputer = new UserComputerBasicLogic(input, field, new SidesAndDiagonalsCheck(new SidesCheck(field.getField()), new DiagonalsCheck(field.getField())), false);
       //userComputer.playGame();
        BasicLogic userUser = new UserUserBasicLogic(userComputer);
        userUser.playGame();
    }


}
