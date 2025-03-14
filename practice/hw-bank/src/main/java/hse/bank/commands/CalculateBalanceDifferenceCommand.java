package hse.bank.commands;

import hse.bank.facades.AnalyticsFacade;
import hse.bank.interfaces.Command;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CalculateBalanceDifferenceCommand implements Command<Double> {
    private final AnalyticsFacade analyticsFacade;

    @Override
    public Double execute() {
        return analyticsFacade.calculateBalanceDifference();
    }
}
