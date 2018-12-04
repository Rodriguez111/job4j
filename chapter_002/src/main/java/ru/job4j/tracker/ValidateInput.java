package ru.job4j.tracker;

public class ValidateInput extends ConsoleInput {
    @Override
    public int ask(String question, int range) {
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value = super.ask(question, range);
                invalid = false;
            } catch (NumberFormatException e) {
                System.out.println("Entered value is not number");
            } catch (MenuOutException e) {
                System.out.println("You must select key from menu range");
            }
        } while (invalid);
        return value;
    }
}
