package ru.job4j.advancedcalculator;

import ru.job4j.calculator.CalcMenu;

public class AdvancedCalcMenu extends CalcMenu {
    AdvancedCalculation advancedCalculation = new AdvancedCalculation();

    public AdvancedCalcMenu() {
        initMenu();
    }

    private void initMenu() {
       super.addMenuItem("^", "Exponentiation",
               (a, b) -> {
                   advancedCalculation.exp(a, b);
                   return advancedCalculation.getResult();
       });
        super.addMenuItem("r", "Root of the power",
                (a, b) -> {
                    advancedCalculation.root(a, b);
                    return advancedCalculation.getResult();
        });
    }

}
