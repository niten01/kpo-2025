package hse.bank.commands;

import hse.bank.domains.AnalyticsResult;
import hse.bank.facades.AnalyticsFacade;
import hse.bank.interfaces.Command;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@RequiredArgsConstructor
public class AnalyticsCommand implements Command<AnalyticsResult> {
    private final AnalyticsFacade analyticsFacade;
    private final Date startDate;
    private final Date endDate;

    @Override
    public AnalyticsResult execute() {
        var dif = analyticsFacade.calculateBalanceDifference(startDate, endDate);
        var grouped = analyticsFacade.groupOperations();
        return new AnalyticsResult(dif, grouped);
    }
}
