package ru.job4j.ticktacktoe.output;

public class ConsoleOutput implements Output {

    @Override
    public void printOutOfFieldError() {
        System.out.println("Sorry, out of the field size");
    }

    @Override
    public void printFieldEngagedError() {
        System.out.println("This field is engaged, You can't make a move here");
    }

    @Override
    public void printWhoMoves(char sign) {
        System.out.println("Now " + sign + " moves:");
    }

    @Override
    public void printField(char[][] field) {
        for (int y = 0; y < field[0].length; y++) {
            for (int x = 0; x < field.length; x++) {
                System.out.print(field[x][y]);
            }
            System.out.println();
        }
        System.out.println("----------");
    }
}
